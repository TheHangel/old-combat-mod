package dev.hangel.old_combat_mod.gamerules;

import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRules;

public final class OldCombatGamerule {
    public static GameRule<Boolean> OLD_COMBAT;

    private OldCombatGamerule() {}

    public static void register() {
        OLD_COMBAT = GameRules.registerBoolean("old_combat", GameRuleCategory.PLAYER, true);
    }
}