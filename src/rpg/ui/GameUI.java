package rpg.ui;

import java.util.List;

public interface GameUI {
    void println(String s);

    /** Ask for a non-empty string (name, etc.) */
    String readNonBlank(String prompt);

    /**
     * Show a prompt + options, return the chosen option index (0-based).
     * Example: options = ["Attack", "Defend"] -> returns 0 or 1
     */
    int chooseOption(String prompt, List<String> options);
}
