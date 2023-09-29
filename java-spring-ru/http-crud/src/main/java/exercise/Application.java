package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping(path = "/posts")
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit,
                            @RequestParam(defaultValue = "1") Integer page) {

        int pageSize = limit;
        int pageNumber = Math.max(page, 1); // Убедиться, что номер страницы не меньше 1
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, posts.size()); // Убедиться, что endIndex не выходит за границы коллекции
        return posts.subList(startIndex, endIndex);
    }

    @PostMapping(path = "/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @GetMapping(path = "/posts/{id}")
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream()
            .filter(p -> p.getSlug().equals(id))
            .findFirst();
        return post;
    }

    @PutMapping(path = "posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
            .filter(p -> p.getSlug().equals(id))
            .findFirst();
        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setSlug(data.getSlug());
            post.setBody(data.getBody());
            post.setTitle(data.getTitle());
        }
        return data;
    }

    @DeleteMapping(path = "/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getSlug().equals(id));
    }
    // END
}
