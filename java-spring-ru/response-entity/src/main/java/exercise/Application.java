package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<Post>> index() {

        return ResponseEntity.status(HttpStatus.OK)
            .header("X-Total-Count", String.valueOf(posts.size()))
            .body(posts);
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        Optional<Post> post = posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        if (post.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                post.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<Post> create(@RequestBody Post maybePost) {
        Post post = new Post();
        post.setId(maybePost.getId());
        post.setBody((maybePost.getBody()));
        post.setTitle(maybePost.getTitle());
        posts.add(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);

    }

    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post maybePost) {
        Optional<Post> existingPostOptional = posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();

        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();
            existingPost.setId(maybePost.getId());
            existingPost.setBody(maybePost.getBody());
            existingPost.setTitle(maybePost.getTitle());

            return ResponseEntity.status(HttpStatus.OK).body(existingPost);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(maybePost);
        }
    }


    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
