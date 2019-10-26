package com.github.zljtt.underwaterbiome.Objects.TileEntities;

import com.github.zljtt.underwaterbiome.Objects.Blocks.BlockInvisible;
import com.github.zljtt.underwaterbiome.Utils.Enum.LightingItem;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntityMovingLightLow extends TileEntity implements ITickableTileEntity
{
	@ObjectHolder("underwaterbiome:moving_light_low")	
    public static TileEntityType<TileEntityMovingLightLow> TYPE;
	
    public LivingEntity theEntityLiving;
    protected boolean shouldDie = false;
    protected int deathTimer = 2; 
    
	public TileEntityMovingLightLow() 
	{
		super(TYPE);
	}

	
	@Override
	public void tick() 
	{	
		if (shouldDie)
		{
			if (deathTimer > 0)
    		{
    			deathTimer--;
    			return;
    		}
    		else
    		{
    			if (this.getBlockState().has(BlockStateProperties.WATERLOGGED) && this.getBlockState().get(BlockStateProperties.WATERLOGGED) == true)
    			{
    				this.getWorld().setBlockState(this.getPos(), Blocks.WATER.getDefaultState(),16);	
    				this.getWorld().removeTileEntity(this.getPos());
    			}
    			else 
    			{
    				this.getWorld().setBlockState(this.getPos(), Blocks.AIR.getDefaultState(),16);
    				this.getWorld().removeTileEntity(this.getPos());
    			}
    		}

		}
		Block blockAtLocation = world.getBlockState(getPos()).getBlock();
		if (theEntityLiving == null || !theEntityLiving.isAlive())
        {
            if (blockAtLocation instanceof BlockInvisible)
            {
                shouldDie = true;
            }                
            return;
        }
		
//		if (blockAtLocation instanceof BlockInvisible && !world.getBlockState(getPos()).get(BlockInvisible.DISTANCED))
		{
			double distanceSquared = getDistanceSq(theEntityLiving.posX, theEntityLiving.posY, theEntityLiving.posZ);
			if (distanceSquared > 5.0D && blockAtLocation instanceof BlockInvisible) 
	        {
	                shouldDie = true;
	                return;
	        }
		}
//		else if(blockAtLocation instanceof BlockInvisible && world.getBlockState(getPos()).get(BlockInvisible.DISTANCED) && EventHandler.rayTraceBlockPos!= null)
//		{
//	        BlockPos pos_point = EventHandler.rayTraceBlockPos;
//			double distanceSquared2 = getDistanceSq(pos_point.getX(), pos_point.getY(), pos_point.getZ());
//			if (distanceSquared2 > 5.0D)
//	        {
//                shouldDie = true;
//                return;
//	        }
//		}
        
        
        
        
        if (LightingItem.getHoldingLightItem(theEntityLiving).getLight_value()!=1)
        {
            if (blockAtLocation instanceof BlockInvisible)
            {
            	shouldDie = true;
            }            
        }
	}
	public LivingEntity getEntityLiving()
    {
    	return theEntityLiving;
    }
	public void setEntityLiving(LivingEntity parEntityLiving)
    {
        theEntityLiving = parEntityLiving;
    }
//	@Override
//	public CompoundNBT write(CompoundNBT compound) 
//	{
//		// TODO Auto-generated method stub
////		if(world.getBlockState(getPos()).has(BlockInvisible.DISTANCED))
////		{
////	        compound.putBoolean("distanced", world.getBlockState(getPos()).get(BlockInvisible.DISTANCED));
////		}
//        compound.putBoolean("should_die", this.shouldDie);
//
//		return super.write(compound);
//	}
//	@Override
//	public void read(CompoundNBT compound) 
//	{
//		this.shouldDie = compound.getBoolean("should_die");
//		super.read(compound);
//	}
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
    {
    	this.read(pkt.getNbtCompound());
    	super.onDataPacket(net, pkt);
    }
    @Override
	public void setPos(BlockPos posIn)
    {
        pos = posIn.toImmutable();
//        if (this.getBlockState().get(BlockInvisible.DISTANCED))
//        {
//         	setEntityLiving(world.getClosestPlayer(posIn.getX(), posIn.getY(), posIn.getZ(), 10, null));
//        }
        setEntityLiving(world.getClosestPlayer(posIn.getX(), posIn.getY(), posIn.getZ(), 2, null));
    }
}
