package net.bananaman532.extrahealthmod.server;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



public class DeathCounterState extends PersistentState {

    private final Map<UUID, Integer> deathCounts = new HashMap<>(); // maps a 'UUID' (unique identifier for each player) to an 'Integer' (the death count)


    // Calls the constructor of the superclass ('PersistentState') using 'super()'
    public DeathCounterState() {
        super();
    }

    // Takes a 'UUID' representing a player and returns the death count for that player. [Defaults to 0 if player is not in the 'deathCounts' map]
    public int getDeathCount(UUID player) {
        return deathCounts.getOrDefault(player, 0);
    }

    // Increments the death count for a given player by first retrieving the current death count, adding 1 to it, and updating the map with the new value.
    public void incrementDeathCount(UUID player) {
        deathCounts.put(player, getDeathCount(player) + 1);
        this.markDirty(); // indicates the state has changed and needs to be saved
    }

    // Decrements the death count for a given player by first retrieving the current death count, subtracting 1 to it, and updating the map with the new value.
    public void decrementDeathCount(UUID player) {
        deathCounts.put(player, getDeathCount(player) - 1);
        this.markDirty(); // indicates the state has changed and needs to be saved
    }


    // Method to serialize the data
    // Serializes the 'deathCounts' map to an 'NbtCompound' for saving.
    // Creates a new 'NbtCompound' called 'deathsNbt' and populates it with the death counts.
    // Each player's UUID is converted to a string and used as a key with the death count as the value.
    // The 'deathsNbt' is then added to the main 'nbt' compound under the key "death_counts".
    // The modified 'nbt' is returned.
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound deathsNbt = new NbtCompound();
        deathCounts.forEach((uuid, count) -> deathsNbt.putInt(uuid.toString(), count));
        nbt.put("death_counts", deathsNbt);
        return nbt;
}


    // Method to deserialize the data
    // Deserializes the data from an 'NbtCompound' and creates a 'DeathCounterState' instance.
    // Creates a new 'DeathCounterState' object, then retrieves the 'NbtCompound' containing the death counts from the main 'nbt' compound.
    // Iterates over each key in the 'deathsNbt' compound, converting the key back to a 'UUID', retrieves the death count, and updates the 'deathCounts' map with this data.
    // Returns the populated 'DeathCounterState' instance.
    public static DeathCounterState createFromNbt(NbtCompound nbt) {
        DeathCounterState state = new DeathCounterState();
        NbtCompound deathsNbt = nbt.getCompound("death_counts");
        deathsNbt.getKeys().forEach(key -> {
            UUID uuid = UUID.fromString(key);
            int count = deathsNbt.getInt(key);
            state.deathCounts.put(uuid, count);
        });
        return state;
    }

}
