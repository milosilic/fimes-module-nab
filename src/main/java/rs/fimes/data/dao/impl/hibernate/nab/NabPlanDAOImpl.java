package rs.fimes.data.dao.impl.hibernate.nab;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import rs.etf.rc.common.application.Application;
import rs.etf.rc.common.application.ComponentType;
import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabPlanDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.data.dao.generic.ColumnAgregateProperty;
import rs.fimes.data.dao.generic.OrderBy;
import rs.fimes.data.dao.generic.QueryJoin;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.domain.nab.NabPlan;

public class NabPlanDAOImpl  extends BaseDaoImplHibernate<NabPlan, Integer> implements NabPlanDAO{

    /**
     * 
     */
    private static final long serialVersionUID = 2707402715808005514L;

    public NabPlanDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
        // TODO Auto-generated constructor stub
    }
}
