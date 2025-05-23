package upeu.edu.pe.ms_paciente.Client;

import feign.codec.ErrorDecoder;
import feign.FeignException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, feign.Response response) {
        if (response.status() == 404) {
            return new FeignException.NotFound("Error 404", response.request(), null, null);
        }
        return new Default().decode(methodKey, response);
    }
}
