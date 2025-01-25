package de.adrian.projectbee.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.manager.PlayerManager;
import de.adrian.projectbee.model.PlayerModel;
import de.adrian.projectbee.score.CurrencyScoreboard;

import java.util.HashSet;
import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerManager playerManager = ProjectBee.projectBee.getPlayerManager();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        PlayerModel playerModel = playerManager.getPlayer(uuid);

        if (playerModel == null) {
            playerModel = new PlayerModel(uuid, 1, 0, 0, null, new HashSet<>());
            playerManager.registerPlayer(playerModel);
        }

        CurrencyScoreboard currencyScoreboard = new CurrencyScoreboard(player);
        playerModel.setCurrencyScoreboard(currencyScoreboard);

        currencyScoreboard.updateTitle();
    }
}
