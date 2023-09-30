package exercise.repository;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN

    Iterable<Comment> findAllByPostId(long id);

    Optional<Comment> findByPostIdAndId(long postId, long id);

    void deleteByPostIdAndId(long postId, long id);


    // END
}
