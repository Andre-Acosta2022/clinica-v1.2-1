package com.clinic.client_service.specification;

import com.clinic.client_service.domain.Genero;
import com.clinic.client_service.domain.persona;
import org.springframework.data.jpa.domain.Specification;

public class PersonaSpecification {
    public static Specification<persona> nameLike(String name) {
        return (root, query, cb) -> name == null? cb.conjunction() : cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<persona> fullNameLike(String fullName) {
        return (root, query, cb) -> fullName == null? cb.conjunction() : cb.like(cb.concat(cb.concat(root.get("surname")
                                , " "),
                        root.get("name")),
                "%" + fullName + "%");
    }

    public static Specification<persona> surnameLike(String surname) {
        return (root, query, cb) -> surname == null? cb.conjunction() : cb.like(root.get("surname"), "%" + surname + "%");
    }

    public static Specification<persona> dniLike(String dni) {
        return (root, query, cb) -> dni == null? cb.conjunction() : cb.like(root.get("dni"), "%" + dni + "%");
    }

    public static Specification<persona> genderEqual(Genero gender){
        return (root, query, cb) -> gender == null? cb.conjunction() : cb.equal(root.get("gender"), gender);
    }

    public static Specification<persona> deletedEqual(Boolean deleted){
        return (root, query, cb) -> deleted == null? cb.conjunction() : cb.equal(root.get("deleted"), deleted);
    }
}
