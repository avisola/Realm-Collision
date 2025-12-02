package realmcollision.utils;

import realmcollision.classes.Character;
import realmcollision.classes.Deadeye;

public class CombatCalculator {
    public static int calculateFinalDamage(int baseDamage, Character attacker, Character defender,
                                           boolean isSkill, boolean isUltimate) {
        int finalDamage = baseDamage;

        if (attacker instanceof Deadeye && ((Deadeye) attacker).hasPenetration()) {
            defender.addBattleLogMessage("All defenses ignored!");
            ((Deadeye) attacker).usesPenetration();
            return finalDamage;
        }

        if (defender.isPunchlineActive()) {
            finalDamage /= 4;
            defender.setPunchline(false);
            defender.addBattleLogMessage("Guess what? Punchline activated! Damage reduced to " + finalDamage);
        }

        if (defender.isDefendActive() && !isSkill) {
            finalDamage /= 2;
            defender.setDefend(false);
            defender.addBattleLogMessage("Mitigating Damage, reduced to " + finalDamage);
        }

        return finalDamage;
    }
}