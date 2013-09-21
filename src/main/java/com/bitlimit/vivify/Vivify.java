package com.bitlimit.vivify;

import net.minecraft.server.v1_6_R2.World;
import net.minecraft.server.v1_6_R2.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_6_R2.CraftWorld;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Vivify extends JavaPlugin implements Listener
{
    @Override
    public void onEnable()
    {
        super.onEnable();

        this.getServer().getPluginManager().registerEvents(this, this);

        // Proof of concept
        String worldName = "world";

        class BlockRunnable implements Runnable
        {
            private int currentIndex = 0;
            private String m_worldName;

            double defaultUpwards = /* 0.225 */ 0;
//            private Vector[] indices = {new Vector(0, 0.5 + defaultUpwards, 0), new Vector(0.5, 0 + defaultUpwards, 0), new Vector(0, -0.5 + defaultUpwards, 0), new Vector(-0.5, 0 + defaultUpwards, 0)};
            private Vector[] indices = {new Vector(1, 0, 0), new Vector(1, 0, 0), new Vector(1, 0, 0), new Vector(1, 0, 0), new Vector(1, 0, 0), new Vector(1, 0, 0), new Vector(-1, 0, 0), new Vector(-1, 0, 0), new Vector(-1, 0, 0), new Vector(-1, 0, 0), new Vector(-1, 0, 0), new Vector(-1, 0, 0)};
            private FloatingBlock floatingBlock;

            public BlockRunnable(String worldName)
            {
                this.m_worldName = worldName;



                WorldServer world = ((CraftWorld)Bukkit.getWorld(this.m_worldName)).getHandle();
//                public FloatingBlock(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt)

                this.floatingBlock = new FloatingBlock(world, (double)0, (double)120, (double)0, Material.SPONGE.getId(), 0);
                this.floatingBlock.c = 1; // ticksLived

                world.addEntity(this.floatingBlock, CreatureSpawnEvent.SpawnReason.CUSTOM);
            }


            @Override
            public void run() {

                currentIndex++;

                if (currentIndex > indices.length - 1)
                {
                    currentIndex = 0;
                }


                Vector velocity = indices[currentIndex];
                this.floatingBlock.setVelocity(velocity);
            }

        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BlockRunnable(worldName), 1L, 1L);

    }

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent event)
    {
//        if (event.getM)
        event.setCancelled(true);
//        event.getBlock().setTypeId(event.getBlock().getTypeId(), false);

//        Bukkit.broadcastMessage(ChatColor.RED + "Block physics event.");
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
