package com.johnsunday.app.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johnsunday.app.entity.Expense;

@Repository
public interface IExpenseDao extends JpaRepository<Expense,Integer> {
		
	@Query(value="SELECT * FROM expense WHERE expense.employee_id=?1",		   
			nativeQuery=true)
	List<Expense> findAllByEmployeeId(Integer employeeId);
	
	@Query(value="SELECT * FROM expense WHERE expense.amount=?1 "
			+ "and expense.date=?2 "
			+ "and expense.concept=?3 "
			+ "and expense.employee_id=?4",nativeQuery=true)
	Optional<Expense> findByAmountAndDateAndConceptAndEmployeeId(Double amount,
																 LocalDateTime expenseDate,
																 String concept,
																 Integer employeeId);
}
