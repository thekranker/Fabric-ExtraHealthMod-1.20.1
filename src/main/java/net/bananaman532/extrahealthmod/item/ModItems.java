package net.bananaman532.extrahealthmod.item;

import net.bananaman532.extrahealthmod.ExtraHealthMod;
import net.bananaman532.extrahealthmod.item.custom.HeartOfALostSoulItem;
import net.bananaman532.extrahealthmod.item.custom.SoulboundCharmItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;



public class ModItems {

    // Holds an instance of the 'HeartOfALostSoul' & 'Soulbound Charm' items.
    // Each item is registered using the 'registerItem' method and are given their appropriate names.
    // Initialized with 'FabricItemSettings' that specify a maximum stack count of 1.
    public static final Item HEART_OF_A_LOST_SOUL = registerItem("heart_of_a_lost_soul", new HeartOfALostSoulItem(new FabricItemSettings().maxCount(1)));
    public static final Item SOULBOUND_CHARM = registerItem("soulbound_charm", new SoulboundCharmItem(new FabricItemSettings().maxCount(1)));


    // Includes the custom item in an item group, making it appear in the creative inventory tab
    private static void addItemsToItemGroup(FabricItemGroupEntries entries) {
        entries.add(HEART_OF_A_LOST_SOUL);
        entries.add(SOULBOUND_CHARM);
    }

    // Registers an item in the Minecraft item registry - returns the registered item
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ExtraHealthMod.MOD_ID, name), item);
    }

    // Registers all mod items and adds them to their appropriate mod groups
    // Logs an info message indicating that mod items are being registered
    // Uses 'ItemGroupEvents.modifyEntriesEvent' to register the 'addItemsToItemGroup' method - ensures 'HEART' item is added to the 'ItemGroups.INGREDIENTS' item group in the creative inventory.
    public static void registerModItems() {
        ExtraHealthMod.LOGGER.info("Registering Mod Items for " + ExtraHealthMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToItemGroup);
    }

}
