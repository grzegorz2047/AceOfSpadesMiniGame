package pl.grzegorz2047.aceofspadesminigame.GameData;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayersStatsboard {
	 public static void NewStats(Player p) throws Exception{
		 p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		  ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard  board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("Statystyki","dummy");
			objective.setDisplayName("Info");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			Score score = objective.getScore(Bukkit.getOfflinePlayer("§6Naboje"));
			score.setScore(2000);
			p.setScoreboard(board);
	 }
	 public static int IleNaboi(Player p){
			Scoreboard  board = p.getScoreboard();
			Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
			Score score = objective.getScore(Bukkit.getOfflinePlayer("§6Naboje"));
			return score.getScore();
	 }
	 public static void UstawCzas(Player p,int czas){
		 Scoreboard  board = p.getScoreboard();
			Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
			objective.setDisplayName(formatIntoHHMMSS(czas)+" Info");
			p.setScoreboard(board);
			
		 
	 }
	 public static void odejmijNaboje(Player p){
			Scoreboard  board = p.getScoreboard();
			Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
			Score score = objective.getScore(Bukkit.getOfflinePlayer("§6Naboje"));
			score.setScore(score.getScore()-1);
			p.setScoreboard(board);
	 }
	 
	 
		static String formatIntoHHMMSS(int secsIn)
		{

			int remainder = secsIn % 3600,
			minutes = remainder / 60,
			seconds = remainder % 60;

			return ( (minutes < 10 ? "0" : "") + minutes
			+ ":" + (seconds< 10 ? "0" : "") + seconds );

			}
}
