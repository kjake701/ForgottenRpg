package com.forgottenminecraft.ForgottenRpg.utilities;

public class Operations {
	
	int sum, sum1;
	
	public int Add(int x, int y, int z){
		
		sum = x += y;
		
		sum1 = sum += z;
		
		return sum1;
	}

}
