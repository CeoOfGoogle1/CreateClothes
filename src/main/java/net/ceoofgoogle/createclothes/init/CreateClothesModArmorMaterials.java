package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class CreateClothesModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> REGISTRY = DeferredRegister.create(Registries.ARMOR_MATERIAL, CreateClothes.MOD_ID);

    public static final Holder<ArmorMaterial> CROWN = register("crown", 80, 3, 6, 7, 3, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> OFFICER_TUNIC = register("officer_tunic", 80, 2, 3, 4, 2, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> OFFICER_CAP = register("officer_cap", 80, 2, 3, 4, 2, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> BUCKET_HAT = register("bucket_hat", 80, 3, 6, 7, 3, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> CAP = register("cap", 80, 3, 6, 7, 3, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> TRICORN = register("tricorn", 80, 3, 6, 7, 3, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> REDCOAT = register("redcoat", 80, 3, 6, 7, 3, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> BLACK_BOOTS = register("black_boots", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> GRAY_BOOTS = register("gray_boots", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> BROWN_BOOTS = register("brown_boots", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> GREEN_BOOTS = register("green_boots", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);

    public static final Holder<ArmorMaterial> PLAINS = register("plains", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> FOREST = register("forest", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> TAIGA = register("taiga", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> SAVANNA = register("savanna", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> DESERT = register("desert", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> SNOW = register("snow", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> WATER = register("water", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> MESA = register("mesa", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> DIRT = register("dirt", 80, 3, 6, 7, 8, 9, 0.0F, 0.0F, () -> Ingredient.EMPTY);

    private static Holder<ArmorMaterial> register(String name, int durabilityMultiplier, int helmet, int chestplate, int leggings, int boots, int enchantmentValue, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        EnumMap<ArmorItem.Type, Integer> defense = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, boots);
            map.put(ArmorItem.Type.LEGGINGS, leggings);
            map.put(ArmorItem.Type.CHESTPLATE, chestplate);
            map.put(ArmorItem.Type.HELMET, helmet);
        });

        // In 1.21.1, we define a single layer as a base. 
        // Actual texture paths are handled by BaseCreateClothesArmorItem extensions to support legacy paths.
        List<ArmorMaterial.Layer> layers = List.of(
            new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(CreateClothes.MOD_ID, name))
        );


        return REGISTRY.register(name, () -> new ArmorMaterial(defense, enchantmentValue, SoundEvents.ARMOR_EQUIP_LEATHER, repairIngredient, layers, toughness, knockbackResistance));
    }
}
