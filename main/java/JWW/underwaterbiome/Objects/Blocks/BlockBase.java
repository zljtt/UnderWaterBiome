package JWW.underwaterbiome.Objects.Blocks;


import JWW.underwaterbiome.Inits.BlockInit;
import JWW.underwaterbiome.Inits.ItemInit;
import JWW.underwaterbiome.Utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block
{
	public BlockBase(String name, Material material, SoundType sound) 
	{
		super(Properties.create(material)
                .sound(sound)
                .hardnessAndResistance(1.0f));
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new BlockItem(this, new Item.Properties().group((BlockInit.blockGroup))).setRegistryName(this.getRegistryName()));

	}

}
