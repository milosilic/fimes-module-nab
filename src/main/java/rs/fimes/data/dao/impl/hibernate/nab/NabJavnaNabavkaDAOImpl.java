package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabJavnaNabavkaDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabJavnaNabavka;

public class NabJavnaNabavkaDAOImpl extends BaseDaoImplHibernate<NabJavnaNabavka, Integer>
implements NabJavnaNabavkaDAO, Serializable {

    private static final long serialVersionUID = 1L;
    
    public NabJavnaNabavkaDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }
    
    
    
}