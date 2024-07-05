package net.bananaman532.extrahealthmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class SoulboundCharmItem extends Item {

    // Calls the parent 'Item' constructor with the given 'settings'
    public SoulboundCharmItem(Settings settings) {
        super(settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


        if (!world.isClient) { // ensures code is executed only on server side and not the client side

            if (hand == Hand.OFF_HAND) { // ensures the 'Soulbound Charm' is located in the player's off-hand

                // Teleport the player to their spawn location
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
                BlockPos spawnPos = serverPlayer.getSpawnPointPosition();
                if (spawnPos != null) {
                    serverPlayer.teleport(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                    // Consume the item
                    user.getStackInHand(hand).decrement(1);
                }
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }


    public void onDeath(LivingEntity entity) {
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack offHandStack = player.getOffHandStack();
            if (offHandStack.getItem() instanceof SoulboundCharmItem) {
                BlockPos spawnPos = player.getSpawnPointPosition();
                if (spawnPos != null) {
                    player.teleport(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                    offHandStack.decrement(1);
                    player.setHealth(10.0F); // Set player's health to 1 to prevent death
                }
            }
        }
    }




}
