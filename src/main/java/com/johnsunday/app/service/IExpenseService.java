package com.johnsunday.app.service;

import java.time.LocalDateTime;
import java.util.List;

import com.johnsunday.app.entity.Expense;

public interface IExpenseService {

	public List<Expense> findAll() throws Exception;

	public Expense findById(Long id, String headerAuth) throws Exception;

	public Expense save(Expense expense, String headerAuth) throws Exception;

	public Expense update(/* Integer id, */Expense expense, String headerAuth) throws Exception;

	public Boolean delete(Long id) throws Exception;

	public List<Expense> findAllByEmployeeId(Long employeeId, String headerAuth) throws Exception;

	public Expense findByAmountAndDateAndConceptAndEmployeeId(Double amount,
			LocalDateTime expenseDate, String concept,
			Long employeeId,
			String headerAuth) throws Exception;
}
