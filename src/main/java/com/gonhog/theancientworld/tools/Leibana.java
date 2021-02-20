package com.gonhog.theancientworld.tools;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Leibana extends SwordItem {
    public Leibana(Properties properties) {
        super(BasicModItemTier.LEIBANA, 20, -4.0f,
                new Item.Properties()
                        .maxStackSize(1)
                        .isImmuneToFire()
                        .rarity(Rarity.create("Legendary", TextFormatting.GOLD))
                        .group(TheAncientWorld.TAB));
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("message.leibana"));
    }



}
