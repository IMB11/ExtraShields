package dev.imb11.shields;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import dev.imb11.shields.enchantments.ShieldsEnchantmentEffects;
import dev.imb11.shields.items.ShieldsItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shields implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("Shields");

    @Override
    public void onInitialize() {
        ShieldsItems.initialize();

        ShieldBlockCallback.EVENT.register(ShieldsEnchantmentEffects::eventShieldBlock);
    }
}