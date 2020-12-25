package com.gonhog.theancientworld;

import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.util.RegistryHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("theancientworld")
public class TheAncientWorld
{

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "theancientworld";

    public TheAncientWorld() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::renderPlayerEventGlove);
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::makePlayerUntargetable);

        RegistryHandler.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
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
 */
