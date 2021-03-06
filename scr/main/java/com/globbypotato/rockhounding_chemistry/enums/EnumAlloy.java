package com.globbypotato.rockhounding_chemistry.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumAlloy implements IStringSerializable{
	CUBE,
	SCAL,
	BAM,
	YAG,
	CUPRONICKEL,
	NIMONIC,
	HASTELLOY,
	NICHROME,
	CUNIFE,
	SIENA,
	CARBORUNDUM,
	TINITE,
	HYDRONALIUM;

	@Override
	public String getName() {
		return toString().toLowerCase();
	}

	public static int size(){
		return values().length;
	}

	public static String[] getAlloys(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getAlloy(i);
		}
		return temp;
	}

	public static String getAlloy(int index){
		return EnumAlloy.values()[index].getName();
	}

	public static String[] getItemNames(){
		String[] temp = new String[size()*3];
		for(int i=0;i<size();i++){
			temp[i*3] = getItemDust(i);
			temp[(i*3) + 1] = getItemIngot(i);
			temp[(i*3) + 2] = getItemNugget(i);
		}
		return temp;
	}

	public static String getItemName(int index){
		return EnumAlloy.getItemNames()[index].toString();
	}

	public static String[] getDust(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getDust(i);
		}
		return temp;
	}

	public static String getItemDust(int index){
		return EnumAlloy.values()[index].getName() + "Dust";
	}

	public static String getDust(int index){
		return "dust" + EnumAlloy.values()[index].toString().substring(0, 1).toUpperCase() + EnumAlloy.values()[index].getName().substring(1);
	}

	public static String[] getIngots(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getIngot(i);
		}
		return temp;
	}

	public static String getItemIngot(int index){
		return EnumAlloy.values()[index].getName() + "Ingot";
	}

	public static String getIngot(int index){
		return "ingot" + EnumAlloy.values()[index].toString().substring(0, 1).toUpperCase() + EnumAlloy.values()[index].getName().substring(1);
	}

	public static String[] getNuggets(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getIngot(i);
		}
		return temp;
	}

	public static String getItemNugget(int index){
		return EnumAlloy.values()[index].getName() + "Nugget";
	}

	public static String getNugget(int index){
		return "nugget" + EnumAlloy.values()[index].toString().substring(0, 1).toUpperCase() + EnumAlloy.values()[index].getName().substring(1);
	}

	public static String[] getBlocks(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getIngot(i);
		}
		return temp;
	}

	public static String getBlock(int index){
		return "block" + EnumAlloy.values()[index].toString().substring(0, 1).toUpperCase() + EnumAlloy.values()[index].getName().substring(1);
	}

}