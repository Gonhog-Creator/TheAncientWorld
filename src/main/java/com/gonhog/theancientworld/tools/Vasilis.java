package com.gonhog.theancientworld.tools;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;
import java.util.List;

public class Vasilis extends SwordItem {
    public Vasilis(Properties properties) {
        super(BasicModItemTier.VASILIS,
                20,
                2.0f,
                new Item.Properties()
                        .isImmuneToFire()
                        .rarity(Rarity.create("Legendary", TextFormatting.GOLD))
                        .maxStackSize(1)
                        .group(TheAncientWorld.TAB));
    }



    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("message.vasilis"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking()) {
            playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 300, 2, true, true));
            return ActionResult.resultPass(itemstack);
        } else {
            return ActionResult.resultFail(itemstack);
        }
    }

    private static int counter = 0;

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean flag) {
        if (counter < 20) {
            counter++;
        } else {
            counter = 1;
        }
    }

    public static int getCounter() {
        return counter;
    }


}
