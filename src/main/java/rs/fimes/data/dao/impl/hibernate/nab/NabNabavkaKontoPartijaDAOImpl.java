package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.nab.NabNabavkaKontoPartijaDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabNabavkaKontoPartija;
import rs.fimes.domain.nab.XnabKonto;

public class NabNabavkaKontoPartijaDAOImpl extends BaseDaoImplHibernate<NabNabavkaKontoPartija, Integer>
implements NabNabavkaKontoPartijaDAO, Serializable {

    private static final long serialVersionUID = 1L;
    
    public NabNabavkaKontoPartijaDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);

    }

    @Override
    public long countAllKontoPoNabavci(NabJavnaNabavka izabranaNabavka) {
        String search = "select count(jn) from NabNabavkaKontoPartija jn "+
        " where jn.nabJavnaNabavka = :nabJavnaNabavka ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nabJavnaNabavka", izabranaNabavka);
        return (Long) getSingleResult(search, params);
    }

    @Override
    public long countAllPoXnabKontu(XnabKonto xnabKontoSelected) {
        String search = "select count(jn) from NabNabavkaKontoPartija jn "+
        " where jn.nabKonto = :nabKonto ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nabKonto", xnabKontoSelected);
        return (Long) getSingleResult(search, params);
    }
    
    
    
}