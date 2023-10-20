package xd.viper.ezwarp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class WarpSystem {
    public static Main thisPlugin;

    public static void setWarp(CommandSender sender, String warpName) {
        Player player = (Player) sender;
        if(player.isOp() || player.hasPermission("ezwarp.setwarp")){
            Location location = player.getLocation();
            File configFile = new File(thisPlugin.getDataFolder(), "warps.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            config.set("warps." + warpName + ".world", location.getWorld().getName());
            config.set("warps." + warpName + ".x", location.getX());
            config.set("warps." + warpName + ".y", location.getY());
            config.set("warps." + warpName + ".z", location.getZ());
            config.set("warps." + warpName + ".yaw", location.getYaw());
            config.set("warps." + warpName + ".pitch", location.getPitch());
            try {
                config.save(configFile);
                sender.sendMessage(Main.prefix + "Warp \"" + warpName + "\" has been created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void warp(CommandSender sender, String warpName) {
        Player player = (Player) sender;
        if (player.isOp() || player.hasPermission("ezwarp.warp")) {
            File configFile = new File(thisPlugin.getDataFolder(), "warps.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            if (config.contains("warps." + warpName)) {
                String worldName = config.getString("warps." + warpName + ".world");
                World world = Bukkit.getWorld(worldName);
                double x = config.getDouble("warps." + warpName + ".x");
                double y = config.getDouble("warps." + warpName + ".y");
                double z = config.getDouble("warps." + warpName + ".z");
                float yaw = (float) config.getDouble("warps." + warpName + ".yaw");
                float pitch = (float) config.getDouble("warps." + warpName + ".pitch");
                Location location = new Location(world, x, y, z, yaw, pitch);
                player.teleport(location);
                player.sendMessage(Main.prefix + "You have been warped to \"" + warpName + "\".");
            } else {
                player.sendMessage(Main.prefix + "Warp \"" + warpName + "\" doesn't exist.");
            }
        }
    }

    public static void delWarp(CommandSender sender, String warpName) {
        File configFile = new File(thisPlugin.getDataFolder(), "warps.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        if (config.contains("warps." + warpName)) {
            config.set("warps." + warpName, null);
            try {
                config.save(configFile);
                if(sender instanceof ConsoleCommandSender) {
                    Bukkit.getLogger().info(Main.prefixWC + "Warp \"" + warpName + "\" has been deleted.");
                } else if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(Main.prefix + "Warp \"" + warpName + "\" has been deleted.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if(sender instanceof ConsoleCommandSender) {
                Bukkit.getLogger().info(Main.prefixWC + "Warp \"" + warpName + "\" doesn't exist.");
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(Main.prefix + "Warp \"" + warpName + "\" doesn't exist.");
            }
        }
    }

    public static void listWarps(CommandSender sender) {
        File configFile = new File(thisPlugin.getDataFolder(), "warps.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        ConfigurationSection warpsSection = config.getConfigurationSection("warps");
        if (warpsSection != null) {
            Set<String> warpNames = warpsSection.getKeys(false); // Get all warp names
            if (!warpNames.isEmpty()) {
                if(sender instanceof ConsoleCommandSender) {
                    Bukkit.getLogger().info(Main.prefixWC + "All existing warps:");
                } else if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(Main.prefix + "All existing warps:");
                }

                for (String warpName : warpNames) {
                    if(sender instanceof ConsoleCommandSender) {
                        Bukkit.getLogger().info("- " + warpName);
                    } else if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.sendMessage("- " + warpName);
                    }
                }
            } else {
                if(sender instanceof ConsoleCommandSender) {
                    Bukkit.getLogger().info(Main.prefixWC + "There aren't any warps.");
                } else if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(Main.prefix + "There aren't any warps.");
                }
            }
        } else {
            if(sender instanceof ConsoleCommandSender) {
                Bukkit.getLogger().info(Main.prefixWC + "There aren't any warps.");
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(Main.prefix + "There aren't any warps.");
            }
        }
    }
}