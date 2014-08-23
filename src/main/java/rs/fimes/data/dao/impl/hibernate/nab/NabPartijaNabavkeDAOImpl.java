package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabPartijaNabavkeDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabPartijaNabavke;

public class NabPartijaNabavkeDAOImpl extends BaseDaoImplHibernate<NabPartijaNabavke, Integer>
implements NabPartijaNabavkeDAO, Serializable {

    private static final long serialVersionUID = 1L;
    
    public NabPartijaNabavkeDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }

    @Override
    // po uzoru na OrgGrupaRadnihMestaDAOImpl.dohvatiGrupeSaVecimIliJednakimRedonimBrojem
    public List<NabPartijaNabavke> dohvatiPartijeNabavke(Integer idJavnaNabavka) {

        String searchQuery = 
                "SELECT o FROM NabPartijaNabavke o " + 
                " WHERE o.nabJavnaNabavka.idJavnaNabavka = :idJavnaNabavka ";
                
            
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("idJavnaNabavka", idJavnaNabavka.intValue());                
            
            searchQuery += " ORDER BY o.nabJavnaNabavka.idJavnaNabavka ";
            return customSearch(searchQuery, params);
    }
    
    
    
}