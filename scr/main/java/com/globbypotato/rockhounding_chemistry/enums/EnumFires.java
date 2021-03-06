package com.globbypotato.rockhounding_chemistry.enums;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum EnumFires implements IStringSerializable {
	GREEN(0x5CB25A, MapColor.EMERALD),
	BLUE(0x5A8AB2, MapColor.LAPIS),
	CYAN(0x4FD5AA, MapColor.CYAN),
	WHITE(0xFFFFFF, MapColor.SNOW),
	YELLOW(0xFFF000, MapColor.YELLOW),
	ORANGE(0xFF9600, MapColor.GOLD),
	PURPLE(0xE17DF3, MapColor.PURPLE),
	RED(0xF83030, MapColor.RED),
	PALE_BLUE(0x91D6D7, MapColor.CLAY),
	PALE_GREEN(0xD4FFBB, MapColor.LIME);

	private int colorTag;
	private MapColor mapcolor;

	private EnumFires(int colorTag, MapColor mapcolor) {
		this.colorTag = colorTag;
		this.mapcolor = mapcolor;
	}

	@Override
	public String getName() {
		return toString().toLowerCase();
	}

	public static int size(){
		return values().length;
	}

	public int colorTag(){
		return this.colorTag;
	}

	public MapColor mapColorTag(){
		return this.mapcolor;
	}

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getName(i);
		}
		return temp;
	}

	public static String getName(int index){
		return EnumFires.values()[index].getName();
	}

	public static int[] getColors(){
		int[] temp = new int[size()];
		for(int i=0;i<size();i++){
			temp[i] = getColor(i);
		}
		return temp;
	}

	public static int getColor(int index){
		return EnumFires.values()[index].colorTag();
	}

	public static MapColor[] getMapColors(){
		MapColor[] temp = new MapColor[size()];
		for(int i=0;i<size();i++){
			temp[i] = getMapColor(i);
		}
		return temp;
	}

	public static MapColor getMapColor(int index){
		return EnumFires.values()[index].mapColorTag();
	}

}