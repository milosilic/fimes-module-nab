package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.service.exception.FimesServiceException;

public interface NabPlanServiceApi extends BaseServiceApi {

    NabPlan createNabPlan(NabPlan noviPlan);

    void deleteNabPlan(NabPlan nabPlanSelected) throws FimesServiceException;
    
    //implementirati funkciju da se vraćaju svi planovi koji postoje u sistemu
    
}
