package rs.fimes.data.dao.api.nab;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabPlan;

public interface NabJavnaNabavkaDAO extends BaseDaoApi<NabJavnaNabavka, Integer> {

    long countNabNabavkiIzPlana(NabPlan nabPlanSelected);



    
}
