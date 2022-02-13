package com.mrcrayfish.backpacked.common.data;

import com.mrcrayfish.backpacked.Reference;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PickpocketChallenge
{
    public static final Capability<PickpocketChallenge> PICKPOCKET_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    private final Map<Player, Long> detectedPlayers = new HashMap<>();

    public Map<Player, Long> getDetectedPlayers()
    {
        return this.detectedPlayers;
    }

    public static void register(RegisterCapabilitiesEvent event)
    {
        event.register(PickpocketChallenge.class);
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof WanderingTrader)
        {
            Provider provider = new Provider();
            event.addCapability(new ResourceLocation(Reference.MOD_ID, "pickpocket_challenge"), provider);
            event.addListener(provider::invalidate);
        }
    }

    public static class Provider implements ICapabilitySerializable<CompoundTag>
    {
        private final PickpocketChallenge instance = new PickpocketChallenge();
        private final LazyOptional<PickpocketChallenge> optional = LazyOptional.of(() -> this.instance);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
        {
            return PICKPOCKET_CAPABILITY.orEmpty(cap, this.optional.cast());
        }

        @Override
        public CompoundTag serializeNBT()
        {
            return new CompoundTag();
        }

        @Override
        public void deserializeNBT(CompoundTag tag) {}

        public void invalidate()
        {
            this.optional.invalidate();
        }
    }
}