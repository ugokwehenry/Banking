package com.cloud.computing.project.deposit.account.data;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cloud.computing.project.user.data.JsonSerializable;

public class DepositAccountData implements JsonSerializable{

		private String accountId;
		private String accountTitle;
	    private String accountNumber;
	    private String accountType;
	    private String productCategory;
	    private String product;
	    private String openDate;
	    private String lastDepAmount;
	    private String lastWdAmount;
	    private String currentBalance;
	    private String availableBalance;
	    private String colBalance;
	    private String status;
	    private String description;
	    private String effectiveDate;
	    private String lastWdDate;
	    private String lastDepDate;
	    private String valueDate;
	    private String rsm;
	    private String accruedInterest;
	    
	    public DepositAccountData(){}
	
	public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

	public String getAccountTitle() {
			return accountTitle;
		}

		public void setAccountTitle(String accountTitle) {
			this.accountTitle = accountTitle;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getProductCategory() {
			return productCategory;
		}

		public void setProductCategory(String productCategory) {
			this.productCategory = productCategory;
		}

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}

		public String getOpenDate() {
			return openDate;
		}

		public void setOpenDate(String openDate) {
			this.openDate = openDate;
		}

		public String getLastDepAmount() {
			return lastDepAmount;
		}

		public void setLastDepAmount(String lastDepAmount) {
			this.lastDepAmount = lastDepAmount;
		}

		public String getLastWdAmount() {
			return lastWdAmount;
		}

		public void setLastWdAmount(String lastWdAmount) {
			this.lastWdAmount = lastWdAmount;
		}

		public String getCurrentBalance() {
			return currentBalance;
		}

		public void setCurrentBalance(String currentBalance) {
			this.currentBalance = currentBalance;
		}

		public String getAvailableBalance() {
			return availableBalance;
		}

		public void setAvailableBalance(String availableBalance) {
			this.availableBalance = availableBalance;
		}

		public String getColBalance() {
			return colBalance;
		}

		public void setColBalance(String colBalance) {
			this.colBalance = colBalance;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getEffectiveDate() {
			return effectiveDate;
		}

		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = effectiveDate;
		}

		public String getLastWdDate() {
			return lastWdDate;
		}

		public void setLastWdDate(String lastWdDate) {
			this.lastWdDate = lastWdDate;
		}

		public String getLastDepDate() {
			return lastDepDate;
		}

		public void setLastDepDate(String lastDepDate) {
			this.lastDepDate = lastDepDate;
		}

		public String getValueDate() {
			return valueDate;
		}

		public void setValueDate(String valueDate) {
			this.valueDate = valueDate;
		}

		public String getRsm() {
			return rsm;
		}

		public void setRsm(String rsm) {
			this.rsm = rsm;
		}

		public String getAccruedInterest() {
			return accruedInterest;
		}

		public void setAccruedInterest(String accruedInterest) {
			this.accruedInterest = accruedInterest;
		}

	@Override
	public String toString(){
		return "DepositAccountData[accountId="+accountId+",accountTitle="+accountTitle+",accountNumber="+accountNumber+",accountType="+accountType+",productCategory="+productCategory+","
				+ "product="+product+",openDate="+openDate+",lastDepAmount="+lastDepAmount+"lastWdAmount="+lastWdAmount+"currentBalance="+currentBalance+"colBalance="+colBalance+","
				+ "status="+status+",description="+description+","+ "effectiveDate="+effectiveDate+",lastWdDate="+lastWdDate+",lastDepDate="+lastDepDate+",valueDate="+valueDate+",rsm="
				+rsm+",accruedInterest="+accruedInterest+"]";
	}
		
	@Override
	public JSONObject toJson() throws JSONException {
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("accountId", this.accountId);
		jsonObject.put("accountTitle", this.accountTitle);
		jsonObject.put("accountNumber", this.accountNumber);
		jsonObject.put("accountType", this.accountType);
		jsonObject.put("productCategory", this.productCategory);
		jsonObject.put("product", this.product);
		jsonObject.put("openDate", this.openDate);
		jsonObject.put("lastDepAmount", this.lastDepAmount);
		jsonObject.put("lastWdAmount", this.lastWdAmount);
		jsonObject.put("currentBalance", this.currentBalance);
		jsonObject.put("colBalance", this.colBalance);
		jsonObject.put("status", this.status);
		jsonObject.put("description", this.description);
		jsonObject.put("effectiveDate", this.effectiveDate);
		jsonObject.put("lastWdDate", this.lastWdDate);
		jsonObject.put("lastDepDate", this.lastDepDate);
		jsonObject.put("valueDate", this.valueDate);
		jsonObject.put("rsm", this.rsm);
		jsonObject.put("accruedInterest", this.accruedInterest);
		return jsonObject;
	}

	public DepositAccountData(String accountId, String accountTitle,
			String accountNumber, String openDate) {
		super();
		this.accountId = accountId;
		this.accountTitle = accountTitle;
		this.accountNumber = accountNumber;
		this.openDate = openDate;
	}

}
