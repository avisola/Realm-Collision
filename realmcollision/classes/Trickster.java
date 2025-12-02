package realmcollision.classes;

public class Trickster extends Character {
    private int attackStacks;
    private static final int MAX_STACKS = 999;
    private static final int STACK_ATTACK_BONUS = 300;
    private static final int STACK_ULTIMATE_BONUS = 500;
    private static final int BASE_ULTIMATE_DAMAGE = 5000;
    private boolean ultimateUsedThisTurn = false;

    public Trickster(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
        this.attackStacks = 0;
    }

    @Override
    public int attack() {
        if (this.battleLog != null) {
            this.addBattleLogMessage("|||Banishing Triple Slash, be careful, you might bleed on that|||");
        }
        this.setRegenActionPoints(true);
        this.setRegenEnergy(true);

        // Calculate damage: base 1000 + stack bonus
        int baseDamage = 1000 + (this.attackStacks * STACK_ATTACK_BONUS);
        this.ultimateUsedThisTurn = false;

        if (this.battleLog != null && this.attackStacks > 0) {
            this.addBattleLogMessage("Attack stacks boost damage! Total: " + baseDamage + " (+" + (this.attackStacks * STACK_ATTACK_BONUS) + " from stacks)");
        }

        return baseDamage;
    }

    @Override
    public void defense() {
        if (this.getActionsPoints() >= 2) {
            if (this.battleLog != null) {
                this.addBattleLogMessage("Trickster defends |||Ops, Catch me if you can|||");
            }
            this.setDefend(true);
            this.setRegenEnergy(true);
            this.setActionsPoints(this.getActionsPoints() - 2);
            this.ultimateUsedThisTurn = false;
        } else {
            if (this.battleLog != null) {
                this.addBattleLogMessage("Can't defend - not enough action points");
            }
        }
    }

    @Override
    public int castSkill() {
        if (this.getActionsPoints() >= 3) {
            if (this.battleLog != null) {
                this.addBattleLogMessage("Trickster cast her skill");
                this.addBattleLogMessage("|||Can I steal this spotlight of yours?|||");
            }
            this.setPunchline(true);
            this.setRegenEnergy(true);
            this.setActionsPoints(this.getActionsPoints() - 3);

            // Gain attack stack
            if (this.attackStacks < MAX_STACKS) {
                this.attackStacks++;

                if (this.battleLog != null) {
                    this.addBattleLogMessage("Trickster gains an attack stack! Current stacks: " +
                            this.attackStacks + " | Total ATK Bonus: " + (this.attackStacks * STACK_ATTACK_BONUS));
                    this.addBattleLogMessage("Basic Attack now deals: " + (1000 + (this.attackStacks * STACK_ATTACK_BONUS)) + " damage");
                }
            } else {
                if (this.battleLog != null) {
                    this.addBattleLogMessage("Maximum stacks reached! (" + MAX_STACKS + " stacks)");
                }
            }

            this.ultimateUsedThisTurn = false;
            return 0;
        }
        if (this.battleLog != null) {
            this.addBattleLogMessage("Can't cast - not enough action points");
        }
        return 0;
    }

    @Override
    public int castUltimate(Character target) {
        if (this.getEnergy() >= 1500) {
            if (this.battleLog != null) {
                this.addBattleLogMessage("Trickster cast her ultimate");
                this.addBattleLogMessage("|||I'm the deliverer of time god, TEMPORAL LOOP!|||");
                this.addBattleLogMessage("|||you will never reach your truth|||");
            }

            this.ultimateUsedThisTurn = true;

            int energyBefore = this.getEnergy();
            this.setEnergy(this.getEnergy() - 1500);
            if (this.battleLog != null) {
                this.addBattleLogMessage("ENERGY BURN: " + energyBefore + " → " + this.getEnergy());
            }

            // Calculate ultimate damage: base + stack bonus
            int stackBonusDamage = this.attackStacks * STACK_ULTIMATE_BONUS;
            int totalUltimateDamage = BASE_ULTIMATE_DAMAGE + stackBonusDamage;

            if (this.battleLog != null) {
                if (this.attackStacks > 0) {
                    this.addBattleLogMessage(this.attackStacks + " stacks converted to " + stackBonusDamage + " bonus damage!");
                }
                this.addBattleLogMessage("Total Ultimate Damage: " + totalUltimateDamage);
            }

            // Reset stacks after ultimate
            if (this.attackStacks > 0) {
                int totalAttackBonusLost = this.attackStacks * STACK_ATTACK_BONUS;
                if (this.battleLog != null) {
                    this.addBattleLogMessage("Attack stacks reset! Lost " + totalAttackBonusLost + " attack power.");
                }
                this.attackStacks = 0;
            }

            this.setExtra(true);
            if (this.battleLog != null) {
                this.addBattleLogMessage("Trickster gets an extra turn!");
            }
            return totalUltimateDamage;
        }
        if (this.battleLog != null) {
            this.addBattleLogMessage("Can't cast - not enough energy");
        }
        return 0;
    }

    public boolean hasExtra() {
        return this.isExtraActive() && this.ultimateUsedThisTurn;
    }

    public void useExtra() {
        this.setExtra(false);
        this.ultimateUsedThisTurn = false;
        if (this.battleLog != null) {
            this.addBattleLogMessage("Trickster uses her extra turn!");
        }
    }

    public int getTotalAttackBonus() {
        return this.attackStacks * STACK_ATTACK_BONUS;
    }

    @Override
    public String getStatus() {
        if (this.attackStacks > 0) {
            return "Stacks: " + this.attackStacks + " (+" + getTotalAttackBonus() + " ATK)";
        }
        return super.getStatus();
    }
}