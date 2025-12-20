package rpg.model;

import rpg.model.enums.EnemyType;

public class Enemy extends Character {
    private final EnemyType type;

    public Enemy(EnemyType type) {
        super(type.displayName, type.baseMaxHp, type.baseAttack, type.baseDefense);
        this.type = type;
    }

    public EnemyType getType() {
        return type;
    }
}
