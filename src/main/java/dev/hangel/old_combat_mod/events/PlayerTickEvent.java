package dev.hangel.old_combat_mod.events;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import dev.hangel.old_combat_mod.gamerules.OldCombatGamerule;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerTickEvent {
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}
	
	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}
	
	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world.getLevelData().getGameRules().getBoolean(OldCombatGamerule.OLDCOMBAT) == true) {
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null)
					_ent.getServer().getCommands().performPrefixedCommand(_ent.createCommandSourceStack().withSuppressedOutput().withPermission(4),
							"attribute @p minecraft:generic.attack_speed base set 1024");
			}
		} else {
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null)
					_ent.getServer().getCommands().performPrefixedCommand(_ent.createCommandSourceStack().withSuppressedOutput().withPermission(4),
							"attribute @p minecraft:generic.attack_speed base set 4");
			}
		}
	}

}
