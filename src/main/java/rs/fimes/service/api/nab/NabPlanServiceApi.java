package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabPlan;

public interface NabPlanServiceApi extends BaseServiceApi {

    NabPlan createNabPlan(NabPlan noviPlan);

    //implementirati funkciju da se vraćaju svi planovi koji postoje u sistemu
    
}
