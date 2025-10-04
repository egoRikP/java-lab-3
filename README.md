## Description

This is a DROID GAME BATTLE in console

---

# <center>Screenshot Examples</center>

## <center>Main Menu</center>

![mainMenu.png](screenshots/mainMenu.png)

---

## <center> Create droid </center>

![createDroidMenu.png](screenshots/createDroidMenu.png)
---

## <center> Battle 1 vs 1 </center>

![oneByOneBattleStart.png](screenshots/oneByOneBattleStart.png)
...
![oneByOneBattleEnd.png](screenshots/oneByOneBattleEnd.png)
---

## <center> Saving last battle to battle log file </center>

![savingLastBattle.png](screenshots/savingLastBattle.png)

## <center> Reading battle from battle log file</center>

![readingBattleFromFile.png](screenshots/readingBattleFromFile.png)

## Project Structure

```declarative
java-lab-3/
├─ src/
│  ├─ Main.java
│  ├─ battle/
│  │  ├─ AbstractBattle.java
│  │  ├─ BattleLogger.java
│  │  ├─ BattleUtils.java
│  │  ├─ OneByOneBattle.java
│  │  └─ TeamByTeamBattle.java
│  ├─ droid/
│  │  ├─ AbstractDroid.java
│  │  ├─ DefaultDroid.java
│  │  ├─ DroidFactory.java
│  │  ├─ BerserkDroid.java
│  │  ├─ BomberDroid.java
│  │  ├─ HealerDroid.java
│  │  └─ LaserDroid.java
│  └─ io/
│     └─ BattleFileManager.java
├─ battleLogs/
│  ...
```

---

# How to start

## Autostart

Run the `start.bat` file.

---

## Manual

### Compile files

```
javac -d out src/battle/*.java src/droid/*.java src/io/*.java src/Main.java
```

#### Run program

```
java -cp out Main
```

---

## Authors

- [@egorikp](https://github.com/egoRikP)