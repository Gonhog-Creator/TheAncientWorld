package com.gonhog.theancientworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class AdamantBlock extends Block {

    public AdamantBlock() {
        super(Properties.create(Material.IRON)
            .hardnessAndResistance(5.0f, 6.0f)
            .sound(SoundType.METAL)
            .setRequiresTool()
            .harvestLevel(4)
            .harvestTool(ToolType.PICKAXE)
        );
    }
}
