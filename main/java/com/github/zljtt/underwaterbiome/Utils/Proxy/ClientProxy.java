package com.github.zljtt.underwaterbiome.Utils.Proxy;

import com.github.zljtt.underwaterbiome.Handlers.RegistryHandler;
import com.github.zljtt.underwaterbiome.Utils.Interface.IProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy
{
	 
	@Override
    public void init() 
	{
    	RegistryHandler.preInitRegistries();
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }

}
