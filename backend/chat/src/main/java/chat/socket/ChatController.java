package chat.socket;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import chat.service.CommentEntity;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class ChatController {

	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public String send(CommentEntity message) throws Exception {
	    String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return message.getDescription() + time.toString();
	}
	
	
}
