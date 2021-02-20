package com.gonhog.theancientworld.items.potionRings;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Optional;

public class HasteRing extends Item {

    public HasteRing(Properties properties) {
        super(new Properties()
                .maxStackSize(1)
                .group(TheAncientWorld.TAB)
        );
    }

    PotionRingEffectHandler potionHandler = new PotionRingEffectHandler();

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean flag) {
        PlayerEntity playerEntity = (PlayerEntity) entity;
        if (!world.isRemote) {
            if (potionHandler.returnPotionLevel(playerEntity, RegistryHandler.HASTE_RING.get()) == 2) {
                if (potionHandler.isPotionActive(playerEntity, Effects.HASTE, 1)) {
                    return;
                } else {
                    potionHandler.addEffect(playerEntity, Effects.HASTE, 1);
                }
            }

            if (potionHandler.returnPotionLevel(playerEntity, RegistryHandler.HASTE_RING.get()) == 1) {
                if (potionHandler.isPotionActive(playerEntity, Effects.HASTE, 0)) {
                    return;
                } else {
                    potionHandler.addEffect(playerEntity, Effects.HASTE, 0);
                }
            }
        }
    }
}
