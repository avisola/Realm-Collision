package realmcollision.classes;

import realmcollision.utils.CharacterStats;

public class Deadeye extends Character {
    private boolean penetrationActive;

    public Deadeye(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
        this.penetrationActive = false;
    }

    @Override
    public int attack() {
        if (battleLog != null) {
            battleLog.addMessage("|||Dodge this, lethal strike|||");
        }
        this.setRegenActionPoints(true);
        this.setRegenEnergy(true);
        return 3000;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (battleLog != null) {
                battleLog.addMessage("Cipher defends |||Practically Invisible|||");
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
                battleLog.addMessage("Cipher cast his skill");
                battleLog.addMessage("|||Let me tell you something, I don't miss|||");
            }
            this.setActionsPoints(this.getActionsPoints() - 3);
            this.setRegenEnergy(true);
            return 5000;
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough action points");
        }
        return 0;
    }

    @Override
    public int castUltimate(Character target) {
        if (this.getEnergy() >= 1500) {
            if (battleLog != null) {
                battleLog.addMessage("Cipher cast his ultimate");
                battleLog.addMessage("Deadeye, descender from future, BULLET OF FATE!");
                battleLog.addMessage("you can't dodge something that doesn't even exist");
            }
            this.setEnergy(this.getEnergy() - 1500);
            this.penetrationActive = true;
            return 10000;
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough energy");
        }
        return 0;
    }

    public boolean hasPenetration() { return penetrationActive; }
    public void usesPenetration() { this.penetrationActive = false; }
}