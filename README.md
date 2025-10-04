## Description

This is a DROID GAME BATTLE in console

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