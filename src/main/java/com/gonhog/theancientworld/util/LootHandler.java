package com.gonhog.theancientworld.util;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheAncientWorld.MOD_ID)
public class LootHandler {

    @SubscribeEvent
    public static void lootLoad(LootTableLoadEvent evt) {
        String prefix = "minecraft:chests/";
        String name = evt.getName().toString();

        if (name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            switch (file) {
                case "stronghold_library":
                case "shipwreck_supply":
                case "shipwreck_map":
                case "abandoned_mineshaft":
                case "desert_pyramid":
                case "spawn_bonus_chest":
                    evt.getTable().addPool(getInjectPool(file));
                    break;
                default:
                    break;
            }
        }
    }

    private static net.minecraft.loot.LootPool getInjectPool(String entryName) {
        return LootPool.builder().addEntry(getInjectEntry(entryName, 1)).bonusRolls(0, 1).name("TAW_inject").build();
    }

    @SuppressWarnings("rawtypes")
    private static LootEntry.Builder getInjectEntry(String name, int weight) {
        ResourceLocation table = new ResourceLocation(TheAncientWorld.MOD_ID, "chests/" + name);
        return TableLootEntry.builder(table).weight(weight);
    }
}
