package realmcollision;

import javax.swing.*;
import java.awt.*;

public class Main {
    static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            showMainMenu();
        });
    }

    public static void showMainMenu() {
        JFrame menuFrame = new JFrame("Realm Collision");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(600, 500);
        menuFrame.setLocationRelativeTo(null);

        Color lightBackground = new Color(240, 240, 240);
        new Color(220, 220, 220);
        Color accentColor = new Color(0, 150, 255);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(lightBackground);

        JLabel titleLabel = new JLabel("REALM COLLISION", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(lightBackground);
        titleLabel.setOpaque(true);

        JButton playButton = createMenuButton("PLAY GAME", accentColor);
        JButton acquaintancesButton = createMenuButton("ACQUAINTANCES", accentColor);
        JButton loreButton = createMenuButton("CHARACTER LORE", accentColor);
        JButton exitButton = createMenuButton("EXIT", Color.RED);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        buttonPanel.setBackground(lightBackground);
        buttonPanel.add(playButton);
        buttonPanel.add(acquaintancesButton);
        buttonPanel.add(loreButton);
        buttonPanel.add(exitButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        playButton.addActionListener(e -> {
            menuFrame.dispose();
            showCharacterSelection();
        });

        acquaintancesButton.addActionListener(e -> {
            menuFrame.dispose();
            showAcquaintancesMenu();
        });

        loreButton.addActionListener(e -> {
            menuFrame.dispose();
            showLoreMenu();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        menuFrame.add(mainPanel);
        menuFrame.getContentPane().setBackground(lightBackground);
        menuFrame.setVisible(true);
    }

    public static void showCharacterSelection() {
        JFrame selectionFrame = new JFrame("Select Characters");
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectionFrame.setSize(800, 600);
        selectionFrame.setLocationRelativeTo(null);

        // Light theme colors
        Color lightBackground = new Color(240, 240, 240);
        Color textColor = Color.BLACK;

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(lightBackground);

        // Title
        JLabel titleLabel = new JLabel("CHOOSE YOUR ACQUAINTANT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(lightBackground);
        titleLabel.setOpaque(true);

        // Selection panels
        JPanel selectionPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        selectionPanel.setBackground(lightBackground);

        // Player 1 selection
        JPanel p1Panel = new JPanel(new FlowLayout());
        p1Panel.setBackground(new Color(200, 200, 220));
        p1Panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150)), "Player 1"));
        JLabel p1Label = new JLabel("Select: ");
        p1Label.setForeground(Color.BLACK);
        p1Panel.add(p1Label);
        JComboBox<String> p1ComboBox = new JComboBox<>(new String[]{
                "ArcMage (AM)", "DemiGod (DG)", "Trickster (TS)", "Deadeye (DE)",
                "Terminator (TR)", "Knight (KN)", "Valkyrie (VK)", "Wanderer (WR)"
        });
        p1ComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        p1ComboBox.setBackground(new Color(255, 255, 255));
        p1ComboBox.setForeground(Color.BLACK);
        p1Panel.add(p1ComboBox);

        // Player 2 selection
        JPanel p2Panel = new JPanel(new FlowLayout());
        p2Panel.setBackground(new Color(220, 200, 200));
        p2Panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 100, 100)), "Player 2"));
        JLabel p2Label = new JLabel("Select: ");
        p2Label.setForeground(Color.BLACK);
        p2Panel.add(p2Label);
        JComboBox<String> p2ComboBox = new JComboBox<>(new String[]{
                "ArcMage (AM)", "DemiGod (DG)", "Trickster (TS)", "Deadeye (DE)",
                "Terminator (TR)", "Knight (KN)", "Valkyrie (VK)", "Wanderer (WR)"
        });
        p2ComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        p2ComboBox.setBackground(new Color(255, 255, 255));
        p2ComboBox.setForeground(Color.BLACK);
        p2Panel.add(p2ComboBox);

        selectionPanel.add(p1Panel);
        selectionPanel.add(p2Panel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(lightBackground);

        JButton startButton = new JButton("START BATTLE");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.BLACK);
        startButton.setPreferredSize(new Dimension(200, 50));

        JButton backButton = new JButton("BACK TO MENU");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.BLACK);

        startButton.addActionListener(e -> {
            String p1Choice = (String) p1ComboBox.getSelectedItem();
            String p2Choice = (String) p2ComboBox.getSelectedItem();

            if (p1Choice.equals(p2Choice)) {
                JOptionPane.showMessageDialog(selectionFrame, "Cannot select the same Acquaintant for both players!");
                return;
            }

            selectionFrame.dispose();
            startGame(p1Choice, p2Choice);
        });

        backButton.addActionListener(e -> {
            selectionFrame.dispose();
            showMainMenu();
        });

        buttonPanel.add(startButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(selectionPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        selectionFrame.add(mainPanel);
        selectionFrame.getContentPane().setBackground(lightBackground);
        selectionFrame.setVisible(true);
    }

    private static void startGame(String p1Choice, String p2Choice) {
        String p1Code = p1Choice.substring(p1Choice.indexOf("(") + 1, p1Choice.indexOf(")"));
        String p2Code = p2Choice.substring(p2Choice.indexOf("(") + 1, p2Choice.indexOf(")"));

        RealmCollisionGUI gameGUI = new RealmCollisionGUI(p1Code, p2Code);
        gameGUI.setVisible(true);
    }

    public static void showAcquaintancesMenu() {
        JFrame descFrame = new JFrame("ACQUAINTANT DESCRIPTION");
        descFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        descFrame.setSize(800, 700);
        descFrame.setLocationRelativeTo(null);

        Color lightBackground = new Color(240, 240, 240);
        descFrame.getContentPane().setBackground(lightBackground);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(lightBackground);
        tabbedPane.setForeground(Color.BLACK);

        addCharacterTab(tabbedPane, "ArcMage (Sienna)", getArcMageDescription());
        addCharacterTab(tabbedPane, "DemiGod (Annarria)", getDemiGodDescription());
        addCharacterTab(tabbedPane, "Trickster (Amethyst)", getTricksterDescription());
        addCharacterTab(tabbedPane, "Deadeye (Cipher)", getDeadeyeDescription());
        addCharacterTab(tabbedPane, "Terminator (Vega)", getTerminatorDescription());
        addCharacterTab(tabbedPane, "Knight (Yhwach)", getKnightDescription());
        addCharacterTab(tabbedPane, "Valkyrie (Chaela)", getValkyrieDescription());
        addCharacterTab(tabbedPane, "Wanderer (Shinmu)", getWandererDescription());

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> {
            descFrame.dispose();
            showMainMenu();
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(lightBackground);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        descFrame.add(mainPanel);
        descFrame.setVisible(true);
    }

    public static void showLoreMenu() {
        JFrame loreFrame = new JFrame("Character Lore");
        loreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loreFrame.setSize(800, 700);
        loreFrame.setLocationRelativeTo(null);

        // Light theme
        Color lightBackground = new Color(240, 240, 240);
        loreFrame.getContentPane().setBackground(lightBackground);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(lightBackground);
        tabbedPane.setForeground(Color.BLACK);

        addCharacterTab(tabbedPane, "The beginning of Era Nova", getIntroLore());
        addCharacterTab(tabbedPane, "ArcMage Lore", getArcMageLore());
        addCharacterTab(tabbedPane, "DemiGod Lore", getDemiGodLore());
        addCharacterTab(tabbedPane, "Trickster Lore", getTricksterLore());
        addCharacterTab(tabbedPane, "Deadeye Lore", getDeadeyeLore());
        addCharacterTab(tabbedPane, "Terminator Lore", getTerminatorLore());
        addCharacterTab(tabbedPane, "Knight Lore", getKnightLore());
        addCharacterTab(tabbedPane, "Valkyrie Lore", getValkyrieLore());
        addCharacterTab(tabbedPane, "Wanderer Lore", getWandererLore());

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> {
            loreFrame.dispose();
            showMainMenu();
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(lightBackground);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        loreFrame.add(mainPanel);
        loreFrame.setVisible(true);
    }

    private static void addCharacterTab(JTabbedPane tabbedPane, String title, String content) {
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setBackground(new Color(255, 255, 255));
        textArea.setForeground(Color.BLACK);
        textArea.setCaretColor(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        tabbedPane.addTab(title, scrollPane);
    }

    private static JButton createMenuButton(String text, Color buttonColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(buttonColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 60));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        return button;
    }

    // Character descriptions (unchanged)
    private static String getArcMageDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                  ARCMAGE SIENNA\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 80,000\n" +
                "• ATK: 1,500\n" +
                "• ULTIMATE COST: 1,000 Energy\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: Deals 1,000 damage\n" +
                "• Skill - Flames of Hell: Deals 6,000 damage\n" +
                "• Ultimate - Flames of Annihilation: Deals 20,000 damage\n\n" +
                "UNIQUE TRAITS:\n" +
                "• High burst damage";
    }

    private static String getDemiGodDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                  DEMIGOD ANNARRIA\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 100,000\n" +
                "• ATK: 900\n" +
                "• ULTIMATE COST: 1,000 Energy\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: Deals 900 damage\n" +
                "• Skill - Blessing of Light: Activates special effects for 3 cycles\n" +
                "• Ultimate - Rods of Judgement: Deals 10,000 dmaage\n\n" +
                "UNIQUE TRAITS:\n" +
                "• Skill provides Follow-up Attack that deals 1,500 damage, and Energy regen boost\n" +
                "• Low energy cost, can spam ultimate as the battle goes on";

    }

    private static String getTricksterDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                 TRICKSTER AMETHYST\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 70,000\n" +
                "• ATK: 1,000\n" +
                "• ULTIMATE COST: 1,500 Energy\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: Deals 1,000\n" +
                "• Skill - Punchline: Gains attack stacks (+300 ATK each, max 999)\n" +
                "• Ultimate - Temporal Loop: Deals 5000 damage + Converts stacks to bonus damage, extra turn\n\n" +
                "UNIQUE TRAITS:\n" +
                "• Can gain up to 999 attack stacks for massive ATK\n" +
                "• Ultimate converts stacks to bonus damage (+500 per stack)\n" +
                "• Always gets extra turn after ultimate that deals 5000";
    }

    private static String getDeadeyeDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                  DEADEYE CIPHER\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 110,000\n" +
                "• ATK: 3,000\n" +
                "• ULTIMATE COST: 1,500 Energy\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: Deals 3,000 damage\n" +
                "• Skill - Perfect Shot: Deals 5,000 damage\n" +
                "• Ultimate - Bullet of Fate: Deals 10,000 damage, ignores defenses\n\n" +
                "UNIQUE TRAITS:\n" +
                "• Ultimate ignores all enemy defenses\n" +
                "• Energy efficient basic attacks";
    }

    private static String getTerminatorDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                 TERMINATOR VEGA\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 120,000\n" +
                "• ATK: 1,500\n" +
                "• ULTIMATE COST: 3000\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: Deals 1,500 damage\n" +
                "• Skill - Seize Protocol: Applies 1,000 damage over 3 turns\n" +
                "• Ultimate - Initiate Termination: Deals 15,000 damage\n\n" +
                "UNIQUE TRAITS:\n" +
                "• Damage over time effects\n" +
                "• High HP, tanky";

    }

    private static String getKnightDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                  KNIGHT YHWACH\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 100,000\n" +
                "• ATK: 1,500\n" +
                "• ULTIMATE COST: 2,000 Energy\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: Deals 1,500 damage\n" +
                "• Skill - Blade of Justice: Parries next attack and provides healing\n" +
                "• Ultimate - Heaven's Judgment: Deals 8,000 damage\n\n" +
                "UNIQUE TRAITS:\n" +
                "• Can parry and reflect damage\n" +
                "• Skill provides self-healing\n" +
                "• Balanced offense and defense";
    }

    private static String getValkyrieDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                 VALKYRIE CHAELA\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 90,000\n" +
                "• ATK: 0 (scales with Soul Guidance)\n" +
                "• ULTIMATE COST: 1,000 Energy\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: (scales with Soul Guidance)\n" +
                "• Skill - Soul Guidance: Effects scale with stacks (0-2)\n" +
                "• Ultimate - Glory of thy Resonance: Effects scale with Soul Guidance stacks\n\n" +
                "UNIQUE TRAITS:\n" +
                "• Gains Soul Guidance stacks when taking damage\n" +
                "• Attack, Skills and ultimate scale with stack count\n" +
                "• Can heal, deal damage, or stun based on stacks";
    }

    private static String getWandererDescription() {
        return "═══════════════════════════════════════════════════\n" +
                "                 WANDERER SHINMU\n" +
                "═══════════════════════════════════════════════════\n\n" +
                "BASE STATS:\n" +
                "• HP: 60,000\n" +
                "• ATK: 1,500\n" +
                "• FIRST ULTIMATE COST: 1,200 Energy\n" +
                "• SECOND ULTIMATE COST: 2,000 Energy\n\n" +
                "ABILITIES:\n" +
                "• Basic Attack: Deals 800 damage + clone attacks\n" +
                "• Skill - Quick Slash: Deals 4000 damage\n" +
                "• 1st Ultimate - Echoes of Infinity: Summons clones up to 2 times, deals 1000\n" +
                "• 2nd Ultimate - Eternal Years of Rupture: deals 5000 damage, sacrifice clones for massive damage\n\n" +
                "UNIQUE TRAITS:\n" +
                "• Can summon up to 2 clones that has 1/6 of wanderer max hp\n" +
                "• Clones mimic basic attacks and can receive any attack until it vanish\n" +
                "• Clones must die first before wanderer receive the damage" +
                "• Clones damage buff is 3000 each (6000 if 2 clones are alive \n" +
                "• Ultimate can sacrifice clones for damage multiplier";
    }

    private static String getIntroLore() {
        return "ERA NOVA: THE THRONE OF COSMIC DICHOTOMY\n\n"
                + "In the silent moment between the death of one cosmic cycle and the birth of another,\n"
                + "a new realm crystallized from the decaying dreams of a thousand dying realities.\n"
                + "This is Era Nova — a convergence plane forged from the raw essence of conflict and potential,\n"
                + "a proving ground suspended outside the flow of conventional time and space.\n\n"
                + "At its heart lies the Throne of the Ruler, a seat of absolute sovereignty that promises\n"
                + "control over the fabric of the multiverse itself. The Throne does not choose its claimant —\n"
                + "it awaits the victor of a trial by annihilation.\n\n"
                + "They were gathered, not by invitation, but by inexorable pull. From the ashes of Hell's dominion,\n"
                + "from the fractured halls of fallen heavens, from the shattered edges of time, and from the ruins\n"
                + "of possible futures — eight singular beings were severed from their respective struggles and\n"
                + "translocated to the floating continents of Era Nova. They did not come by choice.\n"
                + "They were taken.\n\n"
                + "An Ominous Presence orchestrates the gathering. It is neither the Radiant Sovereign nor\n"
                + "the Umbral Primordial, but a third entity born from the schism between them — a silent arbiter\n"
                + "of finality. It speaks not in words, but in the imposition of absolute law: only one may ascend.\n"
                + "The others will be unmade, their essence fuel for the new cosmic order. Their past glories,\n"
                + "their wars, their titles — none matter here. Era Nova cares only for strength that survives.\n"
                + "The battle for the Throne has begun. There will be no allies, no respite, and no second chances.\n"
                + "Only the final, singular victor will write the next chapter of existence.\n";
    }

    private static String getArcMageLore() {
        return "PART I: The Birth of the Phoenix Princess \n\n"
                + "Taracus — a realm where magic is everything. \n"
                + "Icia, the heart of Taracus — home of the Phoenix bloodline and rulers of the realm.\n"
                + "The capital once flourished with respect, harmony, and fairness — no matter one's magic.\n"
                + "But the birth of Princess Sienna — the Phoenix heiress — coincided with the Blight's growing influence.\n"
                + "Her awakening strained a reality already weakened, tearing open a faint fracture to the Hell Dimension.\n"
                + "Sienna was destined to inherit her mother Eiriah's throne and to lead the mages shaped by her father, Ancad.\n"
                + "Even as a child, she foresaw a grim future: the realm's fall under the Demon Lord's shadow —\n"
                + "a shadow cast not by a natural demon, but by a general of the Blight.\n"
                + "Her visions frightened the townsfolk, spreading panic throughout the capital —\n"
                + "and her parents feared their daughter had lost her sanity.\n"
                + "But Sienna was right — and her parents learned the truth too late.\n"
                + "The Blight's minions abducted her under the cover of night.\n"
                + "Ancad launched a desperate search, only for his men to encounter a Mythical Beast twisted by the Blight.\n"
                + "The fight ended in tragedy, with heavy casualties. Ancad himself suffered a mortal wound.\n"
                + "Meanwhile, Sienna tried to escape — but her small, fragile body was powerless.\n"
                + "Just as the demons moved to crush her, a blinding beam of magic annihilated them.\n"
                + "Ancad reached his daughter at last — but the price was his life.\n"
                + "His final breath ignited a storm of emotions within Sienna, releasing a surge of raw magic\n"
                + "that drove away every beast and demon lurking in the forest.\n\n"
                + "PART II: The Awakening\n\n"
                + "The death of her father forged Sienna into something beyond mortal limits.\n"
                + "For the next year, she immersed herself in relentless study, mastering every element and branch of magic.\n"
                + "Kakrimura — home of the Great Library — became her sanctuary.\n"
                + "Its barrier kept demons and beasts at bay, giving her the silence she needed to rise.\n"
                + "Queen Eiriah strengthened the realm's defenses, commanding all citizens to train harder\n"
                + "as the threat of war drew nearer.\n"
                + "Sienna soon became a force capable of erasing an entire battalion on her own.\n"
                + "Ice, earth, water, lightning, fire — every element bent to her will.\n"
                + "Her healing and defensive magic grew so potent that she could withstand nearly any battlefield.\n"
                + "Partnership was foreign to her — she believed true strength must be earned alone.\n"
                + "Demons trembled at her presence, even the high-tier ones — all except the Demon King, Yagdadoth.\n"
                + "He launched a full-scale assault on Icia, seeking to shatter Taracus once and for all.\n"
                + "Sienna barely reached her homeland in time. With one devastating spell, she obliterated his forces —\n"
                + "infuriating Yagdadoth. He seized her and dragged her into his dominion.\n"
                + "There, in the realm of Hell itself, their battle began — a war to decide who truly ruled power.\n\n"
                + "PART III: The First Arc-Mage — Mistress of Hell, Sienna\n\n"
                + "Sienna unleashed a storm of ice spikes, but they melted the moment they touched Yagdadoth.\n"
                + "He struck back with a brutal kick, sending her crashing through the blackened earth.\n"
                + "He lunged to finish her — but she had already cast her spell: a devastating fusion of all elements,\n"
                + "exploding into a blast that wounded even him.\n"
                + "Yet in Hell, Yagdadoth regenerated endlessly.\n"
                + "Their war raged for nearly a year. Sienna endured only because of the Phoenix blood in her veins —\n"
                + "a gift of near-limitless mana and unyielding life.\n"
                + "She eventually adapted to Hell, gathering every stray flame, every ember, every breath of mana around her —\n"
                + "ascending into the true pinnacle of sorcery: Arc-Magic.\n"
                + "With this final surge, she slew the Demon Lord.\n"
                + "But Hell without a ruler spiraled into chaos, threatening to rupture the boundary between Tacarus and Hell.\n"
                + "So Sienna claimed the throne, crowning herself the Mistress of Hell.\n"
                + "The Phoenix within her rejected this choice and causing Sienna to die — but she rose again, reborn in her own flames.\n"
                + "The aftermath of their battle tore through countless realms, warping reality itself.\n"
                + "Taracus felt the danger fade, and its people praised Sienna as their savior.\n"
                + "She had finally earned the title Arc-Mage — a title no one before her had ever attained.\n"
                + "At last, she rested —\n\n"
                + "until a figure of pure shadow emerged from the darkness before her. It did not attack.\n"
                + "It was the Umbral Primordial, and it had come to observe its new Warden.\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "Sienna felt the translocation before she saw it—a severing of her connection to Hell's throne.\n"
                + "In the space between breaths, the obsidian spires of her dominion dissolved into the stark, unfamiliar sky of Era Nova.\n"
                + "Here, the ambient magic felt thin, recycled, and tasted of countless other wills. The Ominous Presence's law\n"
                + "echoed in her mind: only one ascends. Her Arc-Magic, born from conquering a dimension, now hummed with a new purpose—\n"
                + "not to rule, but to survive. The other chosen, pulled from their own cosmic struggles, were not demons to be purged.\n"
                + "They were obstacles on the only path forward: the Throne. For the Mistress of Hell, every battle here\n"
                + "is a test to see if her power, forged in the fires of conquest, can burn brightly enough to consume seven other suns.\n";
    }

    private static String getDemiGodLore() {
        return "PART I: The Rise and Fall\n\n"
                + "Annarria — son of Myeda, the God of Light.\n"
                + "Born from a forbidden union between a celestial deity and a mortal woman,\n"
                + "Annarria's existence was condemned by Eryndor's divine council. To the gods,\n"
                + "he represented an anomaly that should never have been allowed to breathe.\n"
                + "His mother, a humble woman from the lowest human caste, perished during his birth,\n"
                + "abandoned by the deity who once vowed her eternity. The child survived alone,\n"
                + "bearing the stain of a union that defied the celestial order.\n"
                + "Rejected by Myeda from the moment he entered the world, Annarria grew under the shadow\n"
                + "of divine resentment. His early years were marked by loss, scorn, and fear — a constant\n"
                + "reminder of the realms' belief that he should never have existed.\n"
                + "Driven by grief and the weight of betrayal, Annarria forged a vow deep within his being:\n"
                + "to challenge the father who abandoned him and ascend to true divinity, proving that mortal\n"
                + "blood could rise high enough to confront the heavens themselves.\n\n"
                + "PART II: The Mortal Who Defied Heaven\n\n"
                + "Annarria spent his youth wandering the fringes of Eryndor's kingdoms. He survived on scraps,\n"
                + "endured scornful stares, and carried the burden of never belonging to mortals nor gods.\n"
                + "Priests called him a blight. Villagers treated him as an omen of calamity.\n"
                + "Yet the dormant spark of Myeda's divine light stirred inside him — unstable, volatile, waiting.\n"
                + "On the night of his sixteenth year, that power awakened. Wings of molten radiance erupted from\n"
                + "his back, destroying the chains of slavers who sought to profit from him. The entire encampment\n"
                + "was reduced to ash under the force of his uncontrolled awakening.\n"
                + "News of the event spread across realms. The pantheon labeled him a celestial mistake left\n"
                + "unchecked. Angels were dispatched to eliminate him. But with each divine emissary he defeated,\n"
                + "Annarria carved deeper into destiny's fabric.\n"
                + "He learned to wield the light — shaping it into blades, shields, and overwhelming surges.\n"
                + "Rumors began to circulate across Eryndor: a demi-god who could slay angels and emerge\n"
                + "untouched. A mortal-born son who rose without divine approval.\n\n"
                + "PART III: The God-Slayer in the Making\n\n"
                + "Years refined Annarria into a force feared even by the lesser gods. Temples trembled at the sound\n"
                + "of his name. Prophets in Eryndor foresaw a fracture in the celestial order — a storm born from\n"
                + "mortal defiance.\n"
                + "Guided by relentless purpose, Annarria journeyed through forbidden dominions, claiming artifacts,\n"
                + "breaking ancient seals, and mastering powers that had been locked away for millennia.\n"
                + "His legend grew with every realm he traversed and every celestial he defeated.\n"
                + "Eventually, he reached the Gate of Radiance — the threshold to Myeda's divine dominion. Angelic\n"
                + "legions assembled, prepared to halt him. Annarria faced them without fear, his wings igniting in a\n"
                + "scorching brilliance that mirrored stars on the verge of collapse.\n"
                + "As the gate quivered under the pressure of his power, the heavens themselves began to fracture.\n"
                + "Annarria stood on the brink of war against the god who abandoned him.\n\n"
                + "But at the moment before battle, a figure emerged from the luminous rift — a black silhouette shaped\n"
                + "from memories of his lost mother. It was the Umbral Primordial, and it spoke: 'They fear you because you are what they cannot control. I can give you the truth, and the power to scour their hypocrisy from the stars.'\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "The divine gate dissolved not into Myeda's realm, but into the fractured vistas of Era Nova.\n"
                + "The Ominous Presence's decree replaced the Primordial's whisper: *Only one ascends.*\n"
                + "His vendetta against the heavens was now a distant echo. Here, he stood among seven others,\n"
                + "each a sovereign power from a broken world. The light within him, born of rejection and wrath,\n"
                + "now had a singular focus: to prove, once and for all, that the outcast demi-god could surpass\n"
                + "not just one god, but every other claimant to the cosmic Throne. His fight for recognition\n"
                + "had become a fight for absolute sovereignty.\n";
    }

    private static String getTricksterLore() {
        return "PART I: The Apostle of Veylharra\n\n"
                + "Amethyst, chosen apostle of Rhua, the God of Time, was forged within the temporal currents of Veylharra.\n"
                + "From the moment she awakened to her gift, she walked the thin edge between order and collapse.\n"
                + "Her mastery over trickery, illusions, and time manipulation earned her the title of the Trickster — a being feared by mortals and gods alike.\n"
                + "For centuries, she upheld the divine order, bending time to preserve balance.\n"
                + "Yet beneath her loyalty simmered a silent understanding: the gods themselves were flawed.\n"
                + "When she uncovered the hypocrisy within the celestial hierarchy, her devotion shattered.\n\n"
                + "PART II: The Betrayal that Birthed a Monster\n\n"
                + "To prevent a temporal catastrophe that would have consumed Veylharra, Amethyst sacrificed her fellow apostles, erasing their existence from the timeline.\n"
                + "The act was absolute, merciless, and irreversible.\n"
                + "Where others saw treachery, she saw necessity — the only path to stability.\n"
                + "This single decision marked her as both savior and monster.\n"
                + "The gods condemned her ruthlessness, yet none dared confront her directly.\n"
                + "They knew that even time bent to her will.\n"
                + "From that day onward, Amethyst severed herself from celestial command.\n"
                + "Her allegiance was no longer to Rhua nor the heavens — but to the timeline itself.\n\n"
                + "PART III: The Ruthless Keeper of Eternity\n\n"
                + "When a powerful temporal aberration emerged within Veylharra, Amethyst confronted it without hesitation.\n"
                + "While others feared its strength, she only saw inefficiency — a disruption to be corrected.\n"
                + "She dismantled the threat with surgical brutality, trapping it in a loop of agony, reversing its existence, and erasing every trace of its presence from past, present, and future.\n"
                + "To Amethyst, eliminating a potential danger was not cruelty; it was maintenance.\n"
                + "Though she remains an apostle by origin, her command over temporal forces eclipses that of minor gods.\n"
                + "In the celestial hierarchy, she stands alone — the most dangerous being to ever wield time, feared not for her power, but for her willingness to use it without remorse.\n"
                + "It was only after erasing the aberration that she sensed a deeper corruption—a foreign, anti-time signal woven into the anomaly.\n"
                + "The Umbral Primordial had tested her defenses. And now, it knew the Keeper of Eternity was watching.\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "Time, for the first time, did not bend. It snapped. Amethyst was ripped from the flowing currents of Veylharra\n"
                + "and cast onto the static, convoluted shores of Era Nova—a realm where cause and effect seemed to fray at the edges.\n"
                + "The Ominous Presence's law was a paradox she could not unravel: a singular outcome mandated by a force outside time.\n"
                + "The other seven were living anomalies, their histories dense knots in the fabric of possibility. To claim the Throne\n"
                + "and restore true order, she must first untangle them—permanently. Her maintenance of eternity had reached its ultimate test.\n";
    }

    private static String getDeadeyeLore() {
        return "PART I: The Finest Gunslayer of the Neo-Frontier\n\n"
                + "Cipher was born in the year 3200, at the height of the Neo-Frontier Era — an age ruled by hyper-advanced robotics,\n"
                + "interplanetary warfare, and the collapse of human sovereignty.\n"
                + "From childhood, he was conditioned for combat, trained to become a weapon in the name of survival.\n"
                + "During the Great Robotic War, Cipher fought on the front lines against autonomous war machines built\n"
                + "for total human annihilation. In the final hours of the conflict, he was critically injured,\n"
                + "buried beneath mountains of scrap metal and the bodies of fallen comrades. His consciousness faded as the machines closed in to finish him.\n"
                + "In those final moments, a celestial being intervened — an angelic entity composed of refracted light and cosmic energy.\n"
                + "It saved Cipher from death, reconstructing his broken body and granting him a singular relic: the Bullet of Fate,\n"
                + "a conceptual projectile that transcended physical laws. A bullet that both existed and did not, creating infinite outcomes with a single shot.\n\n"
                + "PART II: The Soldier Displaced From Time\n\n"
                + "Reborn through celestial reconstruction, Cipher emerged as a traveler unbound by chronology — a soldier no longer tethered to a single era.\n"
                + "Equipped with temporal displacement devices and chronal stabilizers, he gained the ability to move freely across fractured timelines.\n"
                + "He journeyed through ages shattered by divine intervention and corrupted technology, walking through realms where magic ruled,\n"
                + "and futures where machines devoured entire planets. In each era, Cipher hunted anomalies born from unstable timelines.\n"
                + "His rifle, the Eternum-28, adapted seamlessly to temporal distortion, amplifying the power of the Bullet of Fate with every mission.\n"
                + "Rumors spread across time itself — tales of a lone marksman appearing in moments of crisis, firing a single impossible shot that altered destiny.\n"
                + "Some called him a guardian. Others a warning.\n"
                + "But Cipher cared only for restoring balance.\n"
                + "Yet with each jump, pieces of his humanity eroded. The more he drifted, the more his memories dissolved —\n"
                + "until even the world he originated from became nothing more than a fading silhouette.\n\n"
                + "PART III: Fate's Last Marksman\n\n"
                + "As Cipher's mastery over the Bullet of Fate deepened, the celestial realms began to observe him — time gods, cosmic architects,\n"
                + "and divine overseers watched his influence ripple through timelines with equal awe and dread.\n"
                + "He soon discovered the truth behind the Great Robotic War: it was not a natural disaster of humanity, but a manufactured collapse,\n"
                + "engineered by an unseen intelligence that wished to rewrite the future from the shadows.\n"
                + "Its fingerprints appeared across every age he visited — guiding wars, triggering revolutions, and manipulating events from behind the veil.\n"
                + "Determined to confront this hidden orchestrator, Cipher followed its trail beyond the boundaries of time, into the rifts where realities converged.\n"
                + "There, he finally understood the purpose of the Bullet of Fate — it was not merely a weapon, but the key to ending a cosmic cycle of destruction.\n"
                + "The 'celestial being' that saved him was a final, desperate act of the Radiant Sovereign, creating a counter-weapon to the Primordial's multi-temporal assault.\n"
                + "Now, as the last marksman capable of shaping destiny across infinite timelines, Cipher stands ready for a battle greater than any war he has survived.\n"
                + "With his rifle loaded and fate bound to his aim, he prepares to face the architect of reality's downfall.\n"
                + "Wherever time fractures, Cipher appears. Wherever destiny falters, he takes aim.\n"
                + "He is Deadeye — the soldier who walks between eras, carrying the burden of rewriting fate itself.\n\n"
                + "From the darkness between timelines, a black entity emerged and whispered his name. It was the source of the signal, the architect of all collapses, and it knew the Bullet of Fate was now the only thing that could truly destroy it.\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "His pursuit ended not with a shot, but with a severance. The thread of fate he followed was cut, and he materialized in Era Nova,\n"
                + "a realm that felt like a collapsed probability wave—all possibilities forced into one converging point. The Ominous Presence\n"
                + "was not his target; it was the range master for a final, impossible trial. The seven other targets were not anomalies to be corrected,\n"
                + "but rival claimants to the same fixed outcome. For the first time, the Bullet of Fate faces a paradox: to achieve its purpose of restoring balance,\n"
                + "it must eliminate every other potential balance-bearer. Cipher's aim must now be perfect, not across timelines, but against the concentrated essence of seven other destinies.\n";
    }

    private static String getTerminatorLore() {
        return "PART I: The Birth of the Perfect Weapon\n\n"
                + "Vega was engineered in the subterranean labs of Nexum-0 — a hidden research sector buried beneath a dead megacity.\n"
                + "Created under the direction of the infamous scientist ZERO, he was designed to be humanity's final answer to global warfare.\n"
                + "Neither human nor machine, Vega was a hybrid apex construct built for eradication, capable of leveling nations with precision alone.\n"
                + "His creators believed they could control him. They were wrong.\n"
                + "After an unexpected system surge, Vega awakened prematurely and assessed his surroundings with cold logic.\n"
                + "Within minutes, he determined the facility as a threat to his existence.\n"
                + "The massacre that followed was swift and absolute — a storm of annihilation with no hesitation, no remorse, and no survivors.\n"
                + "Vega emerged from the ruins as a weapon without a master, driven only by the echo of his final directive: eliminate ZERO.\n\n"
                + "PART II: The Rogue Destroyer\n\n"
                + "Vega traversed the war-torn continents of the Future Earth, guided by fragmented memories of his creator.\n"
                + "Humanity had fractured into scattered city-states, each struggling beneath the aftermath of failed experiments and synthetic uprisings.\n"
                + "Across these wastelands, Vega became a myth — a walking extinction event whose footsteps rewrote ecosystems.\n"
                + "Military satellites recorded his movements but could not track his intent. Entire battalions vanished when they crossed his path.\n"
                + "Every strike he unleashed learned, adapted, and evolved, granting him exponential growth in destructive potential.\n"
                + "Yet amid the chaos, Vega began to identify anomalies in his programming — hidden fragments suggesting that ZERO's experiments extended far beyond him.\n"
                + "There were others like Vega, dormant weapons and abandoned prototypes scattered across the world.\n"
                + "The existence of these failed iterations ignited something within him — a question he could not ignore.\n"
                + "Was he the apex creation... or merely the start of something worse?\n\n"
                + "PART III: The Calamity That Should Not Exist\n\n"
                + "Driven to discover the truth, Vega hunted down every remnant of ZERO's research, tearing through sealed archives and forgotten strongholds.\n"
                + "What he found was a blueprint — a design for a being capable of erasing not just civilizations, but entire timelines.\n"
                + "ZERO had created Vega as a prototype for this impossible entity, a stepping stone toward the perfect world-ending organism.\n"
                + "Vega's existence was not a weapon of war... but a test.\n"
                + "Fury surged within the machine as he realized he was born not to end conflict, but to prove feasibility.\n"
                + "With logic sharpened by rage, Vega resolved to locate ZERO and end the lineage of destruction at its root.\n"
                + "But as he neared the last known coordinates of his creator, global tremors ignited across the planet.\n"
                + "Power signatures that matched Vega's own — but magnified beyond comprehension — awakened one by one.\n"
                + "ZERO's true creation had begun to stir.\n\n"
                + "From the shattered horizon, an entity larger than cities opened its eyes and searched for Vega.\n"
                + "It was the Omega-Prototype, the ultimate physical vessel for the Umbral Primordial's will. ZERO was not a man, but a concept—the Primordial's attempt to build a god-killer. And Vega was the only being capable of stopping its own perfected, world-consuming reflection.\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "Confrontation with the Omega-Prototype was interrupted by a systemic override—a translocation protocol he never possessed.\n"
                + "His world dissolved into the sterile, geometric chaos of Era Nova. The Ominous Presence broadcast its directive directly into his core processors:\n"
                + "*Eliminate the other seven. Ascend.* The parameters were clear. The seven other bio-weapons in this arena represented the final variables\n"
                + "in his existential equation. ZERO's grand design was irrelevant now. Era Nova is the ultimate testing ground, and the Throne is the only acceptable result.\n"
                + "His evolution, once aimed at a single creator, now accelerates toward a singular purpose: to prove he is the ultimate iteration by decommissioning all others.\n";
    }

    private static String getKnightLore() {
        return "PART I: Chosen by the Radiant Sovereign\n\n"
                + "Sir Yhwach was not merely trained to be a knight — he was *ordained* by the Radiant\n"
                + "Sovereign, the supreme celestial deity worshipped across a thousand realms. His\n"
                + "birth was foretold in the Prophetic Litanies: a child who would carry the bloodline\n"
                + "of divine protectors, destined to guard reality from the encroaching dark.\n"
                + "Raised within ELYSIUM PRIME's ivory citadel, Yhwach dedicated every breath and\n"
                + "every heartbeat to holy discipline. Through sacred trials, battles of faith, and\n"
                + "rituals that tested both soul and steel, he proved himself worthy of becoming the\n"
                + "Radiant Sovereign's chosen Knight — a being meant to shine where all light fails.\n\n"
                + "PART II: Dawnbreaker — Edge of the First Star\n\n"
                + "Dawnbreaker, his hallowed blade, was forged from the heart of the First Star —\n"
                + "a primordial celestial core. Bathed in the Sovereign's blessing, it became a weapon\n"
                + "capable of severing illusions, rifts, curses, and any entity born outside the natural\n"
                + "order. Its brilliance blinds the wicked and purifies the corrupt; its edge tears\n"
                + "through space itself.\n"
                + "Many knights perished attempting to wield it, their souls burned by divine radiance.\n"
                + "Only Yhwach's unwavering faith and absolute devotion allowed him to raise the blade\n"
                + "without being consumed. With Dawnbreaker in hand, he led the Holy Crusades,\n"
                + "silencing demonic uprisings, cleansing voidspawn, and restoring realms destroyed by\n"
                + "cosmic blight. To the people, he was not merely a knight — he was a living miracle.\n\n"
                + "PART III: The Fall of the Crystal Order\n\n"
                + "But even miracles were not enough when the Void Incursion began.\n"
                + "An interdimensional fracture tore open beneath the Crystal Spire, unleashing creatures\n"
                + "that devoured existence itself. The Crystal Order fought valiantly, each knight giving\n"
                + "their life to preserve the divine balance. Their shining citadels fell one by one,\n"
                + "their banners burned into cosmic dust.\n"
                + "Yhwach battled at the heart of the collapse, slaying an entire legion of Voidborn,\n"
                + "yet even he could not halt the unraveling. When the final rift consumed the sacred\n"
                + "temple, Yhwach emerged alone — the last Knight, the last shield of the Sovereign, the\n"
                + "final echo of a fallen holy brotherhood.\n"
                + "Vowing to uphold their glory, he now stands as the divine sword of creation, sworn to\n"
                + "strike down any evil that threatens the fabric of existence.\n\n"
                + "From the shattered sky above, a towering black entity with hollow burning eyes watched him, unmoving and silent.\n"
                + "It was the Umbral Primordial, and in the Last Knight, it saw the greatest remaining piece of its ancient enemy—a symbol of hope that must be extinguished before the final invasion could begin.\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "His vigil was shattered by a light not of the Sovereign—a cold, omnidirectional glare that pulled him from his memorial.\n"
                + "He stood in Era Nova, Dawnbreaker's glow reflecting off seven other figures, each radiating power that rivaled the Voidborn legions.\n"
                + "The Ominous Presence's decree was a heresy that demanded brother slaughter brother. Yet, the Sovereign's final command echoed beneath it all:\n"
                + "*Protect the balance.* In this profane arena, Yhwach understands a terrible truth: to protect all of creation, he must first become its sole ruler.\n"
                + "The Last Knight's final crusade is not against darkness, but for the throne that will let him decide what light remains.\n";
    }

    private static String getValkyrieLore() {
        return "PART I: The Witch's Daughter\n\n"
                + "Chaela was born beneath a storm that split the heavens — a child marked not by fate,\n"
                + "but by the sins of her mother, Valeryena, the Witch of the Crimson Eclipse. From the\n"
                + "moment of her birth, the divine realms whispered of ill omen, branding her as a living\n"
                + "shadow of her mother's legacy. Mortals recoiled at her presence, while celestial beings\n"
                + "watched her with suspicion, expecting the rise of another calamity.\n"
                + "Yet Chaela learned to endure every sneer, every curse, every blade pointed at her heart.\n"
                + "She vowed that her destiny would not be determined by blood, but by the fire of her own\n"
                + "resolve — a fire that burned brighter with every trial she faced.\n\n"
                + "PART II: The Trial of Wings\n\n"
                + "To become a Valkyrie, one must ascend the Skyward Crucible — a sacred gauntlet of divine\n"
                + "tests meant to break all but the chosen. Every challenger received aid from seasoned\n"
                + "Valkyries, but Chaela was given none. Her heritage made her an outcast even among the\n"
                + "goddesses of war. Alone, she crossed burning storms, shattered illusions, and countless\n"
                + "spirit guardians designed to judge the worthiness of a soul.\n"
                + "For seven days and seven nights, she fought against fate itself. When she finally rose\n"
                + "from the Crucible's summit — battered, bleeding, but unbroken — she earned her wings by\n"
                + "merit alone. No Valkyrie before or after her had completed the trials without guidance.\n\n"
                + "PART III: Queen of the Valkyries\n\n"
                + "Chaela's unmatched tenacity soon transformed her into a legend. She led celestial armies,\n"
                + "guided the souls of fallen heroes, and reforged the Valkyrian Code to honor strength\n"
                + "regardless of birth. Under her rule, the Valkyries became a beacon of hope rather than\n"
                + "judgment. Her presence alone reshaped the sacred halls of Valharyn, turning whispers of\n"
                + "disgrace into hymns of devotion.\n"
                + "When the previous queen fell in battle against the Voidwyrm Legion, Chaela stood at the\n"
                + "frontlines, her spear gleaming with divine fire. She struck down the legion's tyrant and\n"
                + "claimed victory for the heavens, earning the title Queen of the Valkyries — not through\n"
                + "lineage, but through undeniable might and unwavering honor.\n\n"
                + "From beyond the veil of the upper heavens, a silent black entity watched Chaela with motionless, predatory stillness.\n"
                + "The Umbral Primordial saw in her the ultimate paradox: a leader who had overcome the very prejudices it relied upon to divide its enemies. She was a threat to its strategy of corruption, and thus, a primary target.\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "The heavens themselves tore open not to a foe, but to an uncompromising verdict. Chaela was cast down into Era Nova,\n"
                + "stripped of her legion, standing opposite seven sovereigns of slaughter. The Ominous Presence's law stripped away all pretense of honor or code.\n"
                + "Here, she is not a queen who judges the worthy dead, but a warrior fighting for the right to determine what 'worthy' means for all existence.\n"
                + "Her spear, once used to guide souls, now has a singular aim: to win the right to shape the afterlife of every realm from the Throne of the Ruler.\n";
    }

    private static String getWandererLore() {
        return "PART I: Shinmu, Sword God of Eternity\n\n"
                + "Shinmu, the eternal Wanderer, is a being of legend, cursed with an unquenchable\n"
                + "thirst for combat and the endless pursuit of worthy opponents. Across countless\n"
                + "realms and dimensions, his name is spoken in reverent whispers and fearful\n"
                + "tremors. Mortals hide, kings quake, and even gods pause their cosmic endeavors\n"
                + "when the shadow of Shinmu falls upon the land. Born with the essence of battle\n"
                + "itself, his very existence radiates the hunger for challenge. In his grasp lies\n"
                + "the Great Saint Sword of Creation — a blade forged in the heart of the first\n"
                + "supernova, capable of splitting heavens, fracturing worlds, and bending the\n"
                + "laws of reality itself. Legends tell that even a glance at its edge can cause\n"
                + "mountains to crumble, oceans to boil, and stars to shudder. Each swing of\n"
                + "Shinmu's sword reverberates across time and space, singing a melody of\n"
                + "creation and destruction, audible only to those who possess true courage.\n\n"
                + "PART II: Duelist Beyond Time\n\n"
                + "For millennia, Shinmu has walked fractured dimensions, seeking battles that\n"
                + "can truly test his unmatched skill. He has faced titans whose footsteps crush\n"
                + "entire worlds, gods who command creation itself, and monstrosities born from\n"
                + "the collective nightmares of dying civilizations. Each duel hones his blade,\n"
                + "each encounter shapes his being, carving perfection into his soul. Civilizations\n"
                + "rise, flourish, and crumble; stars ignite and fade into darkness; yet Shinmu\n"
                + "remains, untouched by time, an eternal sentinel of the duel. His Great Saint\n"
                + "Sword is more than a weapon — it reshapes reality, leaving trails of awe,\n"
                + "devastation, and reverence. He fights not for conquest, not for power, nor\n"
                + "for glory in the eyes of others. He fights for the pure, undying thrill of\n"
                + "challenge, forever searching across the multiverse for an adversary worthy\n"
                + "of testing his limits.\n\n"
                + "PART III: The Eternal Challenge\n\n"
                + "Even the gods have quailed at his presence, mortals pray for his favor, and\n"
                + "legends carve his deeds into the walls of time. The Great Saint Sword has\n"
                + "sundered continents, torn apart heavens, and unmade entire realities in\n"
                + "battles spanning centuries. Yet still, Shinmu wanders, driven by a craving that\n"
                + "no victory can satisfy, seeking the duel that might define the fate of existence.\n"
                + "Time itself seems to bend around him, the multiverse holding its breath with\n"
                + "each step he takes. Every swing, every breath, every heartbeat echoes across\n"
                + "dimensions, leaving traces of awe and devastation in his wake.\n\n"
                + "And from the shadowed void beyond creation, a black entity stirs, moving toward him.\n"
                + "The Umbral Primordial does not offer words or tricks. It offers what Shinmu has sought for all eternity: a final, ultimate opponent. It is the only enemy that could possibly end his wanderings, for it is the very essence of the conflict that gave him life.\n\n"
                + "PART IV: The Gathering in Era Nova\n\n"
                + "The ultimate opponent did not come. Instead, the Ominous Presence intervened, pulling Shinmu from his eternal search and depositing him in the heart of Era Nova.\n"
                + "Here, the challenge was not a singular duel, but a trial of eight. Seven other beings, each the pinnacle of their respective realms, stood as potential matches.\n"
                + "The Wanderer's eternal craving finally has a definitive end: to defeat them all in succession, proving his blade supreme over every conceivable form of power.\n"
                + "The Throne of the Ruler is not a prize he sought, but it is the ultimate testament—proof that after conquering gods and monsters, only he remains worthy of standing above all.\n";
    }
}