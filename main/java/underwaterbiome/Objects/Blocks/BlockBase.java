package underwaterbiome.Objects.Blocks;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import underwaterbiome.Inits.BlockInit;
import underwaterbiome.Inits.ItemInit;
import underwaterbiome.Utils.Reference;

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
