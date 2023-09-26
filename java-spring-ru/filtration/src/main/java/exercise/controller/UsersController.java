package exercise.controller;
import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;


// Зависимости для самостоятельной работы
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN


    @GetMapping("")
    public Iterable<User> getUsers(
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName
    ) {
        List<Specification> specifications = new ArrayList<>();
        if (firstName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<String>("firstName", firstName)));
        }
        if (lastName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<String>("lastName", lastName)));
        }

        Specification<User> result = specifications.stream()
            .reduce(null, (specificationResult, specification) -> {
                if (specificationResult == null) {
                    return specification;
                }
                return specificationResult.and(specification);
            });

        if (!specifications.isEmpty()) {
            return this.userRepository.findAll(result);
        }
        return this.userRepository.findAll();
    }

/*
    @GetMapping("")
    public Iterable<User> getUsers(
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName
    ) {

        if (firstName == null && lastName == null) {
            return this.userRepository.findAll();
        }

        if (lastName == null) {
            return this.userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
        }
        if (firstName == null) {
            return this.userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
        }

        return this.userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName)
            .and(QUser.user.lastName.containsIgnoreCase(lastName)));
    }
*/
/*
    @GetMapping("")
    public Iterable<User> getUsers(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return this.userRepository.findAll(predicate);
  }
*/

}
