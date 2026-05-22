package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.entity.ParachuteEntity;
import net.ceoofgoogle.createclothes.init.CreateClothesModDataComponents;
import net.ceoofgoogle.createclothes.init.CreateClothesModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ParachuteItem extends Item {
    public ParachuteItem() {
        super(new Item.Properties().stacksTo(1)
            .component(CreateClothesModDataComponents.IS_OPEN.get(), false)
            .component(CreateClothesModDataComponents.IS_USED.get(), false));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getOrDefault(CreateClothesModDataComponents.IS_USED.get(), false)) {
            return InteractionResultHolder.fail(stack);
        }
        if (hasAnyOpenParachute(player) || hasActiveParachuteEntity(level, player)) {
            return InteractionResultHolder.fail(stack);
        }
        if (!stack.getOrDefault(CreateClothesModDataComponents.IS_OPEN.get(), false)) {
            stack.set(CreateClothesModDataComponents.IS_OPEN.get(), true);
            stack.set(CreateClothesModDataComponents.IS_USED.get(), true);
            if (!level.isClientSide) {
                ParachuteEntity parachute = new ParachuteEntity(CreateClothesModEntities.PARACHUTE.get(), level);
                parachute.setAttached(player);
                parachute.setPos(player.getX(), player.getY(), player.getZ());
                level.addFreshEntity(parachute);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_ELYTRA, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    private boolean hasAnyOpenParachute(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.getItem() == this && stack.getOrDefault(CreateClothesModDataComponents.IS_OPEN.get(), false)) {
                return true;
            }
        }
        for (ItemStack stack : player.getInventory().offhand) {
            if (!stack.isEmpty() && stack.getItem() == this && stack.getOrDefault(CreateClothesModDataComponents.IS_OPEN.get(), false)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasActiveParachuteEntity(Level level, Player player) {
        List<ParachuteEntity> list = level.getEntitiesOfClass(
            ParachuteEntity.class,
            player.getBoundingBox().inflate(32.0),
            entity -> entity.getAttached() == player
        );
        return !list.isEmpty();
    }
}
