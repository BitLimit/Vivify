package com.bitlimit.vivify;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Vivify extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        super.onEnable();
    }

    @Override
    public void onDisable()
    {
        super.onDisable();

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    @Override
    public void saveConfig()
    {
        super.saveConfig();

        this.reloadConfig();
    }
}
