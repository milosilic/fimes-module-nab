package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabKriterijumDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabKriterijum;


public class XnabKriterijumDAOImpl extends
        BaseDaoImplHibernate<XnabKriterijum, Integer> implements
        XnabKriterijumDAO, Serializable {

    private static final long serialVersionUID = 2875133599595888912L;

    public XnabKriterijumDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
    }

    @Override
    public List<XnabKriterijum> getXnabKriterijumList() {
        String sqlQuery = "select o from XnabKriterijum o";
        Map<String, Object> params = new HashMap<String, Object>();
        List<XnabKriterijum> lista = customSearch(sqlQuery, params);
        return lista;

    }

}
