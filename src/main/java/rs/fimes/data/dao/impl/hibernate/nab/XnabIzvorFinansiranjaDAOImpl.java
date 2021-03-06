package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabIzvorFinansiranjaDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabIzvorFinansiranja;

public class XnabIzvorFinansiranjaDAOImpl extends
        BaseDaoImplHibernate<XnabIzvorFinansiranja, Integer> implements
        XnabIzvorFinansiranjaDAO, Serializable {

    private static final long serialVersionUID = 2875133599595888912L;

    public XnabIzvorFinansiranjaDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
    }

    @Override
    public List<XnabIzvorFinansiranja> getXnabIzvorFinansiranjaList() {
        String sqlQuery = "select o from XnabIzvorFinansiranja o";
        Map<String, Object> params = new HashMap<String, Object>();
        List<XnabIzvorFinansiranja> lista = customSearch(sqlQuery, params);
        return lista;

    }

}
