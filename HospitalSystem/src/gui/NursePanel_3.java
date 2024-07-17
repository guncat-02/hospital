package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dao;
import dto.Memo;
import dto.Patient;

public class NursePanel_3 implements ItemListener, MouseListener, KeyListener{
	Dao dao;
	JPanel info = new JPanel();
	JPanel memo = new JPanel();
	JPanel memoList = new JPanel();
	JPanel list = new JPanel();
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	String[] col = {"ȯ�ڸ�","�ֹι�ȣ","��ȭ��ȣ","����","�ּ�"};
	String[][] row = new String[0][col.length];
	
	DefaultTableModel model = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable patient = new JTable(model);
	JScrollPane pScroll = new JScrollPane(patient);
	
	JTextArea memoT = new JTextArea();
	JScrollPane memoScr = new JScrollPane(memoT);
	
	String[] col_2 = {"�ۼ���","�޸�"};
	String[][] row_2 = new String[0][col_2.length]; 
	
	DefaultTableModel dModel = new DefaultTableModel(row_2, col_2) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	DefaultTableModel nModel = new DefaultTableModel(row_2, col_2) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable memoTable = null;
	
	String[] sel = {"��ȣ��","�ǻ�"};
	JComboBox select = new JComboBox(sel);
	
	//ȯ�� ���� �г�
	public void infoPanel() {
		tableManage();
		memoIn();
		memoList();
		info.setBounds(5,365,590,140);
		info.setBorder(line);
		
		JPanel title = new JPanel();
		
		JLabel titleMg = new JLabel("ȯ������");
		
		title.add(titleMg);
		
		info.setLayout(new BorderLayout());
		
		info.add(title, "North");
		info.add(pScroll, "Center");
	}
	
	//�޸� �߰� �г�
	private void memoIn() {
		memo.setBounds(5,510,200,180);
		memo.setBorder(line);
		
		JPanel memoTitle = new JPanel();
		JPanel memoField = new JPanel();
		memoTitle.setBorder(line);
		
		JLabel mTitle = new JLabel("�޸� �ۼ���");
		
		memoTitle.add(mTitle);
		
		memoField.setLayout(new BoxLayout(memoField,BoxLayout.X_AXIS));
		memoField.add(memoScr);
		
		memoT.setEnabled(false);
		
		memoT.setText("");
		
		memoT.setLineWrap(true);
		memoT.addKeyListener(this);
		
		memo.setLayout(new BorderLayout());
		
		memo.add(memoTitle,"North");
		memo.add(memoField,"Center");
	}
	
	//�޸𸮽�Ʈ �г�
	public void memoList() {
		memoList.setBounds(210,510,385,180);
		memoList.setLayout(new BorderLayout());
		
		JPanel title = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		title.setBorder(line);
		title.add(select);
		
		memoList.add(title,"North");
		memoList.add(list,"Center");
		
		list.setLayout(new BoxLayout(list,BoxLayout.X_AXIS));
		
		list.setBorder(line);
		
		select.addItemListener(this);
		
		select.setEnabled(false);
		
		memoTable = new JTable(nModel);
		JScrollPane mScroll = new JScrollPane(memoTable);
		list.add(mScroll);
		
		
	}
	//���̺� ����
	private void tableDel(DefaultTableModel m) {
		while(m.getRowCount() != 0) {
			m.removeRow(0);
		}
	}
	
	//���̺� �� �߰�(��ȣ��)
	private void inTable(String idNum, String word) {
		Patient p = null;
		if(word.equals("��ȣ��")) {
			p = dao.mDao.select(idNum, word);
		} else if(word.equals("�ǻ�")) {
			p = dao.mDao.select(idNum, word);
		}
		if(p != null) {
			Memo m = p.getMemo();
			if(word.equals("��ȣ��")) {
				for(int i = 0; i < m.getNurMemo().size(); i++) {
					String[] row1 = {m.getNurDate().get(i), m.getNurMemo().get(i)};
					nModel.addRow(row1);
				}
			} else if(word.equals("�ǻ�")) {
				for(int i = 0; i < m.getDocMemo().size(); i++) {
					String[] row1 = {m.getDocDate().get(i), m.getDocDate().get(i)};
					dModel.addRow(row1);
				}
			}

		}
	}
	
	//���̺� ����
	public void tableManage() {
		tableDel(model);
		ArrayList<Patient> patient = dao.pDao.all();
		for(int i = 0; i < patient.size(); i++) {
			Patient p = patient.get(i);
			String[] row1 = {p.getName(), p.getIdNum(), p.getTelNum(), p.getGender(), p.getAddr()};
			model.addRow(row1);
		}
		this.patient.addMouseListener(this);
	}
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(select)) {
			if(select.getSelectedItem().equals("��ȣ��")) {
				memoTable = new JTable(nModel);
			} else if(select.getSelectedItem().equals("�ǻ�")) {
				memoTable = new JTable(dModel);
			}
			list.removeAll();
			
			JScrollPane mScroll = new JScrollPane(memoTable);
			list.add(mScroll);
			
			list.setVisible(false);
			list.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(patient)) {
			if(e.getClickCount() == 2) {
				select.setEnabled(true);
				tableDel(nModel);
				memoT.setEnabled(true);
				inTable(String.valueOf(patient.getValueAt(patient.getSelectedRow(), 1)), "��ȣ��");
				tableDel(dModel);
				inTable(String.valueOf(patient.getValueAt(patient.getSelectedRow(), 1)), "�ǻ�");
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

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource().equals(memoT)) {
			if(e.getKeyCode() == 10) {
				if(!memoT.getText().equals("")) {
					Patient p = new Patient();
					int rowNum = patient.getSelectedRow();
					p.setName(String.valueOf(patient.getValueAt(rowNum, 0)));
					p.setIdNum(String.valueOf(patient.getValueAt(rowNum, 1)));
					String inMemo = memoT.getText();
					dao.mDao.insert(p, inMemo);
					memoT.setText("");
					memoT.setEnabled(false);
					tableDel(nModel);
					inTable(String.valueOf(patient.getValueAt(patient.getSelectedRow(), 1)),"��ȣ��");
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}



	
	
	
}
