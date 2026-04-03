package dev.hangel.old_combat_mod;

import dev.hangel.old_combat_mod.gamerules.OldCombatGamerule;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OldCombatLogic {

    private static final Map<ResourceKey<Level>, Boolean> LAST = new ConcurrentHashMap<>();

    public static void onWorldTick(TickEvent.LevelTickEvent.Post event) {
        if (!(event.level() instanceof ServerLevel world)) return;

        GameRules rules = world.getGameRules();
        boolean enabled = rules.get(OldCombatGamerule.OLD_COMBAT);

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

    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        boolean enabled = player.level().getGameRules().get(OldCombatGamerule.OLD_COMBAT);
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