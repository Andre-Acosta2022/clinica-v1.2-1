package upeu.edu.pe.ms_paciente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "upeu.edu.pe.ms_paciente.Client")
@EnableJpaAuditing
public class MsPacienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPacienteApplication.class, args);
	}

}
