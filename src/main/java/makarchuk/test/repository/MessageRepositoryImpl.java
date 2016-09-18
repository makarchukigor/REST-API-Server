package makarchuk.test.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import makarchuk.test.entity.Message;

@org.springframework.stereotype.Repository("messageRepository")
@Transactional
public class MessageRepositoryImpl implements MessageRepository {

	
	@PersistenceContext
	private EntityManager entityManager;	
	
	
	public Message persist(Message message) {		
		Session session = entityManager.unwrap(Session.class);		  
		session.saveOrUpdate(message);			
		return message;		
	}

	public void delete(Message message) {
		Session session = entityManager.unwrap(Session.class);		
		session.delete(message);		
	}	
	
	public List<Message> getMessages() {			
		Session session = entityManager.unwrap(Session.class);		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<?> query = builder.createQuery(Message.class);
		Root<?> root = query.from(Message.class);		
		query.where(builder.equal(root.get("available"), true));
		query.orderBy(builder.asc(root.get("id")));
		@SuppressWarnings("unchecked")
		List<Message> result = (List<Message>) session
				.createQuery(query)
				.getResultList().stream()
				.collect(Collectors.toList());		
		return result;
	}

	@Override
	public Message getMessage(Integer id) {
		Session session = entityManager.unwrap(Session.class);		
		Message message = session.get(Message.class, id);		
		return message;
	}

	

}
