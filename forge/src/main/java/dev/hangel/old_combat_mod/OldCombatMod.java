package dev.hangel.old_combat_mod;

import dev.hangel.old_combat_mod.gamerules.OldCombatGamerule;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("old_combat_mod")
public class OldCombatMod {

    public OldCombatMod() {
        OldCombatGamerule.register();

        TickEvent.LevelTickEvent.Post.BUS.addListener(OldCombatMod::onLevelTick);
        PlayerEvent.PlayerLoggedInEvent.BUS.addListener(OldCombatMod::onPlayerLogin);
    }

    private static void onLevelTick(TickEvent.LevelTickEvent.Post event) {
        OldCombatLogic.onWorldTick(event);
    }

    private static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        OldCombatLogic.onPlayerJoin(event);
    }
}