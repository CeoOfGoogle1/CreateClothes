package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.ceoofgoogle.createclothes.model.ModelTunic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public abstract class RedcoatItem extends BaseCreateClothesArmorItem {
    public RedcoatItem(ArmorItem.Type type, Properties properties) {
        super(CreateClothesModArmorMaterials.REDCOAT, type, properties);
    }

    public static class Chestplate extends RedcoatItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Properties().durability(Type.CHESTPLATE.getDurability(15)));
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            initializeClientWithModel(consumer, living -> new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new ModelTunic(Minecraft.getInstance().getEntityModels().bakeLayer(ModelTunic.LAYER_LOCATION))).body, "left_arm", (new ModelTunic(Minecraft.getInstance().getEntityModels().bakeLayer(ModelTunic.LAYER_LOCATION))).left_arm, "right_arm", (new ModelTunic(Minecraft.getInstance().getEntityModels().bakeLayer(ModelTunic.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
        }
    }
}
