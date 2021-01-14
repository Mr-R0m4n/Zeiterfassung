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
    private boolean loop;
    private String startTimeHours;
    private String startTimeMinutes;
    private String endTimeHours;
    private String endTimeMinutes;
    private String breakTime;

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
        this.loop = true;
        while (this.loop) {
            System.out.println();
            System.out.println("Bitte geben Sie das Datum des zu erfassenden Arbeitstages ein");
            System.out.println("(TT.MM.JJJJ): ");
            String date = sc.next();
            String year = date.substring(6);
            String month = date.substring(2,6);
            String day = date.substring(0,2);
            date =  year + month + day;
            System.out.println();
            System.out.println("Bitte geben Sie nun den Beginn Ihrer Arbeitszeit ein");
            System.out.println("(hh:mm): ");
            String startTime = sc.next();
            this.startTimeHours = startTime.replaceAll("[:]","").substring(0,2);
            this.startTimeMinutes = startTime.replaceAll("[:]","").substring(2);
            System.out.println();
            System.out.println("Bitte geben Sie nun das Ende Ihrer Arbeitszeit ein");
            System.out.println("(hh:mm): ");
            String endTime = sc.next();
            this.endTimeHours = endTime.replaceAll("[:]","").substring(0,2);
            this.endTimeMinutes = endTime.replaceAll("[:]","").substring(2);
            System.out.println();
            System.out.println("Möchten Sie Ihre Pausenzeit manuell oder automatisch (30min) erfassen?");
            System.out.println("m = Manuell");
            System.out.println("a = Automatisch");
            switch (sc.next()) {
                case "m" -> {
                    System.out.println();
                    System.out.println("Bitte geben Sie Ihre Pausenzeit in Minuten ein: ");
                    this.breakTime = sc.next();
                    this.allLines.add("Datum: " + date + " / Arbeitsbeginn: " + startTime + " - Arbeitsende: " + endTime + " / Pausendauer: " + this.breakTime + " min" + overtimeCalc());
                    System.out.println();
                }
                case "a" -> {
                    System.out.println();
                    System.out.println("30 Minuten wurden Ihrer Pausenzeit hinzugefügt");
                    System.out.println();
                    this.breakTime = "30";
                    this.allLines.add("Datum: " + date + " / Arbeitsbeginn: " + startTime + " - Arbeitsende: " + endTime + " / Pausendauer: " + "30 min" + overtimeCalc());
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

    public void changeTime(){
        System.out.println();
        display();
        System.out.println("Bitte geben Sie die Zeile an die Sie bearbeiten möchten: ");
        this.allLines.remove(sc.nextInt()-1);
        try {
            saveTime();
        }
        catch (IOException ioe){
            System.out.println("bla bla bla");
        }
    }

    public String overtimeCalc(){
        int startTimeHours = Integer.parseInt(this.startTimeHours);
        int startTimeMinutes = Integer.parseInt(this.startTimeMinutes);
        int endTimeHours = Integer.parseInt(this.endTimeHours);
        int endTimeMinutes = Integer.parseInt(this.endTimeMinutes);
        int breakTime = Integer.parseInt(this.breakTime);
        int overtimeCalc = ((endTimeHours - startTimeHours) * 60 + (endTimeMinutes - startTimeMinutes)) - breakTime - 480;
        int overtimeHours = overtimeCalc/60;
        int overtimeMinutes = overtimeCalc%60;
        return " / Überstunden: " + overtimeHours + " Stunden & " + overtimeMinutes + " Minuten";
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
