package realmcollision.classes;

import realmcollision.utils.CharacterStats;

public class Terminator extends Character {
    private int dotDamage;
    private int dotDuration;

    public Terminator(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
    }

    @Override
    public int attack() {
        if (battleLog != null) {
            battleLog.addMessage("|||INITIATING COMBAT|||");
        }
        this.setRegenActionPoints(true);
        this.setRegenEnergy(true);
        return 1500;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (battleLog != null) {
                battleLog.addMessage("Terminator defends |||RESISTING DAMAGE OUTPUT!|||");
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
                battleLog.addMessage("Terminator cast its skill");
                battleLog.addMessage("|||TARGET IDENTIFIED! SEIZE-PROTOCOL!|||");
            }
            this.setActionsPoints(this.getActionsPoints() - 3);
            this.setRegenEnergy(true);

            this.dotDamage = 1000;
            this.dotDuration = 3;

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
        if (this.getEnergy() >= 3000) {
            if (battleLog != null) {
                battleLog.addMessage("Terminator cast its ultimate");
                battleLog.addMessage("{!WARNING!}---[INITIATE-TERMINATION]---{!WARNING!}");
            }
            this.setEnergy(this.getEnergy() - 3000);
            return 15000;
        } else {
            if (battleLog != null) {
                battleLog.addMessage("Can't cast - not enough energy");
            }
            return 0;
        }
    }

    @Override
    public String getStatus() {
        if (hasDOT()) return "Seize-Protocol Active (" + dotDuration + " turns left)";
        return super.getStatus();
    }

    public boolean hasDOT() { return dotDuration > 0; }
    public int getDOTDamage() { return dotDamage; }
    public void decreaseDOTDuration() {
        if (dotDuration > 0) dotDuration--;
    }
}