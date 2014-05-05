package pl.grzegorz2047.aceofspadesminigame;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;
import pl.grzegorz2047.aceofspadesminigame.Main;
/*
 * 
 *  KLASA WYKONUJACA WSZELKIE METODY ZWIAZANE Z ROZGRYWKA
 * 
 */
public class Game {
	
	static File TeamSpawnFile;
	static FileConfiguration TeamSpawnConfig;
	static ArenaMode mode = StoringData.ArenaMode.LOBBY;
	static Main plugin;
	static Game instanceGame;
	public Game(Main instance){
		plugin = instance;

	}

	public Game() {
		TeamSpawnFile = new File(plugin.getDataFolder(), "TeamSpawn.yml");
		TeamSpawnConfig = new YamlConfiguration();
		instanceGame = new Game(plugin);
	}

	public static Game getInstance(){
		return instanceGame;
	}
	
	/*
	 * 
	 * 
	 *   Dodawanie gracza do druzyny
	 * 
	 * 
	 */
	public static void PlayerAddTeam(Player p,int team){
		if(team==1){
			StoringData.Team1.add(p);
		}
		else if(team==2){
			StoringData.Team2.add(p);
		}
		else{
			System.out.println("Wprowadzono niepoprawny numer Teamu w kodzie pluginu przy dodawaniu gracza!");
		}
	}
	/*
	 * 
	 * 
	 *  USUWANIE GRACZA 
	 * 
	 * 
	 */
	public static void RemovePlayer(Player p,int team,String msg){
		p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		p.getInventory().clear();
		p.getInventory().setHelmet(new ItemStack(Material.AIR));
		p.getInventory().setLeggings(new ItemStack(Material.AIR));
		p.getInventory().setChestplate(new ItemStack(Material.AIR));
		p.getInventory().setBoots(new ItemStack(Material.AIR));
		if(team==1){
			StoringData.Team1.remove(p);
		}
		else if(team==2){
			StoringData.Team2.remove(p);
		}
		else{
			System.out.println("Wprowadzono niepoprawny numer Teamu w kodzie pluginu przy usuwaniu gracza!");
		}
		p.kickPlayer(msg);
	}
	
	
	/*
	 *  
	 *   TELEPORTACJA GRACZA
	 * 
	 * 
	 */

	
	

	/*
	 * 
	 * 
	 *  SPRAWDZANIE TRYBU ARENY
	 * 
	 */
	public static StoringData.ArenaMode CheckArenaState() {
		return mode;
	}
	public static void SetArenaState(ArenaMode a) {
		mode=a;
	}
	/*
	 * 
	 * 
	 *  ZARZADZANIE PLIKAMI KONFIGURACYJNYMI
	 * 
	 * 
	 */
	
	
    public static void wczytajTeamSpawn() {
        try {
        	TeamSpawnConfig.load(TeamSpawnFile); //Wczytuje plik z ca§§ konfiguracj§
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void zapiszTeamSpawn() {
        try {
        	TeamSpawnConfig.save(TeamSpawnFile);//Zapisuje konfiguracj§ do pliku

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
}
