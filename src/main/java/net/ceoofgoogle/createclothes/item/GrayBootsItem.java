package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.minecraft.world.item.ArmorItem;

public abstract class GrayBootsItem extends BaseCreateClothesArmorItem {
    public GrayBootsItem(ArmorItem.Type type, Properties properties) {
        super(CreateClothesModArmorMaterials.GRAY_BOOTS, type, properties);
    }

    public static class Boots extends GrayBootsItem {
        public Boots() {
            super(Type.BOOTS, new Properties().durability(Type.BOOTS.getDurability(15)));
        }
    }
}
