package de.adrian.projectbee.manager;

import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.command.SetCoinsCommand;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.listener.PlayerListener;
import de.adrian.projectbee.model.PlayerModel;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerManager {

    private final Map<UUID, PlayerModel> players = new HashMap<>();

    private final ProjectBee PLUGIN;

    public void registerListeners() {
        this.PLUGIN.registerListeners(
                new PlayerListener()
        );
    }

    public void registerCommands() {
        this.PLUGIN.registerCommands(
                new SetCoinsCommand(this.PLUGIN)
        );
    }

    public void registerPlayer(PlayerModel player) {
        players.put(player.getUuid(), player);
    }

    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }

    public PlayerModel getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public Map<UUID, PlayerModel> getAllPlayers() {
        return new HashMap<>(players);
    }

    public void updatePlayerLevel(UUID uuid, int level) {
        PlayerModel player = players.get(uuid);
        if (player != null) {
            player.setLevel(level);
        }
    }

    public void addPlayerCosmetic(UUID uuid, Cosmetic cosmetic) {
        PlayerModel player = players.get(uuid);
        if (player != null) {
            player.addCosmetic(cosmetic);
        }
    }

    public void removePlayerCosmetic(UUID uuid, Cosmetic cosmetic) {
        PlayerModel player = players.get(uuid);
        if (player != null) {
            player.removeCosmetic(cosmetic);
        }
    }

}
