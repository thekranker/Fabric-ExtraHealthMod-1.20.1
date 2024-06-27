package net.bananaman532.extrahealthmod;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class PlayerDeathListener {

    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("12345678-1234-1234-1234-123456789012");
    private static final String HEALTH_MODIFIER_NAME = "Health Reduction on Death";


    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) -> {
            if (!alive) {
                reducePlayerHealth(newPlayer);
            }
        }));
    }


    private static void reducePlayerHealth(ServerPlayerEntity player) {
        var healthAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (healthAttribute != null); {
            healthAttribute.removeModifier(HEALTH_MODIFIER_UUID);
            healthAttribute.addPersistentModifier(new EntityAttributeModifier(
                    HEALTH_MODIFIER_UUID,
                    HEALTH_MODIFIER_NAME,
                    -2.0, // Decrease by 1 heart (2 health points)
                    EntityAttributeModifier.Operation.ADDITION
            ));
            player.setHealth(player.getHealth() - 2.0F); // Apply the health reduction
        }
    }
}
