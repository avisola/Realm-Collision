package realmcollision.classes.wanderer;

import realmcollision.classes.Character;
import realmcollision.utils.CharacterStats;

public class Wanderer extends Character {
    private Clone[] clones;
    private int damageMultiplier;

    public Wanderer(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
        this.clones = new Clone[2];
        this.damageMultiplier = 1;
    }

    @Override
    public int attack() {
        if (battleLog != null) {
            battleLog.addMessage("|||You will be dead, quick and swiftly|||");
        }
        this.setRegenActionPoints(true);
        this.setRegenEnergy(true);
        int damage = 800;

        for (Clone c : clones) {
            if (c != null && c.isAlive()) {
                damage += 3000;
            }
        }
        return damage;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (battleLog != null) {
                battleLog.addMessage("Wanderer defends |||Damn you!|||");
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
                battleLog.addMessage("Wanderer cast his skill");
                battleLog.addMessage("|||I'll cut you nice and easy|||");
            }
            this.setActionsPoints(this.getActionsPoints() - 3);
            return 4000;
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough action points");
        }
        return 0;
    }

    @Override
    public int castUltimate(Character target) {
        if (this.getEnergy() >= 1200) {
            if (battleLog != null) {
                battleLog.addMessage("Wanderer cast his ultimate");
                battleLog.addMessage("|||Echoes of the infinity, you shall bestowed infinite pain|||");
            }
            this.setEnergy(this.getEnergy() - 1200);

            for (int i = 0; i < clones.length; i++) {
                if (clones[i] == null || !clones[i].isAlive()) {
                    int cloneHP = CharacterStats.Wanderer.BASE_HEALTH / 8;
                    clones[i] = new Clone(cloneHP);
                    if (battleLog != null) {
                        battleLog.addMessage("A clone has been created with " + cloneHP + " HP!");
                    }
                    break;
                }
            }
            return 1000;
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough energy");
        }
        return 0;
    }

    public int castSecondUltimate(Character target) {
        int cost = 2000;

        if (this.getEnergy() >= cost) {
            if (battleLog != null) {
                battleLog.addMessage("Wanderer cast his second ultimate");
                battleLog.addMessage("|||From the depths of eternity, I bring forth your demise!|||");
            }
            this.setEnergy(this.getEnergy() - cost);

            int aliveClones = 0;
            for (Clone c : clones) {
                if (c != null && c.isAlive()) {
                    aliveClones++;
                    c.sacrifice();
                }
            }

            if (aliveClones == 2) {
                if (battleLog != null) {
                    battleLog.addMessage("Both clones sacrificed for a massive attack!");
                }
                damageMultiplier = 4;
                return 5000 * damageMultiplier;
            } else if (aliveClones == 1) {
                if (battleLog != null) {
                    battleLog.addMessage("One clone sacrificed for a powerful attack!");
                }
                damageMultiplier = 2;
                return 5000 * damageMultiplier;
            } else {
                if (battleLog != null) {
                    battleLog.addMessage("No clones available to sacrifice. Ultimate failed.");
                }
                return 5000;
            }
        }
        if (battleLog != null) {
            battleLog.addMessage("Can't cast - not enough energy");
        }
        return 0;
    }

    public Object takeDamageWithClones(int damage, Character attacker, boolean isSkill, boolean isUltimate) {
        for (Clone c : clones) {
            if (c != null && c.isAlive()) {
                if (battleLog != null) {
                    battleLog.addMessage("A clone intercepts the attack!");
                }
                c.takeDamage(damage, this, false, false);
                return damage;
            }
        }

        return super.takeDamage(damage, attacker, isSkill, isUltimate);
    }

    public String getCloneStatus() {
        int aliveCount = 0;
        StringBuilder status = new StringBuilder();

        for (int i = 0; i < clones.length; i++) {
            if (clones[i] != null && clones[i].isAlive()) {
                aliveCount++;
                status.append("Clone ").append(i + 1).append(": ").append(clones[i].getHealth()).append(" HP");
                if (i < clones.length - 1) status.append(" | ");
            }
        }

        if (aliveCount == 0) {
            return "No clones";
        }
        return status.toString();
    }
}