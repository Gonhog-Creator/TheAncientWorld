package com.gonhog.theancientworld;

import com.gonhog.theancientworld.entities.astrid.AstridEntity;
import com.gonhog.theancientworld.entities.centaur.CentaurEntity;
import com.gonhog.theancientworld.entities.deer.DeerEntity;
import com.gonhog.theancientworld.entities.dirt_golem.DirtGolemEntity;
import com.gonhog.theancientworld.entities.nipsie.NipsieEntity;
import com.gonhog.theancientworld.entities.satyr.SatyrEntity;
import com.gonhog.theancientworld.entities.unicorn.UnicornEntity;
import com.gonhog.theancientworld.entities.wood_golem.WoodGolemEntity;
import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.items.InvisibilityGlove;
import com.gonhog.theancientworld.structures.TAWConfiguredStructures;
import com.gonhog.theancientworld.tools.BowOfPlenty;
import com.gonhog.theancientworld.tools.Vasilis;
import com.gonhog.theancientworld.util.*;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Mod("theancientworld")
public class TheAncientWorld
{

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "theancientworld";


    public TheAncientWorld() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        LOGGER.info("Setup Complete");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        LOGGER.info("Client Stuff Complete");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(RegistryHandler::curios);
        LOGGER.info("Curio Registration Complete");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Vasilis::vasilis);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(BowOfPlenty::BowOfArrow);
        MinecraftForge.EVENT_BUS.addListener(InvisibilityGlove::renderPlayerEventGlove);
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::BowOfArrowsChat);
        MinecraftForge.EVENT_BUS.addListener(BowOfPlenty::ArrowRecharge);

        LOGGER.info("Special Item Registration Complete");

        MinecraftForge.EVENT_BUS.addListener(StructureHelpers::canCustomMobSpawn);

        GeckoLib.initialize();
        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);

        //TheAncientWorld.STRUCTURES.register(modEventBus);

        // For events that happen after initialization. This is probably going to be use a lot.
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, StructureHelpers::addDimensionalSpacing);

        // The comments for BiomeLoadingEvent and StructureSpawnListGatherEvent says to do HIGH for additions.
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, StructureHelpers::biomeModification);

        System.out.println("Test_1");

    }




    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(RegistryHandler.UNICORN.get(), UnicornEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.SATYR.get(), SatyrEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.CENTAUR.get(), CentaurEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.NIPSIE.get(), NipsieEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.DIRT_GOLEM.get(), DirtGolemEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.WOOD_GOLEM.get(), WoodGolemEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.ASTRID.get(), AstridEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.DEER.get(), DeerEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.WRAITH.get(), DeerEntity.setCustomAttributes().create());

        });
        MinecraftForge.EVENT_BUS.register(new LootHandler());

        event.enqueueWork(() -> {
            StructureHelpers.setupStructures();
            TAWConfiguredStructures.registerConfiguredStructures();

            // There are very few mods that relies on seeing your structure in the noise settings registry before the world is made.
            // This is best done here in FMLCommonSetupEvent after you created your configuredstructures.
            WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
                Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructures().func_236195_a_();

                // Pre-caution in case a mod makes the structure map immutable like datapacks do.
                if(structureMap instanceof ImmutableMap){
                    Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                    //tempMap.put(RegistryHandler.FABLEHAVEN.get(), DimensionStructuresSettings.field_236191_b_.get(RegistryHandler.FABLEHAVEN.get()));

                    settings.getValue().getStructures().field_236193_d_ = tempMap;
                }
                else {
                    //structureMap.put(RegistryHandler.FABLEHAVEN.get(), DimensionStructuresSettings.field_236191_b_.get(RegistryHandler.FABLEHAVEN.get()));
                }
            });
        });
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
    }
    public static final ItemGroup TAB = new ItemGroup("theancientworld_Tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.ADAMANT_PLATE.get());
        }
    };



}

/** helpful links
 * Basic Loot Table - https://pastebin.com/zuCFTsLZ
 * Multi Drop Loot Table - https://pastebin.com/32aJTm59
 * Fortune Addon - https://pastebin.com/nCF8k1iH
 * Silk Touch Addon - https://pastebin.com/FgaZwydW
 * Shaped Crafting - https://pastebin.com/S0ejwc9P
 * Shapeless Crafting - https://pastebin.com/ZicpFgK5
 * Smelting Recipe - https://pastebin.com/GnbxvVMF
 * Tool Json - https://pastebin.com/eAWf7iiD
 * Event Tips - https://cdn.discordapp.com/attachments/665281306426474506/665605979798372392/eventhandler.png
 *
 * Loot Stuff
 * https://github.com/MinecraftForge/MinecraftForge/blob/1.16.x/src/test/java/net/minecraftforge/debug/gameplay/loot/GlobalLootModifiersTest.java#L276
 *
 * How to make a tile entity
 * Make a TileEntity Class for the Block, add things for functionality
 * With Giu
 * Make a Container for the class
 * Make a Screen For the class
 * Register the screen and container
 * Make gui texture
 */
