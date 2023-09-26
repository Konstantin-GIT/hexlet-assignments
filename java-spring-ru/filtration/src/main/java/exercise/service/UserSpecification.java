package exercise.service;

import exercise.model.User;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecification implements Specification<User> {

    private SearchCriteria searchCriteria;

    public UserSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // BEGIN
        return criteriaBuilder.like(criteriaBuilder.lower(root.get(searchCriteria.getKey())),
            "%" + searchCriteria.getValue().toString().toLowerCase() + "%");
        // END
    }
}