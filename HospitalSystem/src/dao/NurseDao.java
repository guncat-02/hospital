package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class NurseDao {
	Connection con;
	
	public NurseDao(Connection c) {
		con = c;
	}
	
	public ArrayList<Patient> select(String idNum) {
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = "select * from nurse where idNum = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, idNum);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				Recode r = new Recode();
				Doctor d = new Doctor();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				r.setReDate(rs.getString("reDate"));
				r.setProgress(rs.getString("progress"));
				r.setReceipt(rs.getString("receipt"));
				r.setMoney(rs.getString("money"));
				r.setPayment(rs.getString("payment"));
				d.setDocName(rs.getString("docName"));
				d.setDocNum(rs.getString("docNum"));
				r.setDisease(rs.getString("disease"));
				r.setMedicine(rs.getString("medicine"));
				r.setDc(d);
				p.setRecode(r);
				patient.add(p);
			}
		} catch (Exception e) {
			System.out.println("nurse select : error");
		}
		return patient;
	}

	//dual table 사용
	public int chk(String date) {
		int day = 0;
		try {
			String sql = "select substr(last_day(?), 7) as day from dual";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, date);
			ResultSet rs;
			rs = psmt.executeQuery();
			while(rs.next()) {
				day = Integer.valueOf(rs.getString("day"));
			}
		} catch (Exception e) {
			System.out.println("day select : error");
		}
		return day;
	}
	
	//reception table select
	public String chkReDate(String idNum) {
		String date = "";
		try {
			String sql = "select substr(b.reDate,1,8) as reDate from ( select rownum as rn, a.* from (select * from reception order by reDate asc ) a where idNum = ?) b where rn = 1";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idNum);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				date = rs.getString("reDate");
			}
		} catch(Exception e) {
			System.out.println("day chk : error");
		}
		return date;
	}
	
	//reception table에 insert
	public void insert(Patient p) {
		try {
			String sql = "insert into reception values(?, ?, ?, ?, default, default, default, ?, ?)";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getName());
			psmt.setString(2, p.getIdNum());
			psmt.setString(3, p.getRecode().get(0).getReDate());
			psmt.setString(4, p.getRecode().get(0).getProgress());
			psmt.setString(5, p.getRecode().get(0).getDc().getDocName());
			psmt.setString(6, p.getRecode().get(0).getDc().getDocNum());
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("nurse insert : error");
		}
	}
	
	// nurse table 
	public ArrayList<Patient> allList(String reDate, String progress) {
		String date = "%"+listChk(reDate)+"%";
		
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = "select * from nurse where reDate like ? and progress = ?";
			PreparedStatement psmt;
			psmt = con.prepareStatement(sql);
			psmt.setString(1, date);
			psmt.setString(2, progress);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				Recode r = new Recode();
				Doctor d = new Doctor();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				r.setReDate(rs.getString("reDate"));
				r.setProgress(rs.getString("progress"));
				r.setMoney(rs.getString("money"));
				r.setPayment(rs.getString("payment"));
				d.setDocName(rs.getString("docName"));
				d.setDocNum(rs.getString("docNum"));
				r.setDc(d);
				p.setRecode(r);
				
				patient.add(p);
			}
		} catch(Exception e) {
			System.out.println("nurse allList : error");
		}
		return patient;
	}
	
	//검색 키워드 정리
	public String listChk(String date) {
		String reDate = "";
		for(int i = 0; i < date.length(); i++) {
			if(i > 1) {
				reDate += date.charAt(i);
			}
		}
		date = "";
		for(int i = 0; i < reDate.length(); i++) {
			if(i%2 == 0 && i != 0) {
				date += "/";
			}
			date += reDate.charAt(i);
		}
		return date;
	}
	
	//reception table에 날짜 수정
	public void udpate(Patient p, String date, String progress, String docNum) {
		try {
			String sql = "update reception set reDate = ?, progress = ? where idnum = ? and reDate = ? and docNum = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, date);
			psmt.setString(2, progress);
			psmt.setString(3, p.getIdNum());
			psmt.setString(4, p.getRecode().get(0).getReDate());
			psmt.setString(5, docNum);
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("nurse update : error");
		}
	}
	
	//reception table update
	public void update(Patient p, String docNum, String word, String payment) {
		try {
			String sql = "";
			if(word.equals("진행")) {
				sql = "update reception set progress = ? where idNum = ? and reDate = ? and docNum = ?";
			} else if(word.equals("수납금")) {
				sql = "update reception set money = ? where idNum = ? and reDate = ? and docNum = ?";
			} else if(word.equals("결제")) {
				sql = "update reception set payment = ?, receipt = ? where idNum = ? and reDate = ? and docNum = ?";
			}
			PreparedStatement psmt = con.prepareStatement(sql);
			if(word.equals("결제")) {
				psmt.setString(1, payment);
				psmt.setString(2, "Y");
				psmt.setString(3, p.getIdNum());
				psmt.setString(4, p.getRecode().get(0).getReDate());
				psmt.setString(5, docNum);
			} else {
				if(word.equals("진행")) {
					psmt.setString(1, "접수");
				} else if(word.equals("수납금")){
					psmt.setString(1, p.getRecode().get(0).getMoney());
				}
				psmt.setString(2, p.getIdNum());
				psmt.setString(3, p.getRecode().get(0).getReDate());
				psmt.setString(4, docNum);
			}
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("nurse update_2 : error");
		}
	}
	
	//reception table에 delete
	public void delete(Patient p, String docNum) {
		try {
			String sql = "delete from reception where reDate = ? and docNum = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getRecode().get(0).getReDate());
			psmt.setString(2, docNum);
			psmt.executeQuery();
		} catch (Exception e){
			System.out.println("nurse delete : error");
		}
	}
	
	//cancle table에 insert
	public void insertCancel(Patient p, String reason) {
		try {
			String sql = "insert into cancle values(?, ?, ?, default, ?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, p.getName());
			psmt.setString(2, p.getIdNum());
			psmt.setString(3, p.getRecode().get(0).getReDate());
			psmt.setString(4, reason);
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("nurse insertCancle : error");
		}
	}
	
	//cancle 취소
	public ArrayList<Patient> selectCancle(String reDate, String idNum, boolean b) {
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = null;
			ResultSet rs;
			if(b) {
				String date = "%"+listChk(reDate)+"%";
				sql = "select name, idNum, substr(reDate, 1, 14) as reDate, progress, reason from cancle a where reDate like ? order by reDate desc";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, date);
				rs = psmt.executeQuery();
			} else {
				sql = "select name, idNum, substr(reDate, 1, 14) as reDate, progress, reason from cancle a where idNum = ? order by reDate desc";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, idNum);
				rs = psmt.executeQuery();
			}
			while(rs.next()) {
				Patient p = new Patient();
				Recode r = new Recode();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				r.setReDate(rs.getString("reDate"));
				r.setProgress(rs.getString("progress"));
				r.setReason(rs.getString("reason"));
				p.setRecode(r);
				patient.add(p);
			}
		} catch(Exception e) {
			System.out.println("nurse selectCancle : error");
		}
		return patient;
	}
	
	//reception 테이블 select
	public ArrayList<Patient> allSelect(String idNum, String progress) {
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = "select * from nurse where idNum = ? and progress = ?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, idNum);
			psmt.setString(2, progress);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				Recode r = new Recode();
				Doctor d = new Doctor();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				r.setReDate(rs.getString("reDate"));
				r.setReceipt(rs.getNString("receipt"));
				r.setProgress(rs.getNString("progress"));
				r.setMoney(rs.getString("money"));
				r.setPayment(rs.getString("payment"));
				d.setDocName(rs.getString("docName"));
				d.setDocNum(rs.getString("docNum"));
				r.setDisease(rs.getString("disease"));
				r.setMedicine(rs.getString("medicine"));
				r.setDc(d);
				p.setRecode(r);
				patient.add(p);
			}
		} catch (Exception e){
			System.out.println("nurse allSelect : error");
		}
		return patient;
	}
	
	//nurse에서 수납 찾기
	public ArrayList<Patient> searchReceipt(String word) {
		ArrayList<Patient> patient = new ArrayList<>();
		try {
			String sql = "select * from nurse where receipt = ? order by money asc, reDate desc";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, word);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient();
				Recode r = new Recode();
				Doctor d = new Doctor();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				r.setReDate(rs.getString("reDate"));
				r.setProgress(rs.getString("progress"));
				r.setReceipt(rs.getString("receipt"));
				r.setMoney(rs.getString("money"));
				r.setPayment(rs.getString("payment"));
				d.setDocName(rs.getString("docName"));
				d.setDocNum(rs.getString("docNum"));
				r.setDc(d);
				p.setRecode(r);
				patient.add(p);
			}
		} catch(Exception e) {
			System.out.println("nurse searchReceipt : error");
		}
		return patient;
	}
}
