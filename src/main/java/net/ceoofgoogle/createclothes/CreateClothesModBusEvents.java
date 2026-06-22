package net.ceoofgoogle.createclothes;

import net.ceoofgoogle.createclothes.init.CreateClothesModAttributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber(modid = CreateClothes.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CreateClothesModBusEvents {
    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        for (var entityType : event.getTypes()) {
            event.add(entityType, CreateClothesModAttributes.DODGE_CHANCE, 1.0);
        }
    }
}
