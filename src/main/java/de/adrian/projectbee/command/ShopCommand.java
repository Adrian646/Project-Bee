package de.adrian.projectbee.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import de.adrian.projectbee.ProjectBee;
import de.adrian.projectbee.form.cosmetic.shop.CosmeticShopTypeForm;

public class ShopCommand extends Command {

    private final ProjectBee PLUGIN;

    public ShopCommand(ProjectBee plugin) {
        super("shop");
        this.setDescription("ADD ME");
        PLUGIN = plugin;
        this.setUsage(PLUGIN.getPrefix() + "§c/shop");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            new CosmeticShopTypeForm(PLUGIN).openForm(player);
        }
        return false;
    }

}
