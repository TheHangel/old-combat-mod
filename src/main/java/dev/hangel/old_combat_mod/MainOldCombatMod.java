package dev.hangel.old_combat_mod;

import dev.hangel.old_combat_mod.gamerules.OldCombatGamerule;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MainOldCombatMod.MODID)
public class MainOldCombatMod {
    public static final String MODID = "old_combat_mod";

    public MainOldCombatMod(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::onCommonSetup);
        ServerStartingEvent.BUS.addListener(this::onServerStarting);
    }

    private void onCommonSetup(FMLCommonSetupEvent e) {
        OldCombatGamerule.bootstrap();
    }
    private void onServerStarting(ServerStartingEvent e) {}
}

