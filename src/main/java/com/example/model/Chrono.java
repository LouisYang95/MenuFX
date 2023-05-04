package com.example.model;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chrono {
    private int minutes;
    private int seconds;
    private final int MAX_DURATION = 25;
    private final int WARNING_THRESHOLD = 15;
    private final int duration;
    private final LocalDateTime endTime;
    private boolean canTakeOrder = true;


    public Chrono(int minutes, int duration) {
        this.minutes = minutes;
        this.duration = duration;
        this.seconds = 0;
        this.endTime = LocalDateTime.now().plusMinutes(duration);
    }

    public void start() {
        System.out.println("Votre service débute ...");
        System.out.println("Votre service débute : " + duration + " minutes");
        IntStream.rangeClosed(0, minutes * 60)
                .map(i -> minutes * 60 - i)
                .forEachRemaining(this::printTime);

        Stream.iterate(MAX_DURATION * 60, i -> i - 1)
                .limit(MAX_DURATION * 60 + 1)
                .map(i -> String.format("%02d:%02d", i / 60, i % 60))
                .peek(time -> {
                    if (time.equals(WARNING_THRESHOLD + ":00")) {
                        System.out.println("Alerte! il vous reste plus que 15 minutes avant la fin du service!");
                    }
                })
                .forEach(System.out::println);

        System.out.println("votre temps est écoulé.");
    }

    private void printTime(int remainingSeconds) {
        if (remainingSeconds == 15 * 60) {
            System.out.println("Plus de commande possible !");
        }
        minutes = remainingSeconds / 60;
        seconds = remainingSeconds % 60;
        System.out.printf("%02d:%02d\n", minutes, seconds);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

            System.out.println("Le service est terminé !");
        }
    }
}
