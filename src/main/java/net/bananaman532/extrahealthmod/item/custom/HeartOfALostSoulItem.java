package net.bananaman532.extrahealthmod.item.custom;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HeartOfALostSoulItem extends Item {

    public HeartOfALostSoulItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);


        if (!world.isClient) {
            System.out.println("use method called on server side");

            // Get the player's current max health attribute
            EntityAttributeInstance healthAttribute = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

            // Check if the health attribute is not null and increase max health
            if (healthAttribute != null) {
                System.out.println("health attribute found. current base value " + healthAttribute.getBaseValue());

                // Increase max health by 2 (one heart)
                healthAttribute.setBaseValue(healthAttribute.getBaseValue() + 2.0);
                System.out.println("new health value: " + healthAttribute.getBaseValue());


                // Consume the item
                itemStack.decrement(1);

                return TypedActionResult.success(itemStack, world.isClient());
            } else {
                System.out.println("Health attribute is null");
            }
        } else {
            System.out.println("Use method called on client side");
        }

        return TypedActionResult.pass(itemStack);
    }
}

