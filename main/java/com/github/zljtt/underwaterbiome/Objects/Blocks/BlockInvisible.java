package com.github.zljtt.underwaterbiome.Objects.Blocks;

import javax.annotation.Nullable;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityMovingLightHigh;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityMovingLightLow;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityMovingLightMiddle;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Enum.LightingItem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.IWorld;

public class BlockInvisible extends Block
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty DISTANCED = BooleanProperty.create("distance");
//	public static final IntegerProperty LIGHT = IntegerProperty.create("light", 0, 100);

	String name;
	public BlockInvisible(String name, Properties properties) 
	{
		super(properties);
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		BlockInit.BLOCKS.add(this);
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED,false).with(DISTANCED, false));//.with(LIGHT, 0));
		this.name = name;
	}

//	@Override
//	public int getLightValue(BlockState state, IEnviromentBlockReader world, BlockPos pos) 
//	{
//			return state.get(LIGHT);
//	}
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		return false;
	}
	@Override
	public BlockRenderLayer getRenderLayer() 
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return VoxelShapes.empty();
	}
	@Override
	public boolean doesSideBlockRendering(BlockState state, IEnviromentBlockReader world, BlockPos pos,
			Direction face) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		// TODO Auto-generated method stub
		return state.get(WATERLOGGED)?BlockRenderType.MODEL:BlockRenderType.INVISIBLE;
	}
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAir(BlockState state) 
	{
		return !state.get(WATERLOGGED);
	}
	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return true;
	}
	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		if (this.name == "moving_light_high")
		{
	        return new TileEntityMovingLightHigh();
		}
		else if (this.name == "moving_light_middle")
		{
	        return new TileEntityMovingLightMiddle();
		}
		else
	        return new TileEntityMovingLightLow();
    }
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	      if (stateIn.get(WATERLOGGED)) 
	      {
	         worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	      }

	      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }
	@Override
	public IFluidState getFluidState(BlockState state) 
	{
	      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
	      return LightingItem.getStateForLight(context.getWorld(), context.getPos(), context.getPlayer());

	}

	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
	      builder.add(WATERLOGGED,DISTANCED);//,LIGHT);
	}
	
}
