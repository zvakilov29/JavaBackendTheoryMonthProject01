package rpg.game.encounters;

import rpg.items.ItemType;
import rpg.model.Player;
import rpg.ui.GameUI;

public class MerchantEncounter implements Encounter {
    @Override
    public String name() {
        return "Merchant";
    }

    @Override
    public void run(Player player, GameUI ui){
        ui.println("\n=== MERCHANT ===");
        ui.println("A merchant offers you potions.");

        // For now, no gold system: one can “buy” 1 potion per encounter
        ui.println("1) Buy 1 Healing Potion");
        ui.println("2) Leave");

        int choice = ui.readIntInRange("Choose 1-2:", 1, 2);
        if (choice == 1) {
            player.getInventory().add(ItemType.HEALING_POTION, 1);
            ui.println("You bought 1 Healing Potion.");
            ui.println("You now have: " + player.getInventory().getCount(ItemType.HEALING_POTION));
        } else {
            ui.println("You leave the merchant.");
        }
    }
}
