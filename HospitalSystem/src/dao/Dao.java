package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private Connection connect;
	
	public DoctorDao dDao;//의사 관련 테이블에 접속하는 dao
	public PatientDao pDao; //환자 관련 테이블에 접속하는 dao
	public NurseDao nDao; //nurse가 관리해야할 테이블에 접속하는 dao
	public MemoDao mDao;
	
	public Dao() {
		driver();
		connect(connect);
	}
	
	//driver build
	private void driver() {
		try {
			Class.forName(driver);
			connection();
		} catch (Exception e) {
			System.out.println("driver : error");
		}
	}
	
	//connection 전달
	private void connect(Connection c) {
		dDao = new DoctorDao(c);
		pDao = new PatientDao(c);
		nDao = new NurseDao(c);
		mDao = new MemoDao(c);
	}
	
	//connection
	private void connection() {
		try {
			connect = DriverManager.getConnection(url, "System", "1111");
		} catch(Exception e) {
			System.out.println("connection : error");
		}
	}
}
