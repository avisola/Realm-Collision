package realmcollision.classes;

public class ArcMage extends Character {
    public ArcMage(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
    }

    @Override
    public int attack() {
        if (battleLog != null) {
            battleLog.addMessage("|||lethal flames, Eradicate!|||");
        }
        this.setRegenActionPoints(true);
        this.setRegenEnergy(true);
        return 1000;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (battleLog != null) {
                battleLog.addMessage("|||Mongrels|||");
            }
            this.setDefend(true);
            this.setActionsPoints(this.getActionsPoints() - 2);
        } else {
            if (battleLog != null) {
                battleLog.addMessage("Can't defend - not enough action points");
            }
        }
    }

    @Override
    public int castSkill() {
        if (this.getActionsPoints() >= 3) {
            if (battleLog != null) {
                battleLog.addMessage("Sienna cast her skill");
                battleLog.addMessage("|||be engulf with this flames of hell|||");
            }
            this.setActionsPoints(this.getActionsPoints() - 3);
            return 6000;
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough action points");
        }
        return 0;
    }

    @Override
    public int castUltimate(Character target) {
        if (this.getEnergy() >= 1000) {
            if (battleLog != null) {
                battleLog.addMessage("Sienna cast her ultimate");
                battleLog.addMessage("I Sienna, the ArcMage of fire, FLAMES OF ANNIHILATION!!! Death is inevitable.");
            }
            this.setEnergy(this.getEnergy() - 1000);
            return 20000;
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough energy");
        }
        return 0;
    }
}