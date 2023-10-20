package xd.viper.ezwarp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xd.viper.ezwarp.WarpSystem;

public class WarpsCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("warps")) {
            WarpSystem.listWarps(sender);
            return true;
        }
        return false;
    }
}