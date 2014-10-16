package com.AlexTheCoder.korra.scoreboard;

import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BendingScoreboard
  extends JavaPlugin
  implements Listener
{
  Plugin plugin = this;
  BendingScoreboardManager sboard;
  BendingScoreboardConfig toggledPlayers;
  
  public void onEnable()
  {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(this, this);
    this.sboard = new BendingScoreboardManager(this);
    this.toggledPlayers = new BendingScoreboardConfig(this);
    getServer().getPluginManager().registerEvents(new BendingScoreboardListener(this), this);
    getCommand("bboard").setExecutor(new BendingScoreboardCommands(this));
    getCommand("bb").setExecutor(new BendingScoreboardCommands(this));
    this.plugin.getServer().getPluginManager().addPermission(new BendingScoreboardPerms().boardDefault);
    this.plugin.getServer().getPluginManager().getPermission("bboard.player").setDefault(PermissionDefault.TRUE);
    this.toggledPlayers.loadConfig();
  }
  
  public void onDisable()
  {
    this.toggledPlayers.saveConfig();
    this.plugin.getServer().getPluginManager().removePermission(new BendingScoreboardPerms().boardDefault);
  }
}
