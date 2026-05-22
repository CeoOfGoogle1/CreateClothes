package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.ceoofgoogle.createclothes.model.ModelHelmet;
import net.ceoofgoogle.createclothes.model.ModelVest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public abstract class ForestCamoItem extends BaseCreateClothesArmorItem {
    public ForestCamoItem(ArmorItem.Type type, Properties properties) {
        super(CreateClothesModArmorMaterials.FOREST, type, properties);
    }

    public static class Boots extends ForestCamoItem {
        public Boots() {
            super(Type.BOOTS, new Properties().durability(Type.BOOTS.getDurability(80)));
        }
    }

    public static class Leggings extends ForestCamoItem {
        public Leggings() {
            super(Type.LEGGINGS, new Properties().durability(Type.LEGGINGS.getDurability(80)));
        }
    }

    public static class Chestplate extends ForestCamoItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Properties().durability(Type.CHESTPLATE.getDurability(80)));
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            initializeClientWithModel(consumer, living -> new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new ModelVest(Minecraft.getInstance().getEntityModels().bakeLayer(ModelVest.LAYER_LOCATION))).body, "left_arm", (new ModelVest(Minecraft.getInstance().getEntityModels().bakeLayer(ModelVest.LAYER_LOCATION))).left_arm, "right_arm", (new ModelVest(Minecraft.getInstance().getEntityModels().bakeLayer(ModelVest.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
        }
    }

    public static class Helmet extends ForestCamoItem {
        public Helmet() {
            super(Type.HELMET, new Properties().durability(Type.HELMET.getDurability(80)));
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            initializeClientWithModel(consumer, living -> new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new ModelHelmet(Minecraft.getInstance().getEntityModels().bakeLayer(ModelHelmet.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
        }
    }
}
