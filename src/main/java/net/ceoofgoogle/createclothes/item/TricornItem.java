package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.ceoofgoogle.createclothes.model.ModelTricorn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public abstract class TricornItem extends BaseCreateClothesArmorItem {
    public TricornItem(ArmorItem.Type type, Properties properties) {
        super(CreateClothesModArmorMaterials.TRICORN, type, properties);
    }

    public static class Helmet extends TricornItem {
        public Helmet() {
            super(Type.HELMET, new Properties().durability(Type.HELMET.getDurability(80)));
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            initializeClientWithModel(consumer, living -> new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new ModelTricorn(Minecraft.getInstance().getEntityModels().bakeLayer(ModelTricorn.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
        }
    }
}
