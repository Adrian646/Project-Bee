package de.adrian.projectbee.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.form.admin.cosmetics.give.GiveCosmeticTypeForm;

public class GiveCosmeticCommand extends Command {

    private final ProjectBee PLUGIN;

    public GiveCosmeticCommand(ProjectBee plugin) {
        super("givecos");
        this.setAliases(new String[]{"gc"});
        this.setPermission("admin");
        this.setDescription("ADD ME");
        PLUGIN = plugin;
        this.commandParameters.put("default", new CommandParameter[]{new CommandParameter("player", CommandParamType.TARGET, false)});
        this.setUsage(PLUGIN.getPrefix() + "§c/givecos [name]");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (!commandSender.hasPermission(this.getPermission())) {
            return false;
        }

        if (args.length != 1) {
            commandSender.sendMessage(this.getUsage());
            return false;
        }

        Player player;
        String name = args[0];

        if ((player = Server.getInstance().getPlayer(name)) != null) {
            new GiveCosmeticTypeForm(PLUGIN, player).openForm(player);
        }
        return false;
    }
}
