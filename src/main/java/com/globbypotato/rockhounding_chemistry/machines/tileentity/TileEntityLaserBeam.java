package com.globbypotato.rockhounding_chemistry.machines.tileentity;

import com.globbypotato.rockhounding_chemistry.ModContents;
import com.globbypotato.rockhounding_chemistry.machines.LaserBeam;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityLaserBeam extends TileEntity implements ITickable {

	@Override
	public void update() {
	    IBlockState state = worldObj.getBlockState(pos);
	    EnumFacing enumfacing = state.getValue(LaserBeam.FACING);

	    if(!worldObj.isRemote){
	    	if( worldObj.getBlockState(pos.offset(enumfacing.getOpposite(), 1)).getBlock() != ModContents.laserBeam && 
	    		worldObj.getBlockState(pos.offset(enumfacing.getOpposite(), 1)).getBlock() != ModContents.laserRedstoneTx &&
	    		worldObj.getBlockState(pos.offset(enumfacing.getOpposite(), 1)).getBlock() != ModContents.laserSplitter){
	    		worldObj.setBlockState(pos, Blocks.AIR.getDefaultState());
	    	}
	    }
	}

}
