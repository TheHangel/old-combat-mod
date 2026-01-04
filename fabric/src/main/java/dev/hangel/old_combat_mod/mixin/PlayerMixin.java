package dev.hangel.old_combat_mod.mixin;

import dev.hangel.old_combat_mod.OldCombatMod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class PlayerMixin {
    @Redirect(
        method = "causeExtraKnockback(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/phys/Vec3;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setSprinting(Z)V")
    )
    private void oldCombat$dontStopSprintingOnKnockback(Player self, boolean sprinting) {
        if (self.level() instanceof ServerLevel serverLevel) {
            boolean enabled = serverLevel.getGameRules().get(OldCombatMod.OLD_COMBAT);
            if (enabled) return;
        }
        self.setSprinting(sprinting);
    }
}
