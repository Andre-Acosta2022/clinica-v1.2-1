package upeu.edu.pe.ms_paciente.specificacion;

import org.springframework.data.jpa.domain.Specification;
import upeu.edu.pe.ms_paciente.domain.Paciente;

import java.util.Set;

public class pacienteSpecificacion {
    public static Specification<Paciente> medicalSecureIdEqual(Long SeguroMedicoId){
        return (root, query, cb) -> SeguroMedicoId == null? cb.conjunction() :
                cb.equal(root.get("SeguroMedico").get("id"), SeguroMedicoId);
    }

    public static Specification<Paciente> personIdIn(Set<Long> personaIds){
        return (root, query, cb) -> personaIds == null? cb.conjunction() : root.get("personaId").in(personaIds);
    }

    public static Specification<Paciente> deletedEqual(Boolean deleted){
        return (root, query, cb) -> deleted == null? cb.conjunction() : cb.equal(root.get("deleted"), deleted);
    }
}
