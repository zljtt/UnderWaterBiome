package com.github.zljtt.underwaterbiome.Objects.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.Blocks.Base.BlockWaterLoggedBase;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockFishCatcher extends BlockWaterLoggedBase
{

	protected static final VoxelShape SHAPE_BASE = Block.makeCuboidShape(0, 0, 0, 	16, 1, 16);
    protected static final VoxelShape SHAPE_TOP = Block.makeCuboidShape(16, 16, 16, 	0, 15, 0);

    protected static final VoxelShape SHAPE_MID_N = Block.makeCuboidShape(0, 1, 0, 		16, 15, 1);
    protected static final VoxelShape SHAPE_MID_S = Block.makeCuboidShape(16, 1, 16, 	0, 15, 15);
    
    protected static final VoxelShape SHAPE_MID_E = Block.makeCuboidShape(16, 1, 16, 	15, 15, 0);
    protected static final VoxelShape SHAPE_MID_W = Block.makeCuboidShape(0, 1, 0, 		1, 15, 16);
    
	public static final BooleanProperty HAS_BAIT = BooleanProperty.create("has_bait");

	public BlockFishCatcher(String name, Properties porperty, boolean needBlueprint, BlueprintType type,
			int... difficulty) 
	{
		super(name, porperty, true,needBlueprint, type, difficulty);
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false).with(HAS_BAIT, false));
	}
	@Override
	 public BlockRenderLayer getRenderLayer() 
	 {
	      return BlockRenderLayer.CUTOUT_MIPPED;
	 }
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return true;
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return combineAll(SHAPE_BASE,SHAPE_TOP, SHAPE_MID_N, SHAPE_MID_S, SHAPE_MID_E, SHAPE_MID_W);
	}
	static VoxelShape combineAll(VoxelShape a,VoxelShape b, VoxelShape c, VoxelShape d, VoxelShape e, VoxelShape f)
	{
		VoxelShape ab = VoxelShapes.combine(a,b,IBooleanFunction.OR);
		VoxelShape cd = VoxelShapes.combine(c,d,IBooleanFunction.OR);
		VoxelShape ef = VoxelShapes.combine(e,f,IBooleanFunction.OR);
		return VoxelShapes.combine(VoxelShapes.combine(ab,cd, IBooleanFunction.OR), ef, IBooleanFunction.OR);
	}
	@Override
	public void randomTick(BlockState state, World worldIn, BlockPos pos, Random random)
	{
		System.out.println("random tick");
		int bait = state.get(HAS_BAIT)?40:0;
		if (random.nextInt(100)<(40+bait))
		{
			AbstractFishEntity fish = (AbstractFishEntity) randomFish(random).create(worldIn);
			fish.setPosition(pos.getX()+0.5D, pos.getY()+0.3D, pos.getZ()+0.5D);
			worldIn.addEntity(fish);
		}
		
		super.randomTick(state, worldIn, pos, random);
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) 
	{		
		{
			String status = "no";
			if (state.get(HAS_BAIT))
			{
				status = "remove";
			}
			else if (player.getHeldItem(handIn).getItem().equals(ItemInit.BAIT))
			{
				status = "add";
			}	
			
			List<ItemStack> list = getLoot(worldIn, pos);
			if (list!=null)
			{
				list.forEach(item->player.addItemStackToInventory(item));
				worldIn.setBlockState(pos, state.with(HAS_BAIT, false));
				return true;
			}
			System.out.println(status);
			if (status == "remove")
			{
				worldIn.setBlockState(pos, state.with(HAS_BAIT, false));
				ItemEntity entity = new ItemEntity(worldIn, 
						pos.getX()+0.5F,
						pos.getY()+1.1F, 
						pos.getZ()+0.5F,
						new ItemStack(ItemInit.BAIT));
				worldIn.addEntity(entity);
				return true;
			}
			if (status == "add")
			{
				worldIn.setBlockState(pos, state.with(HAS_BAIT, true));
				player.getHeldItem(handIn).shrink(1);
				return true;
			}
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) 
	{
		Random ran = new Random();
		List<ItemStack> list = getLoot(worldIn, pos);
		if (state.get(HAS_BAIT))
		{
			list.add(new ItemStack(ItemInit.BAIT));
		}
		if (list!=null)
		{
			list.forEach(item->
			{
				ItemEntity entity = new ItemEntity(worldIn, 
						pos.getX()+ran.nextFloat(),
						pos.getY()+0.5F, 
						pos.getZ()+ran.nextFloat(),
						item);
				worldIn.addEntity(entity);
			});
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	public List<ItemStack> getLoot(World worldIn, BlockPos pos)
	{
		List<AbstractFishEntity> en = worldIn.getEntitiesWithinAABB(AbstractFishEntity.class, new AxisAlignedBB(pos));
		List<ItemStack> stack = new ArrayList<ItemStack>();
		if (en!=null && en.size()>0)
		{
			for (int i = 0; i<en.size();i++)
			{
				Random ran = new Random();
				int ran1 = ran.nextInt(100);
				int ran2 = ran.nextInt(100);
				stack.add(new ItemStack(fishItem(en.get(i))));
				if (ran1 <20)
				{
					stack.add(new ItemStack(ItemInit.SHRIMP));
				}
				if (ran2 <20)
				{
					stack.add(new ItemStack(ItemInit.CRAB));
				}
				en.get(i).remove();
			}
			return stack;
		}
		else return null;
	}
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
	      builder.add(HAS_BAIT);
	      super.fillStateContainer(builder);
	}
	public static EntityType<?> randomFish(Random ran)
	{
		int rand = ran.nextInt(3);
		switch (rand)
		{
		case 0 :return EntityType.TROPICAL_FISH;
		case 1 :return EntityType.COD;
		case 2 :return EntityType.SALMON;
		}
		return null;
	}
	public static Item fishItem(AbstractFishEntity fish)
	{
		if (fish instanceof CodEntity)return Items.COD;
		if (fish instanceof TropicalFishEntity)return Items.TROPICAL_FISH;
		if (fish instanceof SalmonEntity)return Items.SALMON;
		else return Items.COD;

	}
	
}
