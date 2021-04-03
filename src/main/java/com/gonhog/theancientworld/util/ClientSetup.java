package com.gonhog.theancientworld.util;


import com.gonhog.theancientworld.TheAncientWorld;
import com.gonhog.theancientworld.entities.astrid.AstridRender;
import com.gonhog.theancientworld.entities.centaur.CentaurRender;
import com.gonhog.theancientworld.entities.deer.DeerRender;
import com.gonhog.theancientworld.entities.dirt_golem.DirtGolemRender;
import com.gonhog.theancientworld.entities.nipsie.NipsieRender;
import com.gonhog.theancientworld.entities.quick_hit_arrow.QuickHitArrowRender;
import com.gonhog.theancientworld.entities.satyr.SatyrRender;
import com.gonhog.theancientworld.entities.unicorn.UnicornRender;
import com.gonhog.theancientworld.entities.wood_golem.WoodGolemRender;
import com.gonhog.theancientworld.entities.wraith.WraithRender;
import com.gonhog.theancientworld.events.ClientEventHandler;
import com.gonhog.theancientworld.screens.TestBlockScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = TheAncientWorld.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(RegistryHandler.TEST_BLOCK_CONTAINER.get(), TestBlockScreen::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.QUICK_HIT_ARROW.get(), QuickHitArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.UNICORN.get(), UnicornRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.SATYR.get(), SatyrRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.CENTAUR.get(), CentaurRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.NIPSIE.get(), NipsieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.DIRT_GOLEM.get(), DirtGolemRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.WOOD_GOLEM.get(), WoodGolemRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.ASTRID.get(), AstridRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.DEER.get(), DeerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.WRAITH.get(), WraithRender::new);

        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::ItemProperties);
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::renderPlayerEventGlove);
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::BowOfArrowsChat);

    }
}



