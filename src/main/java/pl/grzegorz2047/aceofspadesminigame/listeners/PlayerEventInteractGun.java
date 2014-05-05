package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.grzegorz2047.aceofspadesminigame.Game;
import pl.grzegorz2047.aceofspadesminigame.PluginPrefixes;
import pl.grzegorz2047.aceofspadesminigame.GameData.PlayersStatsboard;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;

public class PlayerEventInteractGun implements Listener {
	
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand().getType() == Material.BLAZE_ROD){
			if(p.hasPermission("aos.usegun")){
				if(Game.CheckArenaState()==ArenaMode.INGAME){
					int AmountOfBullet=PlayersStatsboard.IleNaboi(p);
					if(AmountOfBullet>0){
						if(e.getAction()== Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
							StoringData.snowballsID.put(p.launchProjectile(Snowball.class).getEntityId(),p);
							PlayersStatsboard.odejmijNaboje(p);
						}
					}
				}
				else{
					p.sendMessage(PluginPrefixes.getPrefix()+ChatColor.RED+"Nie mozesz tego teraz uzywac!");
				}
			}
			
		}
	}
}
