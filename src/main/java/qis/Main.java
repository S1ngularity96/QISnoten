package qis;

import java.io.Console;

public class Main {
    public static void main(String[] args){
        // Das UI für das Programm initialisieren
        QISUi ui = new QISUi();
        // Simple UI- Steuerung
        if(args.length >= 1 && args.length <=1){
            if(Integer.parseInt(args[0]) == 1){
                System.out.println("Ausgabe der Noten aus dem QIS-Prüfungssystem");
                Console console = System.console();
                String username = console.readLine("Benutzername: ");
                String password = new String(console.readPassword("Passwort: "));
                ui.zeigeAlleNotenAn(username,password);
            }else if(Integer.parseInt(args[0]) == 2){
                Console console = System.console();
                String username = console.readLine("Benutzername: ");
                String password = new String(console.readPassword("Passwort: "));
                ui.zeigeDurchschnittAn(username,password);
            }
            }else{
                ui.zeigeHilfeAn();
            }
    }
}
