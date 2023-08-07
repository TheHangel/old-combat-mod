package dev.hangel.old_combat_mod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MainOldCombatMod.MODID)
public class MainOldCombatMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "old_combat_mod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public MainOldCombatMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
    
}
