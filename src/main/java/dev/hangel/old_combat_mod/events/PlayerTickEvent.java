package dev.hangel.old_combat_mod.events;

import dev.hangel.old_combat_mod.MainOldCombatMod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

@Mod.EventBusSubscriber
public class PlayerTickEvent {
	
	@SubscribeEvent
	public static void onServerTick(TickEvent.ServerTickEvent event) {
		ServerLevel world = event.getServer().overworld();
		if (event.phase == TickEvent.Phase.END) {
			if (!world.isClientSide()) {
				if (world.getServer().getGameRules().getBoolean(MainOldCombatMod.OLD_COMBAT)) {
					for (Player p : world.players()) {
						AttributeInstance i = p.getAttribute(Attributes.ATTACK_SPEED);
						if (i != null) {
							i.setBaseValue(1024);
						}
					}
				} else {
					for (Player p : world.players()) {
						AttributeInstance i = p.getAttribute(Attributes.ATTACK_SPEED);
						if (i != null) {
							i.setBaseValue(Attributes.ATTACK_SPEED.get().getDefaultValue());
						}
					}
				}
			}
		}
	}
}
