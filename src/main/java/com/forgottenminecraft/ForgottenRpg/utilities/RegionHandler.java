package com.forgottenminecraft.ForgottenRpg.utilities;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

public class RegionHandler {

	private int fileMinX, fileMinY, fileMinZ,
    fileMaxX, fileMaxY, fileMaxZ;
	
	private ArrayList<String> keys2 = new ArrayList<String>();
	
	private ArrayList<Integer> afileMinX, afileMinY, afileMinZ,
    afileMaxX, afileMaxY, afileMaxZ;
	
	
	public RegionHandler(){

	}
	
	public void getRegionsFromFile(RegionFileOperations fileOps){
		FileConfiguration regions = fileOps.GetFileConfiguration();
		
		Set<String> keys;
		keys = regions.getConfigurationSection("Regions").getKeys(true);
		String keyArray[] = new String[keys.size()];
		afileMinX = new ArrayList<Integer>();
		afileMinY = new ArrayList<Integer>();
		afileMinZ = new ArrayList<Integer>();
		
		afileMaxX = new ArrayList<Integer>();
		afileMaxY = new ArrayList<Integer>();
		afileMaxZ = new ArrayList<Integer>();
		
		for(String j : keys){
			System.out.println("Keys: " + j);
			keys2.add(j);
		}
		
		for(int i = 0; i < keys2.size(); i++){
			for(int j = 0; j < keys2.size(); j+=9){
				keyArray[i] = keys2.get(i);
			}
		}
		
		for(String l : keyArray){
			System.out.println("KeyArray: " + l);
		}
		
		keys2.clear();
		for(int i = 0; i < keys.size(); i += 9){
			keys2.add(keyArray[i]);
		}
		
		for(String l : keys2){
			System.out.println("Final Keys: " + l);
		}
		
		if(!keys2.isEmpty()){
			keys2.clear();
			for(String l : keys2){
				fileMinX = regions.getInt("Regions"+ l + ".lowpoint.X");
				fileMinY = regions.getInt("Regions." + l + ".lowpoint.Y");
				fileMinX = regions.getInt("Regions." + l + ".lowpoint.Z");
				
				fileMaxX = regions.getInt("Regions." + l + ".highpoint.X");
				fileMaxY = regions.getInt("Regions." + l + ".highpoint.Y");
				fileMaxZ = regions.getInt("Regions." + l + ".highpoint.Z");
				
				afileMinX.add(fileMinX);
				afileMinY.add(fileMinY);
				afileMinZ.add(fileMinZ);
				
				afileMinX.add(fileMinX);
				afileMinY.add(fileMinY);
				afileMinZ.add(fileMinZ);
			}
		} else {
			for(String l : keys2){
				fileMinX = regions.getInt("Regions"+ l + ".lowpoint.X");
				fileMinY = regions.getInt("Regions." + l + ".lowpoint.Y");
				fileMinX = regions.getInt("Regions." + l + ".lowpoint.Z");
				
				fileMaxX = regions.getInt("Regions." + l + ".highpoint.X");
				fileMaxY = regions.getInt("Regions." + l + ".highpoint.Y");
				fileMaxZ = regions.getInt("Regions." + l + ".highpoint.Z");
				
				afileMinX.add(fileMinX);
				afileMinY.add(fileMinY);
				afileMinZ.add(fileMinZ);
				
				afileMinX.add(fileMinX);
				afileMinY.add(fileMinY);
				afileMinZ.add(fileMinZ);
			}
		}
		

		
		debugKeys();
	}
	
	public void debugKeys(){
		for(int i : afileMinX){
			System.out.println("minX:" + i);
		}
		
		for(int i : afileMinY){
			System.out.println("minY:" + i);
		}
		
		for(int i : afileMinZ){
			System.out.println("minZ:" + i);
		}
		///////////////////////////////////
		
		for(int i : afileMaxX){
			System.out.println("maxX:" + i);
		}
		
		for(int i : afileMaxY){
			System.out.println("maxY:" + i);
		}
		
		for(int i : afileMaxZ){
			System.out.println("maxZ:" + i);
		}
		
		
		
	}
	
	public void loadRegions(){
		
	}
	
	public void applyProtections(){
		
	}
	
}
