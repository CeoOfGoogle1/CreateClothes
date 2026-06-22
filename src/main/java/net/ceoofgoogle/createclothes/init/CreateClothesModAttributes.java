package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreateClothesModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, CreateClothes.MOD_ID);

    public static final Holder<Attribute> DODGE_CHANCE = ATTRIBUTES.register("dodge_chance",
            () -> new RangedAttribute("attribute.createclothes.dodge_chance", 0.0, 0.0, 2.0).setSyncable(true));
}
