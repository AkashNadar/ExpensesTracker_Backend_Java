package com.idfc.controller;

import java.util.List;

import com.idfc.model.Expenses;
import com.idfc.service.ExpensesServiceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

	@Autowired
	private ExpensesServiceImple service;
	
	
	@PostMapping("{userId}")
	public ResponseEntity<Object> insertExpenses(@RequestBody Expenses expenses, @PathVariable int userId){
		Expenses res = service.addExpenses(expenses, userId);
		System.out.println("here");
		if(res == null) {
			return new ResponseEntity<Object>("Valid Create Role Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);
	}
	
	
	@GetMapping("")
	public ResponseEntity<List<Expenses>> getAllExpenses(){
		List<Expenses> expRec = service.getExpenses();
		return new ResponseEntity<List<Expenses>>(expRec, HttpStatus.OK);
	}
	
	
	@GetMapping("{userId}/{expId}")
	public ResponseEntity<Object> getSingleExpenses(@PathVariable int userId, @PathVariable int expId){
		Expenses res = this.service.getExpensesById(userId, expId);
		if(res == null) {
			return new ResponseEntity<Object>("Invalid role", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	
	@PutMapping("{userId}/{expId}")
	public ResponseEntity<Object> updateExpensesById(@RequestBody Expenses expenses, @PathVariable int expId, @PathVariable int userId) {
		Expenses updatedExp = service.updateExpenses(expenses, expId, userId);
		if(updatedExp == null) {
			return new ResponseEntity<Object>("Valid Update Role Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(updatedExp, HttpStatus.OK);
	}
	
	
	@PostMapping("{userId}/approveExpenses/{expId}")
	public ResponseEntity<String> approveExp(@PathVariable int userId, @PathVariable int expId, @RequestBody Expenses exp){
		
		boolean res = this.service.approveExpenses(expId, exp.getApproved(), userId);
		if(!res) {
			return new ResponseEntity<String>("Valid Approve Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("Expenses Approved Sucessfully", HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@DeleteMapping("{userId}/{expId}")
	public ResponseEntity<String> removeExpenses(@PathVariable int userId, @PathVariable int expId){
		boolean res = this.service.deleteExpenses(expId, userId);
		if(!res) {
			return new ResponseEntity<String>("Valid Delete Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("Expenses Deleted Sucessfully", HttpStatus.OK);
	}
	
	
	@GetMapping("{userId}/approvedExpenses")
	public ResponseEntity<Object> allApprovedExpenses(@PathVariable int userId){
		List<Expenses> approvedExp = this.service.getApprovedExpenses(userId);
		if(approvedExp == null) {
			return new ResponseEntity<Object>("Valid Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(approvedExp, HttpStatus.OK);
	}


	@GetMapping("{userId}/notApprovedExpenses")
	public ResponseEntity<Object> notApprovedExpenses(@PathVariable int userId){
		List<Expenses> notApprovedExp = this.service.getNotApprovedExpenses(userId);
		if(notApprovedExp == null) {
			return new ResponseEntity<Object>("Valid Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(notApprovedExp, HttpStatus.OK);
	}
	

	@GetMapping("{userId}/getTotal")
	public ResponseEntity<Object> getTotal(@PathVariable int userId){
		double res = this.service.getAmount(userId);
		if(res == -1) {
			return new ResponseEntity<Object>("Valid Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
}
