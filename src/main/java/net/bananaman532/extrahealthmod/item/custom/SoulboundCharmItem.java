package net.bananaman532.extrahealthmod.item.custom;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class SoulboundCharmItem extends Item {

    // Calls the parent 'Item' constructor with the given 'settings'
    public SoulboundCharmItem(Settings settings) {
        super(settings);
    }


    // Checks if the 'Soulbound Charm' is either in the player's main-hand or off-hand
    public static boolean isCharmInOffHand(PlayerEntity player) {

        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();

        return (mainHandStack.getItem() instanceof SoulboundCharmItem) || (offHandStack.getItem() instanceof SoulboundCharmItem);

    }



    // Consumes the 'Soulbound Charm' from either hand
    public static void consumeCharm(PlayerEntity player) {

        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();

        if (mainHandStack.getItem() instanceof SoulboundCharmItem) {
            mainHandStack.decrement(1);
        } else if (offHandStack.getItem() instanceof SoulboundCharmItem) {
            offHandStack.decrement(1);
        }

    }


}
