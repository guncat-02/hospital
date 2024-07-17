package dto;

import java.util.ArrayList;

public class Memo {
	private ArrayList<String> nurMemo = new ArrayList<>();
	private ArrayList<String> docMemo = new ArrayList<>();
	private ArrayList<String> nurDate = new ArrayList<>();
	private ArrayList<String> docDate = new ArrayList<>();
	
	//getter
	public ArrayList<String> getNurMemo() {
		return nurMemo;
	}
	public ArrayList<String> getDocMemo() {
		return docMemo;
	}
	public ArrayList<String> getNurDate() {
		return nurDate;
	}
	public ArrayList<String> getDocDate() {
		return docDate;
	}
	
	//setter
	public void setNurMemo(String nurMemo) {
		this.nurMemo.add(nurMemo);
	}
	public void setDocMemo(String docMemo) {
		this.docMemo.add(docMemo);
	}
	public void setNurDate(String nurDate) {
		this.nurDate.add(nurDate);
	}
	public void setDocDate(String docDate) {
		this.docDate.add(docDate);
	}
}
