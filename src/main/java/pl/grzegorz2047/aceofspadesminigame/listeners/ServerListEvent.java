package pl.grzegorz2047.aceofspadesminigame.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import pl.grzegorz2047.aceofspadesminigame.Countdown;
import pl.grzegorz2047.aceofspadesminigame.Game;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;
import pl.grzegorz2047.aceofspadesminigame.Main;

public class ServerListEvent implements Listener {
	public Main plugin;
	 FileConfiguration dane;
	 File PlikDanych;
	 
	
	
	public ServerListEvent(Main instance) {

	//	System.out.println("Konstruktor wykonal sie!");
	}
	
	@EventHandler
	void ListaSerwerowEvent(ServerListPingEvent e){
		Game.getInstance();
		if(Game.CheckArenaState()== ArenaMode.LOBBY){
			int roznicaLudzi = e.getMaxPlayers()-e.getNumPlayers();
			e.setMotd("§2|| AoS ||§6 Czekamy na "+roznicaLudzi+" graczy");
		}
		if(Game.CheckArenaState()== ArenaMode.INGAME){
			e.setMotd("§2|| AoS ||§6 Serwer podczas gry!");
		}
		if(Game.CheckArenaState()== ArenaMode.COUNTING){
			e.setMotd("§2|| AoS ||§6 Odliczanie do startu gry "+Countdown.getCountdownTime());
		}
		
	}
	
	
	
    public void wczytajPlikDane() {
        try {
            dane.load(PlikDanych); //Wczytuje plik z ca§§ konfiguracj§
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void zapiszPlikDane() {
        try {
            dane.save(PlikDanych);//Zapisuje konfiguracj§ do pliku

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
