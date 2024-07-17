package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Patient;

public class PatientDao {
	Connection con;
	
	public PatientDao(Connection c) {
		con = c;
	}
	
	//patient table에 insert
	public void insert(Patient p) {
		try {
			String sql = "insert into patient values(?, ?, ?, ?, ?)";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getName());
			psmt.setString(2, p.getIdNum());
			psmt.setString(3, p.getTelNum());
			psmt.setString(4, p.getGender());
			psmt.setString(5, p.getAddr());
			psmt.executeQuery();
		} catch (Exception e) {
			System.out.println("patient insert : error");
		}
		insertR(p);
	}
	
	//name, idNum 확인
	
	public int chk(Patient p) {
		int count = 0;
		try {
			String sql = "select * from patient where name = ? and idNum = ?";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getName());
			psmt.setString(2, p.getIdNum());
			count = psmt.executeUpdate();
			System.out.println(count);
		} catch(Exception e) {
			System.out.println("patient chking : error");
		}
		return count;
	}
	
	//reception table 에 insert
	public void insertR(Patient p) {
		try {
			String sql = "insert into reception values(?, ?, ?, default, default, default, default,?,?)";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getName());
			psmt.setString(2, p.getIdNum());
			psmt.setString(3, p.getRecode().get(0).getReDate());
			psmt.setString(4, p.getRecode().get(0).getDc().getDocName());
			psmt.setString(5, p.getRecode().get(0).getDc().getDocNum());
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("patient insertR : error");
		}
	}
	
	//reception table 확인
	public void select(String progress) {
		try {
			String sql = "select * from reception where progress = ? order by reDate desc";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, progress);
			psmt.executeQuery();
		} catch (Exception e) {
			System.out.println("patient select : error");
		}
	}
	
	//patient table 확인
	public ArrayList<Patient> allList(String name) {
		ArrayList<Patient> patient = new ArrayList<>();
		String namePlus = "%"+name+"%";
		try {
			String sql = "select * from patient where name like ?";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			ResultSet rs;
			psmt.setString(1, namePlus);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				p.setTelNum(rs.getString("telNum"));
				p.setGender(rs.getString("gender"));
				p.setAddr(rs.getString("addr"));
				patient.add(p);
			}
		} catch(Exception e) {
			System.out.println("patient allList : error");
		}
		return patient;
	}
	
	//patient table select
	public Patient tableSelect(String idNum) {
		Patient p = new Patient();
		try {
			String sql = "select * from patient where idNum = ?";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idNum);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				p.setTelNum(rs.getString("telNum"));
				p.setGender(rs.getString("gender"));
				p.setAddr(rs.getString("addr"));
			}
		} catch(Exception e) {
			System.out.println("patient selectTable : error");
		}
		return p;
	}
	
	//patient select all
	public ArrayList<Patient> all() {
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Patient p = new Patient();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				p.setTelNum(rs.getString("telNum"));
				p.setGender(rs.getString("gender"));
				p.setAddr(rs.getString("addr"));
				patient.add(p);
			}
		} catch(Exception e) {
			System.out.println("patient all : error");
		}
		return patient;
	}
}
