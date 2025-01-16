package de.adrian.projectbee.manager;

import de.adrian.projectbee.ProjectBee;
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

    public void addPlayerCosmetic(UUID uuid, String cosmeticId) {
        PlayerModel player = players.get(uuid);
        if (player != null) {
            player.addCosmetic(cosmeticId);
        }
    }

    public void removePlayerCosmetic(UUID uuid, String cosmeticId) {
        PlayerModel player = players.get(uuid);
        if (player != null) {
            player.removeCosmetic(cosmeticId);
        }
    }

}
