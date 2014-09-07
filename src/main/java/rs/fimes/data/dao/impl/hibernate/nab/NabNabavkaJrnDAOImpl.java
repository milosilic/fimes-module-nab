package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabNabavkaJrnDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabNabavkaJrn;

public class NabNabavkaJrnDAOImpl extends BaseDaoImplHibernate<NabNabavkaJrn, Integer>
implements NabNabavkaJrnDAO, Serializable {

    private static final long serialVersionUID = 1L;
    
    public NabNabavkaJrnDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }

    @Override
    // po uzoru na OrgGrupaRadnihMestaDAOImpl.dohvatiGrupeSaVecimIliJednakimRedonimBrojem
    public List<NabNabavkaJrn> dohvatiNabavkaJrn(Integer idJavnaNabavka) {

        String searchQuery = 
                "SELECT o FROM NabNabavkaJrn o " + 
                " WHERE o.nabJavnaNabavka.idJavnaNabavka = :idJavnaNabavka ";
                
            
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("idJavnaNabavka", idJavnaNabavka.intValue());                
            
            searchQuery += " ORDER BY o.nabJavnaNabavka.idJavnaNabavka ";
            return customSearch(searchQuery, params);
    }

    @Override
    public long countAllJrnByNabavka(NabJavnaNabavka izabranaNabavka) {
        String search = "select count(jn) from NabNabavkaJrn jn "+
        " where jn.nabJavnaNabavka = :nabJavnaNabavka ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nabJavnaNabavka", izabranaNabavka);
        return (Long) getSingleResult(search, params);
    }
    
    
    
}