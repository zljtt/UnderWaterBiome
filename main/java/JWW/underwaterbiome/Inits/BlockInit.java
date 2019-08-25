package JWW.underwaterbiome.Inits;

import java.util.ArrayList;
import java.util.List;

import JWW.underwaterbiome.Objects.Blocks.BlockBase;
import JWW.underwaterbiome.Utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BlockInit 
{
	public static ItemGroup blockGroup = new ItemGroup(Reference.MODID+"_blocks") 
	{
        @Override
        public ItemStack createIcon() 
        {
            return new ItemStack(Items.STRIPPED_ACACIA_LOG);
        }
    };
    
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
	public static final Block BLOCKBASE = new BlockBase("blockbase", Material.ROCK, SoundType.STONE);
}
