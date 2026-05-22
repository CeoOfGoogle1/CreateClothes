package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.ceoofgoogle.createclothes.entity.ParachuteEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CreateClothesModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, CreateClothes.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ParachuteEntity>> PARACHUTE = REGISTRY.register("parachute",
        () -> EntityType.Builder.<ParachuteEntity>of(ParachuteEntity::new, MobCategory.MISC)
            .sized(1.0f, 1.0f)
            .clientTrackingRange(64)
            .updateInterval(1)
            .build("parachute"));
}
