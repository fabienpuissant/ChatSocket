package chat.service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<CommentEntity> getAll() {
		return commentRepository.findAll();	}

	public CommentEntity getCommentById(int id) {
		return commentRepository.findById(id);
	}

	public void addComment(CommentEntity comment) {
		commentRepository.save(comment);
	}

	public void updateComment(CommentEntity comment) {
		commentRepository.save(comment);
	}

	public void deleteComment(int id) {
		commentRepository.delete(commentRepository.findById(id));
	}

	public CommentEntity getCommentByDescription(String description) {
		return commentRepository.findOneByDescription(description);
	}

	public List<CommentEntity> findByName(String roomName) {
		return commentRepository.findByRoom(roomName);
	}

}