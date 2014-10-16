package com.AlexTheCoder.korra.scoreboard;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.projectkorra.ProjectKorra.CustomEvents.PlayerCooldownChangeEvent;

public class BendingScoreboardListener
  implements Listener
{
  BendingScoreboard plugin;
  BendingScoreboardManager sboard;
  BendingScoreboardConfig config;
  BendingStrings strings = new BendingStrings();
  
  public BendingScoreboardListener(BendingScoreboard passedPlugin)
  {
    this.sboard = new BendingScoreboardManager(passedPlugin);
    this.config = new BendingScoreboardConfig(passedPlugin);
    this.plugin = passedPlugin;
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
    final Player player = event.getPlayer();
    if (!BendingScoreboardConfig.toggledPlayersFile.contains(player.getName())) {
      BendingScoreboardConfig.toggledPlayersFile.set(player.getName(), Boolean.valueOf(true));
    }
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
    {
      public void run()
      {
        if ((player.hasPermission("bboard.player")) && (BendingScoreboardConfig.toggledPlayersFile.getBoolean(player.getName()))) {
          BendingScoreboardListener.this.sboard.setupScoreboard(player);
        }
      }
    }, 1L);
  }
  
  @EventHandler
  public void onCooldownChange(PlayerCooldownChangeEvent event)
  {
    final Player player = event.getPlayer();
    
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
    {
      public void run()
      {
        if ((player.hasPermission("bboard.player")) && (BendingScoreboardConfig.toggledPlayersFile.getBoolean(player.getName()))) {
          BendingScoreboardListener.this.sboard.setupScoreboard(player);
        }
      }
    }, 1L);
  }
  
  @EventHandler
  public void onPlayerCommand(PlayerCommandPreprocessEvent event)
  {
    final Player player = event.getPlayer();
    String cmd = event.getMessage().toLowerCase();
    String[] splitArray = cmd.split("\\s+");
    int arraySize = Array.getLength(splitArray);
    if (!BendingScoreboardConfig.toggledPlayersFile.getBoolean(player.getName())) {
      return;
    }
    if (!player.hasPermission("bboard.player")) {
      return;
    }
    if (arraySize > 1)
    {
      String cmdP1 = ((String)Arrays.asList(splitArray).get(0)).toLowerCase();
      String cmdP2 = ((String)Arrays.asList(splitArray).get(1)).toLowerCase();
      if ((Arrays.asList(this.strings.cmdP1).contains(cmdP1)) && (Arrays.asList(this.strings.cmdP2).contains(cmdP2))) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if ((player.hasPermission("bboard.player")) && (BendingScoreboardConfig.toggledPlayersFile.getBoolean(player.getName()))) {
              BendingScoreboardListener.this.sboard.setupScoreboard(player);
            }
          }
        }, 1L);
      }
    }
  }
}
