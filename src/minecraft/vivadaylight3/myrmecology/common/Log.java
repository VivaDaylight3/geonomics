package vivadaylight3.myrmecology.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class Log {

    private static Logger logger;

    private static void init() {

	logger = FMLLog.getLogger();

    }

    private static void log(Level level, String text) {

	init();
	logger.log(level, "{" + Reference.MOD_ID + "} " + text);

    }

    public static void info(String text) {

	log(Level.INFO, text);

    }

    public void severe(String text) {

	this.log(Level.SEVERE, text);

    }

    public void warning(String text) {

	this.log(Level.WARNING, text);

    }

    public static void debug(String text) {

	if (Reference.LOG_DEBUG) {

	    log(Level.INFO, "DEBUG: " + text);

	}

    }

}
