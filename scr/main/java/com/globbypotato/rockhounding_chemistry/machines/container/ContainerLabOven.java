package com.globbypotato.rockhounding_chemistry.machines.container;

import com.globbypotato.rockhounding_chemistry.handlers.ModRecipes;
import com.globbypotato.rockhounding_chemistry.machines.tileentity.TileEntityLabOven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerLabOven extends ContainerBase<TileEntityLabOven> {
	Slot tPrev;
	Slot tNext;
	Slot activation;

	public ContainerLabOven(IInventory playerInventory, TileEntityLabOven tile){
		super(playerInventory,tile);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = tile.getInput();
		IItemHandler template = tile.getTemplate();

		this.addSlotToContainer(new SlotItemHandler(input, 0, 62, 34));//input solute
		this.addSlotToContainer(new SlotItemHandler(input, 1, 8, 31));//fuel
		this.addSlotToContainer(new SlotItemHandler(input, 2, 128, 15));//input solvent
		this.addSlotToContainer(new SlotItemHandler(input, 3, 62, 83));//output
		this.addSlotToContainer(new SlotItemHandler(input, 4, 29, 86));//input redstone
		this.addSlotToContainer(new SlotItemHandler(input, 5, 150, 15));//input solvent

		tPrev = this.addSlotToContainer(new SlotItemHandler(template, 0, 137,  121));//prev
		tNext = this.addSlotToContainer(new SlotItemHandler(template, 1, 153,  121));//next
		activation = this.addSlotToContainer(new SlotItemHandler(template, 2, 7,  121));//activation
	}

	@Override
	public ItemStack slotClick(int slot, int dragType, ClickType clickTypeIn, EntityPlayer player){
		if(slot == 6){
			if(this.tile.recipeIndex >= 0){
	    		this.tile.recipeIndex--; 
    			this.tile.activation = false;
			}
    		return null;
    	}else if(slot == 7){
    		if(this.tile.recipeIndex < ModRecipes.labOvenRecipes.size() - 1){
    			this.tile.recipeIndex++; 
    			this.tile.activation = false;
    		}
    		return null;
    	}else if(slot == 8){
   			this.tile.activation = !this.tile.activation; 
    		return null;
    	}else{
    		return super.slotClick(slot, dragType, clickTypeIn, player);
    	}
	}

	@Override
	public boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection){
		if(super.mergeItemStack(stack, startIndex, 6, reverseDirection)){
			return true;
		}else{
			return super.mergeItemStack(stack, 9, endIndex, reverseDirection);
		}
    }
}