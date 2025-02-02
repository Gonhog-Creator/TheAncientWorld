package com.gonhog.theancientworld.armor;

import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public enum ModArmorBase implements IArmorMaterial {


    TAURAN(TheAncientWorld.MOD_ID + ":tauran", 18, new int[]{2, 5, 6, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            1.0f, 0.0F, () -> {return Ingredient.fromItems(RegistryHandler.TAURAN_LEATHER.get() /*, RegistryHandler.ADAMANT_PLATE.get()*/ );  }),
    ADAMANT(TheAncientWorld.MOD_ID + ":adamant",  37, new int[]{6, 12, 16, 6}, 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            3.0F, 0.1F, () -> {return Ingredient.fromItems(RegistryHandler.ADAMANT_PLATE.get()); });



    private static final int[] MAX_DAMAGE_ARRAY = new int[] {11, 16, 15, 13};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorBase(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability,
                 SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial){
        this.name = name;
        this.maxDamageFactor= maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.soundEvent = soundEvent;
        this.enchantability = enchantability;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairMaterial;
    }


    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }


}
