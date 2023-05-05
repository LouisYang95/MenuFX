package com.example.model;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Chrono extends Thread {
    private Label timeLabel;
    private boolean stop;
    private static int remainingSeconds;

    public Chrono(int durationMinutes, Label timeLabel) {
        this.timeLabel = timeLabel;
        this.remainingSeconds = durationMinutes * 60;
        this.stop = false;
    }

    public void run() {
        while (!stop) {
            try {
                Thread.sleep(1000);
                remainingSeconds--;

                Platform.runLater(() -> timeLabel.setText(Chrono.getTimeRemaining()));

                if (remainingSeconds <= 0) {
                    stopChronometer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopChronometer() {
        this.stop = true;
    }

    public static boolean canTakeOrder() {
        return remainingSeconds > 15 * 60;
    }

    public static String getTimeRemaining() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}