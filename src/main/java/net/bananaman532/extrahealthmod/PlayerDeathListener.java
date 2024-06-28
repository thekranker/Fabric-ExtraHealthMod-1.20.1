package net.bananaman532.extrahealthmod;

import net.bananaman532.extrahealthmod.server.DeathCounterState;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class PlayerDeathListener {

    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("12345678-1234-1234-1234-123456789012");
    private static final String HEALTH_MODIFIER_NAME = "Health Reduction on Death";


    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) -> {
            if (!alive) {
                incrementDeathCount(newPlayer);
                reducePlayerHealth(newPlayer);
            }
        }));
    }


    private static void reducePlayerHealth(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server == null) return;

        DeathCounterState state = ExtraHealthMod.getDeathCounterState(server);
        int deathCount = state.getDeathCount(player.getUuid());

        var healthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (healthAttribute != null) {
            healthAttribute.removeModifier(HEALTH_MODIFIER_UUID);

            double healthReduction = deathCount * 2; // Calculates reduction of health based on deaths

            healthAttribute.addPersistentModifier(new EntityAttributeModifier(
                    HEALTH_MODIFIER_UUID,
                    HEALTH_MODIFIER_NAME,
                    -healthReduction, // Decrease by 1 heart (2 health points)
                    EntityAttributeModifier.Operation.ADDITION
            ));

            // Ensure the player's health doesn't go below 1
            double newMaxHealth = Math.max(healthAttribute.getValue(), 1.0);
            player.setHealth((float) Math.min(player.getHealth(), newMaxHealth));
        }
    }

    private static void incrementDeathCount(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server != null) {
            DeathCounterState state = ExtraHealthMod.getDeathCounterState(server);
            state.incrementDeathCount(player.getUuid());
        }
    }
}
