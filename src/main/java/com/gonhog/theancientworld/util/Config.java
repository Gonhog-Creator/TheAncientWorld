package com.gonhog.theancientworld.util;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";
    public static final String SUBCATEGORY_TEST_BLOCK = "test_block";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue TEST_BLOCK_MAXPOWER;
    public static ForgeConfigSpec.IntValue TEST_BLOCK_GENERATE;
    public static ForgeConfigSpec.IntValue TEST_BLOCK_SEND;
    public static ForgeConfigSpec.IntValue TEST_BLOCK_TICKS;

    public static ForgeConfigSpec.DoubleValue ROTATION_SPEED;


    static {

        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        ROTATION_SPEED = CLIENT_BUILDER.comment("Rotation speed of the magic block").defineInRange("rotationSpeed", 100.0, 0.0, 1000000.0);
        CLIENT_BUILDER.pop();

        SERVER_BUILDER.comment("Power settings").push(CATEGORY_POWER);
        setupTestBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_BUILDER.pop();


        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupTestBlockConfig(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("TestBlock settings").push(SUBCATEGORY_TEST_BLOCK);

        TEST_BLOCK_MAXPOWER = SERVER_BUILDER.comment("Maximum power for the Test Block generator")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        TEST_BLOCK_GENERATE = SERVER_BUILDER.comment("Power generation per adamant plate")
                .defineInRange("generate", 1000, 0, Integer.MAX_VALUE);
        TEST_BLOCK_SEND = SERVER_BUILDER.comment("Power generation to send per tick")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);
        TEST_BLOCK_TICKS = SERVER_BUILDER.comment("Ticks per plate")
                .defineInRange("ticks", 20, 0, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }


}

