package net.bananaman532.extrahealthmod;

import net.bananaman532.extrahealthmod.item.ModItems;
import net.bananaman532.extrahealthmod.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraHealthMod implements ModInitializer {
	public static final String MOD_ID = "extrahealthmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();

		ModLootTableModifiers.modifyLootTables();
	}
}