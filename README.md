<h1 align = "center">⋆⭒˚.⋆☾.𖥔 ݁ ˖REALM COLLISION 𓂃 ࣪˖ ִֶָ★˖</h1>
<h3 align = "center">⋆༺𓆩Turn-Based ⋆༺𓆩🗡𓆪༻⋆ PVP Game.𓆪༻⋆</h3>
<p align = "center">
<b>CS-2102  </b> <br/>


Andal, Juan Miguel P. <br/>
Soberano, Jonathan G. <br/>
Vitug, Gian Christian V.
</p>

# 🕮 ┊ Overview

Realm Collision is a Turn-based PVP game.<br/>
Choose amongst different acquiantance throughout the entire Cosmos and challenge those who you deemed worthy!


<br/><br/>
The entire Cosmos was built using core Object-Oriented Programming (OOP) principles, including encapsulation, inheritance, polymorphism, and abstraction.

## Features

### 𝄞𝄢 Freedom to:

⚔️ - Play against other Players<br/>
⚖️ - Learn the abilities of each acquaintant<br/>
𓆩✧𓆪 - Engage yourself to the stories behind each characters<br/>

### ⚖️Choose your Acquaintant⚖️:
⚪ ArcMage <br>
⚪ Deadeye <br>
⚪ Knight <br>
⚪ Terminator <br>
⚪ Trickster <br>
⚪ Wanderer <br>



### ⚔️Battle Mechanics⚔️:

⚔️ Attack - Deal damage against your opponent <br/>
🛡️ Defend - Reduce the incoming damage<br/>
🔮 Skill - Unleash the unique ability of your Acquaintant <br/>
✨ Ultimate - A Powerful unique ability that requires amounts of energy


## 🏰 Project Structure🏰:
```
📂 src/
├── 📂 static/
├── 📖 README.md
└── 📂 realmcollision/
    ├── 📂 classes/ 
    │   ├── ⚪ ArcMage.java          
    │   ├── ⚪ Character.java
    │   ├── ⚪ Deadeye.java
    │   ├── ⚪ Knight.java          
    │   ├── ⚪ Terminator.java
    │   ├── ⚪ Trickster.java
    │   └── 📂 Wanderer/
    │       ├── ⚪ Clone.java
    │       └── ⚪ Wanderer.java
    ├── 📂 panels/
    │   ├── 📜 ActionPanel.java          
    │   ├── 📜 BattleLog.java
    │   ├── 📜 CharacterPanel.java
    │   └── 📜 StatusPanel.java
    ├── 📂 utils/   
    │   ├── 🪶 CharacterStats.java          
    │   ├── 🪶 CombatCalculator.java
    │   └── 🪶 CombatUtils.java
    ├── 🎮 GameController.java
    ├── 🖥️ Main.java
    └── 🌌 RealmCollisionGUI.java


📂 static/      - Includes Images required by the 📖 README.md
    
```

### 🌒To Enter the Realm within the Cosmos🌒:
Open your terminal in the `src/` folder and run: {THIS IS A PLACEHOLDER📌}
```
{ADD HOW TO RUN📌}
```
Run the program using:
```
{ADD HOW TO RUN📌}
```

## 💻 Object-oriented Principles💻

### 🪄 Encapsulation
Each character class encapsulates its own data (`health`, `energy`, `action points`) Private fields with public getters/setters Internal state management within each class BattleLog connection through dependency injection

Example:
```java
// Encapsulation
// Character.java 

public abstract class Character {
    // Private fields - data is hidden
    private int health;
    private int energy;
    private int actionPoints;
    
    // Public getters/setters - controlled access
    public int getHealth() { return this.health; }
    public void setHealth(int health) { 
        if (health >= 0) this.health = health; 
    }
    
    public int getEnergy() { return this.energy; }
    public void setEnergy(int energy) {
        this.energy = Math.min(energy, CharacterStats.getMaxEnergy(this.getClass().getSimpleName()));
    }
}


```

### 🌀 Abstraction
`Character.java` is an abstract base class defining the interface Abstract methods: `attack()`, `defense()`, `castSkill()`, `castUltimate()`. </br> 
Concrete implementations define specific behaviors
UI panels abstract visual representation from game logic

Example:
```java
//Abstract
// Character.java 

public abstract class Character {
    // Abstract methods - define WHAT to do, not HOW
    public abstract int attack();
    public abstract void defense();
    public abstract int castSkill();
    public abstract int castUltimate(Character target);
    
    // Concrete method - shared implementation
    public boolean isDefeated() {
        return this.health <= 0;
    }
}

// ArcMage.java - Concrete Implementation
public class ArcMage extends Character {
    @Override
    public int attack() {
        // Implementation details hidden
        this.setRegenActionPoints(true);
        return 1000; // Base damage
    }
}


```


### 🩸 Inheritance
All character classes extend `Character.java` Method overriding for specialized behaviors `Clone.java` extends `Wanderer.java` for shared functionality.
Common utility methods inherited across all characters

Example:
```java
//inheritance
// Parent class

public abstract class Character {
    protected BattleLog battleLog;
    
    public void setBattleLog(BattleLog battleLog) {
        this.battleLog = battleLog;
    }
    
    public void addBattleLogMessage(String message) {
        if (this.battleLog != null) {
            this.battleLog.addMessage(message);
        }
    }
}

// Child class inherits logging capability
public class Deadeye extends Character {
    @Override
    public int castUltimate(Character target) {
        // Uses inherited method
        this.addBattleLogMessage("Deadeye cast his ultimate");
        this.addBattleLogMessage("BULLET OF FATE!");
        return 10000;
    }
}


```

### 🎲 Polymorphism
`GameController` treats all characters as Character type Dynamic method dispatch for character-specific actions, 
`castUltimate()` behaves differently per character,
`takeDamage()` has special handling for Wanderer with clones.

Example:
```java
//Polymorphism
// GameController.java - Treating all characters uniformly
public class GameController {
    private Character player1, player2;
    
    public void handleAction(String action) {
        Character currentPlayer = (currentTurn == 1) ? player1 : player2;
        
        // Polymorphic method calls - same method, different behavior
        int damage = 0;
        switch (action) {
            case "ATTACK":
                damage = currentPlayer.attack(); // Calls specific implementation
                break;
            case "SKILL":
                damage = currentPlayer.castSkill(); // Different per character
                break;
        }
    }
}

// Different behaviors through overriding
public class Trickster extends Character {
    @Override
    public int castSkill() {
        this.addBattleLogMessage("Trickster gains an attack stack!");
        this.attackStacks++; // Trickster-specific behavior
        return 0;
    }
}

public class Valkyrie extends Character {
    @Override
    public int castSkill() {
        this.addBattleLogMessage("Valkyrie heals 4000 HP!");
        this.health += 4000; // Valkyrie-specific behavior
        return 0;
    }
}


//Composition
// Wanderer HAS Clones (Composition)
public class Wanderer extends Character {
    private Clone[] clones; // Composition - Wanderer "has" clones
    
    public Wanderer(int health, int energy, int actionPoints) {
        super(health, energy, actionPoints);
        this.clones = new Clone[2]; // Creates clones as part of itself
    }
    
    public Object takeDamageWithClones(int damage, Character attacker, 
                                       boolean isSkill, boolean isUltimate) {
        // Damage goes to clones first
        for (Clone c : clones) {
            if (c != null && c.isAlive()) {
                c.takeDamage(damage, this, false, false);
                return damage;
            }
        }
        // Then to wanderer
        return super.takeDamage(damage, attacker, isSkill, isUltimate);
    }
}


```


## <h2 align = "center"> :･ﾟ✧:･.☽˚｡･ﾟ✧:･.: Sample Output :･ﾟ✧:･.☽˚｡･ﾟ✧:･.: </h2>

Main Menu:

<center><img src="./static/MainMenu.png" width="700"></center>
<br>
Acquiantant Selector:
<br> </br>
<center><img src="./static/CharacterSelection.png" width="700"></center>
<br> </br>
The Battlefield:
<br></br>
<center><img src="./static/CombatScreen.png" width="700"></center>
</br>


##  <h2 align = "center"> ♔♕♗♘♖♙ Contributors ♙♖♘♗♕♔ </h2> 

<table>
<tr>
    <th> Name </th>
    <th> Role </th>
</tr>
<tr>
    <td><strong>Vitug, Gian Christian V. ♔</strong> <br/>
    <a href="https://github.com/avisola" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=red" alt="Avisola's GitHub">
        </a>
    </td>
    <td> Party Leader/Lead Developer</td>
</tr>
<tr>
    <td><strong>Andal, Juan Miguel P. ♗</strong> <br/>
    <a href="https://github.com/JuanMiguelAndal" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=green" alt="JuanMiguelAndal's GitHub">
        </a>
    </td>
    <td>Support/Assistant Developer</td>
</tr>
<tr>
    <td><strong>Soberano, Jonathan G. ♘</strong> <br/>
    <a href="https://github.com/JonasNate987" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=blue" alt="JonasNate987's GitHub">
        </a>
    </td>
    <td>Warrior/Assistant Developer</td>
</tr>
</table>

## 🌸 Acknowledgment 🌸
We extend our heartfelt gratitude to our mentor and course instructor, Ma’am <a href="https://github.com/marieemoiselle" target="_blank">Fatima Marie P. Agdon</a>, whose guidance has been nothing short of enchanting. Her patience and wisdom helped us navigate the challenges of object-oriented programming, turning confusion into clarity and allowing us to grow as aspiring developers. For her support and dedication, we are truly thankful. ❀


