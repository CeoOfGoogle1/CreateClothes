package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreateClothesModTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateClothes.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATECLOTHES = REGISTRY.register("create_clothes", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.create_clothes"))
            .icon(() -> new ItemStack(CreateClothesModItems.OFFICER_CAP.get()))
            .displayItems((parameters, tabData) -> {
                tabData.accept(CreateClothesModItems.CROWN.get());
                tabData.accept(CreateClothesModItems.CAP.get());
                tabData.accept(CreateClothesModItems.BUCKET_HAT.get());
                tabData.accept(CreateClothesModItems.OFFICER_CAP.get());
                tabData.accept(CreateClothesModItems.OFFICER_TUNIC.get());
                tabData.accept(CreateClothesModItems.TRICORN.get());
                tabData.accept(CreateClothesModItems.REDCOAT.get());
                tabData.accept(CreateClothesModItems.BLACK_BOOTS.get());
                tabData.accept(CreateClothesModItems.GRAY_BOOTS.get());
                tabData.accept(CreateClothesModItems.BROWN_BOOTS.get());
                tabData.accept(CreateClothesModItems.GREEN_BOOTS.get());
                tabData.accept(CreateClothesModItems.PLAINS_HELMET.get());
                tabData.accept(CreateClothesModItems.PLAINS_TUNIC.get());
                tabData.accept(CreateClothesModItems.PLAINS_PANTS.get());
                tabData.accept(CreateClothesModItems.PLAINS_BOOTS.get());
                tabData.accept(CreateClothesModItems.FOREST_HELMET.get());
                tabData.accept(CreateClothesModItems.FOREST_VEST.get());
                tabData.accept(CreateClothesModItems.FOREST_PANTS.get());
                tabData.accept(CreateClothesModItems.FOREST_BOOTS.get());
                tabData.accept(CreateClothesModItems.TAIGA_HELMET.get());
                tabData.accept(CreateClothesModItems.TAIGA_TUNIC.get());
                tabData.accept(CreateClothesModItems.TAIGA_PANTS.get());
                tabData.accept(CreateClothesModItems.TAIGA_BOOTS.get());
                tabData.accept(CreateClothesModItems.DESERT_HELMET.get());
                tabData.accept(CreateClothesModItems.DESERT_TUNIC.get());
                tabData.accept(CreateClothesModItems.DESERT_PANTS.get());
                tabData.accept(CreateClothesModItems.DESERT_BOOTS.get());
                tabData.accept(CreateClothesModItems.SNOW_HELMET.get());
                tabData.accept(CreateClothesModItems.SNOW_COAT.get());
                tabData.accept(CreateClothesModItems.SNOW_PANTS.get());
                tabData.accept(CreateClothesModItems.SNOW_BOOTS.get());
                tabData.accept(CreateClothesModItems.SAVANNA_HELMET.get());
                tabData.accept(CreateClothesModItems.SAVANNA_TUNIC.get());
                tabData.accept(CreateClothesModItems.SAVANNA_PANTS.get());
                tabData.accept(CreateClothesModItems.SAVANNA_BOOTS.get());
                tabData.accept(CreateClothesModItems.WATER_HELMET.get());
                tabData.accept(CreateClothesModItems.WATER_TUNIC.get());
                tabData.accept(CreateClothesModItems.WATER_PANTS.get());
                tabData.accept(CreateClothesModItems.WATER_BOOTS.get());
                tabData.accept(CreateClothesModItems.MESA_HELMET.get());
                tabData.accept(CreateClothesModItems.MESA_TUNIC.get());
                tabData.accept(CreateClothesModItems.MESA_PANTS.get());
                tabData.accept(CreateClothesModItems.MESA_BOOTS.get());
                tabData.accept(CreateClothesModItems.DIRT_HELMET.get());
                tabData.accept(CreateClothesModItems.DIRT_TUNIC.get());
                tabData.accept(CreateClothesModItems.DIRT_PANTS.get());
                tabData.accept(CreateClothesModItems.DIRT_BOOTS.get());
                tabData.accept(CreateClothesModItems.CLOTH.get());
                tabData.accept(CreateClothesModItems.STURDY_CLOTH.get());
                tabData.accept(CreateClothesModItems.PLAINS_CLOTH.get());
                tabData.accept(CreateClothesModItems.FOREST_CLOTH.get());
                tabData.accept(CreateClothesModItems.TAIGA_CLOTH.get());
                tabData.accept(CreateClothesModItems.SAVANNA_CLOTH.get());
                tabData.accept(CreateClothesModItems.DESERT_CLOTH.get());
                tabData.accept(CreateClothesModItems.SNOW_CLOTH.get());
                tabData.accept(CreateClothesModItems.MESA_CLOTH.get());
                tabData.accept(CreateClothesModItems.DIRT_CLOTH.get());
                tabData.accept(CreateClothesModItems.WATER_CLOTH.get());
                tabData.accept(CreateClothesModItems.PARACHUTE.get());
            }).build());
}
