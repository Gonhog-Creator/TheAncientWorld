package com.gonhog.theancientworld;

import com.gonhog.theancientworld.entities.centaur.CentaurEntity;
import com.gonhog.theancientworld.entities.dirt_golem.DirtGolemEntity;
import com.gonhog.theancientworld.entities.nipsie.NipsieEntity;
import com.gonhog.theancientworld.entities.satyr.SatyrEntity;
import com.gonhog.theancientworld.entities.unicorn.UnicornEntity;
import com.gonhog.theancientworld.entities.wood_golem.WoodGolemEntity;
import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.items.InvisibilityGlove;
import com.gonhog.theancientworld.tools.BowOfArrows;
import com.gonhog.theancientworld.tools.Vasilis;
import com.gonhog.theancientworld.util.ClientSetup;
import com.gonhog.theancientworld.util.Config;
import com.gonhog.theancientworld.util.LootHandler;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;


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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(BowOfArrows::BowOfArrow);
        MinecraftForge.EVENT_BUS.addListener(InvisibilityGlove::renderPlayerEventGlove);
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::BowOfArrowsChat);
        MinecraftForge.EVENT_BUS.addListener(BowOfArrows::ArrowRecharge);

        LOGGER.info("Special Item Registration Complete");

        GeckoLib.initialize();

        RegistryHandler.init();
        MinecraftForge.EVENT_BUS.register(this);
    }




    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(RegistryHandler.UNICORN.get(), UnicornEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.SATYR.get(), SatyrEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.CENTAUR.get(), CentaurEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.NIPSIE.get(), NipsieEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.DIRT_GOLEM.get(), DirtGolemEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(RegistryHandler.WOOD_GOLEM.get(), WoodGolemEntity.setCustomAttributes().create());

        });
        MinecraftForge.EVENT_BUS.register(new LootHandler());
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
