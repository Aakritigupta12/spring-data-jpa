package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//define field for entity manger
	private EntityManager entityManager;
	
	//setup contructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		//get current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery = session.createQuery("from Employee", Employee.class);
		
		//excute query and get result lisst
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		Employee e = session.get(Employee.class, id);
		return e;
	}

	@Override
	public void save(Employee e) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(e);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Employee e = session.get(Employee.class, id);
		session.delete(e);
	}

	

}
