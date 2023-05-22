package jss.commandapi;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    public abstract String name();

    public abstract String permission();

    public abstract boolean requiresPermission();

    public abstract boolean onCommand(CommandSender sender, String[] args);

    public abstract boolean allowConsole();

    public abstract boolean isEnabled();

    public abstract String disabledMessage();

}
