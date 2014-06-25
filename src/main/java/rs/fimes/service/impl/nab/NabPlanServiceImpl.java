package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.Application;
import rs.etf.rc.common.application.ComponentType;
import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.etf.rc.common.utils.StringUtil;
import rs.fimes.data.dao.api.nab.NabPlanDAO;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.service.api.nab.NabPlanServiceApi;

public class NabPlanServiceImpl extends BaseServiceImpl implements NabPlanServiceApi {
    
    private NabPlanDAO nabPlanDAO;

    public NabPlanServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = -4854802014857300768L;

    @Override
    public NabPlan createNabPlan(NabPlan noviPlan) {
       System.out.println(noviPlan);
   
        return nabPlanDAO.merge(noviPlan);
    }

    public NabPlanDAO getNabPlanDAO() {
        return nabPlanDAO;
    }

    public void setNabPlanDAO(NabPlanDAO nabPlanDao) {
        this.nabPlanDAO = nabPlanDao;
    }

  
}
