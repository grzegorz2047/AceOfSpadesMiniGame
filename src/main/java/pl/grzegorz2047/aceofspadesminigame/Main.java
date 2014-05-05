package pl.grzegorz2047.aceofspadesminigame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

//import pl.grzegorz2047.aos.CraftbukkitStuff.HarderBlocks;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;
//import pl.grzegorz2047.aos.GameData.StoringData.ArenaMode;
import pl.grzegorz2047.aceofspadesminigame.commands.SetTeamSpawnCommand;
import pl.grzegorz2047.aceofspadesminigame.commands.StartGameCommand;
import pl.grzegorz2047.aceofspadesminigame.commands.TeamCommand;
import pl.grzegorz2047.aceofspadesminigame.listeners.BlockSnowballs;
import pl.grzegorz2047.aceofspadesminigame.listeners.BreakBlockEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.DamagePlayerSystemEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.ExplodeThingEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.PlaceBlockEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.PlayerDeadEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.PlayerEventInteractGun;
import pl.grzegorz2047.aceofspadesminigame.listeners.PlayerJoinServerEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.PlayerLeaveEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.PlayerLoginServerEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.ServerListEvent;
import pl.grzegorz2047.aceofspadesminigame.listeners.SnowballBlockDamageEvent;

public class Main extends JavaPlugin{
	
	
	static File TeamSpawnFile;
	static FileConfiguration TeamSpawnConfig;
	static FileConfiguration config;
	
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		//HarderBlocks.HardBlocks();
		RegisterEvents();
		PrepareCommands();
		Game.SetArenaState(ArenaMode.LOBBY);
		Bukkit.getScheduler().runTaskTimer(this, new PilnowanieKtoZostal(), 0, 20l);
		
	    
		
		try {
	    	PierwszeUruchomienie();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		TeamSpawnFile = new File(this.getDataFolder(), "TeamSpawn.yml");
		TeamSpawnConfig = new YamlConfiguration();
		config = this.getConfig();
		System.out.println(this.getName()+" zostal wlaczony!");
		
	}
	
	
		@Override
	public void onDisable() {
		StoringData.przywrocMape();
		for(Player p : Bukkit.getOnlinePlayers()){
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			Game.SetArenaState(ArenaMode.LOBBY);
		}
	}


		void RegisterEvents(){
			Bukkit.getServer().getPluginManager().registerEvents(new BlockSnowballs(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new PlayerEventInteractGun(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new SnowballBlockDamageEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new DamagePlayerSystemEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new PlaceBlockEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new BreakBlockEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new ExplodeThingEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinServerEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new PlayerLoginServerEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new ServerListEvent(this), this);
			Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeadEvent(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
		}
		void PrepareCommands(){
	        getCommand("setteamspawn").setExecutor(new SetTeamSpawnCommand(this));
	        getCommand("aos").setExecutor(new StartGameCommand());
	        getCommand("team").setExecutor(new TeamCommand());
		}
		private void PierwszeUruchomienie() throws Exception {
			 File TeamSpawnFile= new File(this.getDataFolder(), "TeamSpawn.yml");
		    if(!TeamSpawnFile.exists()){
		    	System.out.println("Nie ma pliku TeamSpawn.yml - Tworzenie...");
		    	TeamSpawnFile.getParentFile().mkdirs();
		    	kopiujPlik(getResource("TeamSpawn.yml"), TeamSpawnFile);
		    }
		}

		private void kopiujPlik(InputStream in, File file) {
		    try {
		        OutputStream out = new FileOutputStream(file);
		        byte[] buf = new byte[1024];
		        int len;
		        while((len=in.read(buf))>0){
		            out.write(buf,0,len);
		        }
		        out.close();
		        in.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		public static void TeleportPlayerToTeamsSpawn(Player p,int team){
			if(team==1){
				try{
				wczytajTeamSpawn();
				String swiat = TeamSpawnConfig.getString("spawns.team1.w");
	            double posx =  TeamSpawnConfig.getDouble("spawns.team1.x");
	            double posy =  TeamSpawnConfig.getDouble("spawns.team1.y")+2;
	            double posz =  TeamSpawnConfig.getDouble("spawns.team1.z");
	            float posp = (float)  TeamSpawnConfig.getDouble("spawns.team1.p");
	            float posa = (float) TeamSpawnConfig.getDouble("spawns.team1.a");
	            Location loc= new Location(Bukkit.getWorld(swiat), posx, posy, posz,posp,posa);
				//System.out.println("Teleportuje gracza!");
	            p.teleport(loc);
				}
				catch(NullPointerException e){
					System.out.println("Czemu nie ma spawnu druzyny 1 ?!");
				}
			}
			else if(team==2){
				try{
					wczytajTeamSpawn();
				String swiat = TeamSpawnConfig.getString("spawns.team2.w");
	            double posx =  TeamSpawnConfig.getDouble("spawns.team2.x");
	            double posy =  TeamSpawnConfig.getDouble("spawns.team2.y")+2;
	            double posz =  TeamSpawnConfig.getDouble("spawns.team2.z");
	            float posp = (float)  TeamSpawnConfig.getDouble("spawns.team2.p");
	            float posa = (float) TeamSpawnConfig.getDouble("spawns.team2.a");
	            Location loc= new Location(Bukkit.getWorld(swiat), posx, posy, posz,posp,posa);
				p.teleport(loc);
				}
				catch(NullPointerException e){
					System.out.println("Czemu nie ma spawnu druzyny 2 ?!");
				}
			}
		}
		
		public static void DodajGraczyBezDruzynDoDruzyn(){
			
			for(Player p : Bukkit.getOnlinePlayers()){
				if(!(StoringData.Team1.contains(p))&& !(StoringData.Team2.contains(p))){
					int liczbaTeam1 =config.getInt("Team1Size");
					int liczbaTeam2 =config.getInt("Team2Size");
					if(StoringData.Team1.size()<liczbaTeam1){
						StoringData.Team1.add(p);
					}
					else if(StoringData.Team2.size()<liczbaTeam2){
						StoringData.Team2.add(p);
					}
				}
		     }
		}
		
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
