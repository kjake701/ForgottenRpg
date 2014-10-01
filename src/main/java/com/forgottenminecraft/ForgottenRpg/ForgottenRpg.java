package com.forgottenminecraft.ForgottenRpg;

import org.bukkit.plugin.java.JavaPlugin;

import com.forgottenminecraft.ForgottenRpg.utilities.Commons;

public final class ForgottenRpg extends JavaPlugin{

	@Override
	public void onEnable(){
		System.out.println("Forgotten Rpg Enabled");
		getLogger().info("Forgotten Rpg " + Commons.version + " has been enabled.");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Forgotten Rpg " + Commons.version + " has been enabled..");
	}
	
}
