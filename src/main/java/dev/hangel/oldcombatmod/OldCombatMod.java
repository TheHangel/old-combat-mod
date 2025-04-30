package dev.hangel.oldcombatmod;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;

@Mod(OldCombatMod.MODID)
public class OldCombatMod
{
    public static final String MODID = "old_combat_mod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final GameRules.Key<GameRules.BooleanValue> OLD_COMBAT =
            GameRules.register("oldCombat",
                    GameRules.Category.PLAYER,
                    GameRules.BooleanValue.create(true));

    public OldCombatMod(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(this::tickServer);
    }

    private void tickServer(ServerTickEvent.Post event) {
        if (event.getServer().getTickCount() % 2 == 0) {
            ServerLevel world = event.getServer().overworld();
            if(!world.isClientSide()) {
                if(event.getServer().getGameRules().getBoolean(OLD_COMBAT)) {
                    for (Player p : world.players()) {
                        AttributeInstance i = p.getAttribute(Attributes.ATTACK_SPEED);
                        if(i != null) {
                            i.setBaseValue(1024);
                        }
                    }
                }
                else {
                    for (Player p : world.players()) {
                        AttributeInstance i = p.getAttribute(Attributes.ATTACK_SPEED);
                        if(i != null) {
                            i.setBaseValue(Attributes.ATTACK_SPEED.value().getDefaultValue());
                        }
                    }
                }
            }
        }
    }
}
