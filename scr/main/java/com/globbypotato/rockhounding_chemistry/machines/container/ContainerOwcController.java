package com.globbypotato.rockhounding_chemistry.machines.container;

import com.globbypotato.rockhounding_chemistry.handlers.ModRecipes;
import com.globbypotato.rockhounding_chemistry.machines.tileentity.TileEntityOwcController;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerOwcController extends ContainerBase<TileEntityOwcController>{
	Slot acquire;
	Slot extract;

	public ContainerOwcController(IInventory playerInventory, TileEntityOwcController te) {
		super(playerInventory,te);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = tile.getInput();
		IItemHandler template = tile.getTemplate();

		acquire = this.addSlotToContainer(new SlotItemHandler(template, 0, 74,  17));//acquiring
		extract = this.addSlotToContainer(new SlotItemHandler(template, 1, 74,  39));//extraction
	}

	@Override
	public ItemStack slotClick(int slot, int dragType, ClickType clickTypeIn, EntityPlayer player){
		if(slot == 0){
    		this.tile.activationKey = !this.tile.activationKey;
    		return null;
    	}else if(slot == 1){
    		this.tile.extractionKey = !this.tile.extractionKey;
    		return null;
    	}else{
    		return super.slotClick(slot, dragType, clickTypeIn, player);
    	}
	}

	@Override
	public boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection){
		return super.mergeItemStack(stack, 2, endIndex, reverseDirection);
    }

}