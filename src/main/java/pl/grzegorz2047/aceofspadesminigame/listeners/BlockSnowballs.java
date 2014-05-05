package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockSnowballs implements Listener{

	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	void onInteract(PlayerInteractEvent t){
		if(t.getPlayer().getItemInHand().getType() == Material.SNOW_BALL){
			t.getPlayer().getInventory().remove(Material.SNOW_BALL);
			t.setCancelled(true);
			return;
		}
	}
}
