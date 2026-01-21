package jss.commandapi;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Represents a subcommand that can be executed within a custom command.
 *
 * @version 0.0.7-alpha
 * @author jonagamerpro1234
 */
public abstract class SubCommand {

    /**
     * Gets the name of the subcommand.
     *
     * @return the name of the subcommand
     * @since 0.0.1-alpha
     */
    public abstract String name();

    /**
     * Gets the permission required to execute the subcommand.
     *
     * @return the permission required to execute the subcommand, empty string if none
     * @since 0.0.1-alpha
     */
    public String permission() {
        return "";
    }

    /**
     * Checks if the subcommand requires permission to be executed.
     *
     * @return true if the subcommand requires permission, false otherwise
     * @since 0.0.1-alpha
     */
    public boolean requiresPermission() {
        return false;
    }

    /**
     * Executes the subcommand.
     *
     * @param sender the CommandSender executing the subcommand
     * @param args the arguments provided for the subcommand
     * @return true if the subcommand executed successfully, false otherwise
     * @since 0.0.1-alpha
     */
    public abstract boolean onCommand(CommandSender sender, String[] args);

    /**
     * Provides tab completion suggestions for this subcommand.
     *
     * @param sender the CommandSender requesting tab completion
     * @param args the arguments provided for the subcommand
     * @return a list of tab completion suggestions
     * @since 0.0.7-alpha
     */
    public List<String> onTabList(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    /**
     * Checks if the subcommand can be executed from the console.
     *
     * @return true if the subcommand can be executed from the console, false otherwise
     * @since 0.0.1-alpha
     */
    public boolean allowConsole() {
        return false;
    }

    /**
     * Checks if the subcommand is enabled.
     *
     * @return true if the subcommand is enabled, false otherwise
     * @since 0.0.1-alpha
     */
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets the message shown when the subcommand is disabled.
     *
     * @return the disabled message
     * @since 0.0.1-alpha
     */
    public String disabledMessage() {
        return "This command is currently disabled.";
    }

    /**
     * Checks if this subcommand is player-only.
     *
     * @return true if only players can execute this subcommand, false otherwise
     * @since 0.0.7-alpha
     */
    public boolean isPlayerOnly() {
        return false;
    }

    /**
     * Returns a list of alternative names (aliases) for this subcommand.
     *
     * @return a list of aliases
     * @since 0.0.7-alpha
     */
    public List<String> aliases() {
        return Collections.emptyList();
    }

    /**
     * Returns the usage string for this subcommand.
     *
     * @return the usage information
     * @since 0.0.7-alpha
     */
    public String usage() {
        return "";
    }

    /**
     * Returns the description for this subcommand.
     *
     * @return the description of the subcommand
     * @since 0.0.7-alpha
     */
    public String description() {
        return "";
    }
}
