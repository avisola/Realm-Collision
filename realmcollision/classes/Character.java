package realmcollision.classes;

import realmcollision.panels.BattleLog;

public abstract class Character {
    protected int health;
    protected int energy;
    protected int actionsPoints;
    protected boolean punchline;
    protected boolean extra;
    protected boolean protection;
    protected int storedParryDamage;
    protected boolean penetration;
    protected boolean regenEnergy;
    protected boolean defend;
    protected boolean regenActionPoints;
    protected boolean heal;
    protected boolean specialER;
    public boolean stunned = false;

    protected BattleLog battleLog;

    public Character(int health, int energy, int actionsPoints) {
        this.health = health;
        this.energy = energy;
        this.actionsPoints = actionsPoints;
        this.punchline = false;
        this.extra = false;
        this.protection = false;
        this.storedParryDamage = 0;
        this.penetration = false;
        this.regenEnergy = false;
        this.defend = false;
        this.regenActionPoints = false;
        this.heal = false;
        this.specialER = false;
    }

    public void setBattleLog(BattleLog battleLog) {
        this.battleLog = battleLog;
    }

    public abstract int attack();

    public abstract void defense();

    public abstract int castSkill();

    public abstract int castUltimate(Character target);

    public Object takeDamage(int damage, Character attacker, boolean isSkill, boolean isUltimate) {
        int finalDamage = realmcollision.utils.CombatCalculator.calculateFinalDamage(
                damage, attacker, this, isSkill, isUltimate);

        if (this.isProtectionActive() && !isSkill) {
            this.addBattleLogMessage("FOR HONOR!");
            this.setProtection(false);
            this.storedParryDamage = finalDamage;
            return new Object[]{"parry", finalDamage};
        }

        this.health -= finalDamage;
        this.addBattleLogMessage(this.getClass().getSimpleName() + " took " + finalDamage + " damage! Remaining health: " + this.health);
        return finalDamage;
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }

    public int getHealth() {
        return this.health;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getActionsPoints() {
        return this.actionsPoints;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setActionsPoints(int actionsPoints) {
        this.actionsPoints = actionsPoints;
    }

    public boolean isPunchlineActive() {
        return this.punchline;
    }

    public void setPunchline(boolean punchline) {
        this.punchline = punchline;
    }

    public boolean isExtraActive() {
        return this.extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public boolean isProtectionActive() {
        return this.protection;
    }

    public void setProtection(boolean protection) {
        this.protection = protection;
    }

    public boolean isRegenEnergyActive() {
        return this.regenEnergy;
    }

    public void setRegenEnergy(boolean regenEnergy) {
        this.regenEnergy = regenEnergy;
    }

    public boolean isDefendActive() {
        return this.defend;
    }

    public void setDefend(boolean defend) {
        this.defend = defend;
    }

    public boolean isRegenActionPointsActive() {
        return this.regenActionPoints;
    }

    public void setRegenActionPoints(boolean regenActionPoints) {
        this.regenActionPoints = regenActionPoints;
    }

    public boolean isHealActive() {
        return this.heal;
    }

    public void setHeal(boolean heal) {
        this.heal = heal;
    }

    public boolean isSpecialERActive() {
        return this.specialER;
    }

    public void setSpecialER(boolean specialER) {
        this.specialER = specialER;
    }

    public String getStatus() {
        if (isDefeated()) return "Defeated";
        if (isDefendActive()) return "Defending";
        if (isProtectionActive()) return "Protecting";
        if (isSpecialERActive()) return "Empowered";
        if (isPunchlineActive()) return "Punchline";
        return "Normal";
    }

    public void addBattleLogMessage(String message) {
        if (this.battleLog != null) {
            this.battleLog.addMessage(message);
        }
    }
}