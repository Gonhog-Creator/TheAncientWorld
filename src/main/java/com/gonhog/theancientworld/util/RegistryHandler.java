package com.gonhog.theancientworld.util;
import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.armor.AdamantArmorItem;
import com.gonhog.theancientworld.blocks.AdamantBlock;
import com.gonhog.theancientworld.blocks.BlockItemBase;
import com.gonhog.theancientworld.items.ItemBase;
import com.gonhog.theancientworld.items.MortarAndPestleClass;
import com.gonhog.theancientworld.items.CustomMeat;
import com.gonhog.theancientworld.tools.BasicModItemTier;
import com.gonhog.theancientworld.tools.InvisibilityGlove;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheAncientWorld.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheAncientWorld.MOD_ID);



    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Tools
    public static final RegistryObject<SwordItem> ADAMANT_SWORD = ITEMS.register("adamant_sword", () -> new SwordItem(BasicModItemTier.ADAMANT, 16, -2.4f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<PickaxeItem> ADAMANT_PICKAXE = ITEMS.register("adamant_pickaxe", () -> new PickaxeItem(BasicModItemTier.ADAMANT, 8, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<AxeItem> ADAMANT_AXE = ITEMS.register("adamant_axe", () -> new AxeItem(BasicModItemTier.ADAMANT, 10, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<HoeItem> ADAMANT_HOE = ITEMS.register("adamant_hoe", () -> new HoeItem(BasicModItemTier.ADAMANT, 6, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<ShovelItem> ADAMANT_SHOVEL = ITEMS.register("adamant_shovel", () -> new ShovelItem(BasicModItemTier.ADAMANT, 6, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));

    //Armor
    public static final RegistryObject<ArmorItem> ADAMANT_HELM = ITEMS.register("adamant_helm",() -> new AdamantArmorItem(EquipmentSlotType.HEAD));
    public static final RegistryObject<ArmorItem> ADAMANT_CHESTPLATE = ITEMS.register("adamant_chestplate",() -> new AdamantArmorItem(EquipmentSlotType.CHEST));
    public static final RegistryObject<ArmorItem> ADAMANT_LEGGINGS = ITEMS.register("adamant_leggings",() -> new AdamantArmorItem(EquipmentSlotType.LEGS));;
    public static final RegistryObject<ArmorItem> ADAMANT_BOOTS = ITEMS.register("adamant_boots",() -> new AdamantArmorItem(EquipmentSlotType.FEET));

    //Items
    public static final RegistryObject<Item> ADAMANT_PLATE = ITEMS.register("adamant_plate", ItemBase::new);
    public static final RegistryObject<Item> DRAGONBONE_PESTLE = ITEMS.register("dragonbone_pestle", () -> new MortarAndPestleClass(new Item.Properties()));
    public static final RegistryObject<Item> DRAGONBONE = ITEMS.register("dragonbone", ItemBase::new);
    public static final RegistryObject<Item> DRAGONBONE_DUST = ITEMS.register("dragonbone_dust", ItemBase::new);
    public static final RegistryObject<Item> INVISIBILITY_GLOVE = ITEMS.register("invisibility_glove", () -> new InvisibilityGlove(new Item.Properties()));

    //Food
    public static final RegistryObject<CustomMeat> RAW_SATYR_MEAT = ITEMS.register("raw_satyr_meat", CustomMeat::new);
    public static final RegistryObject<CustomMeat> COOKED_SATYR_MEAT = ITEMS.register("cooked_satyr_meat", CustomMeat::new);

    //Blocks
    public static final RegistryObject<Block> ADAMANT_BLOCK = BLOCKS.register("adamant_block", AdamantBlock::new);

    //Block Items
    public static final RegistryObject<Item> ADAMANT_BLOCK_ITEM = ITEMS.register("adamant_block", () -> new BlockItemBase(ADAMANT_BLOCK.get()));
}

