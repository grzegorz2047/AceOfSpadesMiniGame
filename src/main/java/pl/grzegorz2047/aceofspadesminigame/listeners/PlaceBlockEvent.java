package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;

public class PlaceBlockEvent implements Listener{
	@EventHandler
	public void LapBlokiStworzone(BlockPlaceEvent e){
		
		StoringData.listaStworzonychBlokow.add(e.getBlockReplacedState());
	}
}
