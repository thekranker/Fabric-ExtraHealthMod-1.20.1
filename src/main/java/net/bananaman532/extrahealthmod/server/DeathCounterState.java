package net.bananaman532.extrahealthmod.server;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeathCounterState extends PersistentState {

    private final Map<UUID, Integer> deathCounts = new HashMap<>();

    public DeathCounterState() {
        super();
    }

    public int getDeathCount(UUID player) {
        return deathCounts.getOrDefault(player, 0);
    }

    public void incrementDeathCount(UUID player) {
        deathCounts.put(player, getDeathCount(player) + 1);
    }


    // Method to serialize the data
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound deathsNbt = new NbtCompound();
        deathCounts.forEach((uuid, count) -> deathsNbt.putInt(uuid.toString(), count));
        nbt.put("death_counts", deathsNbt);
        return nbt;
    }


    // Method to deserialize the data
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
