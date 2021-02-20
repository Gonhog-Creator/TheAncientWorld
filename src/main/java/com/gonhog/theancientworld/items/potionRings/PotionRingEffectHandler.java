package com.gonhog.theancientworld.items.potionRings;

import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Objects;
import java.util.Optional;

public class PotionRingEffectHandler {

    public PotionRingEffectHandler () {

    }


    public void addEffect(PlayerEntity entity, Effect effectName, Integer amplifier) {
        if (!entity.getEntityWorld().isRemote()) {
            entity.addPotionEffect(new EffectInstance(effectName, 100, amplifier, true, false));
        }
    }

    public boolean isPotionActive(PlayerEntity entity, Effect effectName, Integer amplifier) {
        return entity.isPotionActive(effectName) &&
                Objects.requireNonNull(entity.getActivePotionEffect(effectName)).getAmplifier() == amplifier;
    }

    public int returnPotionLevel(PlayerEntity entity, Item ring) {
        Optional<ICuriosItemHandler> handler = Optional.of(CuriosApi.getCuriosHelper().getCuriosHandler((LivingEntity) entity).resolve().get());
        Optional<ICurioStacksHandler> stackHandler = Optional.of((handler.get().getStacksHandler("ring").get()));
        Item ring1 = stackHandler.get().getStacks().getStackInSlot(0).getItem();
        Item ring2 = stackHandler.get().getStacks().getStackInSlot(1).getItem();
        ItemStack ringEquipped = ClientEventHandler.getEquippedCurios(stack -> stack.getItem() == ring, (PlayerEntity) entity);

        if (ring1 == ring && ring2 == ring) {
            return 2;
        } else if (!ringEquipped.isEmpty()) {
            return 1;
        } else return 0;
    }
}
