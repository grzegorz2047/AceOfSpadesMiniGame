package pl.grzegorz2047.aceofspadesminigame.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import pl.grzegorz2047.aceofspadesminigame.Main;
import pl.grzegorz2047.aceofspadesminigame.PluginPrefixes;

public class SetTeamSpawnCommand implements CommandExecutor{

	Main plugin;
	 File TeamSpawnFile;
	 FileConfiguration TeamSpawnConfig;
	 
	public SetTeamSpawnCommand(Main instance){
		plugin= instance;
		TeamSpawnFile = new File(plugin.getDataFolder(), "TeamSpawn.yml");
		TeamSpawnConfig = new YamlConfiguration();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args)
	{
		if(!(sender instanceof Player)){
			sender.sendMessage("Komenda wylacznie dla gracza!");
			return false;
		}
		Player gracz = (Player) sender;
		if(label.equalsIgnoreCase("setteamspawn")){
			if(sender.hasPermission("aos.setspawn")){
				wczytajTeamSpawn();
				if(args.length==1){
					if(args[0].equals("1")){
						Location loc= gracz.getLocation();
						TeamSpawnConfig.set("spawns.team1.w",loc.getWorld().getName() );
						TeamSpawnConfig.set("spawns.team1.x", loc.getX());
						TeamSpawnConfig.set("spawns.team1.y", loc.getY());
						TeamSpawnConfig.set("spawns.team1.z", loc.getZ());
						TeamSpawnConfig.set("spawns.team1.p", loc.getPitch());
						TeamSpawnConfig.set("spawns.team1.a", loc.getYaw());
						zapiszTeamSpawn();
			            gracz.sendMessage(PluginPrefixes.getPrefix()+ChatColor.RESET+ChatColor.GREEN+" Ustalono spawn dla druzyny 1");
			    		return true;
					}
					if(args[0].equals("2")){
						Location loc= gracz.getLocation();
						TeamSpawnConfig.set("spawns.team2.w",loc.getWorld().getName() );
						TeamSpawnConfig.set("spawns.team2.x", loc.getX());
						TeamSpawnConfig.set("spawns.team2.y", loc.getY());
						TeamSpawnConfig.set("spawns.team2.z", loc.getZ());
						TeamSpawnConfig.set("spawns.team2.p", loc.getPitch());
						TeamSpawnConfig.set("spawns.team2.a", loc.getYaw());
						zapiszTeamSpawn();
			            gracz.sendMessage(PluginPrefixes.getPrefix()+ChatColor.RESET+ChatColor.GREEN+" Ustalono spawn dla druzyny 2");
			    		return true;
					}
				}
			}
			else{
				sender.sendMessage(PluginPrefixes.getPrefix()+ChatColor.RED+"Nie masz do tego uprawnien!");
				return false;
			}
		}
		return false;
	}
    public void wczytajTeamSpawn() {
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
