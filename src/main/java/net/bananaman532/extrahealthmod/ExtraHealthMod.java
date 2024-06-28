package net.bananaman532.extrahealthmod;

import net.bananaman532.extrahealthmod.item.ModItems;
import net.bananaman532.extrahealthmod.server.DeathCounterState;
import net.bananaman532.extrahealthmod.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentStateManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraHealthMod implements ModInitializer {
	public static final String MOD_ID = "extrahealthmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();

		ModLootTableModifiers.modifyLootTables();


		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			PersistentStateManager persistentStateManager = server.getOverworld().getPersistentStateManager();
			persistentStateManager.getOrCreate(
					DeathCounterState::createFromNbt,
					DeathCounterState::new,
					MOD_ID + "_death_counter"
			);
			PlayerDeathListener.register();
		});
	}

	public static DeathCounterState getDeathCounterState(MinecraftServer server) {
		PersistentStateManager persistentStateManager = server.getOverworld().getPersistentStateManager();
		return persistentStateManager.getOrCreate(
				DeathCounterState::createFromNbt,
				DeathCounterState::new,
				MOD_ID + "_death_counter"
		);
	}
}