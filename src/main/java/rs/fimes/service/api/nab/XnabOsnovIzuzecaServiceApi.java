package rs.fimes.service.api.nab;

import java.util.List;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.XnabOsnovIzuzeca;

public interface XnabOsnovIzuzecaServiceApi extends BaseServiceApi {
    
    List<XnabOsnovIzuzeca> getAllOsnovIzuzeca();

}
