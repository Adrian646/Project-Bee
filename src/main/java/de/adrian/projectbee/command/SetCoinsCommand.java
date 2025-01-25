package de.adrian.projectbee.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.data.messages.Messages;

public class SetCoinsCommand extends Command {

    private final ProjectBee PLUGIN;

    public SetCoinsCommand(ProjectBee plugin) {
        super("setcoins");
        this.setPermission("admin");
        this.setDescription("ADD ME");
        PLUGIN = plugin;
        this.commandParameters.put("default", new CommandParameter[]{new CommandParameter("player", CommandParamType.TARGET, false)});
        this.setUsage(PLUGIN.getPrefix() + "§c/setcoins [name]");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (!commandSender.hasPermission(this.getPermission())) {
            return false;
        }

        if (args.length != 2) {
            commandSender.sendMessage(this.getUsage());
            return false;
        }

        Player player;
        String name = args[0];
        String coinsString = args[1];

        if ((player = Server.getInstance().getPlayer(name)) != null) {
            try {
                int coins = Integer.parseInt(coinsString);

                PLUGIN.getPlayerManager().getPlayer(player.getUniqueId()).setCoins(coins);
                PLUGIN.getPlayerManager().getPlayer(player.getUniqueId()).getCurrencyScoreboard().updateTitle();
                commandSender.sendMessage(PLUGIN.getPrefix() + Messages.SUCCESSFULLY_SET_COINS.format(name, coins));
            } catch (Exception e) {
                commandSender.sendMessage(PLUGIN.getPrefix() + Messages.COINS_ARGS_NOT_NUMBER.format());
                return false;
            }
        }

        return false;
    }
}
