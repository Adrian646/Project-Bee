package de.adrian.projectbee;

import cn.nukkit.command.Command;
import cn.nukkit.entity.custom.EntityManager;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import de.adrian.projectbee.command.DebugCommand;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.cosmetic.CosmeticType;
import de.adrian.projectbee.entities.mounts.UfoEntity;
import de.adrian.projectbee.listener.BlockListener;
import de.adrian.projectbee.listener.PacketListener;
import de.adrian.projectbee.manager.CosmeticManager;
import de.adrian.projectbee.manager.PlayerManager;
import de.adrian.projectbee.manager.ProgressManager;
import lombok.Getter;

public class ProjectBee extends PluginBase {

    @Getter
    public static ProjectBee projectBee;

    @Getter
    private CosmeticManager cosmeticManager;

    @Getter
    private PlayerManager playerManager;

    @Getter
    private ProgressManager progressManager;

    @Getter
    private final String prefix = "§eProject§6Bee §r§8» §r";

    @Override
    public void onLoad() {
        projectBee = this;

        this.playerManager = new PlayerManager(this);
        this.cosmeticManager = new CosmeticManager(this);
        this.progressManager = new ProgressManager(this);
    }

    @Override
    public void onEnable() {
        this.cosmeticManager.registerCommands();
        this.progressManager.registerCommands();

        this.playerManager.registerCommands();
        this.playerManager.registerListeners();

        this.handleCommands();

        this.handleListeners();

        this.handleCosmetics();

        this.handleEntities();

        this.getLogger().info(this.getFullName() + " loaded.");
    }

    public void registerCommands(Command... commands) {
        for (Command command : commands) {
            this.getServer().getCommandMap().register("help", command);
        }
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    private void handleCommands() {
        this.registerCommands(
                new DebugCommand(this)
        );
    }

    private void handleListeners() {
        this.registerListeners(
                new BlockListener(this),
                new PacketListener()
        );
    }

    private void handleCosmetics() {
        cosmeticManager.registerCosmetic(new Cosmetic(1, "Graduation Hat", 100, CosmeticType.HAT));
        cosmeticManager.registerCosmetic(new Cosmetic(2, "Irish Hat", 100, CosmeticType.HAT));
        cosmeticManager.registerCosmetic(new Cosmetic(3, "Wand", 100, CosmeticType.BACKBLING));
        cosmeticManager.registerCosmetic(new Cosmetic(999, "Ufo", 100, CosmeticType.MOUNT));
    }

    private void handleEntities() {
        EntityManager.get().registerDefinition(UfoEntity.DEFINITION);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}