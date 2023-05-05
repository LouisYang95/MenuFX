package com.example.model;

public class Chrono extends Thread {
    private boolean stop;
    private int remainingSeconds;

    public Chrono(int durationMinutes) {
        this.remainingSeconds = durationMinutes * 60;
        this.stop = false;
    }

    public void run() {
        while (!stop) {
            try {
                Thread.sleep(1000);
                remainingSeconds--;
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

    public boolean canTakeOrder() {
        return this.remainingSeconds > 15 * 60;
    }

    public static void main(String[] args) {
        Chrono chronometer = new Chrono(25);
        chronometer.start();

        while (!chronometer.stop) {
            try {
                Thread.sleep(1000);
                if (!chronometer.canTakeOrder()) {
                    System.out.println("Fin du service.");
                } else {
                    System.out.println("Service en cours.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}