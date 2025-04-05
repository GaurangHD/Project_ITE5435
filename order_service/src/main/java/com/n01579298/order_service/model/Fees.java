package com.n01579298.order_service.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Fees {

	private String feeId;
	private boolean feeType;
	private double feeAmount;
	private LocalDateTime feeDate;
	private double feeSalesTax;

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public boolean isFeeType() {
		return feeType;
	}

	public void setFeeType(boolean feeType) {
		this.feeType = feeType;
	}

	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public LocalDateTime getFeeDate() {
		return feeDate;
	}

	public void setFeeDate(LocalDateTime feeDate) {
		this.feeDate = feeDate;
	}

	public double getFeeSalesTax() {
		return feeSalesTax;
	}

	public void setFeeSalesTax(double feeSalesTax) {
		this.feeSalesTax = feeSalesTax;
	}
}