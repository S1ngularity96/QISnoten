package qis;
public class Main {
    public static void main(String[] args){
        // Das UI für das Programm initialisieren
        QISUi ui = new QISUi();
        // Simple UI- Steuerung
        if(args.length >= 3 && args.length <=3){
            if(Integer.parseInt(args[0]) == 1){
                ui.zeigeAlleNotenAn(args[1],args[2]);
            }else if(Integer.parseInt(args[0]) == 2){
                ui.zeigeDurchschnittAn(args[1],args[2]);
            }
            }else{
                ui.zeigeHilfeAn();
            }
    }
}
