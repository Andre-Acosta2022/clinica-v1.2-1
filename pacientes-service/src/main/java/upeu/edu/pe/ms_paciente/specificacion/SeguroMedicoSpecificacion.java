package upeu.edu.pe.ms_paciente.specificacion;

import org.springframework.data.jpa.domain.Specification;
import upeu.edu.pe.ms_paciente.domain.SeguroMedico;

public class SeguroMedicoSpecificacion {
    public static Specification<SeguroMedico> nameLike(String name){
        return (root, query, cb) -> name == null? cb.conjunction() : cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<SeguroMedico> deletedEqual(Boolean deleted){
        return (root, query, cb) -> deleted == null? cb.conjunction() : cb.equal(root.get("deleted"), deleted);
    }

}
