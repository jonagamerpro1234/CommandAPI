package jss.commandapi;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Represents a subcommand that can be executed within a BaseCommand.
 * <p>
 * This class defines the structure, metadata and optional behavior
 * of a subcommand, while execution flow, logging and error handling
 * are managed by {@link BaseCommand}.
 * </p>
 *
 * @author jonagamerpro1234
 * @version 0.0.8-alpha
 */
public abstract class SubCommand {

    /**
     * Gets the primary name of the subcommand.
     *
     * @return the name of the subcommand
     * @since 0.0.1-alpha
     */
    public abstract String name();

    /**
     * Gets the permission required to execute the subcommand.
     *
     * @return the permission string, or empty if none is required
     * @since 0.0.1-alpha
     */
    public String permission() {
        return "";
    }

    /**
     * Checks whether this subcommand requires a permission to be executed.
     *
     * @return true if permission is required, false otherwise
     * @since 0.0.1-alpha
     */
    public boolean requiresPermission() {
        return false;
    }

    /**
     * Executes the subcommand logic.
     * <p>
     * Any exception thrown here will be handled by {@link BaseCommand}.
     * </p>
     *
     * @param sender the CommandSender executing the subcommand
     * @param args the full command arguments
     * @return true if execution was successful
     * @since 0.0.1-alpha
     */
    public abstract boolean onCommand(CommandSender sender, String[] args);

    /**
     * Provides tab completion suggestions for this subcommand.
     *
     * @param sender the CommandSender requesting tab completion
     * @param args the command arguments
     * @return a list of tab completion suggestions
     * @since 0.0.7-alpha
     */
    public List<String> onTabList(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    /**
     * Determines whether this subcommand can be executed from the console.
     *
     * @return true if console execution is allowed
     * @since 0.0.1-alpha
     */
    public boolean allowConsole() {
        return false;
    }

    /**
     * Determines whether this subcommand is currently enabled.
     *
     * @return true if enabled
     * @since 0.0.1-alpha
     */
    public boolean isEnabled() {
        return true;
    }

    /**
     * Message displayed when the subcommand is disabled.
     *
     * @return the disabled message
     * @since 0.0.1-alpha
     */
    public String disabledMessage() {
        return "This command is currently disabled.";
    }

    /**
     * Indicates whether this subcommand can only be executed by players.
     *
     * @return true if player-only
     * @since 0.0.7-alpha
     */
    public boolean isPlayerOnly() {
        return false;
    }

    /**
     * Returns alternative names for this subcommand.
     *
     * @return a list of aliases
     * @since 0.0.7-alpha
     */
    public List<String> aliases() {
        return Collections.emptyList();
    }

    /**
     * Returns the usage information for this subcommand.
     *
     * @return the usage string
     * @since 0.0.7-alpha
     */
    public String usage() {
        return "";
    }

    /**
     * Returns a short description of this subcommand.
     *
     * @return the description
     * @since 0.0.7-alpha
     */
    public String description() {
        return "";
    }

    /**
     * Utility method for debug logging related to this subcommand.
     * <p>
     * Messages will only be logged if CommandAPI debug mode is enabled.
     * </p>
     *
     * @param message the debug message
     * @since 0.0.8-alpha
     */
    protected void debug(String message) {
        CommandApi.debug("[SubCommand:" + name() + "] " + message);
    }
}
