package realmcollision.classes;

public class DemiGod extends Character {
    private int blessingDuration;
    private boolean hasFollowUp = false;
    private int followUpDamage = 0;
    private boolean energyBoostActive = false;

    public DemiGod(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
        this.blessingDuration = 0;
    }

    @Override
    public int attack() {
        if (battleLog != null) {
            battleLog.addMessage("|||Take this! Sword of light unleash, Slash!|||");
        }
        this.setRegenEnergy(true);
        this.setRegenActionPoints(true);
        if (battleLog != null) {
            battleLog.addMessage("Regenerate 4 Action points");
        }

        int damage = 900;

        if (hasFollowUp && isBlessingActive()) {
            if (battleLog != null) {
                battleLog.addMessage("DemiGod's divine power triggers a FOLLOW-UP ATTACK! +" + followUpDamage + " damage!");
            }
            damage += followUpDamage;
        }

        return damage;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (battleLog != null) {
                battleLog.addMessage("|||Oh mother, protect me|||");
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
                battleLog.addMessage("Annarria cast his skill");
                battleLog.addMessage("|||Blessing of Light, grant me strength!|||");
            }
            this.setSpecialER(true);
            this.setActionsPoints(this.getActionsPoints() - 3);

            this.blessingDuration = 3;
            this.hasFollowUp = true;
            this.followUpDamage = 1500;
            this.energyBoostActive = true;

            if (battleLog != null) {
                battleLog.addMessage("Blessing of Light is activated! Will last for " + blessingDuration + " turns.");
                battleLog.addMessage("Gains FOLLOW-UP ATTACKS (+1500 damage) and ENERGY BOOST for " + blessingDuration + " turns!");
            }
            return 0;
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
                battleLog.addMessage("Annarria cast his ultimate");
                battleLog.addMessage("Oh gods from above, witness the power of your creation, RODS OF JUDGEMENT!");
            }
            this.setEnergy(this.getEnergy() - 1000);
            return 10000;
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough energy");
        }
        return 0;
    }

    public void updateBlessing() {
        if (blessingDuration > 0) {
            blessingDuration--;

            if (blessingDuration <= 0) {
                hasFollowUp = false;
                energyBoostActive = false;
                followUpDamage = 0;
                if (battleLog != null) {
                    battleLog.addMessage("Blessing of Light has faded away.");
                }
            }
        }
    }

    public boolean isBlessingActive() {
        return blessingDuration > 0;
    }

    @Override
    public void setRegenEnergy(boolean regenEnergy) {
        super.setRegenEnergy(regenEnergy);
        if (regenEnergy && energyBoostActive && isBlessingActive()) {
            this.setEnergy(this.getEnergy() + 250);
            if (battleLog != null) {
                battleLog.addMessage("Blessing of Light boosts energy regeneration!");
            }
        }
    }

    public int getRemainingBlessingTurns() {
        return blessingDuration;
    }

    @Override
    public String getStatus() {
        if (isBlessingActive()) {
            return "Blessing Active (" + blessingDuration + " turns)";
        }
        return super.getStatus();
    }
}