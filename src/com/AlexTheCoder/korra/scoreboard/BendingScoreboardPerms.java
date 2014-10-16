package com.AlexTheCoder.korra.scoreboard;

import org.bukkit.permissions.Permission;

public class BendingScoreboardPerms
{
  public Permission boardDefault;
  
  public BendingScoreboardPerms()
  {
    this.boardDefault = new Permission("bboard.player");
  }
}
