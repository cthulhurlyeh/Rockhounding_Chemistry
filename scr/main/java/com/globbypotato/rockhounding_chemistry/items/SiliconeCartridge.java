package com.globbypotato.rockhounding_chemistry.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SiliconeCartridge extends ItemBase {

	public SiliconeCartridge(String name, int uses) {
		super(name);
		this.setMaxDamage(uses);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack){
		if(itemStack.getItemDamage() < itemStack.getMaxDamage() - 1){
			itemStack.setItemDamage(itemStack.getItemDamage() + 1 );
		}else{
			itemStack.stackSize--;
			if(itemStack.stackSize <= 1){
				itemStack = null;
			}
		}
		return itemStack != null ? itemStack.copy() : null;
	}

}