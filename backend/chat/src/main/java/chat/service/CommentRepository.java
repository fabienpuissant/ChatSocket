package chat.service;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import chat.service.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {

	 public List<CommentEntity> findAll();

	 public CommentEntity findById(int id);

	public CommentEntity findOneByDescription(String Description);

	public List<CommentEntity> findByRoom(String roomName);

}