package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabStatusNabavkeDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabStatusNabavke;


public class XnabStatusNabavkeDAOImpl extends
        BaseDaoImplHibernate<XnabStatusNabavke, Integer> implements
        XnabStatusNabavkeDAO, Serializable {

    private static final long serialVersionUID = 2875133599595888912L;

    public XnabStatusNabavkeDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
    }

    @Override
    public List<XnabStatusNabavke> getXnabStatusNabavkeList() {
        String sqlQuery = "select o from XnabStatusNabavke o";
        Map<String, Object> params = new HashMap<String, Object>();
        List<XnabStatusNabavke> lista = customSearch(sqlQuery, params);
        return lista;

    }

}
