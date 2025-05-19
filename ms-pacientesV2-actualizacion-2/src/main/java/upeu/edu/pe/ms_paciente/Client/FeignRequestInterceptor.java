package upeu.edu.pe.ms_paciente.Client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        template.header("Authorization", "Bearer " + token);
    }
}
