package org.metamechanists.displaymodellib;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;


@UtilityClass
public class Utils {
    public int getMajorServerVersion() {
        Bukkit.getLogger().info(Bukkit.getServer().getBukkitVersion());
        return Integer.parseInt(Bukkit.getServer().getBukkitVersion().split("\\.")[1]);
    }
}
