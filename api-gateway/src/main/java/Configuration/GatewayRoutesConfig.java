package Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;

import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {
    @Value("${EUREKA_URL}")
    private String eurekaUrl;


    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ruta para el servicio de autenticación REST
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("lb://auth-service"))
                // Ruta para el servicio de citas REST
                .route("appointment-service", r -> r.path("/appointments/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("lb://appointment-service"))
                // Ruta para el Eureka Server (si lo necesitas)
                .route("eureka-server", r -> r.path("/eureka/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("https://" + eurekaUrl))
                .build();
    }
}
