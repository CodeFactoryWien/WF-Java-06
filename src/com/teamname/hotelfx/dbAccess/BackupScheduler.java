package com.teamname.hotelfx.dbAccess;

import javafx.application.Platform;
import javafx.concurrent.Task;


public class BackupScheduler {
    public static void backupScheduler() {

        Task<Void> task = new Task<Void>() {
            int maxCount = 3600000;

            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= maxCount; i++) {
                    Thread.sleep(3600000); // Backupcycle -- 1h --
                    final int count = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            Backup.backUpTool();
                        }
                    });
                    updateProgress(i, maxCount);
                }
                return null;
            }
        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
}
