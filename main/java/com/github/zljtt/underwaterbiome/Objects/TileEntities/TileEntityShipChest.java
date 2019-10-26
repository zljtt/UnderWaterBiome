package com.github.zljtt.underwaterbiome.Objects.TileEntities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntityShipChest extends LockableLootTileEntity implements ITickableTileEntity 
{
	@ObjectHolder("underwaterbiome:ship_chest")
	public static TileEntityType<TileEntityShipChest> TYPE;

	private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);
	protected int numPlayersUsing;
	private int ticksSinceSync;
	private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;

	protected TileEntityShipChest(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	public TileEntityShipChest() {
		this(TYPE);
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.chestContents) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.chest");
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound)) {
			ItemStackHelper.loadAllItems(compound, this.chestContents);
		}

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.chestContents);
		}

		return compound;
	}

	@Override
	public void tick() 
	{
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		++this.ticksSinceSync;
		this.numPlayersUsing = changePlayerNumByTick(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
	}

	public static int changePlayerNumByTick(World world, LockableTileEntity tileentity, int tick, int x, int y, int z, int player) 
	{
		if (!world.isRemote && player != 0 && (tick + x + y + z) % 200 == 0) 
		{
			player = changePlayerNum(world, tileentity, x, y, z);
		}

		return player;
	}

	public static int changePlayerNum(World world, LockableTileEntity tileentity, int x, int y, int z) 
	{
		int i = 0;
		float f = 5.0F;

		for (PlayerEntity playerentity : world.getEntitiesWithinAABB(PlayerEntity.class,
				new AxisAlignedBB(x - f, y - f, z - f, x + 1 + f, y + 1 + f, z + 1 + f))) 
		{
			if (playerentity.openContainer instanceof ChestContainer)
			{
				IInventory iinventory = ((ChestContainer) playerentity.openContainer).getLowerChestInventory();
				if (iinventory == tileentity || iinventory instanceof DoubleSidedInventory
						&& ((DoubleSidedInventory) iinventory).isPartOfLargeChest(tileentity)) 
				{
					++i;
				}
			}
		}

		return i;
	}

	/**
	 * See {@link Block#eventReceived} for more information. This must return true
	 * serverside before it is called clientside.
	 */
	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.numPlayersUsing = type;
			return true;
		} else {
			return super.receiveClientEvent(id, type);
		}
	}

	@Override
	public void openInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			if (this.numPlayersUsing < 0) {
				this.numPlayersUsing = 0;
			}

			++this.numPlayersUsing;
			this.onOpenOrClose();
		}

	}

	@Override
	public void closeInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			--this.numPlayersUsing;
			this.onOpenOrClose();
		}

	}

	protected void onOpenOrClose() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof ContainerBlock) {
			this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, block);
		}

	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.chestContents;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.chestContents = itemsIn;
	}

	public static int getPlayersUsing(IBlockReader reader, BlockPos posIn) {
		BlockState blockstate = reader.getBlockState(posIn);
		if (blockstate.hasTileEntity()) {
			TileEntity tileentity = reader.getTileEntity(posIn);
			if (tileentity instanceof TileEntityShipChest) {
				return ((TileEntityShipChest) tileentity).numPlayersUsing;
			}
		}

		return 0;
	}

	public static void swapContents(TileEntityShipChest chest, TileEntityShipChest otherChest) {
		NonNullList<ItemStack> nonnulllist = chest.getItems();
		chest.setItems(otherChest.getItems());
		otherChest.setItems(nonnulllist);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return ChestContainer.createGeneric9X3(id, player, this);
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		if (this.chestHandler != null) {
			this.chestHandler.invalidate();
			this.chestHandler = null;
		}
	}

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(
			net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
		if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (this.chestHandler == null) {
				this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			}
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private net.minecraftforge.items.IItemHandlerModifiable createHandler() 
	{
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof ContainerBlock)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		ChestType type = state.get(ChestBlock.TYPE);
		if (type != ChestType.SINGLE) 
		{
			BlockPos opos = this.getPos().offset(ChestBlock.getDirectionToAttached(state));
			BlockState ostate = this.getWorld().getBlockState(opos);
			if (state.getBlock() == ostate.getBlock()) 
			{
				ChestType otype = ostate.get(ChestBlock.TYPE);
				if (otype != ChestType.SINGLE && type != otype
						&& state.get(ChestBlock.FACING) == ostate.get(ChestBlock.FACING)) 
				{
					TileEntity ote = this.getWorld().getTileEntity(opos);
					if (ote instanceof TileEntityShipChest)
					{
						IInventory top = type == ChestType.RIGHT ? this : (IInventory) ote;
						IInventory bottom = type == ChestType.RIGHT ? (IInventory) ote : this;
						return new net.minecraftforge.items.wrapper.CombinedInvWrapper(
								new net.minecraftforge.items.wrapper.InvWrapper(top),
								new net.minecraftforge.items.wrapper.InvWrapper(bottom));
					}
				}
			}
		}
		return new net.minecraftforge.items.wrapper.InvWrapper(this);
	}

	/**
	 * invalidates a tile entity
	 */
	@Override
	public void remove() {
		super.remove();
		if (chestHandler != null)
			chestHandler.invalidate();
	}

}
