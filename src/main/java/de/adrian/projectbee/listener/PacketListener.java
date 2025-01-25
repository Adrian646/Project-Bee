package de.adrian.projectbee.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.InteractPacket;
import cn.nukkit.network.protocol.PlayerAuthInputPacket;
import cn.nukkit.network.protocol.types.AuthInputAction;
import de.adrian.projectbee.entities.MountableEntity;

public class PacketListener implements Listener {

    @EventHandler
    private void onPlayerInput(DataPacketReceiveEvent event) {
        Player player = event.getPlayer();

        if (event.getPacket() instanceof PlayerAuthInputPacket pk) {
            MountableEntity entity = MountableEntity.playerEntityMap.get(player.getUniqueId());

            if (entity != null) {
                double speed = 0.5;
                double radiansYaw = Math.toRadians(player.yaw);
                double radiansPitch = Math.toRadians(player.pitch);

                double motionX = 0;
                double motionZ = 0;
                double motionY = 0;

                if (pk.getInputData().contains(AuthInputAction.UP)) {
                    motionX += -Math.sin(radiansYaw) * speed * Math.cos(radiansPitch);
                    motionZ += Math.cos(radiansYaw) * speed * Math.cos(radiansPitch);
                    motionY += -Math.sin(radiansPitch) * speed;
                }
                if (pk.getInputData().contains(AuthInputAction.DOWN)) {
                    motionX += Math.sin(radiansYaw) * speed * Math.cos(radiansPitch);
                    motionZ += -Math.cos(radiansYaw) * speed * Math.cos(radiansPitch);
                    motionY += Math.sin(radiansPitch) * speed;
                }
                if (pk.getInputData().contains(AuthInputAction.RIGHT)) {
                    motionX += -Math.cos(radiansYaw) * speed;
                    motionZ += -Math.sin(radiansYaw) * speed;
                }
                if (pk.getInputData().contains(AuthInputAction.LEFT)) {
                    motionX += Math.cos(radiansYaw) * speed;
                    motionZ += Math.sin(radiansYaw) * speed;
                }

                entity.move(motionX, motionY, motionZ);
                entity.setRotation(player.getYaw(), 0.0);
            } else {
                player.setDataFlag(Player.DATA_FLAGS, Player.DATA_FLAG_RIDING, false);
            }
        }
    }

    @EventHandler
    private void onMountableEntityExit(DataPacketReceiveEvent event) {
        Player player = event.getPlayer();

        if (event.getPacket() instanceof InteractPacket pk && pk.action == 3) {
            MountableEntity entity = MountableEntity.playerEntityMap.get(player.getUniqueId());
            if (entity != null) {
                entity.despawnMount();
            }
        }
    }
}
