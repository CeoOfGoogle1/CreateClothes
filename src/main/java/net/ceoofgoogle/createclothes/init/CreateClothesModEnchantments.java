package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class CreateClothesModEnchantments {
    public static final ResourceKey<Enchantment> DODGE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(CreateClothes.MOD_ID, "dodge"));
}
