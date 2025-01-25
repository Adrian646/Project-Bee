package de.adrian.projectbee.entities;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3f;
import cn.nukkit.nbt.tag.CompoundTag;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MountableEntity extends Entity implements CustomEntity {

    public static final Map<UUID, MountableEntity> playerEntityMap = new ConcurrentHashMap<>();

    public Vector3f riderPlayerOffset = new Vector3f(0.0F, 0.0F, 0.0F);

    public MountableEntity(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    public void mountPlayer(Player player) {
        player.setSeatPosition(riderPlayerOffset);
        updatePassengerPosition(player);
        broadcastLinkPacket(player, (byte) 1);

        this.namedTag.putString("owner", player.getName());
        player.setDataFlag(DATA_FLAGS, DATA_FLAG_RIDING, true);
        player.sendData(player);

        playerEntityMap.put(player.getUniqueId(), this);
    }

    public void despawnMount() {
        Player owner = server.getPlayerExact(this.namedTag.getString("owner"));
        playerEntityMap.remove(owner.getUniqueId());
        this.getPassengers().remove(owner);
        owner.setDataFlag(Player.DATA_FLAGS, Player.DATA_FLAG_RIDING, false);
        owner.sendData(owner);
        this.close();
    }
}
