package net.ceoofgoogle.createclothes.item;

import net.ceoofgoogle.createclothes.init.CreateClothesModArmorMaterials;
import net.minecraft.world.item.ArmorItem;

public abstract class BrownBootsItem extends BaseCreateClothesArmorItem {
    public BrownBootsItem(ArmorItem.Type type, Properties properties) {
        super(CreateClothesModArmorMaterials.BROWN_BOOTS, type, properties);
    }

    public static class Boots extends BrownBootsItem {
        public Boots() {
            super(Type.BOOTS, new Properties().durability(Type.BOOTS.getDurability(15)));
        }
    }
}
