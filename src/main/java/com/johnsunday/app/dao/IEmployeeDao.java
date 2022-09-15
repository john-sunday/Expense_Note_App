package com.johnsunday.app.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.johnsunday.app.entity.Employee;

@Repository
public interface IEmployeeDao extends IBaseDao<Employee, Integer> {

	//@Query(value="SELECT * FROM employee WHERE employee.name=?1 and employee.surname=?2", nativeQuery=true)
	Optional<Employee> findByNameAndSurnameAllIgnoreCase(String employeeName,String employeeSurname);
}
