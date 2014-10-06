package com.forgottenminecraft.ForgottenRpg.zone;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.forgottenminecraft.ForgottenRpg.ForgottenRpg;
import com.forgottenminecraft.ForgottenRpg.utilities.Operations;
import com.forgottenminecraft.ForgottenRpg.utilities.RegionFileOperations;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Zone {
	
	/*This class should REALLY be cleaned up and the methods that shouldn't be here should be moved later on.
	 * I'll worry about that later though. Maybe. I'll at least remove debug/unneeded methods/variables*/
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	private int zx1, zy1, zz1, zx2, zy2, zz2, blockcount;
	
	World world;
	
	private int blockNumber;
	
	private Location locMin, locMax;
	
	private int fileMinX, fileMinY, fileMinZ,
	            fileMaxX, fileMaxY, fileMaxZ;
	
	private ArrayList<Integer> afileMinX, afileMinY, afileMinZ,
	                       afileMaxX, afileMaxY, afileMaxZ;
	
	//Vertices of a cuboid
	private int minX, minY, minZ,
	            minX2, minY2, minZ2,
	            minX3, minY3, minZ3,
	            minX4, minY4, minZ4,
	            maxX, maxY, maxZ,
	            maxX2, maxY2, maxZ2,
	            maxX3, maxY3, maxZ3,
	            maxX4, maxY4, maxZ4;
	
	private int[] ax1, ay1, az1;
	
	Block z;
	
	private Block[] blocksArray;
	
	private int blockcountx, blockcounty, blockcountz;
	
	WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
	Selection selection;
	
	Operations op;
	
	private String zonename;
	
	/*Zone Types:
	 * 0: Quest
	 * 1: Job
	 * 2: Dungeon
	 * 3: Town
	 * 4: RandomEvent*/
	private int[] ZoneType = new int[4];
	
	public ArrayList<Block> getRegion(){
		return blocks;
	}
	
	public String getZoneName(){
		return zonename;
	}
	
	public String setZoneName(String name){
		zonename = name;
		return zonename;
	}
	
	public void getSelectionFromFile(String args, RegionFileOperations fileOps){
		FileConfiguration regions = fileOps.GetFileConfiguration();
		
		Set<String> keys;
		ArrayList<String> keys2 = new ArrayList<String>();
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
		
		for(String l : keys2){
			fileMinX = regions.getInt("Regions"+ args + ".lowpoint.X");
			fileMinY = regions.getInt("Regions." + args + ".lowpoint.Y");
			fileMinX = regions.getInt("Regions." + args + ".lowpoint.Z");
			
			fileMaxX = regions.getInt("Regions." + args + ".highpoint.X");
			fileMaxY = regions.getInt("Regions." + args + ".highpoint.Y");
			fileMaxZ = regions.getInt("Regions." + args + ".highpoint.Z");
			
			afileMinX.add(fileMinX);
			afileMinY.add(fileMinY);
			afileMinZ.add(fileMinZ);
			
			afileMinX.add(fileMinX);
			afileMinY.add(fileMinY);
			afileMinZ.add(fileMinZ);
		}
		
		System.out.println(fileMinX);
	}
	
	public int getLowPointX(){
		return minX;
	}
	
	public int getLowPointY(){
		return minY;
	}
	
	public int getLowPointZ(){
		return minZ;
	}
	
	public int getHighPointX(){
		return maxX;
	}
	
	public int getHighPointY(){
		return maxY;
	}
	
	public int getHighPointZ(){
		return maxZ;
	}
	
	//Keep just incase I decide to not use worldedit//
	public void defineZonePointOne(int x1, int y1, int z1, Player player){
		x1 = player.getLocation().getBlockX();
		y1 = player.getLocation().getBlockY();
		z1 = player.getLocation().getBlockZ();
		
		zx1 = x1;
		zy1 = y1;
		zz1 = z1;
		
	}
	
	//Keep for now for debug purposes//
	public void debugSetSelection(){
		if(!blocks.isEmpty()){
			for(Block block : blocks){
				System.out.println(block);
				block.setType(Material.GLASS);
			}
		} else {
			System.out.println("Selection empty! Set a selection first!");
		}
	}
	
	//(Should) Get a cuboid region from a worldedit selection
	/*Should probably trim this method since it doesn't require
	 *all these vertices*/
	//KEEP//
	public void cuboidSelection(Player p){
		selection = worldEditPlugin.getSelection(p);
		locMin = selection.getMinimumPoint();
		locMax = selection.getMaximumPoint();
		
		//Minimum point one (Vertice one)
		minX = locMin.getBlockX();
		minY = locMin.getBlockY();
		minZ = locMin.getBlockZ();
		
		//Minimum point two (Vertice two)
		minX2 = locMax.getBlockX();
		minY2 = locMin.getBlockY();
		minZ2 = locMin.getBlockZ();
		
		//Minimum point three (Vertice three)
		minX3 = locMax.getBlockX();
		minY3 = locMin.getBlockY();
		minZ3 = locMax.getBlockX();
		
		//Minimum point four (Vertice four)
		minX4 = locMin.getBlockX();
		minY4 = locMin.getBlockY();
		minZ4 = locMax.getBlockZ();
		
		//Maximum point one (Vertice five)
		maxX = locMax.getBlockX(); 
		maxY = locMax.getBlockY();
		maxZ = locMax.getBlockZ();
		
		//Maximum point two (Vertice six)
		maxX2 = locMin.getBlockX();
		maxY2 = locMax.getBlockY();
		maxZ2 = locMax.getBlockZ();
		
		//Maximum point three (Vertice seven)
		maxX3 = locMax.getBlockX();
		maxY3 = locMax.getBlockY();
		maxZ3 = locMin.getBlockZ();
		
		//Maximum point four (Vertice eight)
		maxX4 = locMin.getBlockX();
		maxY4 = locMax.getBlockY();
		maxZ4 = locMin.getBlockZ();
		
	}
	
	//Final coordinate get method //KEEP//
	public void experimentalCoordinates(Player p){
		int xMin = minX;
		int yMin = minY;
		int zMin = minZ;
		
		int xMax = maxX;
		int yMax = maxY;
		int zMax = maxZ;
		
		World w = p.getWorld();
		
		if(!blocks.isEmpty()){
			blocks.clear();
			for(int i = yMin; i < yMax + 1; i++){
				for(int j = xMin; j < xMax + 1; j++){
					for(int k = zMin; k < zMax + 1; k++){
						blocks.add(w.getBlockAt(j,i,k));
					}
				}
			}
		} else {
			for(int i = yMin; i < yMax + 1; i++){
				for(int j = xMin; j < xMax + 1; j++){
					for(int k = zMin; k < zMax + 1; k++){
						blocks.add(w.getBlockAt(j,i,k));
					}
				}
			}
		}
	}
	
	public void cuboidMath(Player p){
		
		//Minimum point one (Vertice one)
		int x1 = minX;
		int y1 = minY; 
		int z1 = minZ;
		
		//Minimum point two (Vertice two)
		int x2 = minX2;
		int y2 = minY2;
		int z2 = minZ2;
		
		//Minimum point three (Vertice three)
		int x3 = minX3;
		int y3 = minY3;
        int z3 = minZ3;
		
		//Minimum point four (Vertice four)
		int x4 = minX4;
		int y4 = minY4;
		int z4 = minZ4;
		
		//Maximum point one (Vertice five)
		int x5 = maxX;
		int y5 = maxY;
		int z5 = maxZ;
		
		//Maximum point two (Vertice six)
		int x6 = maxX2;
		int y6 = maxY2;
		int z6 = maxZ2;
		
		//Maximum point three (Vertice seven)
		int x7 = maxX3;
		int y7 = maxY3;
		int z7 = maxZ3;
		
		//Maximum point four (Vertice eight)
		int x8 = maxX4;
		int y8 = maxY4;
		int z8 = maxZ4;
		
		World world = p.getWorld();
		
		
		//X Coordinates get
		
		//Y Coordinates get
		for(int i = y3; i < y5; i++){
			blocks.add(world.getBlockAt(x5, i, z5));
		}
		
		for(int i = y2; i < y7; i++){
			blocks.add(world.getBlockAt(x7, i, z7));
		}
		
		for(int i = y1; i < y8; i++){
			blocks.add(world.getBlockAt(x8, i, z8));
		}
		
		for(int i = y4; i < y6; i++){
			blocks.add(world.getBlockAt(x6, i, z6));
		}
		
	}
	
	
	public void getSelectionTwo(Player p){
		selection = worldEditPlugin.getSelection(p);
		locMin = selection.getMinimumPoint();
		locMax = selection.getMaximumPoint();
		
		boolean firstRun = true;
		
		minX = locMin.getBlockX();
		minY = locMin.getBlockY();
		minZ = locMin.getBlockZ();
		
		maxX = locMax.getBlockX();
		maxY = locMax.getBlockY();
		maxZ = locMax.getBlockZ();
		
		int i = minX;
		int j = minY - 1;
		int k = minZ;
		System.out.println("Area: " + selection.getArea());
		
		int blockNum = selection.getArea();
		
		World w = locMin.getWorld();
		
		System.out.println("MinX: " + minX + " MinY: " + minY + "MinZ: " + minZ);
		System.out.println("MaxX: " + maxX + " MaxY: " + maxY + "MaxZ: " + maxZ);
		
		if(!blocks.isEmpty()){
			blocks.clear();
			blocks.add(w.getBlockAt(i,j,k));
			while(blockNum - 1 > 0){
				if(i < maxX){
				i++;
				} else if(i == maxX){
				 //Do nothing
				}


				if(j < maxY){
				j++;
				} else if(j == maxY){
				 //Do nothing
				}


				if(k < maxZ){
				k++;
				} else if(k == maxZ){
				 //Do nothing
				}
				blocks.add(w.getBlockAt(i,j,k));
				System.out.println("X: "+ i+ " Y: " + j + " Z: " + k);
				blockNum--;
				}
			
		} else {
			blocks.add(w.getBlockAt(i,j,k));
			while(blockNum - 1 > 0){
				if(i < maxX){
				i++;
				} else if(i == maxX){
				 //Do nothing
				}


				if(j < maxY){
				j++;
				} else if(j == maxY){
				 //Do nothing
				}


				if(k < maxZ){
				k++;
				} else if(k == maxZ){
				 //Do nothing
				}
				System.out.println("X: "+ i+ " Y: " + j + " Z: " + k);
				blockNum--;
				blocks.add(w.getBlockAt(i,j,k));
				}
		}
		
		System.out.println(selection.getArea());
		System.out.println(blocks.size());
	}
	
	public void getAllBlocks(Player p){
		world = p.getLocation().getWorld();
		int i = minX;
		int j = minY;
		int k = minZ;


		while(i < maxX && j < maxY && k < maxZ){
		if(i < maxX){
		i++;
		} else if(i == maxX){
		 //Do nothing
		}


		if(j < maxY){
		j++;
		} else if(j == maxY){
		 //Do nothing
		}


		if(k < maxZ){
		k++;
		} else if(k == maxZ){
		 //Do nothing
		}
		blocks.add(world.getBlockAt(i,j,k));
		}

			 System.out.println(blocks.size());
	}
	
	public void defineZonePointTwo(int x2, int y2, int z2, Player player){
		x2 = player.getLocation().getBlockX();
		y2 = player.getLocation().getBlockY();
		z2 = player.getLocation().getBlockZ();
		
		zx2 = x2;
		zy2 = y2;
		zz2 = z2;
	}
	
	public void getMinPoints(){
		
		if(zx1 > zx2){
			maxX = zx1;
			minX = zx2;
		} else if(zx2 >= zx1) {
			maxX = zx2;
			minX = zx1;
		}
		
		if(zy1 > zy2){
			maxY = zy1;
			minY = zy2;
		} else if(zy2 >= zy1) {
			maxY = zy2;
			minY = zy1;
		}
		
		if(zz1 > zz2){
			maxZ = zz1;
			minZ = zz2;
		} else if(zz2 >= zz1) {
			maxZ = zz2;
			minZ = zz1;
		}
		
		System.out.println("MinX: " + minX + "MinY: " + minY + "MinZ" + minZ);
		System.out.println("MaxX: " + maxX + "MaxY: " + maxY + "MaxZ" + maxZ);
		
	}
	
	public void printPointLocations(){
			System.out.println("X1: " + zx1 + " Y1: " + zy1 + " Z1" + zz1 
					+ '\n' + "X2: " + zx2 + " Y2: " + zy2 + " Z2: " + zz2);
	}
	
	public void printWholeSelection(){
		for(int op = ax1.length, op2 = 0; op > op2; op2++){
			for(int op3 = ay1.length, op4 = 0; op3 > op4; op4++){
				for(int op5 = az1.length, op6 = 0; op5 > op6; op6++){
					System.out.println("X: " + ax1[op2] + " Y: " + ay1[op4] + " Z: " + az1[op6]);
				}
			}
		}
	}
	
	public void getWholeSelection(){
		ax1 = new int[blockcountx + 1];
		ay1 = new int[blockcounty + 1];
		az1 = new int[blockcountz + 1];
		
		System.out.println(ax1.length + " " + ay1.length + " " + az1.length);
		
		if(zx1 > zx2){
			for(int op = ax1.length, op2 = 0, op3 = zx2; op > op2; op2++){
				ax1[op2] = op2;
				op3++;
				System.out.println("X: " + op2);
			}
		} else if(zx2 > zx1){
			for(int op = ax1.length, op2 = 0, op3 = zx1; op > op2; op2++, op3++){
				ax1[op2] = op3;
				op3++;
				System.out.println("X: " + op2);
			}
		}
		
		if(zy1 > zy2){
			for(int op = ax1.length, op2 = 0, op3 = zy2; op > op2; op2++, op3++){
				ay1[op2] = op3;
				op3++;
				System.out.println("Y: " + op2);
			}
		} else if(zy2 > zy1){
			for(int op = ax1.length, op2 = 0, op3 = zy1; op > op2; op2++, op3++){
				ay1[op2] = op3;
				op3++;
				System.out.println("Y: " + op2);
			}
		}
		
		if(zz1 > zz2){
			for(int op = ax1.length, op2 = 0, op3 = zz2; op > op2; op2++, op3++){
				az1[op2] = op3;
				op3++;
				System.out.println("Z: " + op2);
			}
		} else if(zz2 > zz1){
			for(int op = ax1.length, op2 = 0, op3 = zz1; op > op2; op2++, op3++){
				az1[op3] = op2;
				op3++;
				System.out.println("Z: " + op2);
			}
		}
	}
	
	public void getAllBlocksInSelection(int mX, int mY, int mZ,
			                            int maX, int maY, int maZ,
			                            int remX, int remY, int remZ){
		
		mX = minX;
		mY = minY;
		mZ = minZ;
		////////////
		maX = maxX;
		maY = maxY;
		maZ = maxZ;
		
		remX = (maX - mX) + 1;
		remY = (maY - mY) + 1;
		remZ = (maZ - mZ) + 1;
		
		
		if(remX >= remY && remX >= remZ){
			blockNumber = remX;
		} else if(remY >= remX && remY >= remZ){
			blockNumber = remY;
		} else if(remZ >= remY && remZ >= remX){
			blockNumber = remZ;
		}
		
		System.out.println("(private:integer)blockNumber:" + blockNumber);
		
	}
	
	public void pointMath(){
		if(zx1 > zx2){
			blockcountx = zx1 -= zx2;
		} else if(zx2 > zx1){
			blockcountx = zx2 -= zx1;
		}
	
		if(zy1 > zy2){
			blockcounty -= zy1 -= zy2;
		} else if(zy2 > zy1){
			blockcounty -= zy2 -= zy2;
		}
		
		if(zz1 >zz2){
			blockcountz = zz1 -= zz2; 
		} else if(zz2 > zz1){
			blockcountz = zz2 -= zz1;
		}
		
		System.out.println(blockcountx + " " + blockcounty + " " + blockcountz);
	}
	
	public void calculateBlockNumber(int rem1, int rem2, int rem3){
		int sum;
			if(zx1 > zx2){
				zx1 -= zx2 = rem1;
			} else {
				zx2 -= zx1 = rem1;
			}
			
			if(zy1 > zy2){
				zy1 -= zy2 = rem2;
			} else {
				zy2 -= zy1 = rem2;
			}
			
			if(zz1 > zz2){
				zz1 -= zz2 = rem3;
			} else {
				zz2 -= zz1 = rem3;
			}
			
			sum = rem1 += rem2;
			sum = sum += rem3;
	}
	
	public void printBlockCount(){
		System.out.println("Block Count: " + blockcount);
	}
	
	public int[] getZoneType(){
		return ZoneType;
	}
	
	public int[] setZoneType(int loc, int type){
		ZoneType[loc] = type;
		return ZoneType;
	}
	
}
