package com.gonhog.theancientworld.events;

import com.gonhog.theancientworld.items.potionRings.PotionRingEffectHandler;
import com.gonhog.theancientworld.tools.BowOfPlenty;
import com.gonhog.theancientworld.tools.Vasilis;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;


@Mod.EventBusSubscriber(modid = "theancientworld", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandler {



    @SubscribeEvent
    public static void BowOfArrowsChat(ServerChatEvent event) {
        PlayerEntity playerEntity = event.getPlayer();
        Hand hand = playerEntity.getActiveHand();
        Item mainHand = playerEntity.getHeldItemMainhand().getItem();
        Item offHand = playerEntity.getHeldItemOffhand().getItem();
        Item bow = RegistryHandler.BOW_OF_PLENTY.get();
        String message = event.getMessage();
        boolean numeric = true;
        try {
            Double num = Double.parseDouble(message);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        if (numeric) {
            int messageInt = Integer.parseInt(message);
            if (messageInt > 0 && messageInt <= 300) {
                if (mainHand == bow || offHand == bow) {
                    BowOfPlenty.setArrows(messageInt, event.getPlayer().getHeldItem(hand).getStack());
                }
            }
        }
    }


    @SubscribeEvent
    public static void ItemProperties(FMLClientSetupEvent event) {
        ItemModelsProperties.registerProperty(RegistryHandler.BOW_OF_PLENTY.get(), new ResourceLocation("theancientworld:bow_of_plenty_pull"), (p_239429_0_, p_239429_1_, p_239429_2_) -> {
            if (p_239429_2_ == null) {
                return 0.0F;
            } else {
                return p_239429_2_.getActiveItemStack() != p_239429_0_ ? 0.0F : (float) (p_239429_0_.getUseDuration() - p_239429_2_.getItemInUseCount()) / 20.0F;
            }
        });
        ItemModelsProperties.registerProperty(RegistryHandler.BOW_OF_PLENTY.get(), new ResourceLocation("theancientworld:bow_of_plenty_pulling"), (p_239428_0_, p_239428_1_, p_239428_2_) -> {
            return p_239428_2_ != null && p_239428_2_.isHandActive() && p_239428_2_.getActiveItemStack() == p_239428_0_ ? 1.0F : 0.0F;
        });

        ItemModelsProperties.registerProperty(RegistryHandler.VASILIS.get(), new ResourceLocation("theancientworld:vasilis"), new IItemPropertyGetter() {
            @Override
            public float call(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity) {
                if (Vasilis.getCounter() <= 5) {
                    return 5;
                } else if (Vasilis.getCounter() <= 10) {
                    return 10;
                } else if (Vasilis.getCounter() <= 15) {
                    return 15;
                } else return 20;
            }
        });
    }

    @SubscribeEvent
    public static void renderPlayerEventGlove(RenderPlayerEvent.Pre event) {
        Entity entity = event.getEntity();
        ItemStack gloveEquipped = PotionRingEffectHandler.getEquippedCurios(stack -> stack.getItem() == RegistryHandler.INVISIBILITY_GLOVE.get(), (PlayerEntity) entity);
        final boolean posX = event.getPlayer().getPosX() == event.getPlayer().lastTickPosX;
        final boolean posY = event.getPlayer().getPosY() == event.getPlayer().lastTickPosY;
        final boolean posZ = event.getPlayer().getPosZ() == event.getPlayer().lastTickPosZ;
        final boolean notMoving = posX && posY && posZ;
        event.setCanceled(!gloveEquipped.isEmpty() && notMoving);
    }

}


