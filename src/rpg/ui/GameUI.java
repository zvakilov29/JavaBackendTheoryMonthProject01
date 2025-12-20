package rpg.ui;

public interface GameUI {
    void println(String s);

    int readIntInRange(String prompt, int min, int max);
}
