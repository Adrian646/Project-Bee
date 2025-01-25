package de.adrian.projectbee.manager;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.IntEntityData;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.command.CosmeticsCommand;
import de.adrian.projectbee.command.GiveCosmeticCommand;
import de.adrian.projectbee.command.ShopCommand;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.cosmetic.MountableCosmetic;
import de.adrian.projectbee.data.cosmetic.type.CosmeticType;
import de.adrian.projectbee.data.messages.Messages;
import de.adrian.projectbee.entities.MountableEntity;
import de.adrian.projectbee.entities.mounts.UfoEntity;
import de.adrian.projectbee.model.PlayerModel;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class CosmeticManager {
    private final Map<Integer, Cosmetic> cosmetics = new HashMap<>();

    private final Map<UUID, Cosmetic> activeCosmetics = new HashMap<>();

    private final ProjectBee PLUGIN;

    public void registerCommands() {
        this.PLUGIN.registerCommands(
                new GiveCosmeticCommand(this.PLUGIN),
                new ShopCommand(this.PLUGIN),
                new CosmeticsCommand(this.PLUGIN)
        );
    }

    public void activateCosmetic(Player player, Cosmetic cosmetic) {
        if (!(cosmetic.getCosmeticType() == CosmeticType.MOUNT)) {
            player.setDataProperty(new IntEntityData(104, cosmetic.getId()));
            player.setNeedSendData(true);
            activeCosmetics.put(player.getUniqueId(), cosmetic);
            player.sendMessage(PLUGIN.getPrefix() + Messages.SUCCESSFULLY_ACTIVATED_COSMETIC.format(cosmetic.getName()));
        } else if (cosmetic instanceof MountableCosmetic mountableCosmetic) {
            String entityIdentifier = mountableCosmetic.getEntityIdentifier();

            MountableEntity oldMount = MountableEntity.playerEntityMap.get(player.getUniqueId());
            MountableEntity mountableEntity = createMountableEntity(player, entityIdentifier);

            if (oldMount != null) {
                oldMount.despawnMount();
            }

            if (mountableEntity != null) {
                mountableEntity.spawnToAll();
                mountableEntity.mountPlayer(player);
                activeCosmetics.put(player.getUniqueId(), cosmetic);
                player.sendMessage(PLUGIN.getPrefix() + Messages.SUCCESSFULLY_ACTIVATED_COSMETIC.format(cosmetic.getName()));
            }
        }
    }

    public void deactivateCosmetic(Player player) {
        UUID playerUUID = player.getUniqueId();
        Cosmetic activeCosmetic = activeCosmetics.remove(playerUUID);

        if (activeCosmetic.getCosmeticType() == CosmeticType.MOUNT) {
            MountableEntity oldMount = MountableEntity.playerEntityMap.get(playerUUID);
            if (oldMount != null) {
                oldMount.despawnMount();
                player.sendMessage(PLUGIN.getPrefix() + Messages.SUCCESSFULLY_DEACTIVATED_COSMETIC.format(activeCosmetic.getName()));
            }
        } else {
            player.setDataProperty(new IntEntityData(104, 0));
            player.setNeedSendData(true);
            player.sendMessage(PLUGIN.getPrefix() + Messages.SUCCESSFULLY_DEACTIVATED_COSMETIC.format(activeCosmetic.getName()));
        }
    }


    public void registerCosmetic(Cosmetic cosmetic) {
        cosmetics.put(cosmetic.getId(), cosmetic);
    }

    public Cosmetic getCosmetic(int id) {
        return cosmetics.get(id);
    }

    public List<Cosmetic> getAllCosmetics() {
        return new ArrayList<>(cosmetics.values());
    }

    public List<CosmeticType> getAllCosmeticTypes() {
        return Arrays.asList(CosmeticType.values());
    }

    public void handleClickedCosmetic(Player player, Cosmetic cosmetic) {
        PlayerModel playerModel = PLUGIN.getPlayerManager().getPlayer(player.getUniqueId());
        if (playerModel.hasCosmetic(cosmetic)) {
            Cosmetic activeCosmetic = activeCosmetics.get(player.getUniqueId());

            if (activeCosmetic != null && activeCosmetic.getId() == cosmetic.getId()) {
                deactivateCosmetic(player);
            } else {
                activateCosmetic(player, cosmetic);
            }
        } else {
            player.sendMessage(PLUGIN.getPrefix() + Messages.YOU_DONT_OWN_COSMETIC.format(cosmetic.getName()));
        }
    }
    
    public List<Cosmetic> getCosmeticsSortedByOwnership(Player player) {
        List<Cosmetic> sortedCosmetics = getAllCosmetics();

        sortedCosmetics.sort((cosmetic1, cosmetic2) -> {
            boolean isOwningCosmetic1 = PLUGIN.getPlayerManager().getPlayer(player.getUniqueId()).hasCosmetic(cosmetic1);
            boolean isOwningCosmetic2 = PLUGIN.getPlayerManager().getPlayer(player.getUniqueId()).hasCosmetic(cosmetic2);

            if (isOwningCosmetic1 && !isOwningCosmetic2) {
                return -1;
            } else if (!isOwningCosmetic1 && isOwningCosmetic2) {
                return 1;
            }
            return 0;
        });
        return sortedCosmetics;
    }

    private MountableEntity createMountableEntity(Player player, String entityIdentifier) {
        switch (entityIdentifier) {
            case UfoEntity.IDENTIFIER:
                return (UfoEntity) Entity.createEntity(UfoEntity.IDENTIFIER, player);
            default:
                player.sendMessage(Messages.ERROR_WHILE_CREATING_MOUNT.format());
                return null;
        }
    }
}
