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
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	String[] col = {"���ᳯ¥","�������","��������","������","��������","����ǻ�","�ǻ��ȣ","���ܸ�","ó��"};
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
	
	//�⺻������
	private void frame() {
		tableManage();
		northPanel();
		southPanel();
		
		frame.setBounds(0, 0, 900, 420);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		frame.setTitle("ȸ������");
		
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
		
		label[0].setText("�̸� : ");
		label[1].setText("�ֹι�ȣ : ");
		label[2].setText("��ȭ��ȣ : ");
		label[3].setText("���� : ");
		label[4].setText("�ּ� : ");
	}
	
	//jtable ��� �г�
	private void southPanel() {
		south.setBounds(10, 170, 865, 200);
		
		south.setLayout(new BorderLayout());
		
		JPanel listTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		listTitle.setBorder(line);
		
		south.add(listTitle,"North");
		
		JLabel title = new JLabel("���� ��� <��ұ���� �˻�â���� Ȯ�����ּ���.>");
		
		listTitle.add(title);
		
		south.add(listSC,"Center");
	}
	
	//jtable ����
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
