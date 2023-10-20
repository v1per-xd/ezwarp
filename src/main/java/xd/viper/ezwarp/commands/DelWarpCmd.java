package xd.viper.ezwarp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xd.viper.ezwarp.Main;
import xd.viper.ezwarp.WarpSystem;

public class DelWarpCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("delwarp")) {
            if(args.length > 0) {
                if(sender instanceof ConsoleCommandSender) {
                    Bukkit.getLogger().info(Main.prefixWC + "Console is deleting the warp \"" + args[0] + "\"!");
                    WarpSystem.delWarp(sender, args[0]);
                } else if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Bukkit.getLogger().info(Main.prefixWC + player.getName() + " is deleting the warp \"" + args[0] + "\"!");
                    WarpSystem.delWarp(player, args[0]);
                }
            } else {
                if (sender instanceof ConsoleCommandSender) {
                    Bukkit.getLogger().info(Main.prefixWC + "You didn't enter a warp name.");
                }  else if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(Main.prefix + "You didn't enter a warp name.");
                }
            }
            return true;
        }
        return false;
    }
}