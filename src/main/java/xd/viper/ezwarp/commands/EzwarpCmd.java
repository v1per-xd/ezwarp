package xd.viper.ezwarp.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xd.viper.ezwarp.Main;

import java.io.Console;

public class EzwarpCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("ezwarp")) {
            if(args.length > 0) {
                switch (args[0]) {
                    case "ping":
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            player.sendMessage(Main.prefix + "Pong! " + ((Player) sender).getPing() + "§7ms");
                        } else {
                            Bukkit.getLogger().info(Main.prefixWC + "This command can only be run by a player.");
                        }
                        return true;
                    case "github":
                        if (sender instanceof ConsoleCommandSender){
                            Bukkit.getLogger().info("My Github Page: https://github.com/v1per-xd/");
                        } else if (sender instanceof Player) {
                            Player player = (Player) sender;
                            ComponentBuilder message = new ComponentBuilder("My Github Page: ").color(ChatColor.WHITE);
                            message.append("https://github.com/v1per-xd/").color(ChatColor.GRAY)
                                    .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/v1per-xd/"))
                                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to open the link!").create()));
                            player.sendMessage(message.create());
                        }
                        return true;
                    case "help":
                        if (sender instanceof ConsoleCommandSender) {
                            sendHelpConsole();
                        } else if (sender instanceof Player) {
                            Player player = (Player) sender;
                            sendHelp(player);
                        }
                        return true;
                }
            } else {
                if (sender instanceof ConsoleCommandSender) {
                    sendHelpConsole();
                } else if (sender instanceof Player) {
                    Player player = (Player) sender;
                    sendHelp(player);
                }
            }
            return true;
        }
        return false;
    }

    public void sendHelp(Player player) {
        player.sendMessage(Main.prefix + "Commands:");
        player.sendMessage("- /ezwarp ping - Check Ping");
        player.sendMessage("- /ezwarp github - Open My Github");
        player.sendMessage("- /ezwarp help - Show Help");
        player.sendMessage("- /setwarp 'NAME' - Create Warp");
        player.sendMessage("- /delwarp 'NAME' - Delete Warp");
        player.sendMessage("- /warp 'NAME' - Warp");
        player.sendMessage("§bMADE BY @vvviperrr");
    }

    public void sendHelpConsole() {
        Bukkit.getLogger().info(Main.prefixWC + "Commands:");
        Bukkit.getLogger().info("- /ezwarp ping - Check Ping");
        Bukkit.getLogger().info("- /ezwarp github - Open My Github");
        Bukkit.getLogger().info("- /ezwarp help - Show Help");
        Bukkit.getLogger().info("- /setwarp 'NAME' - Create Warp");
        Bukkit.getLogger().info("- /delwarp 'NAME' - Delete Warp");
        Bukkit.getLogger().info("- /warp 'NAME' - Warp");
        Bukkit.getLogger().info("MADE BY @vvviperrr");
    }
}