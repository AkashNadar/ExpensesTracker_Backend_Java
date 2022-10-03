package com.idfc.service;

import java.util.ArrayList;
import java.util.List;

import com.idfc.dao.ExpensesRepository;
import com.idfc.dao.UserRepository;
import com.idfc.model.Expenses;
import com.idfc.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesServiceImple implements ExpensesService {

	@Autowired
	private ExpensesRepository expRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public Expenses addExpenses(Expenses expenses, int id) {
		
		User user = this.userRepo.findById(id).get();
		if(user.getId() == 1) {
			return this.expRepo.save(expenses);
		}

		return null;
	}

	
	@Override
	public List<Expenses> getExpenses() {
		System.out.println(expRepo.findAll());
		return this.expRepo.findAll();
	}

	
	@Override
	public Expenses getExpensesById(int userId, int expId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 1) {
			return this.expRepo.findById(expId).get();
		}
		return null;
	}

	
	@Override
	public Expenses updateExpenses(Expenses expenses, int expId, int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 1) {
			Expenses updateExp = this.expRepo.findById(expId).get();
			updateExp.setName(expenses.getName());
			updateExp.setRemark(expenses.getRemark());
			updateExp.setType(expenses.getType());
			updateExp.setPrice(expenses.getPrice());
			return this.expRepo.save(updateExp);
		}
		return null;
		
	}
	
	
	@Override
	public boolean approveExpenses(int expId, int approve, int userId) {
		
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 2) {
			Expenses approveExp = this.expRepo.findById(expId).get();
			if(approve >= 0 && approve <= 1)
				approveExp.setApproved(approve);
			this.expRepo.save(approveExp);
			return true;
		}
		return false;
	}

	
	@Override
	public boolean deleteExpenses(int expId, int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 1) {
			this.expRepo.deleteById(expId);
			return true;
		}
		return false;
	}


	
	
	@Override
	public List<Expenses> getApprovedExpenses(int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 2) {
			List<Expenses> allExp = this.expRepo.findAll();
			List<Expenses> approvedExp = new ArrayList<>();
			for(Expenses exp : allExp) {
				if(exp.getApproved() == 1) {
					approvedExp.add(exp);
				}
			}
			return approvedExp;
		}
		
		return null;
	}


	@Override
	public List<Expenses> getNotApprovedExpenses(int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 2) {
			List<Expenses> allExp = this.expRepo.findAll();
			List<Expenses> notApprovedExp = new ArrayList<>();
			for(Expenses exp : allExp) {
				if(exp.getApproved() == 0) {
					notApprovedExp.add(exp);
				}
			}
			return notApprovedExp;
		}
		
		return null;
	}


	@Override
	public double getAmount(int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 3) {
			double sum = 0;
			List<Expenses> allExp = this.expRepo.findAll();
			for(Expenses exp : allExp) {
				if(exp.getApproved() == 1) {
					sum += exp.getPrice();
				}
			}
			return sum;
		}
		return -1;
	}

	

	

}
