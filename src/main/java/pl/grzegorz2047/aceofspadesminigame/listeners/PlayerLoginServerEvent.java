package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import pl.grzegorz2047.aceofspadesminigame.Game;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;


public class PlayerLoginServerEvent implements Listener {

	@EventHandler
	void PlayerLogin (PlayerLoginEvent e){
		if(Game.CheckArenaState()== ArenaMode.LOBBY){
			if(e.getResult() != Result.KICK_FULL){
				if(e.getPlayer().isOnline()){
					e.disallow(Result.KICK_OTHER, "Taki nick jest juz na serwerze! Przypadek?!");
					return;
				}
			}
			if(e.getResult() == Result.KICK_FULL){
				e.disallow(Result.KICK_OTHER, "Serwer jest pelny!");
				return;
			}
		}
		if(Game.CheckArenaState()== ArenaMode.INGAME){
			if(e.getResult() != Result.KICK_FULL){
				if(e.getPlayer().hasPermission("aos.vip")){
					//Wlacz mu obserwatora
				}
				else{
					e.disallow(Result.KICK_OTHER, "Na serwerze trwa juz rozgrywka! Aby obserwowac potrzebujesz vipa!");
				}
			}
			if(e.getResult() == Result.KICK_FULL){
				e.disallow(Result.KICK_OTHER, "Obserwowac moze tylko vip!");
				return;
				
			}
		}
	}
}
