package com.forgottenminecraft.ForgottenRpg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.forgottenminecraft.ForgottenRpg.utilities.Commons;
import com.forgottenminecraft.ForgottenRpg.zone.Zone;

public final class ForgottenRpg extends JavaPlugin{
	Zone z;
	@Override
	public void onEnable(){
		z = new Zone();
		getLogger().info("Forgotten Rpg " + Commons.version + " has been enabled.");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Forgotten Rpg " + Commons.version + " has been enabled..");
	}
	
	//This should be removed later and replaced with a its own class
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("frpg_pointone")) {
			z.defineZonePointOne(0, 0, 0, (Player) sender);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("frpg_pointtwo")) {
			z.defineZonePointTwo(0, 0, 0, (Player) sender);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("frpg_printpoints")) {
			z.printPointLocations();
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("frpg_clearpoints")) {
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("frpg_getselection")){
			z.getSelectionTwo((Player)sender);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("frpg_blockcount")) {
			z.pointMath();
			return true;
		}
		
		return false;
	}
	
}
