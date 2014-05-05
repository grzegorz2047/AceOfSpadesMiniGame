package pl.grzegorz2047.aceofspadesminigame;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.grzegorz2047.aceofspadesminigame.GameData.PlayersStatsboard;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;
import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData.ArenaMode;



public class Countdown{
	
	
	 private boolean countdownRunning;
	 static int counttime = 0;
	 public	 int threadsync = 0;
	 
	public void countdown(int time)
	{
	  this.threadsync += 1;
	  this.countdownRunning = true;
	  this.counttime = time;
	 // System.out.println("czas "+time);
		for(Player p : Bukkit.getOnlinePlayers()){
			if(Game.CheckArenaState()==ArenaMode.COUNTING){
				p.setLevel(time);
			}
			if(Game.CheckArenaState()==ArenaMode.INGAME){
				PlayersStatsboard.UstawCzas(p,time);
			}

			 if(Game.CheckArenaState()==ArenaMode.LOBBY){
				 this.countdownRunning = false;
			 }
			
			
		}
		if(time==10){
			Bukkit.broadcastMessage(PluginPrefixes.getPrefix()+"Pozostalo 10 sekund do rozpoczecia gry!");
		}
	  if(time >0 && time<11){
		  for (Player p : Bukkit.getOnlinePlayers()) {
	      p.playSound(p.getLocation(), Sound.FIRE_IGNITE,2,1);
		  }
	  }
	  if (time < 6) {
	    for (Player p : Bukkit.getOnlinePlayers()) {
	      p.sendMessage(PluginPrefixes.getPrefix()+"Pozostalo "+time+" sekund do rozpoczecia Gry");
	       
	    }
	  }
	  if ((time > 0)) {
	    new CountdownThread(time).start();
	    
	  }
	  else if ((time <= 0)) {
	    this.countdownRunning = false;
	    if(Game.CheckArenaState()==ArenaMode.COUNTING){
	    	 WykonajPoLobby();
	 	    Game.SetArenaState(ArenaMode.INGAME);
	 	    
	    	 countdown(900);
	    }
	    if(Game.CheckArenaState()==ArenaMode.INGAME){
	    	
	    }
	  }
	}

	public static int getCountdownTime()
	{
	  return counttime;
	}

	class CountdownThread extends Thread
	{
	  int time;
	  int trun = Countdown.this.threadsync;

	  public CountdownThread(int t) { this.time = t; }

	  public void run() {
	    this.time -= 1;
	    try { Thread.sleep(1000L); } catch (Exception localException) {}
	    if (this.trun == Countdown.this.threadsync)
	    	Countdown.this.countdown(this.time);
	    
	  }
	}
	
	public void WykonajPoLobby(){
		Main.DodajGraczyBezDruzynDoDruzyn();
	    for(Player p : Bukkit.getOnlinePlayers()){
	    	if(StoringData.Team1.contains(p)){
	    		Main.TeleportPlayerToTeamsSpawn(p, 1);
	    	}
	    	if(StoringData.Team2.contains(p)){
	    		Main.TeleportPlayerToTeamsSpawn(p, 2);
	    	}
	    	
	    	p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD,1));
	    	p.sendMessage(PluginPrefixes.getPrefix()+"Blaze rod jest twoja bronia! Powodzenia");
	    	try {
				PlayersStatsboard.NewStats(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}
