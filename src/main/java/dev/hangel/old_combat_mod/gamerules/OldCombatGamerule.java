package dev.hangel.old_combat_mod.gamerules;

import net.minecraft.world.level.GameRules;

public class OldCombatGamerule {

	public static final GameRules.Key<GameRules.BooleanValue> OLD_COMBAT =
			GameRules.register("oldCombat", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

	public static void bootstrap() {
		OLD_COMBAT.toString();
	}
}
