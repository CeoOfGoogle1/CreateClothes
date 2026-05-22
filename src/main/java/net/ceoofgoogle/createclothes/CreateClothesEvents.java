package net.ceoofgoogle.createclothes;

import net.ceoofgoogle.createclothes.init.CreateClothesModDataComponents;
import net.ceoofgoogle.createclothes.item.ParachuteItem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

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
}
