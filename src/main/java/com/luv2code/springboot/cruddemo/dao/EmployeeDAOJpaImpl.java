package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		Query q = entityManager.createQuery("from Employee");
		
		List<Employee> employees = q.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int id) {
		
		Employee e = entityManager.find(Employee.class, id);
		
		
		return e;
	}

	@Override
	public void save(Employee e) {
		Employee emp = entityManager.merge(e);
		e.setId(emp.getId());
	}

	@Override
	public void deleteById(int id) {
		Employee e = entityManager.find(Employee.class, id);
		entityManager.remove(e);
	}
	
	

}
