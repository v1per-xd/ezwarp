package xd.viper.ezwarp;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import xd.viper.ezwarp.commands.*;

public final class Main extends JavaPlugin {
    public static String prefix = "§7[§bEZ§3Warp§7]§f ";
    public static String prefixWC = "[EZWarp] ";

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(prefixWC + "Starting, thanks for using my plugin!");
        Bukkit.getLogger().info(prefixWC + "Made by @vvviperrr!");
        WarpSystem.thisPlugin = this;
        AddCommand("ezwarp",  new EzwarpCmd());
        AddCommand("setwarp",  new SetWarpCmd());
        AddCommand("delwarp",  new DelWarpCmd());
        AddCommand("warp",  new WarpCmd());
        AddCommand("warps",  new WarpsCmd());
    }

    public void AddCommand(String name, CommandExecutor executor) {
        this.getCommand(name).setExecutor(executor);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(prefixWC + "Stopping, thanks for using my plugin!");
        Bukkit.getLogger().info(prefixWC + "Made by @vvviperrr!");
    }
}