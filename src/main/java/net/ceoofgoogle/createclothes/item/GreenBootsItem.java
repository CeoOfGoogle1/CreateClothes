package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.minecraft.world.item.ArmorItem;

public abstract class GreenBootsItem extends BaseCreateClothesArmorItem {
    public GreenBootsItem(ArmorItem.Type type, Properties properties) {
        super(CreateClothesModArmorMaterials.GREEN_BOOTS, type, properties);
    }

    public static class Boots extends GreenBootsItem {
        public Boots() {
            super(Type.BOOTS, new Properties().durability(Type.BOOTS.getDurability(15)));
        }
    }
}
