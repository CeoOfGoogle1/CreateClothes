package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.ceoofgoogle.createclothes.model.ModelOfficerCap;
import net.ceoofgoogle.createclothes.model.ModelTunic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public abstract class OfficerItem extends BaseCreateClothesArmorItem {
    public OfficerItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
    }

    public static class Chestplate extends OfficerItem {
        public Chestplate() {
            super(CreateClothesModArmorMaterials.OFFICER_TUNIC, Type.CHESTPLATE, new Properties().durability(Type.CHESTPLATE.getDurability(80)));
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            initializeClientWithModel(consumer, living -> new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new ModelTunic(Minecraft.getInstance().getEntityModels().bakeLayer(ModelTunic.LAYER_LOCATION))).body, "left_arm", (new ModelTunic(Minecraft.getInstance().getEntityModels().bakeLayer(ModelTunic.LAYER_LOCATION))).left_arm, "right_arm", (new ModelTunic(Minecraft.getInstance().getEntityModels().bakeLayer(ModelTunic.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
        }
    }

    public static class Helmet extends OfficerItem {
        public Helmet() {
            super(CreateClothesModArmorMaterials.OFFICER_CAP, Type.HELMET, new Properties().durability(Type.HELMET.getDurability(80)));
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            initializeClientWithModel(consumer, living -> new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new ModelOfficerCap(Minecraft.getInstance().getEntityModels().bakeLayer(ModelOfficerCap.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())))));
        }
    }
}
