package pl.grzegorz2047.aceofspadesminigame.GameData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

public class StoringData {

	public static HashMap<Integer,Player> snowballsID = new HashMap<Integer,Player>();
	public static List<BlockState> listaZniszczonychBlokow= new ArrayList<BlockState>();
	public static List<BlockState> listaStworzonychBlokow= new ArrayList<BlockState>();
	public static List<Player> Team1= new ArrayList<Player>();
	public static List<Player> Team2= new ArrayList<Player>();
	public static List<Player> Team3= new ArrayList<Player>();
	public static List<Player> Team4= new ArrayList<Player>();
	public static enum ArenaMode {
		INGAME,LOBBY,INACTIVE,COUNTING
	}
	
	public static void przywrocMape(){
		for(BlockState b: StoringData.listaZniszczonychBlokow){
			MaterialData md =b.getData();
			b.update(true);
			b.setData(md);
		}
		for(BlockState b: StoringData.listaStworzonychBlokow){
			MaterialData md =b.getData();
			b.update(true);
			b.setData(md);
		}
	}
}
