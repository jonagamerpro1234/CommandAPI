# CommandAPI

[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/jonagamerpro1234/CommandAPI)

A flexible Bukkit/Spigot command system with built-in subcommands, permissions, and tab completion.

Features

Define a main command with optional custom logic.

Add subcommands with their own permissions, console rules, and tab completion.

Automatic tab suggestions for main commands and subcommands.

Easy registration via your JavaPlugin.

Quick Example
```java
new BaseCommand() {
    {
        name("mycommand")
        .addSubCommand(new HelpSubCommand())
        .addSubCommand(new ReloadSubCommand());
    }

    @Override
    public boolean onCommandMain(CommandSender sender, String[] args) {
        sender.sendMessage("Use /mycommand help to see available commands!");
        return true;
    }

    @Override
    public List<String> onTabMain(CommandSender sender, String[] args) {
        return Arrays.asList("option1", "option2");
    }
}.register(this); // automatically registers the command
```

How it works

/mycommand → runs onCommandMain.

/mycommand help → runs HelpSubCommand.

Tab completion works automatically for both main command and subcommands.

Each subcommand can define permissions, console access, and custom tab lists.

##Maven
```xml
<dependency>
  <groupId>jss.commandapi</groupId>
  <artifactId>command-api</artifactId>
  <version>0.0.8-alpha</version>
</dependency>
```

## Gradle (Groovy DSL)
```groovy
dependencies {
    implementation 'jss.commandapi:command-api:0.0.8-alpha'
}
```

## Gradle (Kotlin DSL)
```kotlin
dependencies {
    implementation("jss.commandapi:command-api:0.0.8-alpha")
}
```
