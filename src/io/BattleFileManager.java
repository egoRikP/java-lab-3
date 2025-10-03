package io;

import battle.AbstractBattle;
import battle.BattleLogger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BattleFileManager {

    public static List<String> read(String path) {
        List<String> res = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String buff;
            while ((buff = reader.readLine()) != null) {
                res.add(buff);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't find file " + path);
        } catch (IOException e) {
            System.out.println("IO exception while reading battle: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Other exception " + e.getMessage());
        }

        return res;
    }

    public static void save(AbstractBattle battle) {
        BattleLogger logger = battle.getBattleLogger();
        Path logsDir = Paths.get("battleLogs");

        try {
            // create directory logs when not exist
            Files.createDirectories(logsDir);

            Path filePath = logsDir.resolve(getFileNameWithTimestamp());
            Files.write(filePath, logger.getLogs());
            System.out.println("Battle saved to " + filePath);

        } catch (IOException e) {
            System.out.println("IO exception while saving battle: " + e.getMessage());
        }
    }

    private static String getFileNameWithTimestamp() {
        return String.format("battle_%d.txt", System.currentTimeMillis());
    }
}