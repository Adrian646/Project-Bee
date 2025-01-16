package de.adrian.projectbee.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.form.ShowProgressForm;

public class ShowProgressCommand extends Command {

    private ProjectBee PLUGIN;

    public ShowProgressCommand(ProjectBee plugin) {
        super("showprogress");
        this.setAliases(new String[]{"sp"});
        this.setDescription("ADD ME");
        PLUGIN = plugin;
        this.setUsage(PLUGIN.getPrefix() + "§c/showprogress");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            new ShowProgressForm(PLUGIN).openForm(player);
        }
        return false;
    }
}
