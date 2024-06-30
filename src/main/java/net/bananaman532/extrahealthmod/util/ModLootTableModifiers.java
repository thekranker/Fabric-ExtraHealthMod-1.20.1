package net.bananaman532.extrahealthmod.util;

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



    public static void modifyLootTables() {

        // Registers a lambda function to the MODIFY event of loot tables - called whenever a loot table is being modified
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if(BURIED_TREASURE_ID.equals(id)) { // checks if the loot table being modified is the buried treasure loot table

                LootPool.Builder poolBuilder = LootPool.builder() // creates a new builder for a loot pool

                        .rolls(ConstantLootNumberProvider.create(1)) // sets the number of rolls for this loot pool to 1, meaning it will attempt to drop items once
                        .conditionally(RandomChanceLootCondition.builder(1f)) // a condition that the item will drop 100% of the time
                        .with(ItemEntry.builder(ModItems.HEART)) // adds an item entry to the loot pool, specifying the item 'HEART' from 'ModItems'
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); // applies a loot function to set the count of 'HEART' item to a random value between 1 and 2


                tableBuilder.pool(poolBuilder.build()); // adds the constructed loot pool to the loot table builder
            }
        });
    }


}
