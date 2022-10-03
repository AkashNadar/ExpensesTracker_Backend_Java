package com.idfc.service;

import java.util.List;

import com.idfc.model.Expenses;

public interface ExpensesService {
	
	public Expenses addExpenses(Expenses expenses, int id);
	
	public List<Expenses> getExpenses();
	
	public Expenses getExpensesById(int userId, int expId);
	
	public Expenses updateExpenses(Expenses expenses, int expId, int userId);
	
	public boolean approveExpenses(int expId, int approve, int userId);
	
	public boolean deleteExpenses(int expId, int userId);
	
	public List<Expenses> getApprovedExpenses(int userId);
	
	public List<Expenses> getNotApprovedExpenses(int userId);
	
	public double getAmount(int userId);
}
