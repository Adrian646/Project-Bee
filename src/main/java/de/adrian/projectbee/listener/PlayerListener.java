package de.adrian.projectbee.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.manager.PlayerManager;
import de.adrian.projectbee.model.PlayerModel;

import java.util.HashSet;
import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerManager playerManager = ProjectBee.projectBee.getPlayerManager();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (playerManager.getPlayer(uuid) == null) {
            PlayerModel playerModel = new PlayerModel(uuid, 0,0, new HashSet<>());

            playerManager.registerPlayer(playerModel);

            player.sendMessage("Registered");
        } else {
            player.sendMessage("Already Exist");
        }

    }

}
