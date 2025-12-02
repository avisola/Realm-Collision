package realmcollision.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {
    private JButton attackBtn, defendBtn, skillBtn, ultimateBtn, secondUltimateBtn;
    private ActionListener actionListener;
    private JLabel actionInfoLabel;

    public ActionPanel() {
        initializePanel();
    }

    private void initializePanel() {
        Color darkBackground = new Color(45, 45, 45);
        Color borderColor = new Color(80, 80, 80);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(borderColor), "Actions"));
        setBackground(darkBackground);

        actionInfoLabel = new JLabel("Select an action to continue...", SwingConstants.CENTER);
        actionInfoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        actionInfoLabel.setForeground(new Color(200, 200, 100));
        actionInfoLabel.setBackground(darkBackground);
        actionInfoLabel.setOpaque(true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(darkBackground);

        attackBtn = createDarkButton("Attack", "ATTACK", new Color(80, 140, 200));
        defendBtn = createDarkButton("Defend", "DEFEND", new Color(180, 140, 80));
        skillBtn = createDarkButton("Skill", "SKILL", new Color(160, 120, 220));
        ultimateBtn = createDarkButton("Ultimate", "ULTIMATE", new Color(200, 60, 60));
        secondUltimateBtn = createDarkButton("2nd Ultimate", "SECOND_ULTIMATE", new Color(160, 40, 40));

        secondUltimateBtn.setVisible(false);

        buttonPanel.add(attackBtn);
        buttonPanel.add(defendBtn);
        buttonPanel.add(skillBtn);
        buttonPanel.add(ultimateBtn);
        buttonPanel.add(secondUltimateBtn);

        add(actionInfoLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createDarkButton(String text, String action, Color color) {
        JButton button = new JButton(text);
        button.setActionCommand(action);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 50));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(brighter(color));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        button.addActionListener(e -> {
            if (actionListener != null) {
                actionListener.actionPerformed(e);
            }
        });
        return button;
    }

    private Color brighter(Color color) {
        int r = Math.min(255, color.getRed() + 30);
        int g = Math.min(255, color.getGreen() + 30);
        int b = Math.min(255, color.getBlue() + 30);
        return new Color(r, g, b);
    }

    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    public void setEnabled(boolean enabled) {
        attackBtn.setEnabled(enabled);
        defendBtn.setEnabled(enabled);
        skillBtn.setEnabled(enabled);
        ultimateBtn.setEnabled(enabled);
        secondUltimateBtn.setEnabled(enabled);
    }

    public void showSecondUltimate(boolean show) {
        secondUltimateBtn.setVisible(show);
    }

}