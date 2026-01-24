package jss.commandapi;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * CommandAPI provides global utilities and configuration options
 * for the CommandAPI framework.
 * <p>
 * This class is responsible for:
 * <ul>
 *   <li>Managing debug and logging configuration</li>
 *   <li>Providing centralized command execution logs</li>
 *   <li>Measuring command performance</li>
 *   <li>Handling and reporting command execution errors</li>
 * </ul>
 *
 * <p>
 * This class is static-only and must be initialized once during
 * the plugin {@code onEnable()} lifecycle.
 * </p>
 *
 * @author jonagamerpro1234
 * @version 0.0.9-alpha
 */
public final class CommandApi {

    /** Enables debug output for the CommandAPI. */
    private static boolean debug = false;

    /** Enables logging when commands are executed. */
    private static boolean logExecution = false;

    /** Enables logging of command execution performance. */
    private static boolean logPerformance = false;

    /** The plugin instance used for logging. */
    private static JavaPlugin plugin;

    /**
     * Private constructor to prevent instantiation.
     */
    private void CommandAPI() {
        // Prevent instantiation
    }

    /**
     * Initializes the CommandAPI.
     * <p>
     * This method must be called once in {@code onEnable()} before
     * using any CommandAPI features.
     * </p>
     *
     * @param javaPlugin the plugin instance
     * @since 0.0.9-alpha
     */
    public static void init(@NotNull JavaPlugin javaPlugin) {
        plugin = javaPlugin;
    }

    /* =======================
       Configuration
       ======================= */

    /**
     * Enables or disables debug mode.
     * <p>
     * When enabled, additional debug information and stack traces
     * will be printed to the console.
     * </p>
     *
     * @param enabled true to enable debug mode
     * @since 0.0.9-alpha
     */
    public static void setDebug(boolean enabled) {
        debug = enabled;
    }

    /**
     * Checks whether debug mode is enabled.
     *
     * @return true if debug mode is enabled
     * @since 0.0.9-alpha
     */
    public static boolean isDebug() {
        return debug;
    }

    /**
     * Enables or disables command execution logging.
     *
     * @param enabled true to enable execution logging
     * @since 0.0.9-alpha
     */
    public static void setLogExecution(boolean enabled) {
        logExecution = enabled;
    }

    /**
     * Enables or disables command performance logging.
     * <p>
     * This option only has effect if execution logging is enabled.
     * </p>
     *
     * @param enabled true to enable performance logging
     * @since 0.0.9-alpha
     */
    public static void setLogPerformance(boolean enabled) {
        logPerformance = enabled;
    }

    /* =======================
       Logging utilities
       ======================= */

    /**
     * Logs an informational message using the plugin logger.
     *
     * @param message the message to log
     * @since 0.0.9-alpha
     */
    public static void log(@NotNull String message) {
        if (plugin != null) {
            plugin.getLogger().info("[CommandAPI] " + message);
        }
    }

    /**
     * Logs a debug message if debug mode is enabled.
     *
     * @param message the debug message
     * @since 0.0.9-alpha
     */
    public static void debug(@NotNull String message) {
        if (debug && plugin != null) {
            plugin.getLogger().info("[CommandAPI:DEBUG] " + message);
        }
    }

    /* =======================
       Command utilities
       ======================= */

    /**
     * Logs information about a command execution.
     *
     * @param command the executed command name
     * @param sender the command sender
     * @param executionTimeMs execution time in milliseconds
     * @since 0.0.9-alpha
     */
    public static void logCommandExecution(
            @NotNull String command,
            @NotNull CommandSender sender,
            long executionTimeMs
    ) {
        if (!logExecution || plugin == null) return;

        String senderType = sender.getClass().getSimpleName();
        String senderName = sender.getName();

        plugin.getLogger().info(
                "[CommandAPI] /" + command +
                        " executed by " + senderName +
                        " (" + senderType + ")"
        );

        if (logPerformance) {
            plugin.getLogger().info(
                    "[CommandAPI] Execution time: " + executionTimeMs + " ms"
            );
        }
    }

    /**
     * Handles and logs an error that occurred during command execution.
     *
     * @param command the command being executed
     * @param sender the command sender
     * @param throwable the thrown exception or error
     * @since 0.0.9-alpha
     */
    public static void handleError(
            @NotNull String command,
            @NotNull CommandSender sender,
            @NotNull Throwable throwable
    ) {
        if (plugin == null) return;

        plugin.getLogger().severe(
                "[CommandAPI] Error executing command /" + command +
                        " by " + sender.getName()
        );

        if (debug) {
            throwable.printStackTrace();
        }
    }
}
