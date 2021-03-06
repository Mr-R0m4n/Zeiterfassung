package Zeiterfassung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //create File
        final String path = "D:\\Programmieren\\IdeaProjects\\Zeiterfassung\\List.txt";
        new File(path);
        new FileOutputStream(path, true);


        Zeiterfassung zeiterfassung = new Zeiterfassung();
        boolean loop = true;
        Scanner scMenu = new Scanner(System.in);

        while (loop) {
            System.out.println("********************************");
            System.out.println("**********ZEITERFASSUNG*********");
            System.out.println("********************************");
            System.out.println("1 = Arbeitszeit erfassen");
            System.out.println("2 = Arbeitszeiten einsehen");
            System.out.println("3 = Arbeitszeit bearbeiten");
            System.out.println("4 = Geleistete Überstunden anzeigen");
            System.out.println("5 = Programm Beenden");

            switch (scMenu.nextInt()) {
                case 1 -> {
                    System.out.println();
                    try {
                        zeiterfassung.saveTime();
                    }
                    catch (IOException ioe){
                        System.out.println("Ihre Daten konnten nicht gespeichert werden");
                    }
                    catch (IndexOutOfBoundsException iobe) {
                        System.out.println("Ihre Eingabe ist ungültig");
                    }
                    System.out.println();
                }
                case 2 -> {
                    System.out.println();
                    zeiterfassung.display();
                    System.out.println();
                }
                case 3 -> {
                    System.out.println();
                    try {
                        zeiterfassung.changeTime();
                    }
                    catch (IOException | InputMismatchException | IndexOutOfBoundsException ioe) {
                        System.out.println("Ihre Eingabe ist ungültig");
                    }
                    System.out.println();
                }
                case 4 -> {
                    System.out.println();
                    try {
                        zeiterfassung.totalOvertime();
                    }
                    catch (IOException | InputMismatchException ioe) {
                        System.out.println("Ihre Eingabe ist ungültig");
                    }
                    System.out.println();
                }
                case 5 -> {
                    System.out.println("Bis zum nächsten mal!");
                    loop = false;
                    scMenu.close();
                }
                default -> {
                    System.out.println("Deine Eingabe entsprach nicht den Vorgaben");
                    System.out.println("Bitte versuch es erneut");
                    System.out.println();
                }
            }
        }
    }
}
