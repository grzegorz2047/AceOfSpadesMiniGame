package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;
import pl.grzegorz2047.aceofspadesminigame.WorldEditGuardStuff.CheckArea;

public class DamagePlayerSystemEvent implements Listener{
	
	
	@EventHandler
	public void onSnowballDamage(EntityDamageByEntityEvent e) {
	    // Check if damager is a snowball 
	    Entity damager = e.getDamager();
	    Entity entity = e.getEntity();
	    if(!(damager instanceof Snowball))
	        return;
	    
	    // Check snowball was launched with a stick
	    if(!StoringData.snowballsID.containsKey(damager.getEntityId()))
	        return;
	    Player Attacker= StoringData.snowballsID.get(damager.getEntityId()); 
	    	if(!(entity instanceof Player))
	    		return;
	    	Player p = (Player) entity;
	    // Apply potion effect
	    // ..
	    // Spawn the block beneath the hit entity's position
	    // Have a play around with this until you get it how you want
	    System.out.println("Powinno tu byc bliziutko");
	    Location loc = entity.getLocation();
	    	if(CheckArea.SprawdzCzyWterenie(loc, "spawn")==false){
	    		System.out.println("Powinno tu byc bicie");
	    		  if(StoringData.Team1.contains(p)){
	    			  if(StoringData.Team1.contains(Attacker)){
	    				  
	    			  }
	    			  else{
	    				  p.damage(2);
	    			  }
	    		  }
	    		  if(StoringData.Team2.contains(p)){
	    			  if(StoringData.Team2.contains(Attacker)){
	    				 // Attacker.sendMessage(PluginPrefixes.getPrefix()+ChatColor.RED+"Ni")
	    			  }
	    			  else{
	    				  p.damage(2);
	    			  }
	    		  }
	    	}
	    	
	    // Remove from list
	    StoringData.snowballsID.remove((Integer) damager.getEntityId());
	}

	
}
