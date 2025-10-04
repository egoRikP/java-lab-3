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

    public static List<String> read(String path) throws IOException {
        List<String> res = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String buff;
            while ((buff = reader.readLine()) != null) {
                res.add(buff);
            }
        }

        return res;
    }

    public static void save(AbstractBattle battle) throws IOException {
        BattleLogger logger = battle.getBattleLogger();
        Path logsDir = Paths.get("battleLogs");

        // create directory logs when not exist
        Files.createDirectories(logsDir);

        Path filePath = logsDir.resolve(getFileNameWithTimestamp());
        Files.write(filePath, logger.getLogs());
        System.out.println("Battle saved to " + filePath);
    }

    private static String getFileNameWithTimestamp() {
        return String.format("battle_%d.txt", System.currentTimeMillis());
    }
}