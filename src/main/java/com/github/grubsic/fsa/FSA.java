package com.github.grubsic.fsa;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class FSA extends JavaPlugin{

	private static int blockedAdsCounter = 0;
	private static List<String> disallowedAds;

	@Override
	public void onEnable(){

		this.saveDefaultConfig();
		Configuration config = this.getConfig();

		if(config.getBoolean("enable-adblock")){
			disallowedAds = config.getStringList("disallowed-ads");
			this.getServer().getPluginManager().registerEvents(new Event(this.getConfig()), this);
		}
		else{ this.getPluginLoader().disablePlugin(this); }

		this.getCommand("fsa").setExecutor(new FSACommand());
	}

	public static List<String> getDisallowedAds(){ return disallowedAds; }
	public static int getBlockedAdsCounter(){ return blockedAdsCounter; }
	public static void setBlockedAdsCounter(int counter){ blockedAdsCounter = counter; }
}
