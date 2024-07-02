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

    private static final Identifier BURIED_TREASURE_ID = // defines a constant 'BURIED_TREASURE_ID' that uniquely identifies the buried treasure loot table in minecraft
            new Identifier("minecraft", "chests/buried_treasure");

    private static final Identifier ZOMBIE_ID = // defines a constant 'ZOMBIE_ID' that uniquely identifies the zombie loot table in minecraft
            new Identifier("minecraft", "entities/zombie");



    public static void modifyLootTables() {


        // Registers a lambda function to the MODIFY event of loot tables - called whenever a loot table is being modified
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {


            // BURIED TREASURE LOOT TABLE
            if(BURIED_TREASURE_ID.equals(id)) { // checks if the loot table being modified is the buried treasure loot table

                // Guarantee First Heart
                LootPool.Builder poolBuilder = LootPool.builder() // creates a new builder for a loot pool

                        .rolls(ConstantLootNumberProvider.create(1)) // sets the number of rolls for this loot pool to 1, meaning it will attempt to drop items once
                        .conditionally(RandomChanceLootCondition.builder(1f)) // a condition that the item will drop 100% of the time
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL)) // adds an item entry to the loot pool, specifying the item 'HEART_OF_A_LOST_SOUL' from 'ModItems'
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); // applies a loot function to set the count of 'HEART_OF_A_LOST_SOUL' item to 1


                tableBuilder.pool(poolBuilder.build()); // adds the constructed loot pool to the loot table builder

                // 50% Second Heart
                LootPool.Builder poolBuilder1 = LootPool.builder() // creates a new builder for a loot pool

                        .rolls(ConstantLootNumberProvider.create(1)) // sets the number of rolls for this loot pool to 1, meaning it will attempt to drop items once
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) // a condition that the item will drop 50% of the time
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL)) // adds an item entry to the loot pool, specifying the item 'HEART_OF_A_LOST_SOUL' from 'ModItems'
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); // applies a loot function to set the count of 'HEART_OF_A_LOST_SOUL' item to 1


                tableBuilder.pool(poolBuilder1.build()); // adds the constructed loot pool to the loot table builder
            }


            // ZOMBIE LOOT TABLE
            if (ZOMBIE_ID.equals(id)) {

                LootPool.Builder poolBuilder = LootPool.builder() // creates a new builder for a loot pool

                        .rolls(ConstantLootNumberProvider.create(1)) // sets the number of rolls for this loot pool to 1, meaning it will attempt to drop items once
                        .conditionally(RandomChanceLootCondition.builder(0.01f)) // a condition that the item will drop 1% of the time
                        .with(ItemEntry.builder(ModItems.HEART_OF_A_LOST_SOUL)) // adds an item entry to the loot pool, specifying the item 'HEART_OF_A_LOST_SOUL' from 'ModItems'
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); // applies a loot function to set the count of 'HEART_OF_A_LOST_SOUL' item to 1


                tableBuilder.pool(poolBuilder.build()); // adds the constructed loot pool to the loot table builder

            }



        });
    }


}
