package dev.hangel.old_combat_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OldCombatMain implements ModInitializer {
	
	public static final String MOD_ID = "old_combat_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	public static final GameRules.Key<GameRules.BooleanRule> OLDCOMBAT =
			GameRuleRegistry.register("oldCombat", Category.PLAYER, GameRuleFactory.createBooleanRule(true));
	

	@Override
	public void onInitialize() {
		ServerTickEvents.END_WORLD_TICK.register(world -> changePlayersAttribute(world));
	}
	
	private static void changePlayersAttribute(ServerWorld world) {
		if ( !world.isClient ) {
			if (world.getPlayers().size() > 0) {
				if ( world.getGameRules().getBoolean(OLDCOMBAT) ) {
					for(PlayerEntity p : world.getPlayers()) {
						EntityAttributeInstance i = p.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);
						i.setBaseValue(1024);
					}
				}
				else {
					for(PlayerEntity p : world.getPlayers()) {
						EntityAttributeInstance i = p.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED);
						i.setBaseValue(EntityAttributes.GENERIC_ATTACK_SPEED.getDefaultValue());
					}
				}
			}
		}
	}
	
}
