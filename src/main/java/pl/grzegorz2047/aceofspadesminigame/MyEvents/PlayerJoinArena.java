package pl.grzegorz2047.aceofspadesminigame.MyEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinArena extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
 
    public PlayerJoinArena(Player p) {
        player = p;
    }
 
    public Player player() {
        return player;
    }
 
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
