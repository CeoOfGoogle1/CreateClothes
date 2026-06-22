package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.minecraft.world.item.ArmorItem;

public abstract class BlackBootsItem extends BaseCreateClothesArmorItem {
    public BlackBootsItem(ArmorItem.Type type, Properties properties) {
        super(CreateClothesModArmorMaterials.BLACK_BOOTS, type, properties);
    }

    public static class Boots extends BlackBootsItem {
        public Boots() {
            super(Type.BOOTS, new Properties().durability(Type.BOOTS.getDurability(15)));
        }
    }
}
