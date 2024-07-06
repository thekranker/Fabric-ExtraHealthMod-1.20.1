package net.bananaman532.extrahealthmod;


import net.bananaman532.extrahealthmod.item.custom.SoulboundCharmItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;



public class SoulboundCharmHandler {


    public static void register() {
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {
            if (entity instanceof ServerPlayerEntity player) {
                if (SoulboundCharmItem.isCharmInOffHand(player)) {
                    handleSoulboundCharmActivation(player, damageSource);
                    return false; // prevent death
                }
            }
            return true; // allow death
        });
    }



    private static void handleSoulboundCharmActivation(ServerPlayerEntity player, DamageSource damageSource) {

        // Get spawn position
        BlockPos spawnPos = player.getSpawnPointPosition();
        ServerWorld spawnWorld = player.getServer().getWorld(player.getSpawnPointDimension());

        if (spawnPos == null || spawnWorld == null) {
            // If no spawn point is set, use world spawn
            spawnWorld = player.getServer().getOverworld();
            spawnPos = spawnWorld.getSpawnPos();
        }

        // Teleport player to spawn
        player.teleport(spawnWorld,
                spawnPos.getX() + 0.5,
                spawnPos.getY() + 0.1,
                spawnPos.getZ() + 0.5,
                player.getYaw(), player.getPitch());

        // Heal the player
        player.setHealth(player.getMaxHealth());

        // Add blindness effect for 5 seconds (100 ticks)
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0));

        // Consume the charm
        SoulboundCharmItem.consumeCharm(player);

        // Chat message
        player.sendMessage(net.minecraft.text.Text.literal("§4The souls have rescued you from death's grip, for now..."), false);

    }



}
