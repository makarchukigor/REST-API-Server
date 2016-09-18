package makarchuk.test.repository;

import java.util.List;

import makarchuk.test.entity.Message;

public interface MessageRepository {	
	
	public Message persist(Message message);

    public void delete(Message message);

    public List<Message> getMessages();    
    
    public Message getMessage(Integer id);    
   

}

