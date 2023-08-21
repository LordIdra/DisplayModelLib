package org.metamechanists.displaymodellib;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;


@UtilityClass
public class Utils {
    public int getMajorServerVersion() {
        return Integer.parseInt(Bukkit.getServer().getMinecraftVersion().split("\\.")[1]);
    }
}
