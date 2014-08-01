package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabOsnovIzuzecaDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabOsnovIzuzeca;

public class XnabOsnovIzuzecaDAOImpl extends
        BaseDaoImplHibernate<XnabOsnovIzuzeca, Integer> implements
        XnabOsnovIzuzecaDAO, Serializable {

    private static final long serialVersionUID = 2875133599595888912L;

    public XnabOsnovIzuzecaDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
    }

    @Override
    public List<XnabOsnovIzuzeca> getXnabOsnovIzuzecaList() {
        String sqlQuery = "select o from XnabOsnovIzuzeca o";
        Map<String, Object> params = new HashMap<String, Object>();
        List<XnabOsnovIzuzeca> lista = customSearch(sqlQuery, params);
        return lista;

    }

}
