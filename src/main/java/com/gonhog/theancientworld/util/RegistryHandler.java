package com.gonhog.theancientworld.util;
import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.armor.AdamantArmorItem;
import com.gonhog.theancientworld.armor.TauranArmor;
import com.gonhog.theancientworld.blocks.*;
import com.gonhog.theancientworld.containers.TestBlockContainer;
import com.gonhog.theancientworld.entities.astrid.AstridEntity;
import com.gonhog.theancientworld.entities.centaur.CentaurEntity;
import com.gonhog.theancientworld.entities.deer.DeerEntity;
import com.gonhog.theancientworld.entities.dirt_golem.DirtGolemEntity;
import com.gonhog.theancientworld.entities.nipsie.NipsieEntity;
import com.gonhog.theancientworld.entities.quick_hit_arrow.QuickHitArrowEntity;
import com.gonhog.theancientworld.entities.satyr.SatyrEntity;
import com.gonhog.theancientworld.entities.unicorn.UnicornEntity;
import com.gonhog.theancientworld.entities.wood_golem.WoodGolemEntity;
import com.gonhog.theancientworld.entities.wraith.WraithEntity;
import com.gonhog.theancientworld.items.*;
import com.gonhog.theancientworld.items.potionRings.*;
import com.gonhog.theancientworld.structures.FablehavenStructure;
import com.gonhog.theancientworld.tileEntities.TestBlockTile;
import com.gonhog.theancientworld.tools.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotTypeMessage;


public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheAncientWorld.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheAncientWorld.MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES,TheAncientWorld.MOD_ID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TheAncientWorld.MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheAncientWorld.MOD_ID);
    private static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TheAncientWorld.MOD_ID);



    public static void init(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());

    }



    //Tools
    public static final RegistryObject<SwordItem> ADAMANT_SWORD = ITEMS.register("adamant_sword", () -> new SwordItem(BasicModItemTier.ADAMANT, 16, -2.4f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<PickaxeItem> ADAMANT_PICKAXE = ITEMS.register("adamant_pickaxe", () -> new PickaxeItem(BasicModItemTier.ADAMANT, 8, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<AxeItem> ADAMANT_AXE = ITEMS.register("adamant_axe", () -> new AxeItem(BasicModItemTier.ADAMANT, 10, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<HoeItem> ADAMANT_HOE = ITEMS.register("adamant_hoe", () -> new HoeItem(BasicModItemTier.ADAMANT, 6, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));
    public static final RegistryObject<ShovelItem> ADAMANT_SHOVEL = ITEMS.register("adamant_shovel", () -> new ShovelItem(BasicModItemTier.ADAMANT, 6, -1.0f, new Item.Properties().group(TheAncientWorld.TAB).isImmuneToFire()));

    //Bows
    public static final RegistryObject<BowItem> BOW_OF_PLENTY = ITEMS.register("bow_of_plenty", () -> new BowOfPlenty(new Item.Properties().group(TheAncientWorld.TAB)));

    //Swords of Legend
    public static final RegistryObject<SwordItem> VASILIS = ITEMS.register("vasilis",() -> new Vasilis(new Item.Properties()));
    public static final RegistryObject<SwordItem> LEIBANA = ITEMS.register("leibana", ()-> new Leibana(new Item.Properties()));

    //Armor
    public static final RegistryObject<ArmorItem> ADAMANT_HELM = ITEMS.register("adamant_helm",() -> new AdamantArmorItem(EquipmentSlotType.HEAD));
    public static final RegistryObject<ArmorItem> ADAMANT_CHESTPLATE = ITEMS.register("adamant_chestplate",() -> new AdamantArmorItem(EquipmentSlotType.CHEST));
    public static final RegistryObject<ArmorItem> ADAMANT_LEGGINGS = ITEMS.register("adamant_leggings",() -> new AdamantArmorItem(EquipmentSlotType.LEGS));
    public static final RegistryObject<ArmorItem> ADAMANT_BOOTS = ITEMS.register("adamant_boots",() -> new AdamantArmorItem(EquipmentSlotType.FEET));
    public static final RegistryObject<ArmorItem> TAURAN_HELM = ITEMS.register("tauran_helm", () -> new TauranArmor(EquipmentSlotType.HEAD));
    public static final RegistryObject<ArmorItem> TAURAN_CHESTPLATE = ITEMS.register("tauran_chestplate", () -> new TauranArmor(EquipmentSlotType.CHEST));
    public static final RegistryObject<ArmorItem> TAURAN_LEGGINGS = ITEMS.register("tauran_leggings", () -> new TauranArmor(EquipmentSlotType.LEGS));
    public static final RegistryObject<ArmorItem> TAURAN_BOOTS = ITEMS.register("tauran_boots", () -> new TauranArmor(EquipmentSlotType.FEET));

    //Items
    public static final RegistryObject<Item> ADAMANT_PLATE = ITEMS.register("adamant_plate", ItemBase::new);
    public static final RegistryObject<Item> DRAGONBONE_PESTLE = ITEMS.register("dragonbone_pestle", () -> new MortarAndPestle(new Item.Properties()));
    public static final RegistryObject<Item> BONE_PESTLE = ITEMS.register("bone_pestle", () -> new MortarAndPestle(new Item.Properties()));
    public static final RegistryObject<Item> DRAGONBONE = ITEMS.register("dragonbone", ItemBase::new);
    public static final RegistryObject<Item> DRAGONBONE_DUST = ITEMS.register("dragonbone_dust", ItemBase::new);
    public static final RegistryObject<Item> INVISIBILITY_GLOVE = ITEMS.register("invisibility_glove", () -> new InvisibilityGlove(new Item.Properties()));
    public static final RegistryObject<Item> GLOWING_POWDER = ITEMS.register("glowing_powder", ItemBase::new);
    public static final RegistryObject<Item> GLOWING_INGOT = ITEMS.register("glowing_ingot", ItemBase::new);
    public static final RegistryObject<Item> GLOWING_GEM = ITEMS.register("glowing_gem", ItemBase::new);
    public static final RegistryObject<Item> Potion_RING = ITEMS.register("potion_ring", ItemBase::new);
    public static final RegistryObject<Item> ENCHANTED_EYES_RING = ITEMS.register("enchanted_eyes_ring", () -> new EnchantedEyesRing(new Item.Properties()));
    public static final RegistryObject<Item> RESISTANCE_RING = ITEMS.register("resistance_ring", () -> new ResistanceRing(new Item.Properties()));
    public static final RegistryObject<Item> REGEN_RING = ITEMS.register("regen_ring", () -> new RegenRing(new Item.Properties()));
    public static final RegistryObject<Item> HASTE_RING = ITEMS.register("haste_ring", () -> new HasteRing(new Item.Properties()));
    public static final RegistryObject<Item> SPEED_RING = ITEMS.register("speed_ring", () -> new SpeedRing(new Item.Properties()));
    public static final RegistryObject<Item> JUMP_RING = ITEMS.register("jump_ring", () -> new JumpRing(new Item.Properties()));
    public static final RegistryObject<Item> STRENGTH_RING = ITEMS.register("strength_ring", () -> new StrengthRing(new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_EYE = ITEMS.register("dragon_eye", () -> new DragonEye(new Item.Properties()));
    public static final RegistryObject<Item> ASTRID_FEATHER = ITEMS.register("astrid_feather", ItemBase::new);
    public static final RegistryObject<Item> PHOENIX_FEATHER = ITEMS.register("phoenix_feather", ItemBase::new);
    public static final RegistryObject<Item> UNICORN_HORN = ITEMS.register("unicorn_horn", ItemBase::new);
    public static final RegistryObject<Item> TAURAN_HOOF = ITEMS.register("tauran_hoof", ItemBase::new);
    public static final RegistryObject<Item> TAURAN_LEATHER = ITEMS.register("tauran_leather", ItemBase::new);
    public static final RegistryObject<Item> DEER_ANTLER = ITEMS.register("deer_antler", ItemBase::new);
    public static final RegistryObject<Item> ADAMANT_DUST = ITEMS.register("adamant_dust", ItemBase::new);





    //Food
    public static final RegistryObject<CustomMeat> RAW_SATYR_MEAT = ITEMS.register("raw_satyr_meat", CustomMeat::new);
    public static final RegistryObject<CookedCustomMeat> COOKED_SATYR_MEAT = ITEMS.register("cooked_satyr_meat", CookedCustomMeat::new);
    public static final RegistryObject<CustomMeat> UNICORN_MEAT = ITEMS.register("unicorn_meat", CustomMeat::new);
    public static final RegistryObject<CustomMeat> RAW_CENTAUR_MEAT = ITEMS.register("raw_centaur_meat", CustomMeat::new);
    public static final RegistryObject<CookedCustomMeat> COOKED_CENTAUR_MEAT = ITEMS.register("cooked_centaur_meat", CookedCustomMeat::new);
    public static final RegistryObject<CustomMeat> RAW_VENISON = ITEMS.register("raw_venison", CustomMeat::new);
    public static final RegistryObject<CookedCustomMeat> COOKED_VENISON = ITEMS.register("cooked_venison", CookedCustomMeat::new);




    //Blocks -- register under block items below
    public static final RegistryObject<Block> ADAMANT_BLOCK = BLOCKS.register("adamant_block", AdamantBlock::new);
    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", TestBlock::new);
    public static final RegistryObject<Block> LIVING_PUMPKIN = BLOCKS.register("living_pumpkin", () -> new LivingPumpkinBlock(AbstractBlock.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<AirBlock> SATYR_AIR = BLOCKS.register("satyr_air", () -> new AirBlock(AbstractBlock.Properties.create(Material.AIR).doesNotBlockMovement().noDrops().setAir()));

    //Block Items
    public static final RegistryObject<Item> ADAMANT_BLOCK_ITEM = ITEMS.register("adamant_block", () -> new BlockItemBase(ADAMANT_BLOCK.get()));
    public static final RegistryObject<Item> TEST_BLOCK_ITEM = ITEMS.register("test_block", () -> new BlockItemBase(TEST_BLOCK.get()));
    public static final RegistryObject<Item> LIVING_PUMPKIN_BLOCK_ITEM = ITEMS.register("living_pumpkin", () -> new BlockItemBase(LIVING_PUMPKIN.get()));
    public static final RegistryObject<Item> SATYR_AIR_ITEM = ITEMS.register("satyr_air", () -> new BlockItemBase(SATYR_AIR.get()));


    //Tile Entities
    public static final RegistryObject<TileEntityType<TestBlockTile>> TEST_BLOCK_TILE = TILES.register("test_block", () -> TileEntityType.Builder.create(TestBlockTile::new, TEST_BLOCK.get()).build(null));

    //Containers
    public static final RegistryObject<ContainerType<TestBlockContainer>> TEST_BLOCK_CONTAINER = CONTAINERS.register("test_block", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new TestBlockContainer(windowId, world, pos, inv, inv.player);
    }));

    //Entities

    public static final RegistryObject<EntityType<UnicornEntity>> UNICORN = ENTITIES.register("unicorn", () -> EntityType.Builder.create(UnicornEntity::new, EntityClassification.CREATURE)
            .size(1.4F, 1.6F).trackingRange(10).build("unicorn"));
    public static final RegistryObject<EntityType<SatyrEntity>> SATYR = ENTITIES.register("satyr", () -> EntityType.Builder.create(SatyrEntity::new, EntityClassification.CREATURE)
            .trackingRange(10).build("satyr"));
    public static final RegistryObject<EntityType<CentaurEntity>> CENTAUR = ENTITIES.register("centaur", () -> EntityType.Builder.create(CentaurEntity::new, EntityClassification.CREATURE)
            .size(1.3964844F, 2.0F).trackingRange(10).build("centaur"));
    public static final RegistryObject<EntityType<NipsieEntity>> NIPSIE = ENTITIES.register("nipsie", () -> EntityType.Builder.create(NipsieEntity::new, EntityClassification.CREATURE)
            .size(0.2F, 0.4F).trackingRange(10).build("nipsie"));
    public static final RegistryObject<EntityType<DirtGolemEntity>> DIRT_GOLEM = ENTITIES.register("dirt_golem", () -> EntityType.Builder.create(DirtGolemEntity::new, EntityClassification.CREATURE)
            .size(1.5F, 2.2F).trackingRange(10).build("dirt_golem"));
    public static final RegistryObject<EntityType<WoodGolemEntity>> WOOD_GOLEM = ENTITIES.register("wood_golem", () -> EntityType.Builder.create(WoodGolemEntity::new, EntityClassification.CREATURE)
            .size(1.4F, 2F).trackingRange(10).build("wood_golem"));
    public static final RegistryObject<EntityType<QuickHitArrowEntity>> QUICK_HIT_ARROW = ENTITIES.register("quick_hit_arrow", () -> EntityType.Builder.<QuickHitArrowEntity>create(QuickHitArrowEntity::new, EntityClassification.MISC)
            .size(.5F, .5F).trackingRange(9).build("quick_hit_arrow"));
    public static final RegistryObject<EntityType<AstridEntity>> ASTRID = ENTITIES.register("astrid", () -> EntityType.Builder.create(AstridEntity::new, EntityClassification.CREATURE)
            .size(.6F, .8F).trackingRange(20).build("astrid"));
    public static final RegistryObject<EntityType<DeerEntity>> DEER = ENTITIES.register("deer", () -> EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE)
            .size(.6F, 1.3F).trackingRange(20).build("deer"));
    public static final RegistryObject<EntityType<WraithEntity>> WRAITH = ENTITIES.register("wraith", () -> EntityType.Builder.create(WraithEntity::new, EntityClassification.MONSTER)
            .size(1F, 2F).trackingRange(20).build("wraith"));




    //Eggs
    //public static final RegistryObject<TAWSpawnEgg> UNICORN_EGG = ITEMS.register("unicorn_egg", () -> new TAWSpawnEgg((Supplier<? extends EntityType<?>>) RegistryHandler.UNICORN.get()));
    //public static final RegistryObject<SpawnEggItem> SATYR_EGG = ITEMS.register("satyr_egg", () -> new SpawnEggItem(RegistryHandler.SATYR.get(), 23403, 28082, new Item.Properties().group(TheAncientWorld.TAB)));


    //Curios
    public static void curios(final InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("ring").size(2).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("hands").size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("charm").size(2).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("necklace").size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("head").size(1).build());
    }

    //Structures

    public static final RegistryObject<Structure<NoFeatureConfig>> FABLEHAVEN = STRUCTURES.register("fablehaven", () -> (new FablehavenStructure(NoFeatureConfig.field_236558_a_)));

}
