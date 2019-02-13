package qis;
import comperators.ModulDateComperator;
import java.util.ArrayList;

/**
 * Die View für die Konsole
 */
public class QISUi {
    public void zeigeAlleNotenAn(String user, String password){
        QISParser qis = new QISParser();
        ArrayList<QISModul> module = qis.getDocumentByLogin(user,password);
        module.sort(new ModulDateComperator());
        QISTable table = new QISTable();
        table.setShowVerticalLines(true);
        table.setHeaders("Modulbezeichnung","Datum","Semester","Note","ECTS","Versuch");
        for(QISModul modul :module){
                if(modul.getModulnr() != 1000 || modul.getNote() != 0.0){
                table.addRow(modul.getModulbezeichnung(),
                        modul.getKlausurDatum(),
                        modul.getSemesterBezeichnung(),
                        Double.toString(modul.getNote()),
                        Double.toString(modul.getCreditpoints()),
                        modul.getVersuch());
            }
        }
        table.print();
    }

    /**
     * Rechnet den Durchschnitt von QIS aus
     * @param user Benutzername
     * @param password Passwort
     */
    public void zeigeDurchschnittAn(String user, String password){
        QISParser qis = new QISParser();
        ArrayList<QISModul> module = qis.getDocumentByLogin(user,password);
        QISCalc qis_managment = new QISCalc(module);
        System.out.println("Ihr Durchschitt liegt bei: "+ qis_managment.durchschnitt());
    }

    /**
     * Manual für QISnoten
     */
    public void zeigeHilfeAn(){
        System.out.println("{MANUAL QISnoten}");
        System.out.println("[SYNOPSIS]\n\t<Nr>");
        System.out.println("[Optionen]\n\tNr.1. alle Noten anzeigen\n" +
                "\tNr.2. Durchschnitt anzeigen\n");
    }
}
