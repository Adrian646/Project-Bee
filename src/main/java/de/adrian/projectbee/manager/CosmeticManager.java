package de.adrian.projectbee.manager;

import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.command.GiveCosmeticCommand;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CosmeticManager {
    private final Map<String, Cosmetic> cosmetics = new HashMap<>();

    private final ProjectBee PLUGIN;

    public void registerCommands() {
        this.PLUGIN.registerCommands(
                new GiveCosmeticCommand(this.PLUGIN)
        );
    }

    public void registerCosmetic(Cosmetic cosmetic) {
        cosmetics.put(cosmetic.getId(), cosmetic);
    }

    public Cosmetic getCosmetic(String id) {
        return cosmetics.get(id);
    }

    public Collection<Cosmetic> getAllCosmetics() {
        return cosmetics.values();
    }
}
