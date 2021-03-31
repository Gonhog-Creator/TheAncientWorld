package com.gonhog.theancientworld.armor;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class AdamantArmorItem extends ArmorItem{
    public AdamantArmorItem(EquipmentSlotType slot) {
        super(ModArmorBase.ADAMANT, slot, new Item.Properties().group(TheAncientWorld.TAB).maxStackSize(1));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {

        ItemStack boots = player.getItemStackFromSlot(EquipmentSlotType.FEET);
        ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
        ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        ItemStack helm = player.getItemStackFromSlot(EquipmentSlotType.HEAD);

        final boolean wearingAllAdamantArmor =
                boots.getItem() == RegistryHandler.ADAMANT_BOOTS.get() &&
                legs.getItem() == RegistryHandler.ADAMANT_LEGGINGS.get() &&
                chest.getItem() == RegistryHandler.ADAMANT_CHESTPLATE.get() &&
                helm.getItem() == RegistryHandler.ADAMANT_HELM.get();

        if (wearingAllAdamantArmor) {
            player.addPotionEffect(new EffectInstance(Effects.SPEED, 20, 0, true, false));
        }

    }
}



