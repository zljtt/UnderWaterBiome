package com.github.zljtt.underwaterbiome.Objects.Blocks;

import com.github.zljtt.underwaterbiome.Inits.BlockInit;
import com.github.zljtt.underwaterbiome.Inits.ItemInit;
import com.github.zljtt.underwaterbiome.Objects.TileEntities.TileEntityShipChest;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo;
import com.github.zljtt.underwaterbiome.Utils.BlueprintInfo.BlueprintType;
import com.github.zljtt.underwaterbiome.Utils.Reference;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedBluePrint;
import com.github.zljtt.underwaterbiome.Utils.Interface.INeedItem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockShipChest extends ContainerBlock implements INeedBluePrint, INeedItem {
	BlueprintInfo info;

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;

	public BlockShipChest(String name, Block.Properties porperty, boolean needBlueprint, BlueprintType type,
			int... difficulty) {
		super(porperty);
		setRegistryName(new ResourceLocation(Reference.MODID, name));
		this.info = new BlueprintInfo(needBlueprint, difficulty, type);
		this.setDefaultState(
				this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TYPE, ChestType.SINGLE));
		BlockInit.BLOCKS.add(this);

	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.fullCube();
	}

	@Override
	public BlueprintInfo getBlueprintInfo() {
		return info;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityShipChest();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof TileEntityShipChest) {
				player.openContainer((TileEntityShipChest) tileentity);
			}

			return true;
		}
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof IInventory) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	/**
	 * @deprecated call via {@link IBlockState#hasComparatorInputOverride()}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	/**
	 * @deprecated call via
	 *             {@link IBlockState#getComparatorInputOverride(World,BlockPos)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, TYPE);
	}
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
	}

	@Override
	public boolean needItem() {
		// TODO Auto-generated method stub
		return true;
	}

}
