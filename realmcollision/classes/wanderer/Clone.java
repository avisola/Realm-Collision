package realmcollision.classes.wanderer;

import realmcollision.classes.Character;

public class Clone extends Wanderer {
    public Clone(int health) {
        super(health, 0, 0);
        if (this.battleLog != null) {
            this.addBattleLogMessage("|||A clone has been created with " + health + " HP!|||");
        }
    }

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public void defense() {
        if (this.battleLog != null) {
            this.addBattleLogMessage("The clone cannot defend");
        }
    }

    @Override
    public int castSkill() {
        if (this.battleLog != null) {
            this.addBattleLogMessage("The clone cannot use skills");
        }
        return 0;
    }

    @Override
    public int castUltimate(Character target) {
        if (this.battleLog != null) {
            this.addBattleLogMessage("The clone cannot use ultimate");
        }
        return 0;
    }

    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    public void sacrifice() {
        if (this.battleLog != null) {
            this.addBattleLogMessage("The clone sacrifices itself for Wanderer's power!");
        }
        this.setHealth(0);
    }
}