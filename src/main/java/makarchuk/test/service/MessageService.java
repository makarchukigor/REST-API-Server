package makarchuk.test.service;

import java.util.List;

import makarchuk.test.entity.Message;

public interface MessageService {	
	
	public Message create(String text);
	
	public Message save(Message message);
	
	public Message update(Message message, String newText);
	
	public void delete(Integer id);
	
	public Message close(Integer id);
	
	public Message open(Integer id);

    public List<Message> getMessages();
    
    public Message getMessage(Integer id);
    
   
}
