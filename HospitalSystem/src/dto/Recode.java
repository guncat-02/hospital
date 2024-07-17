package dto;

import java.util.ArrayList;

public class Recode {
	private Doctor dc = new Doctor();
	private String reDate;
	private String progress;
	private String receipt;
	private String money;
	private String payment;
	private String reason;

	private String disease;
	private String medicine;

	//getter
	public String getReason() {
		return reason;
	}
	public Doctor getDc() {
		return dc;
	}
	public String getReDate() {
		return reDate;
	}
	public String getProgress() {
		return progress;
	}
	public String getMedicine() {
		return medicine;
	}
	public String getDisease() {
		return disease;
	}
	public String getPayment() {
		return payment;
	}
	public String getMoney() {
		return money;
	}
	public String getReceipt() {
		return receipt;
	}
	
	//setter
	public void setDc(Doctor dc) {
		this.dc = dc;
	}
	public void setReDate(String reDate) {
		this.reDate = reDate;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public void setMoney(String money) {
		if(chk(money).equals("미정")) {
			this.money = money;
		} else {
			this.money = chk(money)+"원";
		}
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	//문자열에 원 제거
	public String chk(String money) {
		if(!money.equals("미정")) {
			String word = money;
			money = "";
			for(int i = 0; i < word.length(); i++) {
				if(word.charAt(i) != '원') {
					money += word.charAt(i);
				}
			}
			return money;
		}
		return money;
	}
	
}
