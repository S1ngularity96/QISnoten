package qis;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class QISParser {

    String LOGIN_DOCUMENT_URL = "https://www.qis.fh-aachen.de/qisserver/rds?state=user&type=1&" +
            "category=auth.login&startpage=portal.vm&breadCrumbSource=portal&" +
            "asdf=(USERNAME)&" +
            "fdsa=(PASSWORD)&submit=Login+Prüfungsanmeldung+/+Adressänderung+";

    public ArrayList<QISModul> getDocumentByLogin(String user, String password){
        try {
            Connection.Response loginResponse = Jsoup.connect(LOGIN_DOCUMENT_URL.replace("(USERNAME)",user).replace("(PASSWORD)",password)).
                   method(Connection.Method.POST).execute();
            //Startseite von QIS, wenn angemeldet
            Document loginDoc = loginResponse.parse();
            String nextUrl = loginDoc.getElementsByAttributeValueContaining("href","https://www.qis.fh-aachen.de/qisserver/rds?state=notenspiegelStudent").attr("href");
            //Notenseite von QIS
            Document notenRedirect = Jsoup.connect(nextUrl).
                    cookies(loginResponse.cookies()).
                    get();
            //Weiterer Link für die Seite mit der Tabelle
            String notenUrl = notenRedirect.getElementsByAttributeValueContaining("href","asi=").last().attr("href");
            Document notenliste = Jsoup.connect(notenUrl).
                    cookies(loginResponse.cookies()).get();
            //Selektion der Notentabelle
            Element table = notenliste.select("table").last();
            //Zählen der Tabelleneinträge
            Elements tableRows =  table.getElementsByTag("tr");
            int TABLE_ROWS_SIZE = tableRows.size();
            ArrayList<QISModul> module = new ArrayList<QISModul>();
            for(int i = 0; i < TABLE_ROWS_SIZE; i++){
                try{
                    // Checken, ob es sich um ein Modul handelt
                    Integer.parseInt(tableRows.get(i).getElementsByTag("td").first().text());
                    Elements columns = tableRows.get(i).getElementsByTag("td");

                     int modulnr = Integer.parseInt(columns.get(0).text());
                     String modulbezeichnung = columns.get(1).text();
                     String SemesterBezeichnung = columns.get(2).text();
                     String klausurDatum = columns.get(3).text();
                     String note = columns.get(4).text();
                     String creditpoints = columns.get(5).text();
                     String versuch = columns.get(7).text();
                     //Modell für QIS
                     module.add(new QISModul(modulnr,modulbezeichnung,SemesterBezeichnung,
                            klausurDatum,note,creditpoints,versuch));
                }catch (Exception ex){
                    //ungültige Module werden übersprungen
                }
            }
            return module;
         }catch (IOException e) {
            System.err.println("Benutzername oder Passwort falsch eingetragen.\nAnsonsten prüfen Sie die Internetverbindung.");
            return null;
        }
    }
}
