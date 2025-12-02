package realmcollision.panels;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class BattleLog extends JTextPane {
    private StyledDocument doc;
    private SimpleAttributeSet normal, bold, italic, damage, heal;

    public BattleLog() {
        initializeStyles();
        setEditable(false);
        setBackground(new Color(20, 20, 20));
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void initializeStyles() {
        doc = getStyledDocument();

        normal = new SimpleAttributeSet();
        StyleConstants.setFontFamily(normal, "Monospaced");
        StyleConstants.setFontSize(normal, 12);
        StyleConstants.setForeground(normal, Color.WHITE);

        bold = new SimpleAttributeSet(normal);
        StyleConstants.setBold(bold, true);
        StyleConstants.setForeground(bold, new Color(100, 180, 255)); // Light blue

        italic = new SimpleAttributeSet(normal);
        StyleConstants.setItalic(italic, true);
        StyleConstants.setForeground(italic, new Color(180, 180, 180)); // Light gray

        damage = new SimpleAttributeSet(normal);
        StyleConstants.setForeground(damage, new Color(255, 100, 100)); // Red

        heal = new SimpleAttributeSet(normal);
        StyleConstants.setForeground(heal, new Color(100, 255, 100)); // Green

    }

    public void addMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            try {
                AttributeSet style = normal;
                if (message.contains("ULTIMATE") || message.contains("WINS") || message.contains("STARTED")) {
                    style = bold;
                } else if (message.contains("damage") || message.contains("took") || message.contains("STUNNED")) {
                    style = damage;
                } else if (message.contains("heal") || message.contains("recovered") || message.contains("healed")) {
                    style = heal;
                } else if (message.contains("|||")) {
                    style = italic;
                }

                doc.insertString(doc.getLength(), message + "\n", style);
                setCaretPosition(doc.getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    public void clear() {
        setText("");
    }
}