package com.gonhog.theancientworld.events;

import com.gonhog.theancientworld.tools.BowOfPlenty;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Optional;
import java.util.function.Predicate;


@Mod.EventBusSubscriber(modid = "theancientworld", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandler {

    public static ItemStack getEquippedCurios(Predicate<ItemStack> predicate, PlayerEntity player) {
        Optional<ItemStack> stack = CuriosApi.getCuriosHelper().findEquippedCurio(predicate, player).map(ImmutableTriple::getRight);
        return stack.orElse(ItemStack.EMPTY);
    }

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

}


