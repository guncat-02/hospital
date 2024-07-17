package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dao;
import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class NursePanel_2 implements ActionListener, MouseListener{
	Combobox combo = new Combobox();
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	JPanel allList = new JPanel(new BorderLayout()); //ȯ�� ���� ��� ��ü �г�
	JPanel listMenu = new JPanel(); //���� ��� ���� �޴� �г�
	JPanel show = new JPanel(); //���� ��� �г�
	
	JPanel south = new JPanel();
	
	JPanel list = new JPanel();
	
	String nowProgress = "";
	
	JButton[] button = new JButton[5];
	
	JButton receipt = new JButton("����");
	JButton reserve = new JButton("����");
	JButton surgery = new JButton("������");
	JButton complete = new JButton("�Ϸ�");
	JButton cancle = new JButton("���");
	
	String[] col = {"�̸�","�ֹι�ȣ","HH:MM","��������","��������","������","����ǻ�","�ǻ��ȣ"};
	String[][] row = new String[0][col.length];
	
	DefaultTableModel model = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable patient = null;
	
	String[] col_2 = {"�̸�","�ֹι�ȣ","HH:MM","��������","��� ����"};
	String[][] row_2 = new String[0][col_2.length];
	
	DefaultTableModel model_2 = new DefaultTableModel(row_2, col_2) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JPopupMenu pop = new JPopupMenu();
	JMenuItem select = new JMenuItem("ȸ������ Ȯ��");
	JMenu update = new JMenu("���� ���� ����");
	JMenuItem[] progress = new JMenuItem[3];
	
	DefaultTableModel model_all = null;
	
	//button �۾�
	public void button() {
		allList();
		button[0] = receipt;
		button[1] = reserve;
		button[2] = surgery;
		button[3] = complete;
		button[4] = cancle;
		for(int i = 0; i < button.length; i++) {
			button[i].addActionListener(this);
			listMenu.add(button[i]);
		}
	}
	
	// ȯ�� ���� ���� �г� ����
	private void allList() {
		popup();
		south();
		combo.nPanel_2 = this;
		
		list.setLayout(new BoxLayout(list, BoxLayout.X_AXIS));
		
		allList.setBounds(600,105,580,250);
		allList.setLayout(new BorderLayout());
		allList.setBorder(line);
		allList.add(south,"South");
		allList.add(list,"Center");
		
		listMenu.setBounds(600,75,580,30);
		listMenu.setBorder(line);
		listMenu.setLayout(new GridLayout(1,5));
	}
	
	//�Ʒ� �޴� ����
	private void south() {
		combo.date(3);
		combo.add(3);
		
		south.setBorder(line);
		
		south.add(combo.reDateList_3);
		
		combo.year_3.setEnabled(false);
		combo.month_3.setEnabled(false);
		combo.day_3.setEnabled(false);
		
	}

	//jtable ����
	public void patientList(String reDate, DefaultTableModel m, String progress) {
		delTable(m);
		ArrayList<Patient> patientList = null;
		if(m.equals(model)) {
			patientList = combo.dao.nDao.allList(reDate, progress);
		} else if (m.equals(model_2)){
			patientList = combo.dao.nDao.selectCancle(reDate, null, true);
		}
		for(int i = 0; i < patientList.size(); i++) {
			Patient p = patientList.get(i);
			Recode r = patientList.get(i).getRecode().get(0);
			Doctor d = r.getDc();
			String date = "";
			for(int j = 0; j < r.getReDate().length(); j++) {
				if(j > 8) {
					date += r.getReDate().charAt(j);
				}
			}
			if(m.equals(model)) {
				String[] row1 = {p.getName(), p.getIdNum(), date, r.getProgress(),
						r.getPayment(), r.getMoney(), d.getDocName(), d.getDocNum()};
				m.addRow(row1);
			} else if(m.equals(model_2)) {
				String[] row1 = {p.getName(), p.getIdNum(), date, r.getProgress(),
						r.getReason()};
				m.addRow(row1);
			}
		}
	}
	
	//jtable ����
	public void delTable(DefaultTableModel m) {
		while(m.getRowCount() != 0) {
			m.removeRow(0);
		}
	}
	
	//date ���̰� �ϱ�
	private void dateTrue() {
		combo.year_3.setEnabled(true);
		combo.month_3.setEnabled(true);
		combo.day_3.setEnabled(true);
	}
	
	
	//��ư �Ⱥ��̰� �ϰų� ���̰� �ϱ�
	private void buttonFalse(int num) {
		nowProgress = button[num].getText();
		delTable(model);
		for(int i = 0; i < button.length; i++) {
			if(i == num) {
				button[i].setEnabled(false);
			} else {
				button[i].setEnabled(true);
			}
		}
		combo.reDateList_3.removeAll();
		south.removeAll();
		south();
		south.setVisible(false);
		south.setVisible(true);
	}
	
	//�˾�â ����
	private void popup() {
		pop.add(update);
		pop.add(select);
		
		select.removeActionListener(this);
		update.removeActionListener(this);
		select.addActionListener(this);
		update.addActionListener(this);
		
		for(int i = 0; i < progress.length; i++) {
			progress[i] = new JMenuItem();
			progress[i].addActionListener(this);
			update.add(progress[i]);
		}
		progress[0].setText("����");
		progress[1].setText("����");
		progress[2].setText("���");
	}
	
	//�˾�â ����2
	private void menuIt(int num) {
		update.setVisible(true);
		if(num == 0) {
			for(int i = 0; i < progress.length-1; i++) {
				if(num != i) {
					progress[i].setVisible(true);
				} else {
					progress[i].setVisible(false);
				}
			}
		} else if(num == 1) {
			for(int i = 0; i < progress.length-1; i++) {
				progress[i].setVisible(true);
			}
		} else {
			update.setVisible(false);
		}
	}
	
	//jtable ����
	private void createT(int num) {
		list.removeAll();
		
		if(num != 4) {
			patient = new JTable(model);
			model_all = model;
		} else {
			patient = new JTable(model_2);
			model_all = model_2;
		}
		
		JScrollPane pScroll = new JScrollPane(patient);
		
		list.add(pScroll);
		
		patient.setVisible(false);
		patient.setVisible(true);
		
		list.setVisible(false);
		list.setVisible(true);
		
		patient.addMouseListener(this);
	}
	
	//table �� ���� ����
	private Patient tableManage() {
		int rowNum = patient.getSelectedRow();
		Patient p = new Patient();
		Recode r = new Recode();
		Doctor d = new Doctor();
		p.setName(String.valueOf(patient.getValueAt(rowNum, 0)));
		p.setIdNum(String.valueOf(patient.getValueAt(rowNum, 1)));
		r.setProgress(String.valueOf(patient.getValueAt(rowNum, 3)));
		String date = combo.dateSave(3);
		String time = String.valueOf(patient.getValueAt(rowNum, 2));
		d.setDocName(String.valueOf(patient.getValueAt(rowNum, 6)));
		d.setDocNum(String.valueOf(patient.getValueAt(rowNum, 7)));
		for(int i = 0; i < time.length(); i++) {
			if(time.charAt(i) != ':') {
				date += time.charAt(i);
			}
		}
		r.setDc(d);
		r.setReDate(date);
		p.setRecode(r);
		return p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//�޴� ��ư �۵�
		if(e.getSource().equals(receipt)) {
			buttonFalse(0);
			dateTrue();
			menuIt(0);
			createT(0);
		} else if(e.getSource().equals(reserve)) {
			buttonFalse(1);
			dateTrue();
			menuIt(1);
			createT(1);
		} else if(e.getSource().equals(surgery)) {
			buttonFalse(2);
			dateTrue();
			menuIt(2);
			createT(2);
		} else if(e.getSource().equals(complete)) {
			buttonFalse(3);
			dateTrue();
			menuIt(3);
			createT(3);
		} else if(e.getSource().equals(cancle)) {
			buttonFalse(4);
			dateTrue();
			menuIt(4);
			createT(4);
		}
		
		//ȸ������ Ȯ��
		if(e.getSource().equals(select)) {
			if(patient.getSelectedRowCount() != 0) {
				int rowNum = patient.getSelectedRow();
				Manage m1 = new Manage();
				m1.inPatient(combo.dao.pDao.tableSelect(String.valueOf(patient.getValueAt(rowNum, 1))));
				m1.turn();
			}
		}
		
		//���� Ȯ��
		if(e.getSource().equals(progress[1])) {
			if(patient.getSelectedRowCount() != 0) {
				Manage_2 m2 = new Manage_2();
				m2.nurse = this;
				m2.combo = this.combo;
				m2.combo.m2 = m2;
				m2.label(tableManage());
				m2.turn(progress[1].getText());
			}
		}
		
		//���
		if(e.getSource().equals(progress[2])) {
			if(patient.getSelectedRowCount() != 0) {
				Manage_2 m2 = new Manage_2();
				m2.nurse = this;
				m2.combo = this.combo;
				m2.label(tableManage());
				m2.combo.m2 = m2;
				m2.turn(progress[2].getText());
			}
		}
		
		//����
		if(e.getSource().equals(progress[0])) {
			if(patient.getSelectedRowCount() != 0) {
				Manage_2 m2 = new Manage_2();
				m2.nurse = this;
				m2.combo = this.combo;
				m2.label(tableManage());
				m2.turn(progress[0].getText());
				m2.combo.m2 = m2;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//��Ŭ��
		if(e.getSource().equals(patient)) {
			if(SwingUtilities.isRightMouseButton(e)) {
				pop.show(patient, e.getX(), e.getY());
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
