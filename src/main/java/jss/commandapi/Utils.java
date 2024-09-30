package jss.commandapi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    /**
     * @since 0.0.5-alpha
     */
    public static @NotNull List<String> setTabHelper_old(@NotNull List<String> options, String lastArgs){
        List<String> returned = new ArrayList<>();
        options.forEach( s -> {
            if(s != null && s.toLowerCase().startsWith(lastArgs.toLowerCase())){
                returned.add(s);
            }
        });
        return returned;
    }

    /**
     * Filters a list of options and returns a new list containing only elements that start
     * with the given prefix (`lastArgs`), ignoring case differences.
     * <p>
     * This method is commonly used to implement tab-completion functionality in commands
     * or autocompletion in user inputs.
     * </p>
     *
     * @param options  the list of possible string options to filter (must not be null)
     * @param lastArgs the string prefix to match against the options (must not be null)
     * @return a list of strings from the original list that start with the specified prefix, ignoring case
     * @throws NullPointerException if any parameter is null
     * @since 0.0.5-alpha
     */
    public static @NotNull List<String> setTabHelper(@NotNull List<String> options, String lastArgs) {
        // Ensure that lastArgs is not null to prevent NullPointerException during string operations.
        if (lastArgs == null) return Collections.emptyList(); // Returns an empty list if lastArgs is null.

        // Create a stream, filter by elements that start with the given prefix (case-insensitive),
        // and collect them into a new list.
        return options.stream()
                .filter(s -> s != null && s.toLowerCase().startsWith(lastArgs.toLowerCase()))
                .collect(Collectors.toList());
    }

}
