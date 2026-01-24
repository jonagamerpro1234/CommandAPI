package jss.commandapi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BaseCommand is a flexible constructor for Bukkit commands that allows:
 * 1. Registering subcommands.
 * 2. Executing the main command logic if no subcommand is provided.
 * 3. Tab completion for both subcommands and the main command.
 *
 * @author jonagamerpro1234
 * @version 0.0.9-alpha
 */
public abstract class BaseCommand implements TabExecutor {

    private String name;
    private final List<SubCommand> subCommands = new ArrayList<>();

    /**
     * Registers this command automatically using the provided JavaPlugin.
     *
     * @param plugin the JavaPlugin instance used to register the command
     * @throws IllegalStateException if the command name has not been set or is empty
     * @since 0.0.8-alpha
     */
    public void register(@NotNull JavaPlugin plugin) {
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Command name cannot be null or empty!");
        }

        PluginCommand command = plugin.getCommand(name);
        if (command == null) {
            CommandApi.log("Command '" + name + "' is not defined in plugin.yml!");
            return;
        }

        command.setExecutor(this);
        command.setTabCompleter(this);

        CommandApi.log("Registered command: " + name);
    }

    /**
     * Sets the main command name.
     *
     * @param name the name of the main command
     * @return this BaseCommand instance for chaining
     * @since 0.0.7-alpha
     */
    public BaseCommand name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Adds a subcommand to this command.
     *
     * @param subCommand the subcommand to add
     * @return this BaseCommand instance for chaining
     * @since 0.0.7-alpha
     */
    public BaseCommand addSubCommand(SubCommand subCommand) {
        this.subCommands.add(subCommand);
        return this;
    }

    /**
     * Adds multiple subcommands to this command at once.
     *
     * @param subCommands the subcommands to add
     * @return this BaseCommand instance for chaining
     * @since 0.0.7-alpha
     */
    public BaseCommand addSubCommand(SubCommand... subCommands) {
        this.subCommands.addAll(Arrays.asList(subCommands));
        return this;
    }

    /**
     * Logic executed when no subcommand is provided.
     *
     * @param sender the CommandSender executing the command
     * @param args the command arguments
     * @return true if execution was successful
     * @since 0.0.7-alpha
     */
    public boolean onCommandMain(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage("§cUsage: /" + name + " <subcommand>");
        return true;
    }

    /**
     * Tab completion suggestions for the main command.
     *
     * @param sender the CommandSender requesting tab completion
     * @param args the command arguments
     * @return a list of suggestions
     * @since 0.0.7-alpha
     */
    public List<String> onTabMain(@NotNull CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    /**
     * Handles command execution with debug, logging, and performance tracking.
     * @since 0.0.8-alpha
     */
    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            String @NotNull [] args
    ) {
        long startTime = System.nanoTime();

        try {

            if (args.length < 1) {
                return onCommandMain(sender, args);
            }

            String subName = args[0];

            for (SubCommand sub : subCommands) {
                if (subName.equalsIgnoreCase(sub.name()) || sub.aliases().contains(subName)) {

                    if (!sub.isEnabled()) {
                        sender.sendMessage(sub.disabledMessage());
                        return true;
                    }

                    if (sub.requiresPermission() && !sender.hasPermission("plugin." + sub.permission())) {
                        sender.sendMessage("§cYou do not have permission to execute this command!");
                        return true;
                    }

                    if (!sub.allowConsole() && !(sender instanceof Player)) {
                        sender.sendMessage("§cThis command cannot be executed from console!");
                        return true;
                    }

                    return sub.onCommand(sender, args);
                }
            }

            return onCommandMain(sender, args);

        } catch (Exception exception) {

            CommandApi.handleError(name, sender, exception);
            return true;

        } finally {

            long executionTimeMs = (System.nanoTime() - startTime) / 1_000_000;
            CommandApi.logCommandExecution(name, sender, executionTimeMs);
        }
    }

    /**
     * Handles tab completion for the main command and its subcommands.
     *
     * @since 0.0.7-alpha
     */
    @Override
    public List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String alias,
            String @NotNull [] args
    ) {

        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            String start = args[0].toLowerCase();

            List<String> subNames = subCommands.stream()
                    .map(SubCommand::name)
                    .filter(n -> n.toLowerCase().startsWith(start))
                    .collect(Collectors.toList());

            subNames.addAll(onTabMain(sender, args));
            suggestions.addAll(subNames);

        } else if (args.length > 1) {
            for (SubCommand sub : subCommands) {
                if (args[0].equalsIgnoreCase(sub.name()) || sub.aliases().contains(args[0])) {
                    List<String> tab = sub.onTabList(sender, args);
                    return tab != null ? tab : new ArrayList<>();
                }
            }

            return onTabMain(sender, args);
        }

        return suggestions;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public String getName() {
        return name;
    }
}
