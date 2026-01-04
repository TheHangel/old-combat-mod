package dev.hangel.old_combat_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OldCombatMod implements ModInitializer {

    public static GameRule<Boolean> OLD_COMBAT;

    private static final Map<ResourceKey<Level>, Boolean> LAST = new ConcurrentHashMap<>();

    @Override
    public void onInitialize() {
        OLD_COMBAT = GameRuleBuilder
                .forBoolean(true)
                .category(GameRuleCategory.PLAYER)
                .buildAndRegister(Identifier.fromNamespaceAndPath(Identifier.DEFAULT_NAMESPACE, "old_combat"));

        ServerTickEvents.END_WORLD_TICK.register(OldCombatMod::onWorldTick);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.player;
            apply(player, player.level().getGameRules().get(OLD_COMBAT));
        });
    }

    private static void onWorldTick(ServerLevel world) {
        if (world.isClientSide()) return;

        boolean enabled = world.getGameRules().get(OLD_COMBAT);

        ResourceKey<Level> key = world.dimension();
        Boolean last = LAST.putIfAbsent(key, enabled);

        if (last == null) {
            applyAll(world, enabled);
            LAST.put(key, enabled);
            return;
        }

        if (last == enabled) return;

        applyAll(world, enabled);
        LAST.put(key, enabled);
    }

    private static void applyAll(ServerLevel world, boolean enabled) {
        for (ServerPlayer p : world.players()) {
            apply(p, enabled);
        }
    }

    private static void apply(ServerPlayer p, boolean enabled) {
        AttributeInstance inst = p.getAttribute(Attributes.ATTACK_SPEED);
        if (inst == null) return;

        if (enabled) {
            inst.setBaseValue(1024);
        } else {
            inst.setBaseValue(Attributes.ATTACK_SPEED.value().getDefaultValue());
        }
    }
}
