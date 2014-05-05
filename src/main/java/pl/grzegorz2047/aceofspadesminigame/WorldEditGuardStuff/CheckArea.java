package pl.grzegorz2047.aceofspadesminigame.WorldEditGuardStuff;

import static com.sk89q.worldguard.bukkit.BukkitUtil.toVector;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class CheckArea {
   // private Main plugin;
	public CheckArea(/*Main instance*/) {
		//plugin = instance;
	}
	
	  public static boolean  SprawdzCzyWterenie(Location loc,String nazwaAreny)
	  {
		  WorldGuardPlugin wg = getWorldGuard();
		  Vector pt = toVector(loc); // This also takes a location
		  RegionManager regionManager = wg.getRegionManager(loc.getWorld());
		  ApplicableRegionSet set = regionManager.getApplicableRegions(pt);
		  for (ProtectedRegion region : set) {
				   if(region.getId().equalsIgnoreCase(nazwaAreny)){
					  // System.out.println("Jestes w tym regionie"); 
					   //p.sendMessage("Jestes w tym regionie");
					   return true;
				   }
			}
 
		return false;

	  }
	  
		private static WorldGuardPlugin getWorldGuard() {
		    Plugin wg = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		 
		    // WorldGuard may not be loaded
		    if (wg == null || !(wg instanceof WorldGuardPlugin)) {
		       System.out.println("Nie ma pluginu WorldGuard!");
		    }
		 
		    return (WorldGuardPlugin) wg;
		}
}
