package com.github.grubsic.fsa;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FSACommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args != null){
			if(args[0].equals("adCount")){
				if(sender.hasPermission("fsa.adCount")){
					sender.sendMessage(ChatColor.BOLD.toString() + ChatColor.GOLD +
							"Amount of blocked ads in this session: " + ChatColor.GREEN + FSA.getBlockedAdsCounter());
				}
				else{ sender.sendMessage(noPermMsg()); }
			}
		}
		else{ sender.sendMessage(ChatColor.RED + "You didn't specified an option."); }
		return true;
	}

	private String noPermMsg(){ return ChatColor.RED + "You don't have permission to do that."; }
}
