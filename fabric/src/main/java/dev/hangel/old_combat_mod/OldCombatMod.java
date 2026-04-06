package dev.hangel.old_combat_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class OldCombatMod implements ModInitializer {

    public static GameRule<Boolean> OLD_COMBAT;

    @Override
    public void onInitialize() {
        OLD_COMBAT = GameRuleBuilder
                .forBoolean(true)
                .category(GameRuleCategory.PLAYER)
                .buildAndRegister(Identifier.fromNamespaceAndPath(Identifier.DEFAULT_NAMESPACE, "old_combat"));

        ServerTickEvents.END_LEVEL_TICK.register(world -> {
            if (world instanceof ServerLevel serverLevel) {
                OldCombatLogicFabric.onWorldTick(serverLevel);
            }
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                OldCombatLogicFabric.onPlayerJoin(handler.player));
    }
}