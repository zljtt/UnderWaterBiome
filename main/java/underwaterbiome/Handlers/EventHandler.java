package underwaterbiome.Handlers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class EventHandler 
{
	Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent
    public void onPlayerLogsIn(PlayerLoggedInEvent event)
    {
//		event.getPlayer().setFire(10);;
    }

}
