package realmcollision.classes;

public class Knight extends Character {
    public Knight(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
    }

    @Override
    public int attack() {
        if (battleLog != null) {
            battleLog.addMessage("|||For thy realm, I shall weild my blade!|||");
        }
        this.setRegenEnergy(true);
        this.setRegenActionPoints(true);
        return 1500;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (battleLog != null) {
                battleLog.addMessage("|||I shall not yield, not whilst breath yet stirs me!|||");
            }
            this.setDefend(true);
            this.setRegenEnergy(true);
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
                battleLog.addMessage("Knight casts his skill");
                battleLog.addMessage("|||Blade and spirit, as one, deliver justice!|||");
                battleLog.addMessage("Can parry the next attack, and it will provide heal");
            }
            this.setProtection(true);
            this.setRegenEnergy(true);
            this.setHeal(true);
            this.setActionsPoints(this.getActionsPoints() - 3);
            return 0;
        } else {
            if (battleLog != null) {
                battleLog.addMessage("Can't cast - not enough action points");
            }
            return 0;
        }
    }

    @Override
    public int castUltimate(Character target) {
        if (this.getEnergy() >= 2000) {
            if (battleLog != null) {
                battleLog.addMessage("Knight casts his ultimate");
                battleLog.addMessage("|||Bare witness! The heavens themselves pass judgment!|||");
            }
            this.setEnergy(this.getEnergy() - 2000);
            return 8000;
        } else {
            if (battleLog != null) {
                battleLog.addMessage("Can't cast - not enough energy");
            }
            return 0;
        }
    }
}