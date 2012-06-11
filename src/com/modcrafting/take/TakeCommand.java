package com.modcrafting.take;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TakeCommand implements CommandExecutor{
	Take plugin;
	public TakeCommand(Take instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player){
			if(!((Player) sender).isOp()) sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
			return true;
		}
		if ((args.length < 2) || (args.length > 4)) {
			sender.sendMessage(ChatColor.RED + "Usage: /give <player> <item> [amount [data]]");
			return false;
		}
		Player player = plugin.getServer().getPlayer(args[0]);
		if (player != null) {
		int amount = 1;
		short data = 0;
		if (args.length >= 3) {
			try {
				amount = Integer.parseInt(args[2]);
			} catch (NumberFormatException ex) {}
			if (amount < 1) amount = 1;
			if (amount > 64) amount = 64;
			if (args.length >= 4) {
				try {
					data = Short.parseShort(args[3]);
				} catch (NumberFormatException ex) {}
			}
		}
		ItemStack item = new ItemStack(Integer.parseInt(args[1].trim()), amount, data);
		if(player.getInventory().contains(item)){
			player.getInventory().remove(item);
			sender.sendMessage("Taking some " + args[1] + " from " + player.getName());
		}else{
			sender.sendMessage(player.getName() + " does not have any " + args[1]);
		}
	} else {
		sender.sendMessage("Can't find user " + args[0]);
	}
	return true;
}

}
