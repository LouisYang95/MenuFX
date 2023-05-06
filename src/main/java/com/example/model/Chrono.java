package com.example.model;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;

public class Chrono extends Thread {

    private static final int duration = 25 * 60;
    private static int remainingTime = duration;
    private static Timer timer;
    private static Label timeLabel;
    public static boolean isRunning;

    public static boolean stopOrder = false;

    public static void stopService() {
        isRunning = false;
        timer.cancel();
        timer = null;
        remainingTime = duration;
        timeLabel.setText(getRemainingTime());
    }

    public static String getRemainingTime() {
        int minutes = remainingTime / 60;
        int remainingSeconds = remainingTime % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static void setTimerLabel(Label label) {
        timeLabel = label;
    }

    public void run() {
        isRunning = true;

        try {
            for (;;) {
                Platform.runLater(() -> timeLabel.setText(getRemainingTime()));
                Thread.sleep(1000);
                remainingTime--;
                if (remainingTime <= 0) {
                    stopService();
                }
                if (remainingTime <= 900) {
                    stopOrder = true;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Chrono( Label timeLabel) {
        timeLabel = timeLabel;
    }
}