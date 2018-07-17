package com.cloud.computing.project.loan.account.data;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cloud.computing.project.user.data.JsonSerializable;

public class LoanAccountData implements JsonSerializable {
	private String accountId;
	private String accountTitle;
    private String accountNumber;
    private String product;
    private String loanAmount;
    private String startDate;
    private String marturityDate;
    private String termCode;
    private String termValue;
    private String captilizedEvent;
    private String finalRepaymentDate;
    private String closedDate;
    private String status;
    private String rsm;
    private String accruedInterest;
    
    public LoanAccountData(){}
    
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
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getMarturityDate() {
		return marturityDate;
	}
	public void setMarturityDate(String marturityDate) {
		this.marturityDate = marturityDate;
	}
	public String getTermCode() {
		return termCode;
	}
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	public String getTermValue() {
		return termValue;
	}
	public void setTermValue(String termValue) {
		this.termValue = termValue;
	}
	public String getCaptilizedEvent() {
		return captilizedEvent;
	}
	public void setCaptilizedEvent(String captilizedEvent) {
		this.captilizedEvent = captilizedEvent;
	}
	public String getFinalRepaymentDate() {
		return finalRepaymentDate;
	}
	public void setFinalRepaymentDate(String finalRepaymentDate) {
		this.finalRepaymentDate = finalRepaymentDate;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "LoanAccountData[accountId="+accountId+",accountTitle="+accountTitle+",product="+product+",loanAmount="+loanAmount+",startDate="+startDate+","
				+ "marturityDate="+marturityDate+",termCode="+termCode+",termValue="+termValue+",captilizedEvent="+captilizedEvent+",finalRepaymentDate="
				+finalRepaymentDate+",closedDate="+closedDate+",status="+status+",rsm="+rsm+",accruedInterest="+accruedInterest+"]";
	}
	
	@Override
	public JSONObject toJson() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accountId", this.accountId);
		jsonObject.put("accountTitle", this.accountTitle);
		jsonObject.put("product", this.product);
		jsonObject.put("loanAmount", this.loanAmount);
		jsonObject.put("startDate", this.startDate);
		jsonObject.put("marturityDate", this.marturityDate);
		jsonObject.put("termCode", this.termCode);
		jsonObject.put("termValue", this.termValue);
		jsonObject.put("captilizedEvent", this.captilizedEvent);
		jsonObject.put("finalRepaymentDate", this.finalRepaymentDate);
		jsonObject.put("closedDate", this.closedDate);
		jsonObject.put("status", this.status);
		jsonObject.put("rsm", this.rsm);
		jsonObject.put("accruedInterest", this.accruedInterest);
		return jsonObject;
	}

	public LoanAccountData(String accountId, String accountTitle,
			String accountNumber, String loanAmount, String startDate,
			String marturityDate, String termCode, String termValue,
			String captilizedEvent, String finalRepaymentDate,
			String closedDate, String rsm) {
		super();
		this.accountId = accountId;
		this.accountTitle = accountTitle;
		this.accountNumber = accountNumber;
		this.loanAmount = loanAmount;
		this.startDate = startDate;
		this.marturityDate = marturityDate;
		this.termCode = termCode;
		this.termValue = termValue;
		this.captilizedEvent = captilizedEvent;
		this.finalRepaymentDate = finalRepaymentDate;
		this.closedDate = closedDate;
		this.rsm = rsm;
	}

}
