package dev.hangel.old_combat_mod.gamerules;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OldCombatGamerule {
		
	public static final GameRules.Key<GameRules.BooleanValue> OLDCOMBAT = 
			GameRules.register("oldCombat", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
}
