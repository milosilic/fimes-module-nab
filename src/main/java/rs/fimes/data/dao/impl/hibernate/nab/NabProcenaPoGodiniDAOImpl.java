package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabProcenaPoGodiniDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabProcenaPoGodini;

public class NabProcenaPoGodiniDAOImpl extends BaseDaoImplHibernate<NabProcenaPoGodini, Integer>
implements NabProcenaPoGodiniDAO, Serializable {

    private static final long serialVersionUID = 1L;
    
    public NabProcenaPoGodiniDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }

    @Override
    public long countAllProcenaPoNabavci(NabJavnaNabavka izabranaNabavka) {
        return 0;
    }
    
    
    
}