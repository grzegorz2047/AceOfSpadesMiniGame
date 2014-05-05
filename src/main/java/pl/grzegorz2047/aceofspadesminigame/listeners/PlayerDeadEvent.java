package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import pl.grzegorz2047.aceofspadesminigame.Game;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;
import pl.grzegorz2047.aceofspadesminigame.PluginPrefixes;

public class PlayerDeadEvent implements Listener{

	@EventHandler
	void onDead(PlayerDeathEvent e){
		if(Game.CheckArenaState()==ArenaMode.INGAME){
			e.getEntity().kickPlayer(PluginPrefixes.getPrefix()+"Umarles! Aby obserwowac jako vip wejdz ponownie na serwer!");
		}
	}
	
}
