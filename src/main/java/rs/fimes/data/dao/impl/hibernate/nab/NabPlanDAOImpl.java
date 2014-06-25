package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabPlanDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabPlan;

public class NabPlanDAOImpl extends BaseDaoImplHibernate<NabPlan, Integer>
        implements NabPlanDAO, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2707402715808005514L;

    public NabPlanDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }
}
