package com.teamname.hotelfx.dbAccess;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class Backup {

    public static void backUpTool() {
        try {
            CodeSource codeSource = Backup.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            String dbName = HotelfxAccess.DB_DATABASE;
            String dbUser = HotelfxAccess.DB_USER;
            String dbPass = HotelfxAccess.DB_PASSWORD;

            String folderPath = "src\\" + "backup\\";
            File f1 = new File(folderPath);
            f1.mkdir();

            String savePath = "src\\" + "backup\\" + "backup.sql";
            String executeCmd = "src/backup/mysqldump -u" + dbUser + " --database " + dbName + " -r " + savePath;

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            System.out.println(("Error at Backuprestore" + ex.getMessage()));
        }
    }
}