package com.johnsunday.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
//@Audited
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	@Column(name="name",nullable=false,length=128)
	@Length(min=3,max=128)
	@NonNull
	private String name;
	@Column(name="surname",nullable=false,length=255)
	@Length(min=5,max=255)
	@NonNull
	private String surname;
	@Column(name="birth_date")
	//@Temporal(TemporalType.TIMESTAMP)
	@NonNull
	private LocalDateTime birthDate;
	@OneToOne
	@JoinColumn(name="employee_type_id_fk")
	@NonNull
	private EmployeeType employeeType;
	
	@OneToMany(mappedBy="employee",targetEntity=Expense.class,cascade= {CascadeType.MERGE, CascadeType.REMOVE,
            CascadeType.REFRESH, CascadeType.DETACH} ,orphanRemoval=false)
	//@JsonIgnore
	@JsonBackReference
//	@JoinTable(
//			name="employee_expense",
//			joinColumns= {@JoinColumn(name="employee_id",referencedColumnName="id")},
//			inverseJoinColumns= {@JoinColumn(name="expense_id",referencedColumnName="id")})
	private List<Expense>expenses = new ArrayList<>();
	
	@OneToMany(mappedBy="employee",targetEntity=Payroll.class,cascade={CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH},orphanRemoval=false)
	@JsonIgnore
	//@JsonBackReference
//	@JoinTable(
//			name="employee_payroll",
//			joinColumns=@JoinColumn(name="employee_id",referencedColumnName="id"),
//			inverseJoinColumns=@JoinColumn(name="payroll_id",referencedColumnName="id"))	
	private List<Payroll>payrolls = new ArrayList<>();
	
	public void addExpense(Expense expense) {
		expenses.add(expense);
	}
	public void addPayroll(Payroll payroll) {
		payrolls.add(payroll);
	}
	public void removeExpense(Expense expense) {
		expenses.remove(expense);
	}
	public void removePayroll(Payroll payroll) {
		payrolls.remove(payroll);
	}
}
