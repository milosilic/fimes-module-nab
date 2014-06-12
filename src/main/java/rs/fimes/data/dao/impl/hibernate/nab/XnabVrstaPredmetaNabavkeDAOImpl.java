package rs.fimes.data.dao.impl.hibernate.nab;


import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabVrstaPredmetaNabavkeDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;

public class XnabVrstaPredmetaNabavkeDAOImpl extends
BaseDaoImplHibernate<XnabVrstaPredmetaNabavke, Integer> implements
        XnabVrstaPredmetaNabavkeDAO {

    public XnabVrstaPredmetaNabavkeDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 7628644533614432926L;


}
