package dev.hangel.old_combat_mod.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

import dev.hangel.old_combat_mod.gamerules.OldCombatGamerule;

import java.util.Objects;

@Mod.EventBusSubscriber
public class PlayerTickEvent {

	@SubscribeEvent
	public static void onPlayerTick(net.minecraftforge.event.TickEvent.PlayerTickEvent.Post event) {
		Player player = event.player();
		Level level = player.level();
		AttributeInstance i = player.getAttribute(Attributes.ATTACK_SPEED);

		if (level.isClientSide() || i == null) return;

		if (Objects.requireNonNull(level.getServer()).getGameRules().getBoolean(OldCombatGamerule.OLD_COMBAT)) {
			i.setBaseValue(1024);
		}
		else {
			i.setBaseValue(Attributes.ATTACK_SPEED.value().getDefaultValue());
		}
	}
}
