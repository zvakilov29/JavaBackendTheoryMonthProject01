package rpg.game;

import rpg.battle.BattleEngine;
import rpg.model.Player;
import rpg.persistence.SaveManager;
import rpg.ui.ConsoleUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        SaveManager saveManager = new SaveManager("save.properties");

        Player player;

        // -----------------------------
        // Save menu (Continue / New / Delete)
        // -----------------------------

        if (saveManager.exists()) {
            ui.println("Save file found.");
            ui.println("1) Continue");
            ui.println("2) New game (overwrite save)");
            ui.println("3) Delete save");

            int choice = ui.readIntInRange("Choose 1-3:", 1, 3);

            if (choice == 1) {
                try {
                    player = saveManager.load();
                    ui.println("Loaded save for: " + player.getName()
                            + " (" + player.getPlayerClass() + "), Lv " + player.getLevel());
                } catch (Exception e) {
                    ui.println("Failed to load save: " + e.getMessage());
                    ui.println("Starting a new game instead.");
                    player = createNewPlayer(ui);
                }
            } else if (choice == 2) {
                player = createNewPlayer(ui);
                try {
                    saveManager.save(player); // create initial save
                    ui.println("(Saved new game)");
                } catch (IOException e) {
                    ui.println("WARNING: Could not save: " + e.getMessage());
                }
            } else { // 3
                try {
                    saveManager.delete();
                    ui.println("Save deleted.");
                } catch (IOException e) {
                    ui.println("WARNING: Could not delete save: " + e.getMessage());
                }
                player = createNewPlayer(ui);
                try {
                    saveManager.save(player);
                    ui.println("(Saved new game)");
                } catch (IOException e) {
                    ui.println("WARNING: Could not save: " + e.getMessage());
                }
            }
        } else {
            player = createNewPlayer(ui);
            try {
                saveManager.save(player);
                ui.println("(Saved new game)");
            } catch (IOException e) {
                ui.println("WARNING: Could not save: " + e.getMessage());
            }
        }

        Difficulty difficulty = chooseDifficulty(ui);

        BattleEngine battleEngine = new BattleEngine(ui);

        GameEngine engine = new GameEngine(ui, battleEngine, saveManager);

        engine.run(player, difficulty);
    }

    private static Player createNewPlayer(ConsoleUI ui) {
        String name = ui.readNonBlank("Enter your name:");
        var playerClass = ui.choosePlayerClass();
        return new Player(name, playerClass);
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
