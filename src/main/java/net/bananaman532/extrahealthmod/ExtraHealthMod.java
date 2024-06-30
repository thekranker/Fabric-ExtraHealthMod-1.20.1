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

	public static final String MOD_ID = "extrahealthmod"; // Holds the mod's identifier
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID); // A static logger instance which logs messages with the mod's identifier. (Useful for debugging & tracking the mod's behavior)



	@Override
	public void onInitialize() { // Called when the mod is initialized

		// REGISTERS & MODIFICATIONS
		ModItems.registerModItems(); // registers custom items defined in the 'ModItems' class
		ModLootTableModifiers.modifyLootTables(); // modifies loot tables using the 'ModLootTableModifiers' class
		PlayerDeathListener.register(); // registers a player death listener that tracks when a player respawns

		// SERVER
		// Registers an event listener for when the server starts
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			PersistentStateManager persistentStateManager = server.getOverworld().getPersistentStateManager(); // gets the persistent state manager for the server's overworld
			persistentStateManager.getOrCreate( // ensures that the 'DeathCounterState' is loaded or created if it doesn't exist; uses the mod's ID as part of the state identifier
					DeathCounterState::createFromNbt,
					DeathCounterState::new,
					MOD_ID + "_death_counter"
			);
		});

	}



	public static DeathCounterState getDeathCounterState(MinecraftServer server) {

		PersistentStateManager persistentStateManager = server.getOverworld().getPersistentStateManager(); // gets the persistent state manager for the server's overworld
		return persistentStateManager.getOrCreate( // returns the 'DeathCounterState', ensuring it's loaded or created if it doesn't exist
				DeathCounterState::createFromNbt,
				DeathCounterState::new,
				MOD_ID + "_death_counter"
		);

	}
}