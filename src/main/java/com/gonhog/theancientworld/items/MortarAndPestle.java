package com.gonhog.theancientworld.items;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MortarAndPestle extends Item {
    public MortarAndPestle(Properties properties) {

        super(new Item.Properties()
            .group(TheAncientWorld.TAB)
            .maxStackSize(1)

            );
    }
    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemstack) {
        ItemStack stack = itemstack.copy();
        if (stack.getMaxDamage() - stack.getDamage() <= 1) {
            stack.shrink(0);
        } else {
            stack.attemptDamageItem(0, random, null);
        }
        return stack;
    }
}
