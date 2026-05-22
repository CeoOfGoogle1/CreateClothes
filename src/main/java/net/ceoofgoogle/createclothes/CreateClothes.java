package net.ceoofgoogle.createclothes;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.ceoofgoogle.createclothes.init.CreateClothesModDataComponents;
import net.ceoofgoogle.createclothes.init.CreateClothesModEntities;
import net.ceoofgoogle.createclothes.init.CreateClothesModItems;
import net.ceoofgoogle.createclothes.init.CreateClothesModTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(CreateClothes.MOD_ID)
public class CreateClothes
{
    public static final String MOD_ID = "createclothes";

    public CreateClothes(IEventBus bus)
    {
        CreateClothesModItems.REGISTRY.register(bus);
        CreateClothesModTabs.REGISTRY.register(bus);
        CreateClothesModArmorMaterials.REGISTRY.register(bus);
        CreateClothesModEntities.REGISTRY.register(bus);
        CreateClothesModDataComponents.REGISTRY.register(bus);
    }
}