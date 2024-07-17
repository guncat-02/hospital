package dto;

import java.util.ArrayList;

public class Patient {
	private Memo memo = new Memo();
	private String name;
	private String idNum;
	private String telNum;
	private String gender;
	private String addr;
	private ArrayList<Recode> recode = new ArrayList<>();
	
	//getter
	public Memo getMemo() {
		return memo;
	}
	public String getName() {
		return name;
	}
	public String getIdNum() {
		return idNum;
	}
	public String getTelNum() {
		return telNum;
	}
	public String getGender() {
		return gender;
	}
	public String getAddr() {
		return addr;
	}
	public ArrayList<Recode> getRecode() {
		return recode;
	}
	
	//setter
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setMemo(Memo memo) {
		this.memo = memo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setRecode(Recode recode) {
		this.recode.add(recode);
	}
	
	//매개변수 값에 - 추가
	public void setIdNum(String idNum) {
		String word = chk(idNum);
		idNum = "";
		for(int i = 0; i < word.length(); i++) {
			if(i == 6) {
				idNum += '-';
			}
			idNum += word.charAt(i);
		}
		this.idNum = idNum;
	}
	
	//매개변수 값에 - 추가
	public void setTelNum(String telNum) {
		String word = chk(telNum);
		telNum = "";
		for(int i = 0; i < word.length(); i++) {
			if(i == 3 || i == 7) {
				telNum += '-';
			}
			telNum += word.charAt(i);
		}
		this.telNum = telNum;
	}
	
	//문자열에서 - 제거
	private String chk(String word) {
		String chkWord = "";
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) != '-') {
				chkWord += word.charAt(i);
			}
		}
		return chkWord;
	}
}
