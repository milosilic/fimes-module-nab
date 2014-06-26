package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.data.dao.api.nab.XnabPredmetNabavkeDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabPredmetNabavke;

public class XnabPredmetNabavkeDAOImpl extends BaseDaoImplHibernate<XnabPredmetNabavke, Integer> implements XnabPredmetNabavkeDAO, Serializable{

    public XnabPredmetNabavkeDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = -973374911847169117L;

}
