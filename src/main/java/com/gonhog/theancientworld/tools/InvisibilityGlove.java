package com.gonhog.theancientworld.tools;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InvisibilityGlove extends Item {
    public InvisibilityGlove(Properties properties) {
        super( new Item.Properties()
                .maxStackSize(1)
                .rarity(Rarity.EPIC)
                .group(TheAncientWorld.TAB)
        );
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean flag)
    {
        final boolean posX = entity.getPosX() == entity.lastTickPosX;
        final boolean posY = entity.getPosY() == entity.lastTickPosY;
        final boolean posZ = entity.getPosZ() == entity.lastTickPosZ;

        final boolean notMoving = posX && posZ && posY;
        Object glove = RegistryHandler.INVISIBILITY_GLOVE.get();
        Object offhand = ((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.OFFHAND).getItem();
        if (offhand == glove && notMoving) {
            ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 5, 0, true, false));
        }

    }

}
