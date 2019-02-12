package qis;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QISCalc {
    private ArrayList<QISModul> qis_module = null;


    public QISCalc(ArrayList<QISModul> qis_module){
        this.qis_module = qis_module;
    }

    /**
     * Überprüft, ob Module existieren
     * @return Existieren Module
     */
    public boolean ModulesExist(){
        return (this.qis_module != null);
    }

    /**
     * Rechnet den Durchschnitt aus
     * @return
     */
    public double durchschnitt(){
        double SUMME_ECT_MAL_NOTE = 0.0;
        double SUMME_ECT = 0.0;
        double durchschnitt = 0.0;
        if(ModulesExist()){
        for( QISModul module : qis_module){
            if(module.getModulnr() != 1000 && (module.getNote() != 5.0 || module.getNote() != 0.0)){
                SUMME_ECT_MAL_NOTE += module.ECTmitNote();
                SUMME_ECT += module.getCreditpoints();
            }
        }
        }
        return  Math.round(SUMME_ECT_MAL_NOTE/SUMME_ECT * 100) / 100.0;
    }
}
