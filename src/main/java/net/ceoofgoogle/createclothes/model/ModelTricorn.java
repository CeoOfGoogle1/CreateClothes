package net.ceoofgoogle.createclothes.model;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelTricorn<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("createclothes", "tricorn"), "main");
	public final ModelPart head;

	public ModelTricorn(ModelPart root) {
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -9.3F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.2F))
		.texOffs(0, 0).addBox(-6.0F, -6.0F, -3.0F, 12.0F, 0.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(36, 13).addBox(-6.0F, -10.0F, -3.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(36, 35).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(36, 13).addBox(6.0F, -10.0F, -3.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head_r1 = Head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, 0.0F, -4.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, -10.0F, -6.2F, 0.0F, -0.7854F, 0.0F));

		PartDefinition Head_r2 = Head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, 0.0F, -4.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, -10.0F, -6.2F, 0.0F, 0.7854F, 0.0F));

		PartDefinition Head_r3 = Head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(0, 9).addBox(-6.0F, -24.0F, -6.0F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -0.55F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}