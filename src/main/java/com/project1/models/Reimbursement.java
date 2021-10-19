package com.project1.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 1L;
	private int reimb_id;
	private double amount;
	private LocalDate submitted;
	private LocalDate resolved;
	private String description;
	private boolean receipt;
	private int author_id;
	private int resolver_id;
	private int status_id;
	private int type_id;
	
	public Reimbursement(int reimb_id, double amount, LocalDate submitted, LocalDate resolved, String description,
			boolean receipt, int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.reimb_id = reimb_id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public Reimbursement(double amount, LocalDate submitted, LocalDate resolved, String description, boolean receipt,
			int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDate submitted) {
		this.submitted = submitted;
	}

	public LocalDate getResolved() {
		return resolved;
	}

	public void setResolved(LocalDate resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isReceipt() {
		return receipt;
	}

	public void setReceipt(boolean receipt) {
		this.receipt = receipt;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getResolver_id() {
		return resolver_id;
	}

	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", receipt=" + receipt + ", author_id=" + author_id
				+ ", resolver_id=" + resolver_id + ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}
	
}
