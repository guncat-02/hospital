package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class DoctorDao {
	Connection con;
	public DoctorDao(Connection c) {
		con = c;
	}
	
	//doctor table insert
	public void insert(Patient p) {
		try {
			String sql = "insert into doctor values(?, ?, ?, ?, ?, null, null)";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getRecode().get(0).getDc().getDocName());
			psmt.setString(2, p.getRecode().get(0).getDc().getDocNum());
			psmt.setString(3, p.getName());
			psmt.setString(4, p.getIdNum());
			psmt.setString(5, p.getRecode().get(0).getReDate());
			psmt.executeQuery();
		} catch (Exception e) {
			System.out.println("doctor insert : error");
		}
	}
	
	//reception table에서 의사 찾기
	private ArrayList<Doctor> chk(String date) {
		ArrayList<Doctor> doctor = new ArrayList<>();
		try {
			String sql = "select * from doctor where reDate = ? order by reDate desc";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, date);
			ResultSet rs;
			rs = psmt.executeQuery();
			while(rs.next()) {
				Doctor dc = new Doctor();
				dc.setDocName(rs.getString("docName"));
				dc.setDocNum(rs.getString("docNum"));
				doctor.add(dc);
			}
		} catch(Exception e) {
			System.out.println("doctor show : error");
		}
		if(doctor.size() != 0 && doctor.size() != 1) {
			for(int i = 0; i < doctor.size(); i++) {
				for(int j = 0; j < doctor.size(); j++) {
					if(i != j && doctor.get(i).getDocNum().equals(doctor.get(j).getDocNum())) {
						doctor.remove(j);
						j = -1;
					}
				}
			}
		}
		return doctor;
	}
	
	//doctorlist select
	public ArrayList<Doctor> show(String reDate) {
		ArrayList<Doctor> d = chk(reDate);
		ArrayList<Doctor> doctor = new ArrayList<>();
		try {
			ResultSet rs = null;
			if(d.size() == 0) {
				String sql = "select * from doctorList";
				Statement stmt;
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
			} else if(d.size() == 1) {
				String sql = "select * from doctorList where docName != ?";
				PreparedStatement psmt;
				psmt = con.prepareStatement(sql);
				psmt.setString(1, d.get(0).getDocName());
				rs = psmt.executeQuery();
			} else if(d.size() == 2) {
				String sql = "select * from doctorList where docName not in (?, ?)";
				PreparedStatement psmt;
				psmt = con.prepareStatement(sql);
				psmt.setString(1, d.get(0).getDocName());
				psmt.setString(2, d.get(1).getDocName());
				rs = psmt.executeQuery();
			} else if(d.size() == 3) {
				return null;
			}
			while(rs.next()) {
				Doctor dc = new Doctor();
				dc.setDocName(rs.getString("docName"));
				dc.setDocNum(rs.getString("docNum"));
				doctor.add(dc);
			}
		} catch(Exception e) {
			System.out.println("doctor show : error");
		}
		return doctor;
	}
	
	public Doctor select(String docNum) {
		Doctor doctor = null;
		try {
			String sql = "select * from doctorList where docNum = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, docNum);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Doctor d = new Doctor();
				d.setDocName(rs.getString("docName"));
				d.setDocNum(rs.getString("docNum"));
				d.setSubject(rs.getString("subject"));
				doctor = d;
			}
		} catch (Exception e){
			System.out.println("doctor select : error");
		}
		return doctor;
	}
	
	public ArrayList<Patient> selectAll(Doctor d) {
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = "select name, idNum,"
					+ "substr(reDate, 1, 14) as reDate from doctor where docNum = ? and disease is null order by reDate asc";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, d.getDocNum());
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				Recode r = new Recode();
				r.setReDate(rs.getString("reDate"));
				p.setRecode(r);
				patient.add(p);
			}
		} catch(Exception e) {
			System.out.println("doctor selectAll : error");
		}
		
		return patient;
	}
}
