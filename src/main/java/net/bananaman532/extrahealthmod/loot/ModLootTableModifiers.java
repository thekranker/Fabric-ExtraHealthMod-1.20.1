package net.bananaman532.extrahealthmod.loot;

import net.bananaman532.extrahealthmod.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;



public class ModLootTableModifiers {

    // CONSTANTS
    // Defines a constant ID that uniquely identifies the various loot tables in minecraft.

    // Guaranteed 'Heart of a Lost Soul' drop rate (100%)
    private static final Identifier BURIED_TREASURE_ID = new Identifier("minecraft", "chests/buried_treasure");

    // Decent 'Heart of a Lost Soul' drop rate (35%)
    private static final Identifier BASTION_TREASURE_ID = new Identifier("minecraft", "chests/bastion_treasure");

    // Low 'Heart of a Lost Soul' drop rate (15%)
    private static final Identifier ANCIENT_CITY_ID = new Identifier("minecraft", "chests/ancient_city");
    private static final Identifier WOODLAND_MANSION_ID = new Identifier("minecraft", "chests/woodland_mansion");
    private static final Identifier BASTION_BRIDGE_ID = new Identifier("minecraft", "chests/bastion_bridge");
    private static final Identifier BASTION_HOGLIN_STABLE_ID = new Identifier("minecraft", "chests/bastion_hoglin_stable");
    private static final Identifier BASTION_OTHER_ID = new Identifier("minecraft", "chests/bastion_other");
    private static final Identifier END_CITY_TREASURE_ID = new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier STRONGHOLD_LIBRARY_ID = new Identifier("minecraft", "chests/stronghold_library");

    // Very Low 'Heart of a Lost Soul' drop rate (1%)
    private static final Identifier ZOMBIE_ID = new Identifier("minecraft", "entities/zombie"); // entity loot table
    private static final Identifier PILLAGER_OUTPOST_ID = new Identifier("minecraft", "chests/pillager_outpost");
    private static final Identifier ABANDONED_MINESHAFT_ID = new Identifier("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier IGLOO_CHEST_ID = new Identifier("minecraft", "chests/igloo_chest");
    private static final Identifier JUNGLE_TEMPLE_ID = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier RUINED_PORTAL_ID = new Identifier("minecraft", "chests/ruined_portal");
    private static final Identifier SHIPWRECK_TREASURE_ID = new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier SHIPWRECK_MAP_ID = new Identifier("minecraft", "chests/shipwreck_map");
    private static final Identifier SHIPWRECK_SUPPLY_ID = new Identifier("minecraft", "chests/shipwreck_supply");
    private static final Identifier STRONGHOLD_CORRIDOR_ID = new Identifier("minecraft", "chests/stronghold_corridor");
    private static final Identifier STRONGHOLD_CROSSING_ID = new Identifier("minecraft", "chests/stronghold_crossing");
    private static final Identifier UNDERWATER_RUIN_BIG_ID = new Identifier("minecraft", "chests/underwater_ruin_big");
    private static final Identifier UNDERWATER_RUIN_SMALL_ID = new Identifier("minecraft", "chests/underwater_ruin_small");
    private static final Identifier DESERT_PYRAMID_ID = new Identifier("minecraft", "chests/desert_pyramid");





    public static void modifyLootTables() {


        // Registers a lambda function to the MODIFY event of loot tables - called whenever a loot table is being modified
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {


            // HOW LOOT TABLES ARE MODIFIED
            // First check if the loot table being modified is the desired loot table using a conditional statement. If a match, then...
            // Create a new builder for a loot pool.
            // Set the number of rolls for the loot pool. (Each roll is an attempt to drop items once)
            // Add a condition that determines how often the modified loot will drop
            // Add an item entry to the loot pool, in this case, specifying the item 'HEART_OF_A_LOST_SOUL' from the 'ModItems' class.
            // Apply a loot function to set the count of the item found in the loot table.
            // Lastly, add the constructed loot pool to the loot table builder.



            // GUARANTEED DROP RATE LOOT TABLES
            if(BURIED_TREASURE_ID.equals(id)) {

                // Guarantee First Heart
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

                // 50% Chance of Second Heart
                LootPool.Builder poolBuilder1 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder1.build());

            }


            // DECENT DROP RATE LOOT TABLES
            if (BASTION_TREASURE_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.35f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

            }


            // LOW DROP RATE LOOT TABLES
            if (ANCIENT_CITY_ID.equals(id) || WOODLAND_MANSION_ID.equals(id) || BASTION_BRIDGE_ID.equals(id) || BASTION_HOGLIN_STABLE_ID.equals(id) || BASTION_OTHER_ID.equals(id) || END_CITY_TREASURE_ID.equals(id) || STRONGHOLD_LIBRARY_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

            }


            // VERY LOW DROP RATE LOOT TABLES
            if (ZOMBIE_ID.equals(id) || PILLAGER_OUTPOST_ID.equals(id) || ABANDONED_MINESHAFT_ID.equals(id) || IGLOO_CHEST_ID.equals(id) || JUNGLE_TEMPLE_ID.equals(id) || RUINED_PORTAL_ID.equals(id) || SHIPWRECK_TREASURE_ID.equals(id)|| SHIPWRECK_MAP_ID.equals(id)|| SHIPWRECK_SUPPLY_ID.equals(id) || STRONGHOLD_CORRIDOR_ID.equals(id) || STRONGHOLD_CROSSING_ID.equals(id) || UNDERWATER_RUIN_BIG_ID.equals(id) || UNDERWATER_RUIN_SMALL_ID.equals(id) || DESERT_PYRAMID_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.01f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

            }


        });
    }

}
