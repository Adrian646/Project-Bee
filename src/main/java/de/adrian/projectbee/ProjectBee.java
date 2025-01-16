package de.adrian.projectbee;

import cn.nukkit.command.Command;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import de.adrian.projectbee.data.cosmetic.Cosmetic;
import de.adrian.projectbee.data.cosmetic.CosmeticType;
import de.adrian.projectbee.listener.BlockListener;
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
        //Commands
        this.cosmeticManager.registerCommands();
        this.progressManager.registerCommands();

        //Listeners
        this.playerManager.registerListeners();

        this.handleListeners();

        cosmeticManager.registerCosmetic(new Cosmetic("meow1", "Cosmetic1", CosmeticType.HAT));
        cosmeticManager.registerCosmetic(new Cosmetic("meow2", "Cosmetic2", CosmeticType.WING));
        cosmeticManager.registerCosmetic(new Cosmetic("meow3", "Cosmetic3", CosmeticType.EMOTE));

        this.getLogger().info(this.getFullName() + " loaded.");
    }

    private void handleListeners() {
        this.registerListeners(
                new BlockListener(this)
        );
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

    private void registerCosmetics() {

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}