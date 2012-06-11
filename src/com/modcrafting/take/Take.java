package com.modcrafting.take;

import org.bukkit.plugin.java.JavaPlugin;


public class Take extends JavaPlugin{
	public void onEnable(){
		getCommand("take").setExecutor(new TakeCommand(this));
	}
}
