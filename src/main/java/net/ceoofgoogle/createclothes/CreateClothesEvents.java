package net.ceoofgoogle.createclothes;

import net.ceoofgoogle.createclothes.init.CreateClothesModAttributes;
import net.ceoofgoogle.createclothes.init.CreateClothesModDataComponents;
import net.ceoofgoogle.createclothes.init.CreateClothesModEnchantments;
import net.ceoofgoogle.createclothes.init.CreateClothesModSounds;
import net.ceoofgoogle.createclothes.init.CreateClothesModTags;
import net.ceoofgoogle.createclothes.item.ParachuteItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = CreateClothes.MOD_ID)
public class CreateClothesEvents {
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ItemEntity itemEntity) {
            ItemStack stack = itemEntity.getItem();
            if (stack.getItem() instanceof ParachuteItem) {
                if (stack.getOrDefault(CreateClothesModDataComponents.IS_USED.get(), false)) {
                    event.setCanceled(true);
                } else if (stack.getOrDefault(CreateClothesModDataComponents.IS_OPEN.get(), false)) {
                    stack.set(CreateClothesModDataComponents.IS_OPEN.get(), false);
                }
            }
        }
    }

    private static int soundIndex;

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity living = event.getEntity();
        int armorPieces = 0;
        int enchantLevels = 0;
        var enchantmentRegistry = living.level().registryAccess().lookup(Registries.ENCHANTMENT).orElse(null);
        if (enchantmentRegistry == null) return;
        var dodgeHolder = enchantmentRegistry.getOrThrow(CreateClothesModEnchantments.DODGE);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack stack = living.getItemBySlot(slot);
                if (stack.is(CreateClothesModTags.CAMO_SETS)) {
                    armorPieces++;
                    enchantLevels += stack.getEnchantments().getLevel(dodgeHolder);
                }
            }
        }
        if (armorPieces <= 0) return;

        double dodgeChance = armorPieces * 0.05 + enchantLevels * 0.01;
        if (living.getRandom().nextDouble() < dodgeChance) {
            event.setCanceled(true);
            living.level().playSound(null, living.getX(), living.getY(), living.getZ(),
                    CreateClothesModSounds.UI_HITS[soundIndex], living.getSoundSource(), 1.0f, 1.0f);
            soundIndex = (soundIndex + 1) % 11;
        }
    }

    @SubscribeEvent
    public static void onItemAttributeModifier(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(CreateClothesModTags.CAMO_SETS) && stack.getItem() instanceof ArmorItem armorItem) {
            var slot = armorItem.getType().getSlot();
            var group = EquipmentSlotGroup.bySlot(slot);
            double bonus = 0.05;
            for (var entry : stack.getEnchantments().entrySet()) {
                if (entry.getKey().is(CreateClothesModEnchantments.DODGE)) {
                    bonus += entry.getIntValue() * 0.01;
                }
            }
            event.addModifier(
                    CreateClothesModAttributes.DODGE_CHANCE,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath("createclothes", "dodge_chance_" + slot.getName()),
                            bonus,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    ),
                    group
            );
        }
    }
}
