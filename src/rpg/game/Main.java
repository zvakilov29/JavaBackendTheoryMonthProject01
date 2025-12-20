package rpg.game;

import rpg.battle.BattleEngine;
import rpg.model.Player;
import rpg.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();

        String name = ui.readNonBlank("Enter your name:");
        var playerClass = ui.choosePlayerClass();
        Player player = new Player(name, playerClass);

        Difficulty difficulty = chooseDifficulty(ui);

        BattleEngine battleEngine = new BattleEngine(ui);
        GameEngine engine = new GameEngine(ui, battleEngine);

        engine.run(player, difficulty);
    }

    private static Difficulty chooseDifficulty(ConsoleUI ui) {
        ui.println("Choose difficulty:");
        ui.println("1) EASY");
        ui.println("2) NORMAL");
        ui.println("3) HARD");

        int c = ui.readIntInRange("Enter 1-3:", 1, 3);
        return switch (c) {
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.NORMAL;
            default -> Difficulty.HARD;
        };
    }
}
