package rpg.game;

import rpg.battle.BattleEngine;
import rpg.model.Enemy;
import rpg.model.Player;
import rpg.model.enums.EnemyType;
import rpg.model.enums.PlayerClass;
import rpg.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();

        String name = ui.readNonBlank("Enter your name:");
        PlayerClass pc = ui.choosePlayerClass();
        Player player = new Player(name, pc);

        Enemy enemy = new Enemy(EnemyType.GOBLIN);

        BattleEngine battleEngine = new BattleEngine(ui); // ui is GameUI
        battleEngine.fight(player, enemy);
    }
}
