package dev.hangel.old_combat_mod;

import dev.hangel.old_combat_mod.gamerules.OldCombatGamerule;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class OldCombatMod {

    public OldCombatMod(FMLJavaModLoadingContext context) {
        CommonClass.init();

        RegisterEvent.getBus(context.getModBusGroup()).addListener(this::onRegister);

        TickEvent.LevelTickEvent.Post.BUS.addListener(OldCombatMod::onLevelTick);
        PlayerEvent.PlayerLoggedInEvent.BUS.addListener(OldCombatMod::onPlayerLogin);
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.GAME_RULE)) {
            OldCombatGamerule.register();
        }
    }

    private static void onLevelTick(TickEvent.LevelTickEvent.Post event) {
        OldCombatLogic.onWorldTick(event);
    }

    private static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        OldCombatLogic.onPlayerJoin(event);
    }
}