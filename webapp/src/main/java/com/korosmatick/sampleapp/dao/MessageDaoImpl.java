package com.korosmatick.sampleapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.korosmatick.sampleapp.model.db.Message;

@Repository
@Transactional
public class MessageDaoImpl implements MessageDao{

	private static final Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Message findById(Long id) {
		logger.debug("Find Message by id");
		Session session = sessionFactory.getCurrentSession();
		Message message = (Message) session.get(Message.class, id);
		return message;
	}
	
	@Override
	public void add(Message message) {
		logger.debug("adding : " + message.getText());
		if (findByMessageId(message.getMessageId()) == null) {
			Session session = sessionFactory.getCurrentSession();
			session.save(message);
		}
	}

	@Override
	public void delete(Message message) {
		logger.debug("Deleting existing Contact");
		if (message.getId() != null) {
			deleteById(message.getId());
		}
	}

	@Override
	public void deleteById(Long id) {
		logger.debug("Deleting existing Message");
		Session session = sessionFactory.getCurrentSession();
		Message message = (Message) session.get(Message.class, id);
		session.delete(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findAll() {
		logger.debug("findAll() called");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public Message findByMessageId(Integer messageId) {
		logger.debug("Find Message by messageId : " + messageId );
		if (messageId != null) {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Message.class);
			criteria.add(Restrictions.eq("messageId", messageId));
			criteria.addOrder(Order.asc("id"));
			return criteria.list().isEmpty() ? null : (Message) criteria.list().get(0);
		}
		return null;
	}
	
}
