package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabUgovorDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabUgovor;

public class NabUgovorDAOImpl extends BaseDaoImplHibernate<NabUgovor, Integer>
implements NabUgovorDAO, Serializable {

    private static final long serialVersionUID = 1L;
    
    public NabUgovorDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }
    
    
    
}