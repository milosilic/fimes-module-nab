package rs.fimes.service.api.nab;

import java.util.List;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.XnabVrstaPostupka;

public interface XnabVrstaPostupkaServiceApi extends BaseServiceApi {
    
    List<XnabVrstaPostupka> getAllVrstaPostupka();

    XnabVrstaPostupka getNabVrstaPostupkaNabavkeById(int idVrstaPostupkaNabavke);

}
