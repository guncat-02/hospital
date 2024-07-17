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
		if(chk(money).equals("����")) {
			this.money = money;
		} else {
			this.money = chk(money)+"��";
		}
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	//���ڿ��� �� ����
	public String chk(String money) {
		if(!money.equals("����")) {
			String word = money;
			money = "";
			for(int i = 0; i < word.length(); i++) {
				if(word.charAt(i) != '��') {
					money += word.charAt(i);
				}
			}
			return money;
		}
		return money;
	}
	
}
