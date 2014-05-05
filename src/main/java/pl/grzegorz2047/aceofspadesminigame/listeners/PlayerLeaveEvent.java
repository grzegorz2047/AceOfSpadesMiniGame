package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;

import pl.grzegorz2047.aceofspadesminigame.Game;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;

public class PlayerLeaveEvent implements Listener{

	
	@EventHandler
	void onLeave(PlayerQuitEvent e){
		e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		if(StoringData.Team1.contains(e.getPlayer())){
			Game.RemovePlayer(e.getPlayer(), 1, "Opusciles rozgrywke!");
		}
		if(StoringData.Team2.contains(e.getPlayer())){
			Game.RemovePlayer(e.getPlayer(), 2, "Opusciles rozgrywke!");
		}
	}
	
}
