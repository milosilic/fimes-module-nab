package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabKontoDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabKonto;


public class XnabKontoDAOImpl extends
        BaseDaoImplHibernate<XnabKonto, Integer> implements
        XnabKontoDAO, Serializable {

    private static final long serialVersionUID = 2875133599595888912L;

    public XnabKontoDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
    }

    @Override
    public List<XnabKonto> getXnabKontoList() {
        String sqlQuery = "select o from XnabKonto o";
        Map<String, Object> params = new HashMap<String, Object>();
        List<XnabKonto> lista = customSearch(sqlQuery, params);
        return lista;

    }

}
