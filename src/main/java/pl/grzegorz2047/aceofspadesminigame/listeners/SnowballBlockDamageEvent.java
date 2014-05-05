package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.BlockIterator;

import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;
import pl.grzegorz2047.aceofspadesminigame.WorldEditGuardStuff.CheckArea;

public class SnowballBlockDamageEvent implements Listener{
	
	@EventHandler
	public void onSnowballDamage(ProjectileHitEvent e) {
	 
	    if(!(e.getEntity() instanceof Snowball))
	        return;
	    
 
	    if(!StoringData.snowballsID.containsKey(e.getEntity().getEntityId()))
	        return;
	   
	    // Apply potion effect
	    // ..
	 
	    // Spawn the block beneath the hit entity's position
	    // Have a play around with this until you get it how you want
	    Location loc=null;
	    loc = new Location(e.getEntity().getLocation().getWorld(),e.getEntity().getLocation().getX(),e.getEntity().getLocation().getY()-1,e.getEntity().getLocation().getZ());
	    if(loc.getBlock().getType()==Material.AIR){
	    	loc = new Location(e.getEntity().getLocation().getWorld(),e.getEntity().getLocation().getX(),e.getEntity().getLocation().getY()+1,e.getEntity().getLocation().getZ());
	    	loc.getWorld().createExplosion(loc, 0.5f, true);
	    }
	    		Projectile shot = e.getEntity();
    	if(CheckArea.SprawdzCzyWterenie(loc, "spawn")==false){
    		
			Block block = loc.getBlock();
			if (block.getType() != Material.AIR&&block.getType() != Material.BEDROCK)
			{
				StoringData.listaStworzonychBlokow.add(block.getState());
			  block.breakNaturally();
			} else {
			  BlockIterator iterator = new BlockIterator(loc.getWorld(), loc.toVector(), shot.getVelocity().normalize(), 0, 3);
			  while (iterator.hasNext())
			  {
			    if (iterator.next().getType() != Material.AIR)
			    {
			    	block.breakNaturally();
					StoringData.listaStworzonychBlokow.add(block.getState());
			      break;
			    }
			  }
			}
    	}
	    
	 
	    // Remove from list
	    StoringData.snowballsID.remove((Integer) e.getEntity().getEntityId());
	}
}
