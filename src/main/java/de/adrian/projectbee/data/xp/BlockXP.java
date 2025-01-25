package de.adrian.projectbee.data.xp;

import cn.nukkit.block.BlockID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockXP {

    DIAMOND_ORE(BlockID.DIAMOND_ORE, 15),
    ANCIENT_DEBRIS(BlockID.ANCIENT_DEBRIS, 20),
    EMERALD_ORE(BlockID.EMERALD_ORE, 12),
    GOLD_ORE(BlockID.GOLD_ORE, 6),
    LAPIS_ORE(BlockID.LAPIS_ORE, 5),
    REDSTONE_ORE(BlockID.REDSTONE_ORE, 4),
    COAL_ORE(BlockID.COAL_ORE, 1),
    COPPER_ORE(BlockID.COPPER_ORE, 2),
    NETHER_GOLD_ORE(BlockID.NETHER_GOLD_ORE, 6),
    NETHER_QUARZ_ORE(BlockID.QUARTZ_ORE, 4);

    private final int blockId;
    private final int xp;

    public static int getXPForBlock(int blockId) {
        for (BlockXP blockXP : values()) {
            if (blockXP.getBlockId() == blockId) {
                return blockXP.getXp();
            }
        }
        return 0;
    }
}