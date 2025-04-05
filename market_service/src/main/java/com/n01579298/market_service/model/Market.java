package com.n01579298.market_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "market")
public class Market {

	private String orderId;
	private String transactionId;
	private String feesId;
	private double bid;
	private double ask;
	private double previous;
	private double last;
	private double typeOfExchange;
	private String confirmationStatus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFeesId() {
		return feesId;
	}

	public void setFeesId(String feesId) {
		this.feesId = feesId;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public double getPrevious() {
		return previous;
	}

	public void setPrevious(double previous) {
		this.previous = previous;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public double getTypeOfExchange() {
		return typeOfExchange;
	}

	public void setTypeOfExchange(double typeOfExchange) {
		this.typeOfExchange = typeOfExchange;
	}

	public String getConfirmationStatus() {
		return confirmationStatus;
	}

	public void setConfirmationStatus(String confirmationStatus) {
		this.confirmationStatus = confirmationStatus;
	}
}