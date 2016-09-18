package makarchuk.test.controller;


import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import makarchuk.test.entity.Message;
import makarchuk.test.service.MessageService;


@RestController
public class MessageController  {
	
	@Autowired
    @Qualifier("messageService")
    private MessageService messageService;	
	
	@RequestMapping(
			value = "/api/messages/new{text}", 
			method = RequestMethod.POST,			
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE 
			)	
	public ResponseEntity<Message> newMessage(@RequestParam("text") String text ) {					
		Message result = messageService.create(text);
		if (result == null) {
			return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Message>(result, HttpStatus.CREATED);
		}		
    }
	
	@RequestMapping(
			value = "/api/messages/edit/{id}{text}", 
			method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Message> editMessage(@PathVariable("id") Integer id,  @RequestParam("text") String text) {		
		Message result = messageService.getMessage(id); 		
		if (result == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		} else {
			result = messageService.update(result, text);
			if (result != null) {
				return new ResponseEntity<Message>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}		
	}	
	
	@RequestMapping(
			value = "/api/messages/del/{id}", 
			method = RequestMethod.DELETE)
	public ResponseEntity<Message> deleteMessage(@PathVariable("id") Integer id) {
		 messageService.delete(id);
		 return new ResponseEntity<Message>(HttpStatus.NO_CONTENT);		
	}	
	
	@RequestMapping(
			value = "/api/messages/close/{id}", 
			method = RequestMethod.PUT 
			)
	public ResponseEntity<Message> closeMessages(@PathVariable("id") Integer id) {		
		Message result = messageService.close(id);		
		if (result == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		} else {			
			return new ResponseEntity<Message>(HttpStatus.NO_CONTENT);		
			
		}		
	}	
	
	
	@RequestMapping(
			value = "/api/messages/open/{id}", 
			method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Message> openMessages(@PathVariable("id") Integer id) {		
		Message result = messageService.open(id);		
		if (result == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		} else {			
			return new ResponseEntity<Message>(result, HttpStatus.OK);		
			
		}		
	}	
	
	
	@RequestMapping(
			value = "/api/messages", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Message>> getMessages() {		
		List<Message> result = messageService.getMessages();		 
		return new ResponseEntity<Collection<Message>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/messages/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Message> getMessage(@PathVariable("id") Integer id, HttpServletRequest request) {		
		Message result = messageService.getMessage(id);
		if (result == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Message>(result, HttpStatus.OK);
		}		
	}	

}
