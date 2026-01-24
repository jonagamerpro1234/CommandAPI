# CommandAPI



A lightweight and extensible command framework for Bukkit / Spigot plugins, designed to simplify command handling, subcommands, tab completion, and debugging.

CommandAPI focuses on clean structure, developer ergonomics, and future scalability.

[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/jonagamerpro1234/CommandAPI)

### ✨ Features

✅ Clean BaseCommand system

✅ Modular SubCommand architecture

✅ Built-in tab completion

✅ Optional main command logic (no subcommand required)

✅ Centralized debug & logging system

✅ Execution and performance logging

✅ Easy to extend and maintain

### Quick Example
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

###  How it works

/mycommand → runs onCommandMain.

/mycommand help → runs HelpSubCommand.

Tab completion works automatically for both main command and subcommands.

Each subcommand can define permissions, console access, and custom tab lists.

### Maven
```xml
<dependency>
  <groupId>jss.commandapi</groupId>
  <artifactId>command-api</artifactId>
  <version>0.0.8-alpha</version>
</dependency>
```

###  Gradle (Groovy DSL)
```groovy
dependencies {
    implementation 'jss.commandapi:command-api:0.0.8-alpha'
}
```

### Gradle (Kotlin DSL)
```kotlin
dependencies {
    implementation("jss.commandapi:command-api:0.0.8-alpha")
}
```

## 🚀 Getting Started
1️⃣ Initialize CommandAPI
Call this once in your plugin onEnable():

``` java
@Override
public void onEnable() {
CommandApi.init(this);
CommandApi.setDebug(true);
CommandApi.setLogExecution(true);
CommandApi.setLogPerformance(true);
}
```
2️⃣ Create a Base Command
``` java
public class MainCommand extends BaseCommand {

    public MainCommand() {
        name("example")
                .addSubCommand(new HelpSubCommand())
                .addSubCommand(new ReloadSubCommand());
    }

    @Override
    public boolean onCommandMain(CommandSender sender, String[] args) {
        sender.sendMessage("§eUse /example help");
        return true;
    }
}
```
3️⃣ Register the Command
```java
@Override
public void onEnable() {
new MainCommand().register(this);
}
```
⚠️ The command must exist in plugin.yml.

4️⃣ Create a SubCommand
``` java
public class HelpSubCommand extends SubCommand {

    @Override
    public String name() {
        return "help";
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage("§aThis is the help command!");
        return true;
    }
}
```

## 🧩 SubCommand Capabilities
Each SubCommand can define:

- Permissions
- Aliases
- Tab completion
- Console usage
- Player-only restriction
- Usage & description
- Enable / disable logic

Example:
```java
@Override
public List<String> aliases() {
return List.of("h", "?");
}
```

### 🛠 Debug & Logging
- CommandAPI includes a built-in utility for debugging and logging:
- Command execution logs
- Sender type (Player / Console)
- Execution time
- Error handling with stack traces (debug mode)

This helps a lot during development and production debugging.

## 🧪 Current Status
- Version: 0.0.x-alpha
- API is stable for testing
- Breaking changes may occur until 1.0.0

## 🔮 Roadmap
- Command execution hooks
- Context-based command handling
- Better permission resolvers
- Metrics & analytics
- Optional annotations support

## 📜 License
MIT License – free to use, modify, and distribute.


