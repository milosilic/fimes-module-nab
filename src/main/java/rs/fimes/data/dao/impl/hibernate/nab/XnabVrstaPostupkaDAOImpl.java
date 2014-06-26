package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabVrstaPostupkaDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabVrstaPostupka;

public class XnabVrstaPostupkaDAOImpl extends
        BaseDaoImplHibernate<XnabVrstaPostupka, Integer> implements
        XnabVrstaPostupkaDAO, Serializable {

    private static final long serialVersionUID = 2875133599595888912L;

    public XnabVrstaPostupkaDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
        // TODO Auto-generated constructor stub
    }

}
