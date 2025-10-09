package net.funnydude.sunsetarmory.item.armor.geckolib;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.entity.armor.geckolib.NpcNetheriteKnightArmorModel;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;


public class NpcNetheriteKnightArmorItem extends ImbuableGeckolibArmor {
    public NpcNetheriteKnightArmorItem(Type type, Properties properties) {
        super(ModArmorMaterials.KNIGHT_ARMOR_MATERIAL, type, properties, new AttributeContainer(AttributeRegistry.MAX_MANA, 25, AttributeModifier.Operation.ADD_VALUE));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new NpcNetheriteKnightArmorModel());
    }
}
