package rpg.game;

import rpg.battle.BattleEngine;
import rpg.battle.BattleOutcome;
import rpg.model.Enemy;
import rpg.model.Player;
import rpg.ui.GameUI;

public class GameEngine {

    private final GameUI ui;
    private final BattleEngine battleEngine;
    private final EnemyFactory enemyFactory = new EnemyFactory();

    public GameEngine(GameUI ui, BattleEngine battleEngine) {
        this.ui = ui;
        this.battleEngine = battleEngine;
    }

    public void run(Player player, Difficulty difficulty) {
        ui.println("\n=== GAME START ===");
        ui.println("Difficulty: " + difficulty);

        int fightsWon = 0;

        while (true) {
            Enemy enemy = enemyFactory.createEnemy(difficulty, player.getLevel());

            ui.println("\n--- A wild enemy appears! ---");
            ui.println(enemy.getName() + " (" + enemy.getType() + ")");

            BattleOutcome outcome = battleEngine.fight(player, enemy);

            if (outcome == BattleOutcome.WIN) {
                fightsWon++;
                ui.println("Fights won: " + fightsWon);

                // Later: roll an encounter here (merchant/chest/shrine)

            } else if (outcome == BattleOutcome.ESCAPED) {
                ui.println("You escaped. The run ends here (for now).");
                break;
            } else {
                ui.println("Game Over. Total fights won: " + fightsWon);
                break;
            }

            // Optional stop condition for a “demo”
            if (fightsWon >= 5) {
                ui.println("You cleared 5 fights! Demo complete.");
                break;
            }
        }
    }
}
