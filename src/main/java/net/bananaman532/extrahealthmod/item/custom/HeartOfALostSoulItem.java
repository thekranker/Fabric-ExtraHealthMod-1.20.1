package net.bananaman532.extrahealthmod.item.custom;

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



public class HeartOfALostSoulItem extends Item {

    public HeartOfALostSoulItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        if (user instanceof ServerPlayerEntity serverPlayer) {

            // Checks to see that the method is executed on the server side
            if (!world.isClient) {

                // Get the player's current max health attribute
                EntityAttributeInstance healthAttribute = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

                // Check if the health attribute is not null and increase max health
                if (healthAttribute != null) {

                    // Check if max health has been reached or not
                    if (healthAttribute.getBaseValue() < 29) {

                        // Increase max health by 2 (one heart)
                        healthAttribute.setBaseValue(healthAttribute.getBaseValue() + 2.0);


                        // Play totem animation and effects
                        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                        // Spawn Totem of Undying particles
                        if (world instanceof ServerWorld serverWorld) {
                            double angleIncrement = Math.PI / 16;
                            for (int i = 0; i < 256; i++) {
                                double angle = i * angleIncrement;
                                double xOffset = Math.cos(angle) * 1.5;
                                double zOffset = Math.sin(angle) * 1.5;
                                double yOffset = 1 + (world.random.nextFloat() * 4.0);
                                serverWorld.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, user.getX() + xOffset, user.getY() + yOffset, user.getZ() + zOffset, 1, -xOffset * 0.1, 0, -zOffset * 0.1, 0.0);
                            }
                        }


                        // Consume the item
                        itemStack.decrement(1);

                        // Return
                        return TypedActionResult.success(itemStack, world.isClient());

                    } else {
                        serverPlayer.sendMessage(Text.of("The Heart of a Lost Soul isn't potent enough to further increase your health. Max Health Reached [15 Hearts]."));
                    }
                }
            }
        }

        return TypedActionResult.pass(itemStack);
    }
}

