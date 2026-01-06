package net.funnydude.sunsetarmory.registries;

import net.funnydude.sunsetarmory.SunsetArmory;
import net.minecraft.client.renderer.item.ItemProperties;

public class ModItemProperties {

    public static Integer trueOrFalse (boolean e){
        if(e){
            return 1;
        }
        else return 0;
    }


    public static void addCustomItemProperties(){
        ItemProperties.register(ModItems.ORTU_SOLIS_STANDS.get(), SunsetArmory.id("ignited"),
                (itemStack, clientLevel, livingEntity, i) -> {
                    if(livingEntity != null) {
                        return trueOrFalse(livingEntity.hasEffect(ModEffects.BURNING_EFFECT));
                    }else return 0;
                });
    }
}
