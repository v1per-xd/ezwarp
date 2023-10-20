package xd.viper.ezwarp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xd.viper.ezwarp.Main;
import xd.viper.ezwarp.WarpSystem;

public class SetWarpCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("setwarp")) {
            if(args.length > 0) {
                if (sender instanceof ConsoleCommandSender) {
                    Bukkit.getLogger().info(Main.prefixWC + "This command can only be run by a player.");
                } else if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Bukkit.getLogger().info(Main.prefixWC + player.getName() + " is creating the warp \"" + args[0] + "\"!");
                    WarpSystem.setWarp(player, args[0]);
                }
            } else {
                if (sender instanceof ConsoleCommandSender) {
                    Bukkit.getLogger().info(Main.prefixWC + "This command can only be run by a player.");
                }  else if (sender instanceof Player){
                    Player player = (Player) sender;
                    player.sendMessage(Main.prefix + "You didn't enter a warp name.");
                }
            }
            return true;
        }
        return false;
    }
}