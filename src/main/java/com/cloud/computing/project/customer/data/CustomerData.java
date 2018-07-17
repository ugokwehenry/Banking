package com.cloud.computing.project.customer.data;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cloud.computing.project.user.data.JsonSerializable;

public class CustomerData implements JsonSerializable{

	public CustomerData() {	}

	private String customerName = null;
	private String customerNumber = null;
	private String customerId = null;
	private String status = null;
	private String customerShortName = null;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerShortName() {
		return customerShortName;
	}

	public void setCustomerShortName(String customerShortName) {
		this.customerShortName = customerShortName;
	}

	public CustomerData(String customerId, String customerName,
			String customerNumber, String status, String customerShortName) {
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.customerId = customerId;
		this.status = status;
		this.customerShortName = customerShortName;
	}

	@Override
	public String toString() {
		return "Customer[customerId=" + customerId + ",customerName="
				+ customerName + ",customerNumber=" + customerNumber
				+ ",status=" + status + ",customerShortName="
				+ customerShortName + "]";
	}

	public JSONObject toJson() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("customerId", this.customerId);
		jsonObject.put("customerName", this.customerName);
		jsonObject.put("customerNumber", this.customerNumber);
		jsonObject.put("status", this.status);
		jsonObject.put("customerShortName", this.customerShortName);
		return jsonObject;
	}
}
