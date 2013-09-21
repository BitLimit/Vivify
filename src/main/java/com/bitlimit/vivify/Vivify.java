package com.bitlimit.vivify;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class Vivify extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        super.onEnable();

        // Proof of concept
        String worldName = "world";

        class BlockRunnable implements Runnable
        {
            private int currentIndex = 0;
            private String m_worldName;
            private Integer[] indices = {80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81};

            public BlockRunnable(String worldName)
            {
                this.m_worldName = worldName;
            }


            @Override
            public void run() {

                currentIndex++;

                if (currentIndex > indices.length - 1)
                {
                    currentIndex = 0;
                }

                for (Integer idx : indices)
                {
                    Block block = Bukkit.getWorld(this.m_worldName).getBlockAt(0, idx, 0);
                    block.setType(Material.AIR);
                }

                Block animatedBlock = Bukkit.getWorld(this.m_worldName).getBlockAt(0, indices[currentIndex], 0);
                animatedBlock.setType(Material.BEACON);
            }

        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BlockRunnable(worldName), 5L, 5L);

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
