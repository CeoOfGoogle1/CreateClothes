package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.ceoofgoogle.createclothes.model.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(
        modid = CreateClothes.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD,
        value = {Dist.CLIENT}
)
public class CreateClothesModModels {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelBucketHat.LAYER_LOCATION, ModelBucketHat::createBodyLayer);
        event.registerLayerDefinition(ModelCap.LAYER_LOCATION, ModelCap::createBodyLayer);
        event.registerLayerDefinition(ModelCoat.LAYER_LOCATION, ModelCoat::createBodyLayer);
        event.registerLayerDefinition(ModelCrown.LAYER_LOCATION, ModelCrown::createBodyLayer);
        event.registerLayerDefinition(ModelHelmet.LAYER_LOCATION, ModelHelmet::createBodyLayer);
        event.registerLayerDefinition(ModelOfficerCap.LAYER_LOCATION, ModelOfficerCap::createBodyLayer);
        event.registerLayerDefinition(ModelTricorn.LAYER_LOCATION, ModelTricorn::createBodyLayer);
        event.registerLayerDefinition(ModelTunic.LAYER_LOCATION, ModelTunic::createBodyLayer);
        event.registerLayerDefinition(ModelVest.LAYER_LOCATION, ModelVest::createBodyLayer);
        event.registerLayerDefinition(ParachuteModel.LAYER_LOCATION, ParachuteModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CreateClothesModEntities.PARACHUTE.get(), net.ceoofgoogle.createclothes.client.renderer.ParachuteRenderer::new);
    }
}