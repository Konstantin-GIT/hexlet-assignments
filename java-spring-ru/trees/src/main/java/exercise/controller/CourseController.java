package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public List<Course>  getPreviousCourses(@PathVariable long id) {
        List<Course> result = new ArrayList<>();
        String coursePath = getCourse(id).getPath();
        if (coursePath == null || coursePath.isEmpty()) { return result; }

        String[] parentsId = coursePath.split("\\.");

        return Arrays.stream(parentsId)
            .map(parentId -> getCourse(Long.valueOf(parentId)))
            .collect(Collectors.toList());

    }
    // END

}
