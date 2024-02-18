package me.fengming.integratedterminalsfix;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("integratedterminalsfix")
public class IntegratedTerminalsFix {
    private static final Logger LOGGER = LogManager.getLogger();

    public IntegratedTerminalsFix() {https://github.com/3093FengMing/IntegratedTerminalsFix
        LOGGER.info("[IntegratedTerminalsFix] Author: FengMing");
        LOGGER.info("[IntegratedTerminalsFix] Github: https://github.com/3093FengMing/IntegratedTerminalsFix");
    }

    public static void loaded() {
        LOGGER.info("[IntegratedTerminalsFix] Successfully fixed bug.");
    }
}
