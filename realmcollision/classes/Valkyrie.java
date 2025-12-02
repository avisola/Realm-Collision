package realmcollision.classes;

public class Valkyrie extends Character {
    private int soulGuidance = 0;

    public Valkyrie(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
    }

    public int getSoulGuidance() { return soulGuidance; }

    @Override
    public int attack() {
        if (battleLog != null) {
            battleLog.addMessage("|||Get pounced!|||");
        }
        this.setRegenActionPoints(true);
        this.setRegenEnergy(true);

        int baseDamage = 1000 + (soulGuidance * 500);
        return baseDamage;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (battleLog != null) {
                battleLog.addMessage("Valkyrie defends |||You little bastard!|||");
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
        if (this.getActionsPoints() < 3) {
            if (battleLog != null) {
                battleLog.addMessage("Can't cast - not enough action points");
            }
            return 0;
        }

        this.setActionsPoints(this.getActionsPoints() - 3);
        int dmg = 0;

        switch (soulGuidance) {
            case 0:
                if (battleLog != null) {
                    battleLog.addMessage("|||I recieve a blessing from the Gods!|||");
                    battleLog.addMessage("Valkyrie cast her skill");
                    battleLog.addMessage("Valkyrie uses Soul Healing (0 Guidance) : heals 4000 HP");
                }
                this.health += 4000;
                this.setRegenEnergy(true);
                break;

            case 1:
                if (battleLog != null) {
                    battleLog.addMessage("|||I will now return the favor!|||");
                    battleLog.addMessage("Valkyrie cast her skill");
                    battleLog.addMessage("Valkyrie uses Balanced Strike (1 Guidance) : Deals 1500 & Heals 700");
                }
                this.health += 2000;
                this.setRegenEnergy(true);
                dmg = 2000;
                break;

            case 2:
                if (battleLog != null) {
                    battleLog.addMessage("|||I'm gonna give you a big slice from me!|||");
                    battleLog.addMessage("Valkyrie cast her skill");
                    battleLog.addMessage("Valkyrie uses Wrathful Slice (2 Guidance) : Deals 2500 & Heals 300");
                }
                this.health += 1000;
                this.setRegenEnergy(true);
                dmg = 3000;
                break;
        }

        soulGuidance = 0;
        return dmg;
    }

    @Override
    public int castUltimate(Character target) {
        if (this.getEnergy() < 1000) {
            if (battleLog != null) {
                battleLog.addMessage("Can't cast - not enough energy");
            }
            return 0;
        } else {
            this.setEnergy(this.getEnergy() - 1000);
            int dmg = 0;

            switch (soulGuidance) {
                case 0:
                    if (battleLog != null) {
                        battleLog.addMessage("|||Have a taste of my swinging blade!|||");
                        battleLog.addMessage("Valkyrie unleashes her base Ultimate (0 Guidance) : Deals 2500");
                    }
                    dmg = 3000;
                    break;

                case 1:
                    if (battleLog != null) {
                        battleLog.addMessage("|||Your wounds are not enough!|||");
                        battleLog.addMessage("Valkyrie uses Empowered Ultimate (1 Guidance) : Deals 3500");
                    }
                    dmg = 3500;
                    break;

                case 2:
                    if (battleLog != null) {
                        battleLog.addMessage("|||This blade shall be your end!|||");
                        battleLog.addMessage("Valkyrie uses Ascended Ultimate (2 Guidance) : Deals 4000 + STUN");
                    }
                    target.stunned = true;
                    if (battleLog != null) {
                        battleLog.addMessage("The target is STUNNED!");
                    }
                    dmg = 4000;
                    break;
            }

            soulGuidance = 0;
            return dmg;
        }
    }

    @Override
    public Object takeDamage(int damage, Character attacker, boolean isSkill, boolean isUltimate) {
        Object result = super.takeDamage(damage, attacker, isSkill, isUltimate);

        if (damage > 0 && soulGuidance < 2) {
            soulGuidance++;
            if (battleLog != null) {
                battleLog.addMessage("Valkyrie gains Soul Guidance! Current: " + soulGuidance + "/2");
            }
        }

        return result;
    }
}