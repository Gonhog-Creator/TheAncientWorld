package com.gonhog.theancientworld.blocks;

import com.gonhog.theancientworld.tileEntities.TestBlockTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("theancientworld:test_block")
    public static TestBlock TEST_BLOCK;

    @ObjectHolder("theancientworld:test_block")
    public static TileEntityType<TestBlockTile> TEST_BLOCK_TILE;

}
