package comperators;

import qis.QISModul;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Comperator für die Module
 */
public class ModulDateComperator implements Comparator <QISModul> {
    @Override
    public int compare(QISModul o1, QISModul o2) {
        Date dateo1;
        Date dateo2;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            dateo1 = sdf.parse(o1.getKlausurDatum());
            dateo2 = sdf.parse(o2.getKlausurDatum());
            if(dateo1.compareTo(dateo2) > 1){
                return 1;
            }else if(dateo1.compareTo(dateo2) <0) {
                return -1;
            }else{
                return 0;
            }
        } catch (ParseException e) {
            return 0;
        }

    }
}
