package net.bananaman532.extrahealthmod.item;


import net.bananaman532.extrahealthmod.ExtraHealthMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class ModItemGroups {


    // Declares a public static final field of type 'ItemGroup'.
    // Registers the item group in the 'ITEM_GROUP' registry.
    // Uses a new 'Identifier' with the mod's ID and the name "extrahealthmod_item_group".
    // Uses the 'FabricItemGroup' builder to create the item group & sets the display name to a translateable text entry with the key "itemgroup.extrahealthmod".
    // Sets the icon of the item group to the 'Heart of a Lost Soul' texture.
    // Adds the 'Heart of a Lost Soul' item and the 'Soulbound Charm' item to the item group.
    // Builds the item group.
    public static final ItemGroup EXTRAHEALTHMOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ExtraHealthMod.MOD_ID, "extrahealthmod_item_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.extrahealthmod"))
                    .icon(() -> new ItemStack(ModItems.HEART_OF_A_LOST_SOUL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.HEART_OF_A_LOST_SOUL);
                        entries.add(ModItems.SOULBOUND_CHARM);

                    }).build());



    // Logs an info message indicating that the item groups for the mod are being registered
    public static void registerItemGroups () {
        ExtraHealthMod.LOGGER.info("Registering Item Groups for " + ExtraHealthMod.MOD_ID);
    }


}
