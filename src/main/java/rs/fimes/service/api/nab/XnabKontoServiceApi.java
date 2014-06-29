package rs.fimes.service.api.nab;

import java.util.List;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.XnabKonto;

public interface XnabKontoServiceApi extends BaseServiceApi {
    
    List<XnabKonto> getAllKonto();

}
