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

    // More Than Guaranteed 'Heart of a Lost Soul' drop rate (100% - 200%)
    private static final Identifier BURIED_TREASURE_ID = new Identifier("minecraft", "chests/buried_treasure");

    // Guaranteed 'Heart of a Lost Soul' drop rate (100%)
    private static final Identifier BASTION_TREASURE_ID = new Identifier("minecraft", "chests/bastion_treasure");
    private static final Identifier ENDER_DRAGON_ID = new Identifier("minecraft", "entities/ender_dragon");
    private static final Identifier WARDEN_ID = new Identifier("minecraft", "entities/warden");

    // Decent 'Heart of a Lost Soul' drop rate (75%)
    private static final Identifier ANCIENT_CITY_ID = new Identifier("minecraft", "chests/ancient_city");
    private static final Identifier WOODLAND_MANSION_ID = new Identifier("minecraft", "chests/woodland_mansion");
    private static final Identifier BASTION_BRIDGE_ID = new Identifier("minecraft", "chests/bastion_bridge");
    private static final Identifier BASTION_HOGLIN_STABLE_ID = new Identifier("minecraft", "chests/bastion_hoglin_stable");
    private static final Identifier BASTION_OTHER_ID = new Identifier("minecraft", "chests/bastion_other");
    private static final Identifier END_CITY_TREASURE_ID = new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier STRONGHOLD_LIBRARY_ID = new Identifier("minecraft", "chests/stronghold_library");
    private static final Identifier EVOKER_ID = new Identifier("minecraft", "entities/evoker");
    private static final Identifier WITHER_ID = new Identifier("minecraft", "entities/wither");

    // Low 'Heart of a Lost Soul' drop rate (5%)
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

    // Very Low 'Heart of a Lost Soul' drop rate (3%)
    private static final Identifier ZOMBIE_ID = new Identifier("minecraft", "entities/zombie");
    private static final Identifier CREEPER_ID = new Identifier("minecraft", "entities/creeper");
    private static final Identifier SPIDER_ID = new Identifier("minecraft", "entities/spider");
    private static final Identifier SKELETON_ID = new Identifier("minecraft", "entities/skeleton");
    private static final Identifier BLAZE_ID = new Identifier("minecraft", "entities/blaze");
    private static final Identifier CAVE_SPIDER_ID = new Identifier("minecraft", "entities/cave_spider");
    private static final Identifier ENDERMAN_ID = new Identifier("minecraft", "entities/enderman");
    private static final Identifier DROWNED_ID = new Identifier("minecraft", "entities/drowned");
    private static final Identifier ELDER_GUARDIAN_ID = new Identifier("minecraft", "entities/elder_guardian");
    private static final Identifier GUARDIAN_ID = new Identifier("minecraft", "entities/guardian");
    private static final Identifier HOGLIN_ID = new Identifier("minecraft", "entities/hoglin");
    private static final Identifier HUSK_ID = new Identifier("minecraft", "entities/husk");
    private static final Identifier ILLUSIONER_ID = new Identifier("minecraft", "entities/illusioner");
    private static final Identifier MAGMA_CUBE_ID = new Identifier("minecraft", "entities/magma_cube");
    private static final Identifier PHANTOM_ID = new Identifier("minecraft", "entities/phantom");
    private static final Identifier PIGLIN_ID = new Identifier("minecraft", "entities/piglin");
    private static final Identifier PIGLIN_BRUTE_ID = new Identifier("minecraft", "entities/piglin_brute");
    private static final Identifier PILLAGER_ID = new Identifier("minecraft", "entities/pillager");
    private static final Identifier VINDICATOR_ID = new Identifier("minecraft", "entities/vindicator");
    private static final Identifier WITCH_ID = new Identifier("minecraft", "entities/witch");
    private static final Identifier WITHER_SKELETON_ID = new Identifier("minecraft", "entities/wither_skeleton");
    private static final Identifier ZOGLIN_ID = new Identifier("minecraft", "entities/zoglin");
    private static final Identifier ZOMBIE_VILLAGER_ID = new Identifier("minecraft", "entities/zombie_villager");
    private static final Identifier ZOMBIFIED_PIGLIN_ID = new Identifier("minecraft", "entities/zombified_piglin");

    // Extremely Low 'Heart of a Lost Soul' drop rate (1%)
    private static final Identifier VILLAGE_ARMORER_ID = new Identifier("minecraft", "chests/village/village_armorer");
    private static final Identifier VILLAGE_BUTCHER_ID = new Identifier("minecraft", "chests/village/village_butcher");
    private static final Identifier VILLAGE_CARTOGRAPHER_ID = new Identifier("minecraft", "chests/village/village_cartographer");
    private static final Identifier VILLAGE_DESERT_HOUSE_ID = new Identifier("minecraft", "chests/village/village_desert_house");
    private static final Identifier VILLAGE_FISHER_ID = new Identifier("minecraft", "chests/village/village_fisher");
    private static final Identifier VILLAGE_FLETCHER_ID = new Identifier("minecraft", "chests/village/village_fletcher");
    private static final Identifier VILLAGE_MASON_ID = new Identifier("minecraft", "chests/village/village_mason");
    private static final Identifier VILLAGE_PLAINS_HOUSE_ID = new Identifier("minecraft", "chests/village/village_plains_house");
    private static final Identifier VILLAGE_SAVANNA_HOUSE_ID = new Identifier("minecraft", "chests/village/village_savanna_house");
    private static final Identifier VILLAGE_SHEPHERD_ID = new Identifier("minecraft", "chests/village/village_shepherd");
    private static final Identifier VILLAGE_SNOWY_HOUSE_ID = new Identifier("minecraft", "chests/village/village_snowy_house");
    private static final Identifier VILLAGE_TAIGA_HOUSE_ID = new Identifier("minecraft", "chests/village/village_taiga_house");
    private static final Identifier VILLAGE_TEMPLE_ID = new Identifier("minecraft", "chests/village/village_temple");
    private static final Identifier VILLAGE_TOOLSMITH_ID = new Identifier("minecraft", "chests/village/village_toolsmith");
    private static final Identifier VILLAGE_WEAPONSMITH_ID = new Identifier("minecraft", "chests/village/village_weaponsmith");






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



            // MORE THAN GUARANTEED DROP RATE LOOT TABLES
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


            // GUARANTEED DROP RATE LOOT TABLES
            if (BASTION_TREASURE_ID.equals(id) || ENDER_DRAGON_ID.equals(id) || WARDEN_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

            }


            // DECENT DROP RATE LOOT TABLES
            if (ANCIENT_CITY_ID.equals(id) || WOODLAND_MANSION_ID.equals(id) || BASTION_BRIDGE_ID.equals(id) || BASTION_HOGLIN_STABLE_ID.equals(id) || BASTION_OTHER_ID.equals(id) || END_CITY_TREASURE_ID.equals(id) || STRONGHOLD_LIBRARY_ID.equals(id) || EVOKER_ID.equals(id) || WITHER_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.75f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

            }


            // LOW DROP RATE LOOT TABLES
            if (PILLAGER_OUTPOST_ID.equals(id) || ABANDONED_MINESHAFT_ID.equals(id) || IGLOO_CHEST_ID.equals(id) || JUNGLE_TEMPLE_ID.equals(id) || RUINED_PORTAL_ID.equals(id) || SHIPWRECK_TREASURE_ID.equals(id)|| SHIPWRECK_MAP_ID.equals(id)|| SHIPWRECK_SUPPLY_ID.equals(id) || STRONGHOLD_CORRIDOR_ID.equals(id) || STRONGHOLD_CROSSING_ID.equals(id) || UNDERWATER_RUIN_BIG_ID.equals(id) || UNDERWATER_RUIN_SMALL_ID.equals(id) || DESERT_PYRAMID_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

            }


            // VERY LOW DROP RATE LOOT TABLES
            if (ZOMBIE_ID.equals(id) || CREEPER_ID.equals(id) || SPIDER_ID.equals(id) || SKELETON_ID.equals(id) || BLAZE_ID.equals(id) || CAVE_SPIDER_ID.equals(id) || ENDERMAN_ID.equals(id) || DROWNED_ID.equals(id) || ELDER_GUARDIAN_ID.equals(id) || GUARDIAN_ID.equals(id) || HOGLIN_ID.equals(id) || HUSK_ID.equals(id) || ILLUSIONER_ID.equals(id) || MAGMA_CUBE_ID.equals(id) || PHANTOM_ID.equals(id) || PIGLIN_ID.equals(id) || PIGLIN_BRUTE_ID.equals(id) || PILLAGER_ID.equals(id) || VINDICATOR_ID.equals(id) || WITCH_ID.equals(id) || WITHER_SKELETON_ID.equals(id) || ZOGLIN_ID.equals(id) || ZOMBIE_VILLAGER_ID.equals(id) || ZOMBIFIED_PIGLIN_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.03f))
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());

            }


            // EXTREMELY LOW DROP RATE LOOT TABLES
            if (VILLAGE_ARMORER_ID.equals(id) || VILLAGE_BUTCHER_ID.equals(id) || VILLAGE_CARTOGRAPHER_ID.equals(id) || VILLAGE_DESERT_HOUSE_ID.equals(id) || VILLAGE_FISHER_ID.equals(id) || VILLAGE_FLETCHER_ID.equals(id) || VILLAGE_MASON_ID.equals(id) || VILLAGE_PLAINS_HOUSE_ID.equals(id) || VILLAGE_SAVANNA_HOUSE_ID.equals(id) || VILLAGE_SHEPHERD_ID.equals(id) || VILLAGE_SNOWY_HOUSE_ID.equals(id) || VILLAGE_TAIGA_HOUSE_ID.equals(id) || VILLAGE_TEMPLE_ID.equals(id) || VILLAGE_TOOLSMITH_ID.equals(id) || VILLAGE_WEAPONSMITH_ID.equals(id)) {

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
