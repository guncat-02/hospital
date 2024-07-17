package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
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

public class DoctorPanel_1 implements MouseListener{
	Dao dao = new Dao();
	JPanel patient = new JPanel();
	JPanel title = new JPanel();
	JPanel info = new JPanel();
	
	Doctor doctor = null;
	
	LineBorder line = new LineBorder(Color.black, 1); //테두리 설정
	
	String[] col = {"환자이름","주민번호","접수 /예약일"};
	String[][] row = new String[0][col.length];
	
	DefaultTableModel model = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable patientList = new JTable(model);
	
	JScrollPane scroll = new JScrollPane(patientList);
	
	String[] sel = {"간호사","의사"};
	JComboBox select = new JComboBox(sel);
	
	public void titleManage(Doctor d) {
		doctor = d;
		title.setBounds(10,55,300,30);
		title.setBorder(line);
		
		JLabel titleMsg = new JLabel(doctor.getDocName()+" 님 접속");
		
		title.add(titleMsg);
	}
	
	public void patientPanel() {
		tableIn();
		
		patient.setBounds(10,85,300,200);
		patient.setBorder(line);
		patient.setLayout(new BorderLayout());
		
		patient.add(scroll,"Center");
	}
	
	private void del(DefaultTableModel m) {
		while(m.getRowCount() != 0) {
			m.removeRow(0);
		}
	}
	
	private void tableIn() {
		del(model);
		ArrayList<Patient> patient = dao.dDao.selectAll(doctor);
		for(int i = 0; i < patient.size(); i++) {
			Patient p = patient.get(i);
			Recode r = p.getRecode().get(0);
			String[] in = {p.getName(), p.getIdNum(), r.getReDate()};
			model.addRow(in);
		}
	}
	
	public void infoPanel() {	
		info.setBounds(10,290,300,200);
		info.setBorder(line);
		info.setLayout(new BorderLayout());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(patientList)) {
			if(patientList.isRowSelected(0)) {
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
