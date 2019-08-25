package underwaterbiome.Objects.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import underwaterbiome.Inits.ItemInit;
import underwaterbiome.Utils.Reference;

public class ItemBase extends Item
{
	public ItemBase(String name, ItemGroup group) 
	{
		super(new Item.Properties().group((group)));
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		
		ItemInit.ITEMS.add(this);
	}

}
