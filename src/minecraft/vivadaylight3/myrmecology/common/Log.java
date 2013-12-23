package vivadaylight3.myrmecology.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class Log {

    private static Logger logger;

    /**
     * Initiates the logger
     */
    private static void init() {

	logger = FMLLog.getLogger();

    }

    private static void log(Level level, String text) {

	init();
	logger.log(level, "{" + Reference.MOD_ID + "} " + text);

    }

    /**
     * Logs with the INFO level
     * 
     * @param text
     */
    public static void info(String text) {

	log(Level.INFO, text);

    }

    /**
     * Logs with the SEVERER level
     * 
     * @param text
     */
    public void severe(String text) {

	this.log(Level.SEVERE, text);

    }

    /**
     * Logs with the WARNING level
     * 
     * @param text
     */
    public void warning(String text) {

	this.log(Level.WARNING, text);

    }

    /**
     * Logs with the prefix DEBUG
     * 
     * @param text
     */
    public static void debug(String text) {

	if (Reference.LOG_DEBUG) {

	    log(Level.INFO, "DEBUG: " + text);

	}

    }

}
