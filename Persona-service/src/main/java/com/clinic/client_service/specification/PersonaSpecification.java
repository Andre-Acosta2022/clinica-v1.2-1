package com.clinic.client_service.specification;

import com.clinic.client_service.domain.Genero;
import com.clinic.client_service.domain.Persona;
import org.springframework.data.jpa.domain.Specification;

public class PersonaSpecification {
    public static Specification<Persona> nameLike(String name) {
        return (root, query, cb) -> name == null? cb.conjunction() : cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Persona> fullNameLike(String fullName) {
        return (root, query, cb) -> fullName == null? cb.conjunction() : cb.like(cb.concat(cb.concat(root.get("surname")
                                , " "),
                        root.get("name")),
                "%" + fullName + "%");
    }

    public static Specification<Persona> surnameLike(String surname) {
        return (root, query, cb) -> surname == null? cb.conjunction() : cb.like(root.get("surname"), "%" + surname + "%");
    }

    public static Specification<Persona> dniLike(String dni) {
        return (root, query, cb) -> dni == null? cb.conjunction() : cb.like(root.get("dni"), "%" + dni + "%");
    }

    public static Specification<Persona> genderEqual(Genero gender){
        return (root, query, cb) -> gender == null? cb.conjunction() : cb.equal(root.get("gender"), gender);
    }

    public static Specification<Persona> deletedEqual(Boolean deleted){
        return (root, query, cb) -> deleted == null? cb.conjunction() : cb.equal(root.get("deleted"), deleted);
    }
}
