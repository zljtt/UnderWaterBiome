package underwaterbiome.World;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class WorldTypeOcean extends WorldType
{

	public WorldTypeOcean() 
	{
		super("ocean");
	}
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) 
	{
		OverworldGenSettings setting = new OverworldGenSettings();
		return new OverworldChunkGenerator(world, new BiomeProviderUnderWater(world, setting), setting);
	}
}
