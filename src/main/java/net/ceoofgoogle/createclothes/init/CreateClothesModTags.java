package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CreateClothesModTags {
    public static final TagKey<Item> CAMO_ARMOR = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(CreateClothes.MOD_ID, "camo_armor"));
    public static final TagKey<Item> CAMO_SETS = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(CreateClothes.MOD_ID, "camo_sets"));
}
