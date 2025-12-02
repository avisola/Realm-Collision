package realmcollision.panels;

import realmcollision.classes.Character;
import realmcollision.classes.wanderer.Wanderer;
import realmcollision.utils.CharacterStats;
import javax.swing.*;
import java.awt.*;

public class CharacterPanel extends JPanel {
    private JLabel nameLabel, healthLabel, energyLabel, apLabel, statusLabel;
    private CustomHealthBar healthBar;
    private CustomEnergyBar energyBar;
    private Character character;
    private String characterName;

    public CharacterPanel() {
        initializePanel();
    }

    private void initializePanel() {
        Color darkBackground = new Color(45, 45, 45);
        Color textColor = Color.WHITE;
        Color borderColor = new Color(80, 80, 80);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(borderColor), "Character"));
        setPreferredSize(new Dimension(400, 250));
        setBackground(darkBackground);

        nameLabel = new JLabel("Select Character", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(textColor);
        nameLabel.setBackground(darkBackground);
        nameLabel.setOpaque(true);

        JPanel statsPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        statsPanel.setBackground(darkBackground);

        healthLabel = new JLabel("HP: 0/0");
        energyLabel = new JLabel("Energy: 0");
        apLabel = new JLabel("Action Points: 0");
        statusLabel = new JLabel("Status: Normal");

        healthLabel.setForeground(textColor);
        energyLabel.setForeground(textColor);
        apLabel.setForeground(textColor);
        statusLabel.setForeground(textColor);

        healthBar = new CustomHealthBar();
        energyBar = new CustomEnergyBar();

        statsPanel.add(new JLabel("Health:"));
        statsPanel.add(healthBar);
        statsPanel.add(new JLabel("Energy:"));
        statsPanel.add(energyBar);
        statsPanel.add(healthLabel);
        statsPanel.add(energyLabel);
        statsPanel.add(apLabel);
        statsPanel.add(statusLabel);

        for (Component comp : statsPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setBackground(darkBackground);
                ((JLabel) comp).setOpaque(true);
                comp.setForeground(textColor);
            }
        }

        add(nameLabel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
    }

    public void setCharacter(Character character, String name) {
        this.character = character;
        this.characterName = name;
        updateDisplay();
    }

    public void updateDisplay() {
        if (character == null) return;

        String className = character.getClass().getSimpleName();
        int maxHealth = CharacterStats.getMaxHealth(className);
        int maxEnergy = CharacterStats.getMaxEnergy(className);

        nameLabel.setText(characterName);
        healthLabel.setText(String.format("HP: %,d/%,d", character.getHealth(), maxHealth));
        energyLabel.setText(String.format("Energy: %,d/%,d", character.getEnergy(), maxEnergy));
        apLabel.setText(String.format("AP: %d", character.getActionsPoints()));

        if (character instanceof Wanderer) {
            Wanderer wanderer = (Wanderer) character;
            statusLabel.setText("Clones: " + wanderer.getCloneStatus());
        } else {
            statusLabel.setText("Status: " + character.getStatus());
        }

        healthBar.updateValue(character.getHealth(), maxHealth);
        energyBar.updateValue(character.getEnergy(), maxEnergy);
    }

    private class CustomHealthBar extends JPanel {
        private int currentValue;
        private int maxValue;
        private final int BAR_HEIGHT = 20;

        public CustomHealthBar() {
            setPreferredSize(new Dimension(200, BAR_HEIGHT));
            setBackground(new Color(60, 60, 60));
            setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        }

        public void updateValue(int current, int max) {
            this.currentValue = current;
            this.maxValue = max;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();

            g.setColor(new Color(40, 40, 40));
            g.fillRect(0, 0, width, height);

            double percentage = Math.max(0, (double) currentValue / maxValue);
            int fillWidth = (int) (width * percentage);

            Color fillColor;
            if (percentage > 0.7) {
                fillColor = new Color(0, 200, 0);
            } else if (percentage > 0.4) {
                fillColor = new Color(255, 165, 0);
            } else if (percentage > 0.2) {
                fillColor = new Color(255, 100, 0);
            } else {
                fillColor = new Color(220, 0, 0);
            }

            g.setColor(fillColor);
            g.fillRect(0, 0, fillWidth, height);

            g.setColor(new Color(100, 100, 100));
            g.drawRect(0, 0, width - 1, height - 1);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 10));
            String text = String.format("%d/%d", currentValue, maxValue);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            g.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 2);
        }
    }

    private class CustomEnergyBar extends JPanel {
        private int currentValue;
        private int maxValue;
        private final int BAR_HEIGHT = 20;
        private boolean showBurnEffect = false;
        private long burnEffectStartTime = 0;
        private static final long BURN_EFFECT_DURATION = 1000;

        public CustomEnergyBar() {
            setPreferredSize(new Dimension(200, BAR_HEIGHT));
            setBackground(new Color(60, 60, 60));
            setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        }

        public void updateValue(int current, int max) {
            if (current < this.currentValue && (this.currentValue - current) > 500) {
                showBurnEffect = true;
                burnEffectStartTime = System.currentTimeMillis();
            }
            this.currentValue = current;
            this.maxValue = max;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();

            g.setColor(new Color(40, 40, 40));
            g.fillRect(0, 0, width, height);

            double percentage = Math.max(0, (double) currentValue / maxValue);
            int fillWidth = (int) (width * percentage);

            if (showBurnEffect) {
                long currentTime = System.currentTimeMillis();
                long elapsed = currentTime - burnEffectStartTime;

                if (elapsed < BURN_EFFECT_DURATION) {
                    float pulse = (float) (Math.sin(elapsed * 0.02) * 0.3 + 0.7);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, pulse));
                    Color burnColor = new Color(1.0f, 0.3f, 0.1f);
                    g2d.setColor(burnColor);
                    g2d.fillRect(0, 0, width, height);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, pulse * 0.5f));
                    g2d.setColor(new Color(1.0f, 0.8f, 0.2f));
                    for (int i = 0; i < 5; i++) {
                        int x = (int) (Math.random() * width);
                        int y = (int) (Math.random() * height);
                        int crackleSize = (int) (Math.random() * 3) + 1;
                        g2d.fillRect(x, y, crackleSize, crackleSize);
                    }
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                } else {
                    showBurnEffect = false;
                }
            }

            if (fillWidth > 0) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(70, 130, 180),
                        fillWidth, 0, new Color(100, 160, 255)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, fillWidth, height);
            }

            g.setColor(new Color(100, 100, 100));
            g.drawRect(0, 0, width - 1, height - 1);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 10));
            String text = String.format("%d/%d", currentValue, maxValue);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            g.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 2);

            if (percentage > 0.8) {
                Graphics2D g2d = (Graphics2D) g;
                float alpha = 0.5f + (float) (Math.sin(System.currentTimeMillis() * 0.01) * 0.3f);
                g2d.setColor(new Color(255, 255, 255, (int)(alpha * 255)));
                int pulseWidth = (int) (Math.sin(System.currentTimeMillis() * 0.01) * 8 + 15);
                g2d.fillRect(fillWidth - pulseWidth, 2, pulseWidth, height - 4);
            }
        }
    }
}