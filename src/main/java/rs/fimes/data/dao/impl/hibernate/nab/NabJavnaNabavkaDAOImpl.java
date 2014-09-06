package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabJavnaNabavkaDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabPlan;

public class NabJavnaNabavkaDAOImpl extends BaseDaoImplHibernate<NabJavnaNabavka, Integer>
implements NabJavnaNabavkaDAO, Serializable {

    private static final long serialVersionUID = 1L;
    
    public NabJavnaNabavkaDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }

    @Override
    public long countNabNabavkiIzPlana(NabPlan nabPlanSelected) {
        String search = "select count(jn) from NabJavnaNabavka jn "+
        " where jn.nabPlan = :nabPlan ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nabPlan", nabPlanSelected);
        return (Long) getSingleResult(search, params);

    }
    
    
    
}