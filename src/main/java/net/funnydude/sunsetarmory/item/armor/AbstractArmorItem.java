package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public abstract class AbstractArmorItem extends ImbuableChestplateArmorItem {

    public AbstractArmorItem(Holder<ArmorMaterial> pMaterial, Type pType, Properties pProperties, AttributeContainer... attributes) {
        super(pMaterial, pType, pProperties, attributes);
    }

    @Override
    public GeoArmorRenderer<?> supplyRenderer() {
        return null;
    }
}
