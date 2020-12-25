package com.gonhog.theancientworld.blocks;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockItemBase extends BlockItem {
    public BlockItemBase(Block blockIn) {
        super(blockIn, new Properties().group(TheAncientWorld.TAB));
    }
}
