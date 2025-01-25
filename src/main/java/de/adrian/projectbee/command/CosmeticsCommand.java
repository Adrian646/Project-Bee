package de.adrian.projectbee.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.form.cosmetic.view.CosmeticViewForm;

public class CosmeticsCommand extends Command {

    private final ProjectBee PLUGIN;

    public CosmeticsCommand(ProjectBee plugin) {
        super("cosmetics");
        this.setDescription("ADD ME");
        this.PLUGIN = plugin;
        this.setUsage(PLUGIN.getPrefix() + "§c/cosmetics");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            new CosmeticViewForm(PLUGIN).openForm(player);
        }
        return false;
    }
}
