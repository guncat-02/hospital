package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dao;
import dto.Doctor;
import dto.Memo;
import dto.Patient;
import dto.Recode;

public class Search implements ItemListener, MouseListener{
	Dao dao = new Dao();
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	JFrame frame = new JFrame();
	
	JPanel result = new JPanel();
	JPanel patientList = new JPanel();
	JPanel searchList = new JPanel();
	JPanel nurse = new JPanel();
	JPanel doctor = new JPanel();
	JPanel recode = new JPanel();
	
	String[] col = {"ȯ�ڸ�","�ֹι�ȣ","��ȭ��ȣ","����","�ּ�"};
	String[][] row = new String[0][col.length];
	
	String[] col_2 = {"���� / ������","��������","��������","������","��������","����ǻ�","�ǻ��ȣ","���ܸ�","ó��"};
	String[][] row_2 = new String[0][col_2.length];
	
	String[] col_3 = {"�ۼ���","�޸�"};
	String[][] row_3 = new String[0][col_3.length];
	
	String[] col_5 = {"���� / ������","��������","��һ���"};
	String[][] row_5 = new String[0][col_5.length];
	
	DefaultTableModel model = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	DefaultTableModel model_2 = new DefaultTableModel(row_2, col_2) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	DefaultTableModel model_3 = new DefaultTableModel(row_3, col_3) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	DefaultTableModel model_4 = new DefaultTableModel(row_3, col_3) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	DefaultTableModel model_5 = new DefaultTableModel(row_5, col_5) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable pList = new JTable(model);
	JTable searList = new JTable(model_2);
	JTable canList = new JTable(model_5);
	JTable nurseMemo = new JTable(model_3);
	JTable doctorMemo = new JTable(model_4);
	
	JScrollPane scroll = new JScrollPane(pList);
	JScrollPane scroll_2 = new JScrollPane(searList);
	JScrollPane scroll_3 = new JScrollPane(nurseMemo);
	JScrollPane scroll_4 = new JScrollPane(doctorMemo);
	JScrollPane scroll_5 = new JScrollPane(canList);
	
	String[] sel = {"����","����","�Ϸ�","���"};
	JComboBox select = new JComboBox(sel);
	
	String nowProgress = "����";
	
	private void addAction() {
		select.addItemListener(this);
		
		pList.addMouseListener(this);
	}
	
	//frame on
	public void turn(String word) {
		if(insertPatient(word) > 0 && !word.equals("")) {
			frame(word);
			frame.setVisible(true);
		} else {
			Msg msg = new Msg();
			msg.turn("�˻�");
		}
	}
	
	//�⺻ ������
	private void frame(String word) {
		northPanel(word);
		patient();
		search();
		nurMemo();
		docMemo();
		addAction();
		
		frame.setBounds(5,0,1200,750);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		frame.add(result);
		frame.add(patientList);
		frame.add(searchList);
		frame.add(nurse);
		frame.add(doctor);
	}
	
	//�˻���� �˸�
	private void northPanel(String word) {
		result.removeAll();
		
		result.setBounds(20,20,1145,30);
		result.setBorder(line);
		result.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel title = new JLabel("< "+word+" > �� ���� �˻�����Դϴ�.");
		
		result.add(title);
	}
	
	//�˻���� �г�
	private void patient() {
		patientList.removeAll();
		
		patientList.setBounds(20,60,1145,150);
		patientList.setBorder(line);
		
		patientList.setLayout(new BoxLayout(patientList, BoxLayout.X_AXIS));
		
		patientList.add(scroll);
	}
	
	//�˻���� �г�_2
	private void search() {
		searchList.removeAll();
		del(model_2);
		searchList.setBounds(20,220,1145,200);
		searchList.setBorder(line);
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel title = new JLabel("ȯ�� ���");
		
		titlePanel.add(title);
		titlePanel.add(select);
		
		select.setEnabled(false);
		
		recode.setLayout(new BoxLayout(recode, BoxLayout.X_AXIS));
		recode.add(scroll_2);
		
		searchList.setLayout(new BorderLayout());
		
		searchList.add(titlePanel,"North");
		searchList.add(recode, "Center");
		
	}
	
	//��ȣ�� �޸� �г�
	private void nurMemo() {
		del(model_3);
		nurse.removeAll();
		nurse.setBounds(20,430,1145,130);
		nurse.setBorder(line);
		nurse.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel title = new JLabel("��ȣ�� �޸�");
		
		titlePanel.add(title);
		
		nurse.add(titlePanel,"North");
		nurse.add(scroll_3,"Center");
		
	}
	
	//�ǻ�޸� �г�
	private void docMemo() {
		del(model_4);
		doctor.removeAll();
		doctor.setBounds(20,570,1145,130);
		doctor.setBorder(line);
		doctor.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel title = new JLabel("�ǻ� �޸�");
		
		titlePanel.add(title);
		
		doctor.add(titlePanel,"North");
		doctor.add(scroll_4,"Center");
		
	}
	
	//���̺� ����
	private void del(DefaultTableModel m) {
		while(m.getRowCount() != 0) {
			m.removeRow(0);
		}
	}
	
	//ȯ�����̺� �� �߰�
	private int insertPatient(String word) {
		del(model);
		ArrayList<Patient> patient = dao.pDao.allList(word);
		for(int i = 0; i < patient.size(); i++) {
			Patient p = patient.get(i);
			String[] in = {p.getName(), p.getIdNum(), p.getTelNum(), p.getGender(), p.getAddr()};
			model.addRow(in);
		}
		return patient.size();
	}
	
	//�������̺� �� �߰�
	private void insertSearch(String idNum) {
		del(model_2);
		ArrayList<Patient> patient = dao.nDao.allSelect(idNum, nowProgress);
		for(int i = 0; i < patient.size(); i++) {
			Patient p = patient.get(i);
			Recode r = p.getRecode().get(0);
			Doctor d = r.getDc();
			String[] in = {r.getReDate(), r.getProgress(), r.getReceipt(), r.getMoney(), r.getPayment(),
					d.getDocName(), d.getDocNum(), r.getDisease(), r.getMedicine()};
			model_2.addRow(in);
		}
	}
	
	//������̺� �� �߰�
	private void insertCancle(String idNum) {
		del(model_5);
		ArrayList<Patient> patient = dao.nDao.selectCancle(null, idNum, false);
		for(int i = 0; i < patient.size(); i++) {
			Patient p = patient.get(i);
			Recode r = p.getRecode().get(0);
			String[] in = {r.getReDate(), r.getProgress(), r.getReason()};
			model_5.addRow(in);
		}
	}
	
	//�޸����̺� �� �߰�
	private void insertNurMemo(String idNum) {
		Patient p = dao.mDao.select(idNum, "��ȣ��");
		del(model_3);
		if(p != null) {
			Memo m = p.getMemo();
			for(int i = 0; i < m.getNurMemo().size(); i++) {
				String[] in = {m.getNurDate().get(i) ,m.getNurMemo().get(i)};
				model_3.addRow(in);
			}
		}
	}
	
	//�޸����̺� �� �߰�
	private void insertDocMemo(String idNum) {
		Patient p = dao.mDao.select(idNum, "�ǻ�");
		del(model_4);
		if(p != null) {
			Memo m = p.getMemo();
			for(int i = 0; i < m.getDocMemo().size(); i++) {
				String[] in = {m.getDocDate().get(i), m.getDocMemo().get(i)};
				model_4.addRow(in);
			}
		}
	}
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(select)) {
			nowProgress = String.valueOf(select.getSelectedItem());
			recode.removeAll();
			if(nowProgress.equals("���")) {
				insertCancle(String.valueOf(pList.getValueAt(pList.getSelectedRow(), 1)));
				recode.add(scroll_5);
			} else {
				insertSearch(String.valueOf(pList.getValueAt(pList.getSelectedRow(), 1)));
				recode.add(scroll_2);
			}
			recode.setVisible(false);
			recode.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(pList)) {
			if(e.getClickCount() == 2) {
				insertSearch(String.valueOf(pList.getValueAt(pList.getSelectedRow(), 1)));
				insertNurMemo(String.valueOf(pList.getValueAt(pList.getSelectedRow(), 1)));
				insertDocMemo(String.valueOf(pList.getValueAt(pList.getSelectedRow(), 1)));
				select.setEnabled(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
