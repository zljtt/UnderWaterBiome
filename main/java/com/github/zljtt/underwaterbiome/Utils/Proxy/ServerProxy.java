package com.github.zljtt.underwaterbiome.Utils.Proxy;

import com.github.zljtt.underwaterbiome.Handlers.RegistryHandler;
import com.github.zljtt.underwaterbiome.Utils.Interface.IProxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy
{
	
	@Override
    public void init() {
    	RegistryHandler.preInitRegistries();

    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("Only run this on the client!");
    }



}
