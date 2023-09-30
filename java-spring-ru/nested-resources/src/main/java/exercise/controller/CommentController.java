package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path="/{postId}/comments")
    public Iterable<Comment> getAllCommentsByPostId(@PathVariable long postId) {
       return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path="/{postId}/comments/{commentId}")
    public Comment getAllCommentsByPostId(@PathVariable Long postId, @PathVariable Long commentId) {
        var comment = commentRepository.findByPostIdAndId(postId, commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        return comment;
    }

    @PostMapping(path = "/{postId}/comments")
    public CommentDto create(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        var post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(commentDto.content());
        comment.setPost(post);
        commentRepository.save(comment);

        post.getComments().add(comment);
        postRepository.save(post);
        return commentDto;
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public CommentDto update(@PathVariable long postId, @PathVariable long commentId,
                                       @RequestBody CommentDto commentDto) {

        var comment = commentRepository.findByPostIdAndId(postId, commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        comment.setContent(commentDto.content());
        commentRepository.save(comment);

        return commentDto;
    }

    @DeleteMapping(path="/{postId}/comments/{commentId}")
    public void delete(@PathVariable long postId, @PathVariable long commentId) {
        commentRepository.findByPostIdAndId(postId, commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.deleteByPostIdAndId(postId, commentId);
    }
    // END
}
