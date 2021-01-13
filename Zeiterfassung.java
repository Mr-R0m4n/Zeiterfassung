package Zeiterfassung;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Zeiterfassung {

    //Attributes
    Scanner sc = new Scanner(System.in);
    private final String path = "D:\\Programmieren\\IdeaProjects\\Zeiterfassung\\List.txt";
    private final FileOutputStream fos = new FileOutputStream(path, true);
    private final ArrayList<String> allLines = new ArrayList<>(Files.readAllLines(Paths.get(path)));
    private boolean loop = true;

    //Constructors
    public Zeiterfassung() throws IOException {
    }

    //Methods
    public void display() {
        //Display all tasks
        if(this.allLines.isEmpty()){
            System.out.println("Die Datei enthält keine Aufgaben");
        }
        int index = 1;
        for (String line : this.allLines) {
            System.out.println(index + ". " + line);
            index++;
        }
    }

    public void saveTime() throws IOException {
        while (loop) {
            System.out.println();
            System.out.println("Bitte geben Sie das Datum des zu erfassenden Arbeitstages ein");
            System.out.println("(JJJJ.MM.TT): ");
            String date = sc.next();
            System.out.println();
            System.out.println("Bitte geben Sie nun den Beginn Ihrer Arbeitszeit ein");
            System.out.println("(hh:mm): ");
            int startTimeHour = sc.nextInt();
            System.out.print( startTimeHour  + " : " + startTimeHour );
            int startTimeMinutes = sc.nextInt();
            System.out.println();
            System.out.println("Bitte geben Sie nun das Ende Ihrer Arbeitszeit ein");
            System.out.println("(hh:mm): ");
            String endTime = sc.next();
            System.out.println();
            System.out.println("Möchten Sie Ihre Pausenzeit manuell oder automatisch (30min) erfassen?");
            System.out.println("m = Manuell");
            System.out.println("a = Automatisch");
            switch (sc.next()) {
                case "m" -> {
                    System.out.println();
                    System.out.println("Bitte geben Sie Ihre Pausenzeit in Minuten ein: ");
                    String breakTime = sc.next();
                    this.allLines.add("Datum: " + date + " / Arbeitsbeginn: " + startTimeHour + " - Arbeitsende: " + endTime + " / Pausendauer: " + breakTime + " min");
                    System.out.println();
                }
                case "a" -> {
                    System.out.println();
                    System.out.println("30 Minuten wurden Ihrer Pausenzeit hinzugefügt");
                    System.out.println();
                    this.allLines.add("Datum: " + date + " / Arbeitsbeginn: " + startTimeHour + " - Arbeitsende: " + endTime + " / Pausendauer: " + "30 min");
                }
                default -> {
                    System.out.println();
                    System.out.println("Ihre Eingabe war ungültig");
                    System.out.println();
                }
            }
            Collections.sort(this.allLines);
            Files.write(Paths.get(path),this.allLines);
            redo();
        }
    }

    public void redo() {
        //Redo loop?
        System.out.println();
        System.out.println("Möchtest du den Vorgang wiederholen? ");
        System.out.println("J = Ja");
        System.out.println("N = Nein");
        System.out.println();
        //Exit loop
        if (!sc.next().contains("j")) {
            loop = false;
        }
        System.out.println();
    }

    //Exceptions
    //Getter&Setter

}
