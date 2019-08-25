package JWW.underwaterbiome.Inits;

import java.util.ArrayList;
import java.util.List;

import JWW.underwaterbiome.Objects.Items.ItemBase;
import JWW.underwaterbiome.Utils.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemInit 
{
	public static ItemGroup itemGroup = new ItemGroup(Reference.MODID+"_items") 
	{
        @Override
        public ItemStack createIcon() 
        {
            return new ItemStack(Items.COOKIE);
        }
    };
    
	public static final List<Item> ITEMS = new ArrayList<Item>();

	//Items
	public static final Item ITEMBASE = new ItemBase("itembase", itemGroup);
	
	
}
