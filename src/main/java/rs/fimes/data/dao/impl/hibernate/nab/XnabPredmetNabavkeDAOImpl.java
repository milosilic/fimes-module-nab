package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.XnabPredmetNabavkeDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.XnabPredmetNabavke;

public class XnabPredmetNabavkeDAOImpl extends BaseDaoImplHibernate<XnabPredmetNabavke, Integer> implements XnabPredmetNabavkeDAO, Serializable{

    public XnabPredmetNabavkeDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }

    private static final long serialVersionUID = -973374911847169117L;

    @Override
    public List<XnabPredmetNabavke> getXnabPredmetNabavkeList() {
        String sqlQuery = "select o from XnabPredmetNabavke o";
        Map<String, Object> params = new HashMap<String, Object>();
        List<XnabPredmetNabavke> lista = customSearch(sqlQuery, params);
        return lista;
    }

}
