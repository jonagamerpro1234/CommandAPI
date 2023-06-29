package jss.commandapi;

import org.bukkit.command.CommandSender;

/**
 * Represents a subcommand that can be executed within a custom command.
 *
 * @version 0.0.1-alpha
 * @author jonagamerpro1234
 */
public abstract class SubCommand {

    /**
     * Gets the name of the subcommand.
     *
     * @return The name of the subcommand.
     * @since 0.0.1-alpha
     */
    public abstract String name();

    /**
     * Gets the permission required to execute the subcommand.
     *
     * @return The permission required to execute the subcommand.
     * @since 0.0.1-alpha
     */
    public abstract String permission();

    /**
     * Checks if the subcommand requires permission to be executed.
     *
     * @return {@code true} if the subcommand requires permission, {@code false} otherwise.
     * @since 0.0.1-alpha
     */
    public abstract boolean requiresPermission();

    /**
     * Executes the subcommand.
     *
     * @param sender The CommandSender executing the subcommand.
     * @param args   The arguments provided for the subcommand.
     * @return {@code true} if the subcommand was executed successfully, {@code false} otherwise.
     * @since 0.0.1-alpha
     */
    public abstract boolean onCommand(CommandSender sender, String[] args);

    /**
     * Checks if the subcommand can be executed from the console.
     *
     * @return {@code true} if the subcommand can be executed from the console, {@code false} otherwise.
     * @since 0.0.1-alpha
     */
    public abstract boolean allowConsole();

    /**
     * Checks if the subcommand is enabled.
     *
     * @return {@code true} if the subcommand is enabled, {@code false} otherwise.
     * @since 0.0.1-alpha
     */
    public abstract boolean isEnabled();

    /**
     * Gets the disabled message for the subcommand.
     *
     * @return The disabled message for the subcommand.
     * @since 0.0.1-alpha
     */
    public abstract String disabledMessage();

}
