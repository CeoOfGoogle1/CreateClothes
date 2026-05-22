package net.ceoofgoogle.createclothes.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.ceoofgoogle.createclothes.entity.ParachuteEntity;
import net.ceoofgoogle.createclothes.model.ParachuteModel;

public class ParachuteRenderer extends EntityRenderer<ParachuteEntity> {
    private final ParachuteModel<ParachuteEntity> model;

    private static final float MAX_OFFSET_Y = 5.7F;

    public ParachuteRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ParachuteModel<>(context.bakeLayer(ParachuteModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(ParachuteEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("createclothes", "textures/entity/parachute.png");
    }

    @Override
    public void render(ParachuteEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        float scale = entity.getParachuteScale();

        if (scale < 0.01F) return;

        poseStack.pushPose();

        Entity attached = entity.getAttached();
        if (attached != null) {
            double lerpX = Mth.lerp(partialTicks, attached.xo, attached.getX());
            double lerpY = Mth.lerp(partialTicks, attached.yo, attached.getY())
                    + 1.5 + scale * MAX_OFFSET_Y;
            double lerpZ = Mth.lerp(partialTicks, attached.zo, attached.getZ());

            double entX = Mth.lerp(partialTicks, entity.xo, entity.getX());
            double entY = Mth.lerp(partialTicks, entity.yo, entity.getY());
            double entZ = Mth.lerp(partialTicks, entity.zo, entity.getZ());

            poseStack.translate(lerpX - entX, lerpY - entY, lerpZ - entZ);

            float tiltYaw = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
            float tiltPitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());

            this.model.setupAnim(entity, 0, 0, entity.tickCount + partialTicks, tiltYaw, tiltPitch);
        } else {
            float tiltYaw = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
            float tiltPitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
            this.model.setupAnim(entity, 0, 0, entity.tickCount + partialTicks, tiltYaw, tiltPitch);
        }

        float visualScale = scale * 2.5F;
        poseStack.scale(visualScale, visualScale, visualScale);

        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        poseStack.translate(0, 1.5, 0);

        this.model.renderToBuffer(poseStack, buffer.getBuffer(this.model.renderType(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, -1);

        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }
}
