package net.bananaman532.extrahealthmod.item.custom;

import net.bananaman532.extrahealthmod.ExtraHealthMod;
import net.bananaman532.extrahealthmod.server.DeathCounterState;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Objects;



public class HeartOfALostSoulItem extends Item {

    // Calls the parent 'Item' constructor with the given 'settings'
    public HeartOfALostSoulItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) { // Overridden from the 'Item' class and is called when the player uses the item


        // ITEM
        ItemStack itemStack = user.getStackInHand(hand); // gets the item stack (the item & its quantity) that the player is holding in the specified hand



        // WHEN USING ITEM
        if (user instanceof ServerPlayerEntity serverPlayer) { // checks if the player using the item is a 'ServerPlayerEntity', meaning the code is running on the server side

            if (!world.isClient) { // ensures code is executed only on server side and not the client side
                EntityAttributeInstance healthAttribute = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH); // gets the player's current max health attribute


                if (healthAttribute != null) { // ensures the player's health attribute is not null

                    // Death Counter
                    DeathCounterState state = ExtraHealthMod.getDeathCounterState(Objects.requireNonNull(serverPlayer.getServer())); // gets the 'DeathCounterState' object
                    int currentDeathCount = state.getDeathCount(user.getUuid()); // retrieves player's death count using the player's UUID

                    // Removes a death from the death count while deaths are greater than -15. [Caps Max Health]
                    if (currentDeathCount > (-15)) {
                        state.decrementDeathCount(user.getUuid());
                    }

                    if (currentDeathCount > (-15)) {  // ensures max health hasn't yet been reached

                        // Increase max health by 2 (one heart)
                        healthAttribute.setBaseValue(healthAttribute.getBaseValue() + 2.0);

                        // Play totem animation and effects
                        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                        // Spawn totem of undying particles
                        if (world instanceof ServerWorld serverWorld) {
                            double angleIncrement = Math.PI / 16;
                            for (int particleCount = 0; particleCount < 256; particleCount++) {
                                double angle = particleCount * angleIncrement;
                                double xOffset = Math.cos(angle) * 1.5;
                                double zOffset = Math.sin(angle) * 1.5;
                                double yOffset = 1 + (world.random.nextFloat() * 4.0);
                                serverWorld.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, user.getX() + xOffset, user.getY() + yOffset, user.getZ() + zOffset, 1, -xOffset * 0.1, 0, -zOffset * 0.1, 0.0);
                            }
                        }

                        // Consume the item
                        itemStack.decrement(1);

                        // Completion chat message
                        serverPlayer.sendMessage(Text.of("A lost soul has bestowed you with health, restoring one heart."));

                        // Return
                        return TypedActionResult.success(itemStack, world.isClient()); // returns a successful action result, indicating the item was used successfully.

                    } else {
                        // Failure chat message
                        serverPlayer.sendMessage(Text.of("The Heart of a Lost Soul isn't potent enough to further increase your health."));
                    }
                }
            }
        }

        return TypedActionResult.pass(itemStack); // If the item cannot be used > returns a pass action result, indicating that the item usage did not succeed
    }
}

