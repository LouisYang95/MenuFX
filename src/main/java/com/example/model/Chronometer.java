package com.example.model;

public class Chronometer extends Thread{
    private  int value;

    public void run (){
        while (true){
            try {
                Thread.sleep(1000);
            }
        catch(InterruptedException e){
                break;
        }
            synchronized (this){
              value ++;
                System.out.println("Valeur du chrono : " + value);
            }
        }
    }
    public synchronized int getValue(){
        return value;
    }
}
 public class Main{
    public  static void main(String[]args) throws InterruptedException {
        //instance du chrono
        Chronometer chronometer= new Chronometer();

        // Demarrage du Thread
        chronometer.start();

        //Boucle pour verifier la valeur du chrono
        while (true){
            int value = chronometer.getValue();
            System.out.println("valeur actuelle de chronomètre : " + value);
            Thread.sleep(500);
        }
    }
 }

 class Task extends Thread {
    private  int result;

    public void run(){
        //code de la tache a executer
        System.out.println("Execution de la tache: " + Thread.currentThread().getName());
        result = 42;
    }
    public int getResult(){
        return  result;
    }
 }
 public class Main {
    public static void main (String[]arg) throws  InterruptedException{
        //instance du chrono
        Task task = new Task();

        // Demarrage du Thread
        task.start();
        // Attente de la fin du thraed
        task.join();
        // utilisation de la valeur retournee par le thread
        int result = task.getResult();
        System.out.println("Resultat du thread :" + result);
        //afichage du nom
        System.out.println("Nom du thread :" + Thread.currentThread().getName());
    }
 }
