package rs.fimes.data.dao.impl.hibernate.nab;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    private static final long serialVersionUID = 7628644533614432926L;

    @Override
    public List<XnabVrstaPredmetaNabavke> getXnabVrstaPredmetaNabavkeList() {
      String sqlQuery = "select o from XnabVrstaPredmetaNabavke o";
      Map<String, Object> params = new HashMap<String, Object>();
      List<XnabVrstaPredmetaNabavke> lista = customSearch(sqlQuery, params);
        return lista;
    }


}
