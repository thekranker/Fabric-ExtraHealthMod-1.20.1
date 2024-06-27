package net.bananaman532.extrahealthmod.item;

import net.bananaman532.extrahealthmod.ExtraHealthMod;
import net.bananaman532.extrahealthmod.item.custom.HeartOfALostSoulItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item HEART = registerItem("heart", new HeartOfALostSoulItem(new FabricItemSettings().maxCount(1)));

    private static void addItemsToItemGroup(FabricItemGroupEntries entries) {
        entries.add(HEART);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ExtraHealthMod.MOD_ID, name), item);

    }

    public static void registerModItems() {
        ExtraHealthMod.LOGGER.info("Registering Mod Items for " + ExtraHealthMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToItemGroup);
    }
}
