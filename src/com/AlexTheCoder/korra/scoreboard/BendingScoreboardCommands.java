package com.AlexTheCoder.korra.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BendingScoreboardCommands
  implements CommandExecutor
{
  BendingScoreboard plugin;
  BendingScoreboardListener listener;
  BendingScoreboardConfig config;
  BendingScoreboardManager sboard;
  BendingStrings strings = new BendingStrings();
  
  public BendingScoreboardCommands(BendingScoreboard plugin)
  {
    this.listener = new BendingScoreboardListener(plugin);
    this.sboard = new BendingScoreboardManager(plugin);
    this.config = new BendingScoreboardConfig(plugin);
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player player = (Player)sender;
      if (((commandLabel.equalsIgnoreCase("bboard")) || (commandLabel.equalsIgnoreCase("bb"))) && (player.hasPermission("bboard.player"))) {
        if (args.length == 0)
        {
          if (BendingScoreboardConfig.toggledPlayersFile.getBoolean(player.getName()))
          {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.sendMessage(ChatColor.GOLD + "BendingBoard toggled off!");
            BendingScoreboardConfig.toggledPlayersFile.set(player.getName(), Boolean.valueOf(false));
            this.config.saveConfig();
          }
          else
          {
            this.sboard.setupScoreboard(player);
            BendingScoreboardConfig.toggledPlayersFile.set(player.getName(), Boolean.valueOf(true));
            this.config.saveConfig();
            player.sendMessage(ChatColor.GOLD + "BendingBoard toggled on!");
          }
        }
        else {
          player.sendMessage(this.strings.boardHelpA);
        }
      }
    }
    return true;
  }
}
