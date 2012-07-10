package ru.alex.svalka.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ru.alex.svalka.model.Person;
import ru.alex.svalka.web.PersonController;

@Component
public class PersonDao {
	private static final Logger logger = LoggerFactory
			.getLogger(PersonController.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional(readOnly = true)
	public Person get(long id) {
		return (Person) getSession().get(Person.class, id);
	}

	@Transactional
	public void save(Person person) {
		logSuccess(person);
		getSession().saveOrUpdate(person);
		logger.info("try saving " + person);
	}

	@Transactional(readOnly = true)
	public Person find(String familyName) {
		Query query = getSession().createQuery(
				"FROM Person p WHERE p.familyName = :family");
		query.setParameter("family", familyName);
		return (Person) query.list().get(0);
	}

	@Transactional
	public void delete(Person person) {
		getSession().delete(person);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Person> getAllPersons() {
		return getSession().createQuery("FROM Person p ORDER BY p.id").list();
	}

	private void logSuccess(final Person person) {
		TransactionSynchronizationManager
				.registerSynchronization(new TransactionSynchronizationAdapter() {
					@Override
					public void afterCommit() {
						logger.info("saved " + person);
					}

					@Override
					public void afterCompletion(int status) {
						logger.info("completed with status=" + status);
					}
				});
	}
}
