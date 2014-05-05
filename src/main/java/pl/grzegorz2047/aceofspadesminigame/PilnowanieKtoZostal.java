package pl.grzegorz2047.aceofspadesminigame;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;

public class PilnowanieKtoZostal implements Runnable {
	
	public PilnowanieKtoZostal() {
	}

	@Override
	public void run() {
		if(Game.CheckArenaState()==ArenaMode.INGAME){

			if(Bukkit.getOnlinePlayers().length==1){
				for(Player p: Bukkit.getOnlinePlayers()){
				   if(StoringData.Team1.contains(p)){
					   p.kickPlayer("Wygrales bitwe");
				   }
				   else if(StoringData.Team2.contains(p)){
					   p.kickPlayer("Wygrales bitwe");
				   }
				   else{
					   p.kickPlayer("Wygrana, ale nie byles w teamie!?");
				   }
				}
				Game.SetArenaState(ArenaMode.LOBBY);
			}
			
			else if(Bukkit.getOnlinePlayers().length==0){
				Game.SetArenaState(ArenaMode.LOBBY);
			}
			
			else if(!StoringData.Team1.isEmpty()&&StoringData.Team2.isEmpty()){
			 for(Player p : Bukkit.getOnlinePlayers()){
				 if(StoringData.Team1.contains(p)){
					 p.kickPlayer("Wygrala druzyna 1");
				 }	 
			 }		
			 Game.SetArenaState(ArenaMode.LOBBY);
			}
						
			else if(!StoringData.Team2.isEmpty()&&StoringData.Team1.isEmpty()){
			 for(Player p : Bukkit.getOnlinePlayers()){
				 if(StoringData.Team2.contains(p)){
					 p.kickPlayer("Wygrala druzyna 2");
				 }	 
			 }		
			 Game.SetArenaState(ArenaMode.LOBBY);
			}
		}
		}
}
