package rs.fimes.service.api.nab;

import java.util.List;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.XnabJrn;

public interface XnabJrnServiceApi extends BaseServiceApi {
    
    List<XnabJrn> getAllJrn();

}
