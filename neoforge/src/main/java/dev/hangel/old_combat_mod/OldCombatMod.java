package dev.hangel.old_combat_mod;

import dev.hangel.old_combat_mod.gamerules.OldCombatGamerule;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class OldCombatMod {

    public OldCombatMod(IEventBus modBus) {
        modBus.addListener(this::onRegister);

        NeoForge.EVENT_BUS.addListener(OldCombatMod::onLevelTick);
        NeoForge.EVENT_BUS.addListener(OldCombatMod::onPlayerLogin);
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.GAME_RULE)) {
            OldCombatGamerule.register();
        }
    }

    private static void onLevelTick(LevelTickEvent.Post event) {
        OldCombatLogic.onWorldTick(event);
    }

    private static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        OldCombatLogic.onPlayerJoin(event);
    }
}