package realmcollision.panels;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel turnLabel, cycleLabel, gameStatusLabel;
    private JLabel specialStatusLabel;

    public StatusPanel() {
        initializePanel();
    }

    private void initializePanel() {
        // Dark theme colors
        Color darkBackground = new Color(45, 45, 45);
        Color textColor = Color.WHITE;
        Color borderColor = new Color(80, 80, 80);

        setLayout(new GridLayout(4, 1, 5, 5));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(borderColor), "Game Status"));
        setPreferredSize(new Dimension(200, 120));
        setBackground(darkBackground);

        turnLabel = new JLabel("Turn: 1");
        cycleLabel = new JLabel("Cycle: 1");
        gameStatusLabel = new JLabel("Status: Running");
        specialStatusLabel = new JLabel("Special: None");

        turnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cycleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gameStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        specialStatusLabel.setFont(new Font("Arial", Font.BOLD, 12));

        // Set dark theme
        turnLabel.setForeground(textColor);
        cycleLabel.setForeground(textColor);
        gameStatusLabel.setForeground(textColor);
        specialStatusLabel.setForeground(new Color(255, 200, 100)); // Gold for special status

        turnLabel.setBackground(darkBackground);
        cycleLabel.setBackground(darkBackground);
        gameStatusLabel.setBackground(darkBackground);
        specialStatusLabel.setBackground(darkBackground);

        turnLabel.setOpaque(true);
        cycleLabel.setOpaque(true);
        gameStatusLabel.setOpaque(true);
        specialStatusLabel.setOpaque(true);

        add(turnLabel);
        add(cycleLabel);
        add(gameStatusLabel);
        add(specialStatusLabel);
    }

    public void setTurnInfo(int turn, int cycle) {
        turnLabel.setText("Turn: " + turn);
        cycleLabel.setText("Cycle: " + cycle);
    }

    public void setGameStatus(String status) {
        gameStatusLabel.setText("Status: " + status);
    }

    public void setSpecialStatus(String status) {
        specialStatusLabel.setText("Special: " + status);

        // Flash effect for special status
        if (status.contains("Burning")) {
            Timer flashTimer = new Timer(500, e -> {
                Color currentColor = specialStatusLabel.getForeground();
                if (currentColor.equals(new Color(255, 200, 100))) {
                    specialStatusLabel.setForeground(new Color(255, 100, 0)); // Orange-red for burn
                } else {
                    specialStatusLabel.setForeground(new Color(255, 200, 100));
                }
            });
            flashTimer.setRepeats(true);
            flashTimer.start();

            // Stop flashing after 3 seconds
            Timer stopTimer = new Timer(3000, e -> {
                flashTimer.stop();
                specialStatusLabel.setForeground(new Color(255, 200, 100));
            });
            stopTimer.setRepeats(false);
            stopTimer.start();
        } else if (!status.equals("None")) {
            // Regular flashing for other special statuses
            Timer flashTimer = new Timer(500, e -> {
                Color currentColor = specialStatusLabel.getForeground();
                if (currentColor.equals(new Color(255, 200, 100))) {
                    specialStatusLabel.setForeground(new Color(255, 100, 100));
                } else {
                    specialStatusLabel.setForeground(new Color(255, 200, 100));
                }
            });
            flashTimer.setRepeats(true);
            flashTimer.start();

            Timer stopTimer = new Timer(3000, e -> {
                flashTimer.stop();
                specialStatusLabel.setForeground(new Color(255, 200, 100));
            });
            stopTimer.setRepeats(false);
            stopTimer.start();
        }
    }

    public void updateDisplay() {
        // Can add more dynamic status updates here
    }
}