package realmcollision;

import realmcollision.classes.*;
import realmcollision.classes.wanderer.Wanderer;
import realmcollision.utils.CombatUtils;
import realmcollision.utils.CharacterStats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Map;

public class GameController implements ActionListener {
    private RealmCollisionGUI gui;
    private realmcollision.classes.Character player1, player2;
    private int currentTurn = 1;
    private int currentCycle = 1;
    private final int MAX_CYCLES = 100;
    private boolean gameRunning = false;

    private Map<String, String> characterNames = Map.of(
            "AM", "ArcMage Sienna", "DG", "DemiGod Annarria", "TS", "Trickster Amethyst",
            "DE", "Deadeye Cipher", "TR", "Terminator Vega", "KN", "Knight Yhwach",
            "VK", "Valkyrie Chaela", "WR", "Wanderer Shinmu"
    );

    public GameController(RealmCollisionGUI gui, String p1Code, String p2Code) {
        this.gui = gui;
        initializeGame(p1Code, p2Code);
    }

    private void initializeGame(String p1Code, String p2Code) {
        player1 = createCharacter(p1Code);
        player2 = createCharacter(p2Code);

        if (player1 == null || player2 == null) {
            JOptionPane.showMessageDialog(gui, "Error creating characters!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        player1.setBattleLog(gui.getBattleLog());
        player2.setBattleLog(gui.getBattleLog());

        String p1Name = characterNames.get(p1Code);
        String p2Name = characterNames.get(p2Code);

        gui.getPlayer1Panel().setCharacter(player1, p1Name);
        gui.getPlayer2Panel().setCharacter(player2, p2Name);

        gui.getBattleLog().addMessage("=== REALM COLLISION STARTED ===");
        gui.getBattleLog().addMessage(p1Name + " vs " + p2Name);
        gui.getBattleLog().addMessage("Game Ready - Select an action!");

        gameRunning = true;
        updateDisplay();
    }

    private realmcollision.classes.Character createCharacter(String code) {
        switch (code) {
            case "AM":
                return new ArcMage(
                        CharacterStats.ArcMage.BASE_HEALTH,
                        CharacterStats.ArcMage.BASE_ENERGY,
                        CharacterStats.ArcMage.BASE_ACTION_POINTS
                );
            case "DG":
                return new DemiGod(
                        CharacterStats.DemiGod.BASE_HEALTH,
                        CharacterStats.DemiGod.BASE_ENERGY,
                        CharacterStats.DemiGod.BASE_ACTION_POINTS
                );
            case "TS":
                return new Trickster(
                        CharacterStats.Trickster.BASE_HEALTH,
                        CharacterStats.Trickster.BASE_ENERGY,
                        CharacterStats.Trickster.BASE_ACTION_POINTS
                );
            case "DE":
                return new Deadeye(
                        CharacterStats.Deadeye.BASE_HEALTH,
                        CharacterStats.Deadeye.BASE_ENERGY,
                        CharacterStats.Deadeye.BASE_ACTION_POINTS
                );
            case "TR":
                return new Terminator(
                        CharacterStats.Terminator.BASE_HEALTH,
                        CharacterStats.Terminator.BASE_ENERGY,
                        CharacterStats.Terminator.BASE_ACTION_POINTS
                );
            case "KN":
                return new Knight(
                        CharacterStats.Knight.BASE_HEALTH,
                        CharacterStats.Knight.BASE_ENERGY,
                        CharacterStats.Knight.BASE_ACTION_POINTS
                );
            case "VK":
                return new Valkyrie(
                        CharacterStats.Valkyrie.BASE_HEALTH,
                        CharacterStats.Valkyrie.BASE_ENERGY,
                        CharacterStats.Valkyrie.BASE_ACTION_POINTS
                );
            case "WR":
                return new Wanderer(
                        CharacterStats.Wanderer.BASE_HEALTH,
                        CharacterStats.Wanderer.BASE_ENERGY,
                        CharacterStats.Wanderer.BASE_ACTION_POINTS
                );
            default: return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameRunning) return;

        String action = e.getActionCommand();
        handleAction(action);
    }

    public void handleAction(String action) {
        if (!gameRunning) return;

        realmcollision.classes.Character currentPlayer = (currentTurn == 1) ? player1 : player2;
        realmcollision.classes.Character opponent = (currentTurn == 1) ? player2 : player1;

        if (currentPlayer.isDefeated() || opponent.isDefeated()) {
            endGame();
            return;
        }

        if (currentPlayer.stunned) {
            gui.getBattleLog().addMessage(currentPlayer.getClass().getSimpleName() + " is STUNNED and skips turn!");
            currentPlayer.stunned = false;
            advanceTurn();
            return;
        }

        int damage = 0;
        boolean isSkill = false;
        boolean isUltimate = false;

        switch (action) {
            case "ATTACK":
                damage = currentPlayer.attack();
                gui.getBattleLog().addMessage(currentPlayer.getClass().getSimpleName() + " uses Basic Attack!");
                break;

            case "DEFEND":
                currentPlayer.defense();
                gui.getBattleLog().addMessage(currentPlayer.getClass().getSimpleName() + " defends!");
                break;

            case "SKILL":
                damage = currentPlayer.castSkill();
                isSkill = true;
                gui.getBattleLog().addMessage(currentPlayer.getClass().getSimpleName() + " uses Skill!");
                break;

            case "ULTIMATE":
                damage = currentPlayer.castUltimate(opponent);
                isSkill = true;
                isUltimate = true;
                gui.getBattleLog().addMessage(currentPlayer.getClass().getSimpleName() + " uses ULTIMATE!");
                break;

            case "SECOND_ULTIMATE":
                if (currentPlayer instanceof Wanderer) {
                    damage = ((Wanderer) currentPlayer).castSecondUltimate(opponent);
                    isSkill = true;
                    isUltimate = true;
                    gui.getBattleLog().addMessage("Wanderer uses SECOND ULTIMATE!");
                } else {
                    gui.getBattleLog().addMessage("Only Wanderer can use Second Ultimate!");
                }
                break;

            default:
                gui.getBattleLog().addMessage("Invalid action!");
                return;
        }

        CombatUtils.handlePostActionRegeneration(currentPlayer);

        if (damage > 0) {
            Object result;

            if (opponent instanceof Wanderer) {
                Wanderer wanderer = (Wanderer) opponent;
                result = wanderer.takeDamageWithClones(damage, currentPlayer, isSkill, isUltimate);
            } else {
                result = opponent.takeDamage(damage, currentPlayer, isSkill, isUltimate);
            }

            if (result instanceof Object[] && ((Object[]) result)[0].equals("parry")) {
                int parryDamage = (Integer) ((Object[]) result)[1];
                currentPlayer.takeDamage(parryDamage, opponent, false, false);
                gui.getBattleLog().addMessage("PARRY! Damage reflected!");
            }
        }

        if (currentPlayer instanceof Trickster) {
            Trickster trickster = (Trickster) currentPlayer;
            if (trickster.hasExtra()) {
                gui.getBattleLog().addMessage("=== TRICKSTER EXTRA TURN ACTIVATED ===");

                gui.forceImmediateUpdate();

                gui.getBattleLog().addMessage("Trickster gets an EXTRA TURN!");
                trickster.useExtra();

                gui.forceImmediateUpdate();
                return;
            }
        }

        CombatUtils.applyDOT(player1, player2);

        advanceTurn();
        checkGameEnd();
    }

    private void advanceTurn() {
        currentTurn++;
        if (currentTurn > 2) {
            currentTurn = 1;
            currentCycle++;
            gui.getBattleLog().addMessage("=== END OF CYCLE " + currentCycle + " ===");

            if (currentCycle > MAX_CYCLES) {
                endGame();
                return;
            }
        }

        updateDisplay();
    }

    private void checkGameEnd() {
        if (player1.isDefeated() || player2.isDefeated()) {
            endGame();
        }
    }

    private void endGame() {
        gameRunning = false;
        String message;

        if (player1.isDefeated() && player2.isDefeated()) {
            message = "IT'S A DRAW!";
        } else if (player1.isDefeated()) {
            message = characterNames.values().stream()
                    .filter(name -> name.contains(player2.getClass().getSimpleName()))
                    .findFirst()
                    .orElse(player2.getClass().getSimpleName()) + " WINS!";
        } else if (player2.isDefeated()) {
            message = characterNames.values().stream()
                    .filter(name -> name.contains(player1.getClass().getSimpleName()))
                    .findFirst()
                    .orElse(player1.getClass().getSimpleName()) + " WINS!";
        } else {
            // Time's up - compare health
            if (player1.getHealth() > player2.getHealth()) {
                message = characterNames.values().stream()
                        .filter(name -> name.contains(player1.getClass().getSimpleName()))
                        .findFirst()
                        .orElse(player1.getClass().getSimpleName()) + " WINS!";
            } else if (player2.getHealth() > player1.getHealth()) {
                message = characterNames.values().stream()
                        .filter(name -> name.contains(player2.getClass().getSimpleName()))
                        .findFirst()
                        .orElse(player2.getClass().getSimpleName()) + " WINS!";
            } else {
                message = "IT'S A DRAW!";
            }
        }

        gui.getBattleLog().addMessage("=== GAME OVER ===");
        gui.getBattleLog().addMessage(message);

        gui.getActionPanel().setEnabled(false);

        // Show game over dialog with option to play again
        int choice = JOptionPane.showOptionDialog(gui,
                message + "\nWould you like to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Play Again", "Main Menu"},
                "Play Again");

        if (choice == 0) {
            gui.dispose();
            Main.showCharacterSelection();
        } else {
            gui.dispose();
            Main.showMainMenu();
        }
    }

    private void updateDisplay() {
        gui.updateGameState();
        gui.getStatusPanel().setTurnInfo(currentTurn, currentCycle);

        realmcollision.classes.Character currentPlayer = (currentTurn == 1) ? player1 : player2;
        gui.getActionPanel().showSecondUltimate(currentPlayer instanceof Wanderer);

        String player1Status = player1.getStatus();
        String player2Status = player2.getStatus();

        if (player1Status.contains("Burning")) {
            gui.getStatusPanel().setSpecialStatus(player1Status);
        } else if (player2Status.contains("Burning")) {
            gui.getStatusPanel().setSpecialStatus(player2Status);
        } else {
            gui.getStatusPanel().setSpecialStatus("None");
        }

        if (player1.isDefeated() || player2.isDefeated()) {
            gui.getStatusPanel().setGameStatus("Game Over");
        } else {
            gui.getStatusPanel().setGameStatus("Turn: " +
                    (currentTurn == 1 ?
                            characterNames.values().stream()
                                    .filter(name -> name.contains(player1.getClass().getSimpleName()))
                                    .findFirst()
                                    .orElse("Player 1") :
                            characterNames.values().stream()
                                    .filter(name -> name.contains(player2.getClass().getSimpleName()))
                                    .findFirst()
                                    .orElse("Player 2")));
        }
    }

    public realmcollision.classes.Character getPlayer1() { return player1; }
    public realmcollision.classes.Character getPlayer2() { return player2; }
    public boolean isGameRunning() { return gameRunning; }
}