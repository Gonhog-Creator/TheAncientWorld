package com.gonhog.theancientworld.events;

import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = "theancientworld", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void renderPlayerEventGlove(RenderPlayerEvent.Pre event) {
        Item glove = RegistryHandler.INVISIBILITY_GLOVE.get();
        Item offhand = event.getPlayer().getHeldItemOffhand().getItem();
        final boolean posX = event.getPlayer().getPosX() == event.getPlayer().lastTickPosX;
        final boolean posY = event.getPlayer().getPosY() == event.getPlayer().lastTickPosY;
        final boolean posZ = event.getPlayer().getPosZ() == event.getPlayer().lastTickPosZ;
        final boolean notMoving = posX && posY && posZ;
        event.setCanceled(glove == offhand && notMoving);
    }

    @SubscribeEvent
    public static void makePlayerUntargetable(LivingSetAttackTargetEvent event) {

        if (event.getTarget() instanceof PlayerEntity) {
            Item glove = RegistryHandler.INVISIBILITY_GLOVE.get();
            Item offhand = event.getTarget().getHeldItemOffhand().getItem();
            final boolean posX = event.getTarget().getPosX() == event.getTarget().lastTickPosX;
            final boolean posY = event.getTarget().getPosY() == event.getTarget().lastTickPosY;
            final boolean posZ = event.getTarget().getPosZ() == event.getTarget().lastTickPosZ;
            final boolean notMoving = posX && posY && posZ;
            MobEntity mob = (MobEntity) event.getEntityLiving();
            if (offhand == glove && notMoving) {
                mob.setAttackTarget(null);
            } else {
                mob.setAttackTarget(event.getTarget());
            } if (offhand == glove && !notMoving) {
                mob.setAttackTarget(event.getTarget());
            }

        }
    }
}


