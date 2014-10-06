package com.forgottenminecraft.ForgottenRpg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.forgottenminecraft.ForgottenRpg.utilities.Commons;
import com.forgottenminecraft.ForgottenRpg.utilities.RegionFileOperations;
import com.forgottenminecraft.ForgottenRpg.utilities.RegionHandler;
import com.forgottenminecraft.ForgottenRpg.zone.Zone;
import com.forgottenminecraft.ForgottenRpg.zone.ZoneRegion;

public final class ForgottenRpg extends JavaPlugin{
	RegionFileOperations fileOps;
	Zone z;
	RegionHandler rh;
	ZoneRegion zr = new ZoneRegion();
	
	World w = Bukkit.getServer().getWorld("world");
	
	@Override
	public void onEnable(){
		z = new Zone();
		rh = new RegionHandler();
		fileOps = new RegionFileOperations(this);
		getLogger().info("Forgotten Rpg " + Commons.version + " has been enabled.");
		fileOps.fileInit();
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
			z.debugSetSelection();
			fileOps.saveRegion(args[0], sender, z.getHighPointX(), z.getHighPointY(), z.getHighPointZ(),
					                             z.getLowPointX(), z.getLowPointY(), z.getHighPointX());
			rh.getRegionsFromFile(fileOps);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("frpg_getselection")){
			z.cuboidSelection((Player)sender);
			z.experimentalCoordinates((Player)sender);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("frpg_blockcount")) {
			z.pointMath();
			return true;
		}
		
		return false;
	}
	
}
