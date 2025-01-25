package de.adrian.projectbee.listener;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.xp.BlockXP;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class BlockListener implements Listener {

    private final ProjectBee PLUGIN;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        int blockId = block.getId();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PLUGIN.getPlayerManager().getPlayer(uuid).addXP(BlockXP.getXPForBlock(blockId));
    }
}
