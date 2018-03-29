package me.gobli989.extremejumppads;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class MainClass extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJump(PlayerMoveEvent e) {
		Material mat1 = Material.getMaterial(getConfig().getString("settings.Plate_Block").toUpperCase());
		Material mat2 = Material.getMaterial(getConfig().getString("settings.Bottom_Block").toUpperCase());
		Player player = e.getPlayer();
		if(getConfig().getBoolean("settings.Need_Permission") == false) {
			if(player.getLocation().getBlock().getType() == mat1 && player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == mat2);
			Vector vector = player.getLocation().getDirection().multiply(getConfig().getInt("settings.Power_Front")).setY(getConfig().getInt("settings.Power_Height"));
			player.setVelocity(vector);
			player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0f, 1.0f);
			player.setFallDistance(-9999f);
		} else {
			if(player.hasPermission("extremejumppads.use")) {
				if(player.getLocation().getBlock().getType() == mat1 && player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == mat2) {
					Vector vector = player.getLocation().getDirection().multiply(getConfig().getInt("settings.Power_Front")).setY(getConfig().getInt("settings.Power_Height"));
					player.setVelocity(vector);
					player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1.0f, 1.0f);
					player.setFallDistance(-9999f);
				}
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("extremejumppads")) {
			if(sender instanceof Player) {
				if(player.hasPermission("extremejumppads.admin")) {
					if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.Prefix")+" "+ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.help.Reload"))));
						
					} else if(args[0].equalsIgnoreCase("reload")) {
						reloadConfig();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.Prefix")+" "+ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.Reload"))));
					}
				}
			}
		}
		return false;
	}
	
	

}
