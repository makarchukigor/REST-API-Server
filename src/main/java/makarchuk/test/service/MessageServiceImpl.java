package makarchuk.test.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import makarchuk.test.entity.Message;
import makarchuk.test.repository.MessageRepository;

@Service("messageService")
public class MessageServiceImpl implements MessageService  {	
	
	@Autowired
    @Qualifier("messageRepository")
    private MessageRepository messageRepository;		
	
	
	@Override
	public Message save(Message message) {
		try {			
			return (Message) messageRepository.persist(message);
        } catch (Exception e) {            
            System.out.println("ERROR " + e.getMessage());
        	return null;
        }
		
	}
	
	public List<Message> getMessages() {		
		return messageRepository.getMessages();
	}	

	@Override
	public Message getMessage(Integer id) {
		return  (Message) messageRepository.getMessage(id);			
	}

	@Override
	public Message create(String text) {
		Message message = new Message();
		message.setAvailable(true);
		message.setText(text);		
		message.setCreatedAt(new Date());
		return save(message);
	}

	@Override
	public Message update(Message message, String newText) {		
		message.setText(newText);	
		message.setUpdatedAt(new Date());
		Message result = save(message);
		return result;
	}


	@Override
	public void delete(Integer id) {
		Message message = (Message) messageRepository.getMessage(id);;
		messageRepository.delete(message);		
	}


	@Override
	public Message close(Integer id) {
		Message result = getMessage(id);
		if (result != null) {
			result.setAvailable(false);
			result = save(result);
		}
		return result;		
	}


	@Override
	public Message open(Integer id) {
		Message result = getMessage(id);
		if (result != null) {
			result.setAvailable(true);
			result = save(result);
		}
		return result;
	}	

}
