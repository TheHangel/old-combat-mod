package dev.hangel.old_combat_mod;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static dev.hangel.old_combat_mod.OldCombatMod.OLD_COMBAT;

public class OldCombatLogicFabric {

    private static final Map<ResourceKey<Level>, Boolean> LAST = new ConcurrentHashMap<>();

    public static void onWorldTick(ServerLevel world) {
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

    public static void onPlayerJoin(ServerPlayer player) {
        boolean enabled = player.level().getGameRules().get(OLD_COMBAT);
        apply(player, enabled);
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
            inst.setBaseValue(1024.0D);
        } else {
            inst.setBaseValue(Attributes.ATTACK_SPEED.value().getDefaultValue());
        }
    }
}