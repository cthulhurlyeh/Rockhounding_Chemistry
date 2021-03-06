package com.globbypotato.rockhounding_chemistry.enums;

public enum EnumSaltStages {
	EMPTY,
	WATER,
	STEPA,
	STEPB,
	STEPC,
	STEPD,
	SALT;

	public static int size(){
		return values().length;
	}

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getName(i);
		}
		return temp;
	}

	public static String getName(int index){
		return EnumSaltStages.values()[index].toString().toLowerCase();
	}
}