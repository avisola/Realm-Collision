package realmcollision.utils;

import realmcollision.classes.Character;
import realmcollision.classes.Terminator;
import realmcollision.classes.DemiGod;

public class CombatUtils {
    public static void handlePostActionRegeneration(Character current) {
        if (current.isRegenActionPointsActive()) {
            int newActionsPoints = current.getActionsPoints() + 4;
            current.setActionsPoints(newActionsPoints);
            current.addBattleLogMessage(current.getClass().getSimpleName() + " Action Points increased to: " + newActionsPoints);
            current.setRegenActionPoints(false);
        }

        if (current.isRegenEnergyActive()) {
            int newEnergy = current.getEnergy() + 250;
            current.setEnergy(newEnergy);
            current.addBattleLogMessage(current.getClass().getSimpleName() + " energy increased to: " + newEnergy);
            current.setRegenEnergy(false);
        }

        if (current.isHealActive()) {
            int newHealth = current.getHealth() + 500;
            current.setHealth(newHealth);
            current.addBattleLogMessage(current.getClass().getSimpleName() + " healed for 500 HP!");
            current.setHeal(false);
        }

        if (current.isSpecialERActive()) {
            int newEnergy = current.getEnergy() + 500;
            current.setEnergy(newEnergy);
            current.addBattleLogMessage("Bear witness! " + current.getClass().getSimpleName() + " receive a blessing from God");
            current.setSpecialER(false);
        }
    }

    public static void applyDOT(Character char1, Character char2) {
        // Apply Terminator's Seize Protocol
        if (char1 instanceof Terminator && ((Terminator) char1).hasDOT()) {
            int dot = ((Terminator) char1).getDOTDamage();
            char2.addBattleLogMessage("Seize Protocol from Terminator hits " + char2.getClass().getSimpleName() + " for " + dot);
            char2.takeDamage(dot, null, true, false);
            ((Terminator) char1).decreaseDOTDuration();
        }

        if (char2 instanceof Terminator && ((Terminator) char2).hasDOT()) {
            int dot = ((Terminator) char2).getDOTDamage();
            char1.addBattleLogMessage("Seize Protocol from Terminator hits " + char1.getClass().getSimpleName() + " for " + dot);
            char1.takeDamage(dot, null, true, false);
            ((Terminator) char2).decreaseDOTDuration();
        }

        if (char1 instanceof DemiGod) {
            ((DemiGod) char1).updateBlessing();
        }
        if (char2 instanceof DemiGod) {
            ((DemiGod) char2).updateBlessing();
        }
    }
}