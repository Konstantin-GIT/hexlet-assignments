package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post post: posts) {
            List<Comment> comments = commentRepository.findByPostId(post.getId());
            postsDTO.add(postToDTO(post, comments));
        }
        return postsDTO;

    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        List<Comment> comments = commentRepository.findByPostId(id);
        return postToDTO(post, comments);
    }


    private List<CommentDTO> commentsToDTO(List<Comment> comments) {
        List<CommentDTO> result = new ArrayList<>();
        for (Comment comment : comments) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        result.add(commentDTO);
        }
        return result;
    }

    private PostDTO postToDTO(Post post, List<Comment> comments) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());
        postDTO.setComments(commentsToDTO(comments));
        return postDTO;
    }


}
// END
