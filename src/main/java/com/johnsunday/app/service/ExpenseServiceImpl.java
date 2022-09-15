package com.johnsunday.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnsunday.app.dao.IExpenseDao;
import com.johnsunday.app.entity.Expense;

@Service
public class ExpenseServiceImpl extends BaseServiceImpl<Expense,Integer> 
								implements IExpenseService {

	@Autowired private IExpenseDao expenseDao;
	
	
	@Override	
	public List<Expense> findAllExpenseByEmployeeId(Integer employeeId) throws Exception {
		try {
			//return expenseDao.findAllExpenseByEmployeeId(employeeId);
			return expenseDao.findAllByEmployeeId(employeeId);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public Boolean findByAmountAndExpenseDateAndConceptAndEmployeeIdFk(Double amount, 
																	   LocalDateTime expenseDate,
																	   String concept, 
																	   Integer employeeIdFk) {
		return expenseDao.findByAmountAndExpenseDateAndConceptAndEmployeeIdFkAllIgnoreCase(amount, 
																						   expenseDate, 
																						   concept, 
																						   employeeIdFk);
	}
}
