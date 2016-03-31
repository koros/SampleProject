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

import com.korosmatick.sampleapp.model.db.Action;


@Repository
@Transactional
public class ActionDaoImpl implements ActionDao{
	
	private static final Logger logger = LoggerFactory.getLogger(ActionDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Action findById(Long id) {
		logger.debug("Find Contact by id");
		Session session = sessionFactory.getCurrentSession();
		Action action = (Action) session.get(Action.class, id);
		return action;
	}

	@Override
	public Action findActionByName(String name) {
		logger.debug("Find Action by name : " + name );
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Action.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.addOrder(Order.asc("id"));
		return criteria.list().isEmpty() ? null : (Action) criteria.list().get(0);
	}

	@Override
	public void add(Action action) {
		logger.debug("adding : " + action.getName());
		Session session = sessionFactory.getCurrentSession();
		session.save(action);
	}

	@Override
	public void delete(Action action) {
		logger.debug("Deleting existing Contact");
		if (action.getId() != null) {
			deleteById(action.getId());
		}
	}

	@Override
	public void deleteById(Integer id) {
		logger.debug("Deleting existing Action");
		Session session = sessionFactory.getCurrentSession();
		Action action = (Action) session.get(Action.class, id);
		session.delete(action);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> findAll() {
		logger.debug("findAll() called");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Action.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> findAllActionsForMessageId(Long messageId) {
		logger.debug("findAllActionsForMessageId() called");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Action.class);
		criteria.add(Restrictions.eq("associatedMessageId", messageId));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
}
