package com.forgottenminecraft.ForgottenRpg.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.forgottenminecraft.ForgottenRpg.ForgottenRpg;
import com.forgottenminecraft.ForgottenRpg.zone.Zone;
import com.forgottenminecraft.ForgottenRpg.zone.ZoneRegion;

public class RegionFileOperations {
	private ForgottenRpg plugin;
	Zone z;
	File regionsFile;
	FileConfiguration regions;
	
	World w = Bukkit.getServer().getWorld("world");
	
	public RegionFileOperations(ForgottenRpg plugin){
		this.plugin = plugin;
	}

	public void fileInit(){
		 regionsFile = new File(plugin.getDataFolder(), "regions.yml");
		 
		 try {
			 genFiles();
		 } catch (Exception e){
			 e.printStackTrace();
		 }
	}
	
	public File getRegionFile(){
		return regionsFile;
	}
	
	public FileConfiguration GetFileConfiguration(){
		return regions;
	}
	
	public void cycleArray(){
		for(Block l : z.getRegion()){
			System.out.println(l.toString());
		}
	}
	
	public void saveRegion(String name, CommandSender sender,
			               int highX, int highY, int highZ,
			               int lowX, int lowY, int lowZ){
		regions = YamlConfiguration.loadConfiguration(regionsFile);
		z = new Zone();

		
		if(regions.contains(name)){
			sender.sendMessage("The region all ready exists!");
		} else {
			regions.set("Regions." + name + ".highpoint.X", highX);
			regions.set("Regions." + name + ".highpoint.Y", highY);
			regions.set("Regions." + name + ".highpoint.Z", highZ);
			
			regions.set("Regions." + name + ".lowpoint.X", lowX);
			regions.set("Regions." + name + ".lowpoint.Y", lowY);
			regions.set("Regions." + name + ".lowpoint.Z", lowZ);
			sender.sendMessage("Created region " + name);
		}
		
		System.out.println("HighX: " + highX + " HighY: " + highY + " HighZ: " + highZ + 
				           " lowX: " + lowX + " lowY: " + lowY + " lowZ: " + lowZ);

		
		try {
			regions.save(regionsFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void genFiles() throws Exception{
		if(!regionsFile.exists()){
			regionsFile.getParentFile().mkdirs();
			copy(plugin.getResource("region.yml"),regionsFile);
		}
	}
	
	public void copy(InputStream in, File file){
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
