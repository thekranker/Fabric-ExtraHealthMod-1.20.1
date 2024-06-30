package net.bananaman532.extrahealthmod;

import net.bananaman532.extrahealthmod.server.DeathCounterState;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import java.util.UUID;



public class PlayerDeathListener {

    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("12345678-1234-1234-1234-123456789012"); // unique health modifier ID
    private static final String HEALTH_MODIFIER_NAME = "Health Reduction on Death"; // name of the health modifier
    private static final double HEALTH_REDUCTION_PER_DEATH = 2.0; // amount of health reduced per death ( 1 heart = 2 health points)



    public static void register() {

        // Event listener that triggers after a player respawns
        ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) -> {
            if (!alive) {
                incrementDeathCount(newPlayer);
                reducePlayerHealth(newPlayer);
            }
        }));

    }


    private static void reducePlayerHealth(ServerPlayerEntity player) {

        // SERVER
        MinecraftServer server = player.getServer(); // gets the server instance
        if (server == null) return; // exits method if server instance is null

        // DEATH COUNTER
        DeathCounterState state = ExtraHealthMod.getDeathCounterState(server); // retrieves the 'DeathCounterState'
        int deathCount = state.getDeathCount(player.getUuid()); // gets the death count of the player using the player's UUID


        // PLAYER HEALTH
        var healthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH); // gets player's health attribute

        if (healthAttribute != null) {
            healthAttribute.removeModifier(HEALTH_MODIFIER_UUID); // removes any existing health modifiers with same UUID

            double healthReduction = deathCount * HEALTH_REDUCTION_PER_DEATH; // calculates reduction of health based on deaths


            // Adds a new persistent modifier to reduce the player's health
            healthAttribute.addPersistentModifier(new EntityAttributeModifier(
                    HEALTH_MODIFIER_UUID,
                    HEALTH_MODIFIER_NAME,
                    -healthReduction, // decrease by 1 heart (2 health points)
                    EntityAttributeModifier.Operation.ADDITION
            ));


            // Set player to spectator mode after 10 deaths
            if (deathCount >= 10) player.changeGameMode(GameMode.SPECTATOR);


            // Debug
            System.out.println("Total Deaths: " + deathCount);
        }
    }



    private static void incrementDeathCount(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer(); // gets the server instance

        // Increments the player's death count if the server is not null
        if (server != null) {
            DeathCounterState state = ExtraHealthMod.getDeathCounterState(server);
            state.incrementDeathCount(player.getUuid());
        }
    }


}
