package realmcollision.utils;

public class CharacterStats {
    public static class ArcMage {
        public static final int BASE_HEALTH = 80000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static class DemiGod {
        public static final int BASE_HEALTH = 100000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static class Trickster {
        public static final int BASE_HEALTH = 70000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static class Deadeye {
        public static final int BASE_HEALTH = 110000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static class Terminator {
        public static final int BASE_HEALTH = 120000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static class Knight {
        public static final int BASE_HEALTH = 100000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static class Valkyrie {
        public static final int BASE_HEALTH = 90000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static class Wanderer {
        public static final int BASE_HEALTH = 60000;
        public static final int BASE_ENERGY = 0;
        public static final int BASE_ACTION_POINTS = 0;
        public static final int MAX_ENERGY = 10000;
    }

    public static int getMaxHealth(String characterClass) {
        switch (characterClass) {
            case "ArcMage": return ArcMage.BASE_HEALTH;
            case "DemiGod": return DemiGod.BASE_HEALTH;
            case "Trickster": return Trickster.BASE_HEALTH;
            case "Deadeye": return Deadeye.BASE_HEALTH;
            case "Terminator": return Terminator.BASE_HEALTH;
            case "Knight": return Knight.BASE_HEALTH;
            case "Valkyrie": return Valkyrie.BASE_HEALTH;
            case "Wanderer": return Wanderer.BASE_HEALTH;
            default: return 150000;
        }
    }

    public static int getMaxEnergy(String characterClass) {
        switch (characterClass) {
            case "ArcMage": return ArcMage.MAX_ENERGY;
            case "DemiGod": return DemiGod.MAX_ENERGY;
            case "Trickster": return Trickster.MAX_ENERGY;
            case "Deadeye": return Deadeye.MAX_ENERGY;
            case "Terminator": return Terminator.MAX_ENERGY;
            case "Knight": return Knight.MAX_ENERGY;
            case "Valkyrie": return Valkyrie.MAX_ENERGY;
            case "Wanderer": return Wanderer.MAX_ENERGY;
            default: return 3000;
        }
    }
}