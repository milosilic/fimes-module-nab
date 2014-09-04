package rs.fimes.service.api.nab;

import java.util.List;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;

public interface XnabVrstaPredmetaNabavkeServiceApi extends BaseServiceApi {
    
    List<XnabVrstaPredmetaNabavke> getAllVrstaPredmetaNabavke();

    XnabVrstaPredmetaNabavke findById(int idVrstaPredmetaNabavke);

}
