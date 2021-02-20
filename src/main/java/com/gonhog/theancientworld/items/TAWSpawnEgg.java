package com.gonhog.theancientworld.items;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TAWSpawnEgg extends SpawnEggItem {
    private Supplier<? extends EntityType<?>> typeGetter;

    public TAWSpawnEgg(Supplier<? extends EntityType<?>> typeIn) {
        super(null, 11022961, 11035249,
                new Item.Properties()
                        .maxStackSize(1)
                        .group(TheAncientWorld.TAB));
        typeGetter = typeIn;
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
        return typeGetter.get();
    }

}
