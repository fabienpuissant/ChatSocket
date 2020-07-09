package chat.service;
import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CommentRestController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("CommentService/getAll")
	public List<CommentEntity> getAll(){
		return commentService.getAll();
	}
	
	@GetMapping("CommentService/getByRoom/{roomName}")
	public List<CommentEntity> getByRoom(@PathVariable String roomName){
		return commentService.findByName(roomName);
	}

	@GetMapping("CommentService/{id}")
	public CommentEntity getUserById(@PathVariable int id) { 
		 return commentService.getCommentById(id);	}

	@PostMapping(value="CommentService/addComment", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addComment(@RequestBody CommentEntity comment) { 
		commentService.addComment(comment);
	}

	@DeleteMapping("CommentService/delete/{id}")
	public void deleteByIdComment(@PathVariable int id) { 
		commentService.deleteComment(id);
	}

}