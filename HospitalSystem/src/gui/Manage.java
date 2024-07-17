package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dao;
import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class Manage {
	JFrame frame = new JFrame();
	JPanel north = new JPanel();
	JPanel south = new JPanel();
	
	JLabel[] insert = new JLabel[5];
	
	LineBorder line = new LineBorder(Color.black, 1); //테두리 설정
	
	String[] col = {"진료날짜","진료상태","수납여부","수납금","결제수단","담당의사","의사번호","진단명","처방"};
	String[][] row = new String[0][col.length];
	
	DefaultTableModel model = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	private String nowPatient = "";
	
	JTable list = new JTable(model);
	JScrollPane listSC = new JScrollPane(list);
	
	//frame on
	public void turn () {
		frame();
		frame.setVisible(true);
	}
	
	public void inPatient(Patient p) {
		for(int i = 0; i < insert.length; i++) {
			insert[i] = new JLabel();
		}
		
		insert[0].setText(p.getName());
		insert[1].setText(p.getIdNum());
		insert[2].setText(p.getTelNum());
		insert[3].setText(p.getGender());
		insert[4].setText(p.getAddr());
		
		nowPatient = p.getIdNum();
	}
	
	//기본프레임
	private void frame() {
		tableManage();
		northPanel();
		southPanel();
		
		frame.setBounds(0, 0, 900, 420);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		frame.setTitle("회원정보");
		
		frame.add(north);
		frame.add(south);
		
	}
	
	private void northPanel() {
		north.setBounds(10,10,865,150);
		north.setLayout(new GridLayout(5,0));
		
		JPanel[] panel = new JPanel[5];
		JLabel[] label = new JLabel[5];
		
		for(int i = 0;  i < panel.length; i++) {
			panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			north.add(panel[i]);
			panel[i].setBorder(line);
			
			label[i] = new JLabel();
			panel[i].add(label[i]);
			
			panel[i].add(insert[i]);
		}
		
		label[0].setText("이름 : ");
		label[1].setText("주민번호 : ");
		label[2].setText("전화번호 : ");
		label[3].setText("성별 : ");
		label[4].setText("주소 : ");
	}
	
	//jtable 출력 패널
	private void southPanel() {
		south.setBounds(10, 170, 865, 200);
		
		south.setLayout(new BorderLayout());
		
		JPanel listTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		listTitle.setBorder(line);
		
		south.add(listTitle,"North");
		
		JLabel title = new JLabel("진료 기록 <취소기록은 검색창에서 확인해주세요.>");
		
		listTitle.add(title);
		
		south.add(listSC,"Center");
	}
	
	//jtable 관리
	private void tableManage() {
		Dao dao = new Dao();
		ArrayList<Patient> patient = dao.nDao.select(nowPatient);
		for(int i = 0; i < patient.size(); i++) {
			Patient p = patient.get(i);
			Recode r = p.getRecode().get(0);
			Doctor d = r.getDc();
			String[] row1 = {r.getReDate(),r.getProgress(),r.getReceipt(), r.getMoney(), r.getPayment(),
					d.getDocName(), d.getDocNum(),r.getDisease(),r.getMedicine()};
			model.addRow(row1);
		}
	}
}
