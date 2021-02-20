package com.gonhog.theancientworld.items.potionRings;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.util.RegistryHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ResistanceRing extends Item {

    public ResistanceRing(Properties properties) {
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
            if (potionHandler.returnPotionLevel(playerEntity, RegistryHandler.RESISTANCE_RING.get()) == 2) {
                if (potionHandler.isPotionActive(playerEntity, Effects.RESISTANCE, 1)) {
                    return;
                } else {
                    potionHandler.addEffect(playerEntity, Effects.RESISTANCE, 1);
                }
            }

            if (potionHandler.returnPotionLevel(playerEntity, RegistryHandler.RESISTANCE_RING.get()) == 1) {
                if (potionHandler.isPotionActive(playerEntity, Effects.RESISTANCE, 0)) {
                    return;
                } else {
                    potionHandler.addEffect(playerEntity, Effects.RESISTANCE, 0);
                }
            }
        }
    }
}
