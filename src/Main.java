import battle.AbstractBattle;
import battle.OneByOneBattle;
import battle.TeamByTeamBattle;
import droid.AbstractDroid;
import droid.DroidFactory;
import io.BattleFileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<AbstractDroid> myDroids = new ArrayList<>();
    private static AbstractBattle lastBattle;

    public static void main(String[] args) {

        AbstractDroid allDroids[] = {
                DroidFactory.createDefaultDroid("Attacker"),
                DroidFactory.createDefaultLaser("Laser"),
                DroidFactory.createDefaultBomber("Bomber"),
                DroidFactory.createDefaultBerserk("Berserk"),
                DroidFactory.createDefaultHealer("Healer")
        };
        myDroids.addAll(Arrays.stream(allDroids).toList());

        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("DROID GAME BATTLE");

        int userChoice;

        while (true) {
            System.out.println("""
                    1 - CREATE DROID
                    2 - SHOW DROIDS
                    3 - START ONE VS ONE
                    4 - START TEAM VS TEAM
                    5 - SAVE BATTLE
                    6 - READ BATTLE
                    0 - EXIT GAME""");

            System.out.print("ENTER: ");
            userChoice = getValidInt(0, 6);

            if (userChoice == 0) {
                System.out.print("EXIT GAME");
                break;
            }

            switch (userChoice) {
                case 1 -> {
                    AbstractDroid droid = createDroidMenu();
                    if (droid != null) {
                        myDroids.add(droid);
                        System.out.println(String.format("CREATED %s", droid));
                    }
                }
                case 2 -> showDroids(myDroids);
                case 3 -> {
                    if (myDroids.isEmpty()) {
                        System.out.println("DROID LIST EMPTY! FIRST CREATE DROIDS TO START BATTLE!");
                        break;
                    }
                    startBattle(createOneByOneBattle());
                }
                case 4 -> {
                    if (myDroids.isEmpty()) {
                        System.out.println("DROID LIST EMPTY! FIRST CREATE DROIDS TO START BATTLE!");
                        break;
                    }
                    startBattle(createTeamByTeamBattle());
                }
                case 5 -> {
                    if (lastBattle == null) {
                        System.out.println("TO SAVE LOG FIRST START BATTLE");
                    } else {
                        try {
                            BattleFileManager.save(lastBattle);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                case 6 -> {
                    System.out.print("ENTER PATH TO BATTLE LOG FILE: ");
                    String path = getNotEmptyString();
                    try {
                        List<String> logs = BattleFileManager.read(path);
                        for (String log : logs) {
                            System.out.println(log);
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println();
        }
    }

    private static void startBattle(AbstractBattle battle) {
        lastBattle = battle;
        try {
            lastBattle.start();
            lastBattle.getBattleLogger().show();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            lastBattle = null;
        }

    }


    private static AbstractBattle createOneByOneBattle() {
        showDroids(myDroids);
        AbstractDroid first = enterDroidForTeam("FIRST DROID", 1).getFirst();
        AbstractDroid second = enterDroidForTeam("SECOND DROID", 1).getFirst();
        return new OneByOneBattle(first, second);
    }

    private static AbstractBattle createTeamByTeamBattle() {
        showDroids(myDroids);
        System.out.print("ENTER TEAM SIZE IN RANGE [1;5]: ");
        int teamSize = getValidInt(1, 5);

        List<AbstractDroid> teamA = enterDroidForTeam("FIRST TEAM", teamSize);
        List<AbstractDroid> teamB = enterDroidForTeam("SECOND TEAM", teamSize);
        return new TeamByTeamBattle(teamA, teamB);
    }

    private static AbstractDroid createDroidMenu() {

        System.out.println("CREATE DROID MENU");
        System.out.println("""
                1 - \uD83E\uDD16 DEFAULT
                2 - ❤️ HEALER (heal mates)
                3 - \uD83D\uDCA3 BOMBER (single boom + splash dmg)
                4 - ⚡ LASER (charge attack)
                5 - \uD83E\uDE93 BERSERK (less HP more dmg)
                0 - LEAVE""");

        System.out.print("ENTER: ");
        int userChoice = getValidInt(0, 5);

        System.out.print("ENTER DROID NAME: ");
        String name = getNotEmptyString();

        return
                switch (userChoice) {
                    case 0 -> null;
                    case 1 -> DroidFactory.createDefaultDroid(name);
                    case 2 -> DroidFactory.createDefaultHealer(name);
                    case 3 -> DroidFactory.createDefaultBomber(name);
                    case 4 -> DroidFactory.createDefaultLaser(name);
                    case 5 -> DroidFactory.createDefaultBerserk(name);
                    default -> throw new IllegalArgumentException("Unexpected value: " + userChoice);
                };
    }

    public static void showDroids(List<AbstractDroid> droidList) {
        if (droidList.isEmpty()) {
            System.out.println("DROID LIST EMPTY!");
            return;
        }

        System.out.println("DROID LIST:");
        for (int i = 0; i < droidList.size(); i++) {
            System.out.println(String.format("%d). %s", i + 1, droidList.get(i)));
        }
    }

    private static List<AbstractDroid> enterDroidForTeam(String team, int teamSize) {
        List<AbstractDroid> result = new ArrayList<>();
        System.out.println("ENTER FOR " + team);
        for (int i = 0; i < teamSize; i++) {
            if (teamSize > 1) {
                System.out.printf("ENTER ID FOR %d DROID: ", i + 1);
            }
            AbstractDroid inputDroid = myDroids.get(getValidInt(1, myDroids.size()) - 1);
            result.add(inputDroid);
            System.out.println(String.format("ENTERED %s", inputDroid.formatPrint()));
        }

        return result;
    }

    public static String getNotEmptyString() {
        String name;
        do {
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.print("NOT EMPTY STRING: ");
            }
        } while (name.isEmpty());
        return name;
    }

    public static int getValidInt(int min, int max) {

        int buff;

        while (true) {

            if (scanner.hasNextInt()) {
                buff = scanner.nextInt();
                scanner.nextLine();
                if (buff >= min && buff <= max) {
                    break;
                } else {
                    System.out.printf("Only in range [%d;%d]: ", min, max);
                }
            } else {
                scanner.nextLine();
                System.out.print("Only INT! ");
            }

        }
        return buff;
    }
}