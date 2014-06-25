package rs.fimes.web.datamodel.api.nab;

import rs.fimes.data.dao.api.nab.NabPlanDAO;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.web.datamodel.FimesExtendedDataTableModelApi;

public interface NabPlanExtendedDataTableModelApi extends
        FimesExtendedDataTableModelApi<NabPlanDAO, NabPlan, Integer> {

}
