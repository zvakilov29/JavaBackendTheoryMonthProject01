package rpg.ui;

import rpg.model.enums.PlayerClass;

import java.util.Scanner;

public class ConsoleUI implements GameUI {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void println(String s) { System.out.println(s); }

    public String readNonBlank(String prompt) {
        println(prompt);
        while (true) {
            String s = sc.nextLine().trim();
            if (!s.isBlank()) return s;
            println("Please enter a non-empty value:");
        }
    }

    @Override
    public int readIntInRange(String prompt, int min, int max) {
        println(prompt);
        while (true) {
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v < min || v > max) throw new IllegalArgumentException();
                return v;
            } catch (Exception e) {
                println("Enter a number between " + min + " and " + max + ":");
            }
        }
    }

    public PlayerClass choosePlayerClass() {
        println("Choose your class:");
        println("1) WARRIOR");
        println("2) MAGE");
        println("3) ROGUE");
        int choice = readIntInRange("Enter 1-3:", 1, 3);

        return switch (choice) {
            case 1 -> PlayerClass.WARRIOR;
            case 2 -> PlayerClass.MAGE;
            default -> PlayerClass.ROGUE;
        };
    }
}
