package com.AlexTheCoder.korra.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.projectkorra.ProjectKorra.BendingPlayer;
import com.projectkorra.ProjectKorra.Methods;

public class BendingScoreboardManager
{
  BendingScoreboard plugin;
  BendingStrings strings = new BendingStrings();
  ScoreboardManager sbManager;
  Scoreboard sBoard;
  Objective obj;
  
  public BendingScoreboardManager(BendingScoreboard plugin)
  {
    this.plugin = plugin;
  }
  
  public void giveObjectives(Objective o, String... strings)
  {
    int i = strings.length;
    for (int j = 0; i > j; i--) {
      o.getScore(strings[(i - 1)]).setScore(strings.length - i + 1);
    }
  }
  
  public String getMove(BendingPlayer bPlayer, int i)
  {
    String ability = (String)bPlayer.getAbilities().get(Integer.valueOf(i));
    String moveName = ChatColor.GRAY + "" + ChatColor.ITALIC + "-- Slot " + i + " --";
    if ((ability != null) && (!ability.equalsIgnoreCase("null"))) {
      if (bPlayer.isOnCooldown(ability))
      {
        if (ability.toString().length() > 16)
        {
          String ability2 = ability.toString().substring(0, 11);
          moveName = Methods.getAbilityColor(ability) + "" + ChatColor.STRIKETHROUGH + ability2 + ".";
        }
        else
        {
          moveName = Methods.getAbilityColor(ability) + "" + ChatColor.STRIKETHROUGH + ability.toString();
        }
      }
      else if (ability.toString().length() > 16)
      {
        String ability2 = ability.toString().substring(0, 13);
        moveName = Methods.getAbilityColor(ability) + ability2 + ".";
      }
      else
      {
        moveName = Methods.getAbilityColor(ability) + ability.toString();
      }
    }
    return moveName;
  }
  
  public void setupScoreboard(Player player)
  {
    this.sbManager = Bukkit.getScoreboardManager();
    this.sBoard = this.sbManager.getNewScoreboard();
    
    this.obj = this.sBoard.registerNewObjective("BoundMoves", "dummy");
    this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    this.obj.setDisplayName(this.strings.title);
    
    BendingPlayer bPlayer = Methods.getBendingPlayer(player.getName());
    if (bPlayer != null)
    {
      Objective o = this.sBoard.getObjective("BoundMoves");
      giveObjectives(o, new String[] {
        "§f/bb to toggle", 
        " ", 
        getMove(bPlayer, 1), 
        getMove(bPlayer, 2), 
        getMove(bPlayer, 3), 
        getMove(bPlayer, 4), 
        getMove(bPlayer, 5), 
        getMove(bPlayer, 6), 
        getMove(bPlayer, 7), 
        getMove(bPlayer, 8), 
        getMove(bPlayer, 9) });
    }
    player.setScoreboard(this.sBoard);
  }
}
