package de.adrian.projectbee.manager;

import cn.nukkit.Player;
import cn.nukkit.entity.data.IntEntityData;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.command.GiveCosmeticCommand;
import de.adrian.projectbee.command.ShopCommand;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.cosmetic.CosmeticType;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class CosmeticManager {
    private final Map<Integer, Cosmetic> cosmetics = new HashMap<>();

    private final ProjectBee PLUGIN;

    public void registerCommands() {
        this.PLUGIN.registerCommands(
                new GiveCosmeticCommand(this.PLUGIN),
                new ShopCommand(this.PLUGIN)
        );
    }

    public void setCosmetic(Player player, Cosmetic cosmetic) {
        player.setDataProperty(new IntEntityData(104, cosmetic.getId()));

        player.setNeedSendData(true);
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
}
