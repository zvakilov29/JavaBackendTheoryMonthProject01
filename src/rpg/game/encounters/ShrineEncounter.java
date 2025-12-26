package rpg.game.encounters;

import rpg.model.Player;
import rpg.ui.GameUI;

public class ShrineEncounter implements Encounter {
    @Override
    public String name() {
        return "Shrine";
    }

    @Override
    public void run(Player player, GameUI ui) {
        ui.println("\n=== SHRINE ===");
        ui.println("You find a mysterious shrine. Choose a blessing:");

        ui.println("1) +10 Max HP");
        ui.println("2) +2 Attack");
        ui.println("3) +2 Defense");

        int choice = ui.readIntInRange("Choose 1-3:", 1, 3);

        switch (choice) {
            case 1 -> {
                player.addMaxHpModifier(10);
                ui.println("Blessing received: +10 Max HP!");
            }
            case 2 -> {
                player.addAttackModifier(2);
                ui.println("Blessing received: +2 Attack!");
            }
            default -> {
                player.addDefenseModifier(2);
                ui.println("Blessing received: +2 Defense!");
            }
        }

        ui.println("Updated stats -> HP: " + player.getHp() + "/" + player.getMaxHp()
                + " | ATK: " + player.getAttack()
                + " | DEF: " + player.getDefense());
    }
}
