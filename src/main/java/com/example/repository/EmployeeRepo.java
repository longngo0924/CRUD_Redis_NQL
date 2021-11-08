package com.example.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

@Repository
public class EmployeeRepo {
	private HashOperations hashOperations;// crud hash
	private ListOperations listOperations;
	private SetOperations setOperations;

	private RedisTemplate redisTemplate;

	public EmployeeRepo(RedisTemplate redisTemplate) {

//		this.hashOperations = redisTemplate.opsForHash();
//		this.listOperations = redisTemplate.opsForList();
		this.setOperations = redisTemplate.opsForSet();
		this.redisTemplate = redisTemplate;

	}

	public void saveEmployee(Employee employee) {

//		hashOperations.put("EMPLOYEE", employee.getId(), employee);
//		listOperations.rightPush("EMPLOYEE", employee);
		setOperations.add("EMPLOYEE", employee);

	}

	public Set<Employee> findAll() {

//		return hashOperations.values("EMPLOYEE");
		return setOperations.members("EMPLOYEE");
	}

	public Employee findById(Integer id) {

//		return (Employee) hashOperations.get("EMPLOYEE", id);
		return (Employee) setOperations.intersect("EMPLOYEE", id);
	}

	public void update(Employee employee) {
		saveEmployee(employee);
	}

	public void delete(Integer id) {
//		hashOperations.delete("EMPLOYEE", id);
		setOperations.remove("EMPLOYEE", 0, id);
	}
}
