package rs.fimes.web.datamodel.impl.nab;

import rs.fimes.data.dao.api.nab.NabPlanDAO;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.web.datamodel.FimesExtendedDataTableModelImpl;
import rs.fimes.web.datamodel.api.nab.NabPlanExtendedDataTableModelApi;

public class NabPlanExtendedDataTableModelImpl extends
        FimesExtendedDataTableModelImpl<NabPlanDAO, NabPlan, Integer> implements
        NabPlanExtendedDataTableModelApi {

    private static final long serialVersionUID = 2533327380034952021L;

}
