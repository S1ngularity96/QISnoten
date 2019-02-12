package qis;

/**
 * Model für ein einzelnen QIS-Modul
 */
public class QISModul {

    private int modulnr;
    private String modulbezeichnung;
    private String SemesterBezeichnung;
    private String klausurDatum;
    private String note;
    private String creditpoints;
    private String versuch;


    public QISModul(int modulnr, String modulbezeichnung, String semesterBezeichnung, String klausurDatum, String note, String creditpoints, String versuch) {
        this.modulnr = modulnr;
        this.modulbezeichnung = modulbezeichnung;
        SemesterBezeichnung = semesterBezeichnung;
        this.klausurDatum = klausurDatum;
        this.note = note;
        this.note = note;
        this.creditpoints = creditpoints;
        this.versuch = versuch;
    }

    public int getModulnr() {
        return modulnr;
    }

    public String getModulbezeichnung() {
        return modulbezeichnung;
    }

    public String getSemesterBezeichnung() {
        return SemesterBezeichnung;
    }

    public String getKlausurDatum() {
        return klausurDatum;
    }

    public double getNote() {
        if(this.note.length() == 0){
            return 0.0;
        }
        return Double.parseDouble(note.replace(',','.'));
    }

    public double getCreditpoints() {
        return Double.parseDouble(creditpoints.replace(',','.'));
    }

    public double ECTmitNote(){
        return getNote() * getCreditpoints();
    }

    public String getVersuch(){
        return this.versuch;
    }

    @Override
    public String toString() {
        return  "\n\nModul:" + modulbezeichnung + '\n' +
                "Datum:" + klausurDatum + '\n' +
                "Note:" + note + '\n' +
                "ECTS:" + creditpoints + '\n' +
                "Versuch:" + versuch;
    }
}
