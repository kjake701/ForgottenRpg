package com.forgottenminecraft.ForgottenRpg.zone;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.forgottenminecraft.ForgottenRpg.utilities.Operations;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Zone {
	
	private int zx1, zy1, zz1, zx2, zy2, zz2, blockcount;
	
	private int[] ax1, ay1, az1;
	
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
	
	public String getZoneName(){
		return zonename;
	}
	
	public String setZoneName(String name){
		zonename = name;
		return zonename;
	}
	
	public void defineZonePointOne(int x1, int y1, int z1, Player player){
		x1 = player.getLocation().getBlockX();
		y1 = player.getLocation().getBlockY();
		z1 = player.getLocation().getBlockZ();
		
		zx1 = x1;
		zy1 = y1;
		zz1 = z1;
		
	}
	
	public void defineZonePointTwo(int x2, int y2, int z2, Player player){
		x2 = player.getLocation().getBlockX();
		y2 = player.getLocation().getBlockY();
		z2 = player.getLocation().getBlockZ();
		
		zx2 = x2;
		zy2 = y2;
		zz2 = z2;
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
	
	public void getSelectionTwo(Player p){
		selection = worldEditPlugin.getSelection(p);
		System.out.println(selection.getArea());
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
