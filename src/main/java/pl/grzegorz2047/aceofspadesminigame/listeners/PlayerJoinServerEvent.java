package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.grzegorz2047.aceofspadesminigame.Countdown;
import pl.grzegorz2047.aceofspadesminigame.Game;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;

public class PlayerJoinServerEvent implements Listener{

	@EventHandler
	void PlayerJoin (PlayerJoinEvent e){
		int roznicaGraczy = Bukkit.getServer().getMaxPlayers() - Bukkit.getServer().getOnlinePlayers().length;
		if(roznicaGraczy==0){
			if(Game.CheckArenaState()== ArenaMode.LOBBY){
				Game.SetArenaState(ArenaMode.COUNTING);
				Countdown licznik = new Countdown();
				licznik.countdown(20);
			}
			
		}
	}
	
}
