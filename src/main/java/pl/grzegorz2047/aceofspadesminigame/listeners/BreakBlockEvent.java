package pl.grzegorz2047.aceofspadesminigame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import pl.grzegorz2047.aceofspadesminigame.GameData.StoringData;

public class BreakBlockEvent implements Listener{
	
	@EventHandler
	public void LapBlokiZniszczone(BlockBreakEvent e){
		StoringData.listaZniszczonychBlokow.add(e.getBlock().getState());

	}
}
