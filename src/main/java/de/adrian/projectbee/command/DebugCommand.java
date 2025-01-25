package de.adrian.projectbee.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.data.IntEntityData;
import de.adrian.projectbee.ProjectBee;

public class DebugCommand extends Command {

    private final ProjectBee PLUGIN;

    public DebugCommand(ProjectBee plugin) {
        super("debug");
        this.setPermission("admin");
        this.setDescription("ADD ME");
        this.PLUGIN = plugin;
        this.setUsage(PLUGIN.getPrefix() + "§c/debug");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {

        int cosmeticId = Integer.parseInt(strings[0]);

        if(commandSender instanceof Player player) {
            player.setDataProperty(new IntEntityData(104, cosmeticId));

            player.setNeedSendData(true);
        }
        return false;
    }
}
