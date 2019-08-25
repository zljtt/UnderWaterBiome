package JWW.underwaterbiome.Objects.Items;

import JWW.underwaterbiome.Inits.ItemInit;
import JWW.underwaterbiome.Utils.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item
{
	public ItemBase(String name, ItemGroup group) 
	{
		super(new Item.Properties().group((group)));
		setRegistryName(new ResourceLocation(Reference.MODID,name));
		
		ItemInit.ITEMS.add(this);
	}

}
