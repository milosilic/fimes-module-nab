package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabJrnDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabJrn;


public class XnabJrnDAOImpl extends
        BaseDaoImplHibernate<XnabJrn, Integer> implements
        XnabJrnDAO, Serializable {

    private static final long serialVersionUID = 3909094991391868593L;

    public XnabJrnDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
    }

    @Override
    public List<XnabJrn> getXnabJrnList() {
        String sqlQuery = "select o from XnabJrn o";
        Map<String, Object> params = new HashMap<String, Object>();
        List<XnabJrn> lista = customSearch(sqlQuery, params);
        return lista;

    }

}
