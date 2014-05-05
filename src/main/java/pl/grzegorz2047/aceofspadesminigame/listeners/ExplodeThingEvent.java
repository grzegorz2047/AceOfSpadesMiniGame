package pl.grzegorz2047.aceofspadesminigame.listeners;

import java.util.Iterator;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;

public class ExplodeThingEvent implements Listener {

	@EventHandler
	   public void onEntityExplode(EntityExplodeEvent event) {
	        List<Block> destroyed = event.blockList();
	        Iterator<Block> it = destroyed.iterator();
	        while (it.hasNext()) {
	            Block block = it.next();
	            StoringData.listaZniszczonychBlokow.add(block.getState());
	        }
	    }
	
}
