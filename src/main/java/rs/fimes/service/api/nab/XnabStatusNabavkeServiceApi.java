package rs.fimes.service.api.nab;

import java.util.List;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.XnabStatusNabavke;

public interface XnabStatusNabavkeServiceApi extends BaseServiceApi {

    List<XnabStatusNabavke> getAllStatusNabavke();

}
