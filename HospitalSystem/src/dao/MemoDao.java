package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Memo;
import dto.Patient;

public class MemoDao {
	Connection con;
	public MemoDao(Connection c) {
		con = c;
	}
	
	//nurmemo table에 insert
	public void insert(Patient p, String memo) {
		try {
			String sql = "insert into nurMemo values (?, ?, ?, default)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getName());
			psmt.setString(2, p.getIdNum());
			psmt.setString(3, memo);
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("memo insert : error");
		}
	}
	
	//nurtable select
	public Patient select(String idNum, String word) {
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = "";
			if(word.equals("간호사")) {
				sql = "select name, idNum, nurMemo, substr(inDate,1,14) as inDate from nurMemo where idNum = ? order by inDate desc";
			} else if(word.equals("의사")) {
				sql = "select name, idNum, docMemo, substr(inDate,1,14) as inDate from docMemo where idNum = ? order by inDate desc";
			}
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, idNum);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				Memo m = new Memo();
				if(word.equals("간호사")) {
					m.setNurMemo(rs.getString("nurMemo"));
				} else if(word.equals("의사")) {
					m.setNurMemo(rs.getString("docMemo"));
				}
				m.setNurDate(rs.getString("inDate"));
				p.setMemo(m);
				patient.add(p);
			}
		} catch(Exception e) {
			System.out.println("memo select : error");
		}
		return array(patient, word);
	}
	
	//같은 환자의 메모 합치기
	private Patient array(ArrayList<Patient> p, String word) {
		for(int i = 0; i < p.size()-1; i++) {
			if(p.get(i).getIdNum().equals(p.get(i+1).getIdNum())) {
				if(word.equals("간호사")) {
					p.get(i).getMemo().setNurMemo(p.get(i+1).getMemo().getNurMemo().get(0));
					p.get(i).getMemo().setNurDate(p.get(i+1).getMemo().getNurDate().get(0));
				} else if(word.equals("의사")) {
					p.get(i).getMemo().setDocMemo(p.get(i+1).getMemo().getDocMemo().get(0));
					p.get(i).getMemo().setDocDate(p.get(i+1).getMemo().getDocDate().get(0));
				}
				p.remove(i+1);
				i = -1;
			}
		}
		Patient patient = null;
		if(p.size() != 0) {
			patient = p.get(0);
		}
		return patient;
	}
}
