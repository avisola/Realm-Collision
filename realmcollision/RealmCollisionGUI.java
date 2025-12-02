package realmcollision;

import realmcollision.panels.*;
import javax.swing.*;
import java.awt.*;

public class RealmCollisionGUI extends JFrame {
    private GameController gameController;
    private CharacterPanel player1Panel, player2Panel;
    private BattleLog battleLog;
    private ActionPanel actionPanel;
    private StatusPanel statusPanel;

    // Store character codes
    private String p1Code, p2Code;

    public RealmCollisionGUI(String p1Code, String p2Code) {
        this.p1Code = p1Code;
        this.p2Code = p2Code;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Realm Collision - " + p1Code + " vs " + p2Code);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Dark theme colors
        Color darkBackground = new Color(30, 30, 30);
        Color darkPanel = new Color(45, 45, 45);
        Color textColor = Color.WHITE;
        Color accentColor = new Color(0, 150, 255);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(darkBackground);

        // Create components with dark theme
        player1Panel = new CharacterPanel();
        player2Panel = new CharacterPanel();
        battleLog = new BattleLog();
        actionPanel = new ActionPanel();
        statusPanel = new StatusPanel();

        // Apply dark theme to components
        applyDarkTheme(player1Panel, darkPanel, textColor);
        applyDarkTheme(player2Panel, darkPanel, textColor);
        applyDarkTheme(actionPanel, darkPanel, textColor);
        applyDarkTheme(statusPanel, darkPanel, textColor);

        // Set battle log dark theme
        battleLog.setBackground(new Color(20, 20, 20));
        battleLog.setForeground(Color.WHITE);
        battleLog.setCaretColor(Color.WHITE);

        // Character panels at top
        JPanel characterPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        characterPanel.setBackground(darkBackground);
        characterPanel.add(player1Panel);
        characterPanel.add(player2Panel);

        // Battle log in center
        JScrollPane scrollPane = new JScrollPane(battleLog);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.getViewport().setBackground(new Color(20, 20, 20));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));

        // Action and status at bottom
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(darkBackground);
        bottomPanel.add(actionPanel, BorderLayout.CENTER);
        bottomPanel.add(statusPanel, BorderLayout.EAST);

        // Add everything to main panel
        mainPanel.add(characterPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        getContentPane().setBackground(darkBackground);

        // Initialize game with selected characters
        gameController = new GameController(this, p1Code, p2Code);
        actionPanel.setActionListener(gameController);
    }

    private void applyDarkTheme(JPanel panel, Color backgroundColor, Color textColor) {
        panel.setBackground(backgroundColor);
        panel.setForeground(textColor);

        // Recursively set dark theme for all components
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                applyDarkTheme((JPanel) component, backgroundColor, textColor);
            } else if (component instanceof JLabel) {
                component.setForeground(textColor);
                component.setBackground(backgroundColor);
                ((JLabel) component).setOpaque(true);
            } else if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setBackground(new Color(70, 130, 180));
                button.setForeground(textColor);
                button.setOpaque(true);
                button.setBorderPainted(false);
            }
        }
    }

    // === GETTER METHODS ===
    public CharacterPanel getPlayer1Panel() {
        return player1Panel;
    }

    public CharacterPanel getPlayer2Panel() {
        return player2Panel;
    }

    public BattleLog getBattleLog() {
        return battleLog;
    }

    public ActionPanel getActionPanel() {
        return actionPanel;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void updateGameState() {
        player1Panel.updateDisplay();
        player2Panel.updateDisplay();
        statusPanel.updateDisplay();

        // Force immediate repaint
        SwingUtilities.invokeLater(() -> {
            player1Panel.repaint();
            player2Panel.repaint();
            statusPanel.repaint();
            actionPanel.repaint();
        });
    }

    public void forceImmediateUpdate() {
        // Force immediate repaint of all components
        player1Panel.updateDisplay();
        player2Panel.updateDisplay();
        player1Panel.repaint();
        player2Panel.repaint();
        statusPanel.repaint();

        // Also update the action panel if needed
        if (gameController != null && gameController.isGameRunning()) {
            realmcollision.classes.Character currentPlayer = gameController.getPlayer1();
            actionPanel.showSecondUltimate(currentPlayer instanceof realmcollision.classes.wanderer.Wanderer);
        }
    }
}