package com.gonhog.theancientworld.items;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CustomMeat extends Item {
    public CustomMeat() {
        super(new Item.Properties()
                .group(TheAncientWorld.TAB)
                .food(new Food.Builder()
                        .hunger(6)
                        .meat()
                        .saturation(6)
                        //.effect(new EffectInstance(Effects.FIRE_RESISTANCE, 100, 1),1.0f)
                        //.effect(new EffectInstance(Effects.ABSORPTION, 100, 4),1.0f)
                        //.effect(new EffectInstance(Effects.REGENERATION, 100, 4),1.0f)
                        .build())
        );
    }
}
