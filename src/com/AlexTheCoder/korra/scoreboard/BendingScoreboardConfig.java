package com.AlexTheCoder.korra.scoreboard;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BendingScoreboardConfig
{
  BendingScoreboard plugin;
  public static String newline = System.getProperty("line.separator");
  public static File pluginFolder;
  public static File configFile;
  public static FileConfiguration toggledPlayersFile;
  
  public BendingScoreboardConfig(BendingScoreboard passedPlugin)
  {
    this.plugin = passedPlugin;
  }
  
  public void createConfig()
  {
    pluginFolder = this.plugin.getDataFolder();
    configFile = new File(pluginFolder, "ToggledPlayers.yml");
    
    toggledPlayersFile = new YamlConfiguration();
    if (!pluginFolder.exists()) {
      try
      {
        pluginFolder.mkdir();
      }
      catch (Exception e)
      {
        this.plugin.getLogger().info("Failed to generate directory!");
        e.printStackTrace();
      }
    }
    if (!configFile.exists()) {
      try
      {
        configFile.createNewFile();
      }
      catch (Exception e)
      {
        this.plugin.getLogger().info("Failed to generate ToggledPlayers.yml file!");
        e.printStackTrace();
      }
    }
  }
  
  public void loadConfig()
  {
    pluginFolder = this.plugin.getDataFolder();
    configFile = new File(pluginFolder, "ToggledPlayers.yml");
    
    createConfig();
    try
    {
      toggledPlayersFile.load(configFile);
    }
    catch (Exception localException) {}
  }
  
  public void saveConfig()
  {
    pluginFolder = this.plugin.getDataFolder();
    configFile = new File(pluginFolder, "ToggledPlayers.yml");
    try
    {
      toggledPlayersFile.save(configFile);
    }
    catch (Exception localException) {}
  }
}
