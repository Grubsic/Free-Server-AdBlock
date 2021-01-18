package com.github.grubsic.fsa;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class FSAAdEvent implements Listener{

	private final Configuration config;
	private String commandKeyword;

	public FSAAdEvent(Configuration config){
		this.config = config;
		commandKeyword = config.getString("command-keyword");
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAdsEvent(ServerCommandEvent event){

		// Without "stripColor" the command is not detected due color char codes
		String command = ChatColor.stripColor(event.getCommand());

		if(command.contains(commandKeyword)){
			if(FSA.getDisallowedAds().size() > 0){
				for(String disallowedAd : FSA.getDisallowedAds()){
					if(command.equalsIgnoreCase(commandKeyword + " " + disallowedAd)){

						event.setCancelled(true);
						FSA.setBlockedAdsCounter(FSA.getBlockedAdsCounter() + 1);

						if(config.getBoolean("notify-console")){
							notifyBlockedAdOnConsole(command);
						}

						return;

					}
				}
			}
		}
	}

	@SuppressWarnings("all")
	public void notifyBlockedAdOnConsole(String cmd){
		String notify = config.getString("blocked-ad").replaceAll("%command%", cmd)
				.replaceAll("%amount%", String.valueOf(FSA.getBlockedAdsCounter()));

		System.out.println(notify);
	}
}
