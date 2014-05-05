package pl.grzegorz2047.aceofspadesminigame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.grzegorz2047.aceofspadesminigame.Game;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;
import pl.grzegorz2047.aceofspadesminigame.PluginPrefixes;

public class TeamCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args)
	{
		if(!(sender instanceof Player)){
			sender.sendMessage("Komenda jedynie dla gracza!");
			return false;
		}
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("team")){
			if(p.hasPermission("aos.team")){
				if(args.length>0){
					if(args[0].equalsIgnoreCase("1")){
						if(Game.CheckArenaState()==ArenaMode.LOBBY||Game.CheckArenaState()==ArenaMode.COUNTING){
							Game.PlayerAddTeam(p,1);
							p.sendMessage(PluginPrefixes.getPrefix()+"Tu komenda dolaczy cie do druzyny 1");
							return true;
						}
						else{
							p.sendMessage(PluginPrefixes.getPrefix()+"Nie mozesz dolaczyc do druzyny podczas gry!");
						}
					}
					if(args[0].equalsIgnoreCase("2")){
						if(Game.CheckArenaState()==ArenaMode.LOBBY||Game.CheckArenaState()==ArenaMode.COUNTING){
							Game.PlayerAddTeam(p,2);
							p.sendMessage(PluginPrefixes.getPrefix()+"Tu komenda dolaczy cie do druzyny 2");
							return true;	
						}
						else{
							p.sendMessage(PluginPrefixes.getPrefix()+"Nie mozesz dolaczyc do druzyny podczas gry!");
						}
					}
				}
			}
		}
		return false;
	}
}
