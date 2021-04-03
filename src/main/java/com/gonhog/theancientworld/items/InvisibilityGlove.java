package com.gonhog.theancientworld.items;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;

public class InvisibilityGlove extends Item {
    public InvisibilityGlove(Properties properties) {
        super( new Item.Properties()
                .maxStackSize(1)
                .rarity(Rarity.EPIC)
                .group(TheAncientWorld.TAB)
        );
    }

    //Gives the player invisibility effect if the item is in the hand slot
    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
    {
        final boolean posX = entity.getPosX() == entity.lastTickPosX;
        final boolean posY = entity.getPosY() == entity.lastTickPosY;
        final boolean posZ = entity.getPosZ() == entity.lastTickPosZ;

        final boolean notMoving = posX && posZ && posY;
        ItemStack gloveEquipped = ClientEventHandler.getEquippedCurios(stack -> stack.getItem() == RegistryHandler.INVISIBILITY_GLOVE.get(), (PlayerEntity) entity);
        if (!world.isRemote) {
            if (!gloveEquipped.isEmpty() && notMoving) {
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 5, 0, true, true));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("message.invisibilityglove"));
    }

}
