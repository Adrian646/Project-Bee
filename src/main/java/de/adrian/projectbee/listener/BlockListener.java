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
        player.sendMessage("BlockId: " + blockId + " Block Name: " + block.getName() + " XP: " + BlockXP.getXPForBlock(blockId) + " Next Level AT: " + PLUGIN.getPlayerManager().getPlayer(uuid).xpRequiredToLevelUp() + " XP: " + PLUGIN.getPlayerManager().getPlayer(uuid).getXp());
    }

}
