package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Dao;
import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class NursePanel_4 implements ItemListener, MouseListener, ActionListener, KeyListener{
	Dao dao = new Dao();
	
	Msg msg = new Msg();
	
	JPanel title = new JPanel();
	JPanel center = new JPanel();
	JPanel select = new JPanel();
	JPanel money = new JPanel();
	JPanel payment = new JPanel();
	
	JLabel selInfo = new JLabel();
	JLabel chk = new JLabel();
	
	JButton pay = new JButton("����");
	JButton cancle = new JButton("���");
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	JRadioButton yes = new JRadioButton("Y");
	JRadioButton no = new JRadioButton("N");
	
	JRadioButton card = new JRadioButton("ī��");
	JRadioButton cash = new JRadioButton("����");
	JRadioButton bank = new JRadioButton("������ü");
	
	String paymentSel = "";
	
	String[] col = {"�̸�","�ֹι�ȣ","���� / ������","��������","��������","������","��������","����ǻ�","�ǻ��ȣ"};
	String[][] row = new String[0][col.length];
	
	DefaultTableModel model = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	DefaultTableModel model_2 = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable noPay = new JTable(model);
	JTable yesPay = new JTable(model_2);
	
	JScrollPane noScroll = new JScrollPane(noPay);
	JScrollPane yesScroll = new JScrollPane(yesPay);
	
	JButton insert = new JButton("�Է�");
	
	JTextField insertT = new JTextField(40);
	
	JPopupMenu pop = new JPopupMenu();
	JMenuItem receipt = new JMenuItem("����");
	
	//interface �߰�
	private void addAction() {
		insert.removeActionListener(this);
		insert.addActionListener(this);
		receipt.removeActionListener(this);
		receipt.addActionListener(this);
		pay.removeActionListener(this);
		pay.addActionListener(this);
		cancle.removeActionListener(this);
		cancle.addActionListener(this);
		
		insertT.addKeyListener(this);
		
		no.addItemListener(this);
		yes.addItemListener(this);
		card.addItemListener(this);
		cash.addItemListener(this);
		bank.addItemListener(this);
		
		noPay.addMouseListener(this);
		yesScroll.addMouseListener(this);
	}
	
	//���� ����Ʈ �г�
	public void titlePanel() {
		popManage();
		addAction();
		title.setBounds(600,365,580,140);
		
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		
		title.setLayout(new BorderLayout());
		
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north.setBorder(line);
		JLabel titleLabel = new JLabel("���� ����");
		
		title.add(north, "North");
		north.add(titleLabel);
		north.add(no);
		north.add(yes);
		
		center.add(noScroll);
		
		title.add(center, "Center");
		
		no.setSelected(true);
	}
	
	//ȯ�� ���� ���� �г�
	public void selectPanel() {
		select.setBounds(600,510,580,30);
		select.setBorder(line);
		selInfo.setText("ȯ�ڸ� �������ּ���.");
		
		select.add(selInfo);
	}
	
	//�����ݰ��� �г�
	public void moneyIn() {
		money.setBounds(600,545,580,40);
		money.setBorder(line);
		
		JLabel insertMoney = new JLabel("������");
		
		money.add(insertMoney);
		money.add(insertT);
		money.add(insert);
		
		insert.setEnabled(false);
	}
	
	//�������� �г�
	public void paymentIn() {
		payment.setBounds(600,590,580,100);
		payment.setLayout(new BorderLayout());
		payment.setBorder(line);
		
		JPanel north = new JPanel();
		north.setBorder(line);

		north.add(chk);
		
		JPanel middle = new JPanel();
		middle.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel msg = new JLabel("��������");
		middle.add(msg);
		middle.add(card);
		middle.add(cash);
		middle.add(bank);
		
		JPanel south = new JPanel(new GridLayout(0,2));

		south.add(pay);
		south.add(cancle);
		
		payment.add(north,"North");
		payment.add(middle,"Center");
		payment.add(south,"South");
		
		remove(false);

	}
	
	//���̺� �� ����
	private void del(DefaultTableModel m) {
		while(m.getRowCount() != 0) {
			m.removeRow(0);
		}
	}
	
	//���̺� �� �߰�
	private void inTable(String word) {
		del(model);
		del(model_2);
		selInfo.setText("ȯ�ڸ� �������ּ���.");
		remove(false);
		insert.setEnabled(false);
		yesPay.clearSelection();
		noPay.clearSelection();
		ArrayList<Patient> patient = dao.nDao.searchReceipt(word);
		for(int i = 0; i < patient.size(); i++) {
			Patient p = patient.get(i);
			Recode r = p.getRecode().get(0);
			Doctor d = r.getDc();
			String[] in = {p.getName(), p.getIdNum(), r.getReDate(), r.getProgress(), r.getReceipt(),
					r.getMoney(), r.getPayment(), d.getDocName(), d.getDocNum()};
			if(word.equals(yes.getText())) {
				model_2.addRow(in);
			} else if(word.equals(no.getText())) {
				model.addRow(in);
			}
		}
	}
	
	private void remove(boolean b) {
		card.setEnabled(b);
		cash.setEnabled(b);
		bank.setEnabled(b);
		pay.setEnabled(b);
		cancle.setEnabled(b);
		card.setSelected(false);
		cash.setSelected(false);
		bank.setSelected(false);
		chk.setText("���������� ���� ���ּ���.");
	}
	
	private void popManage() {
		pop.add(receipt);
		
		noPay.add(pop);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(no.isSelected()) {
			if(e.getSource().equals(yes) && yes.isSelected()) {
				no.setSelected(false);
			}
		}
		if(yes.isSelected()) {
			if(e.getSource().equals(no) && no.isSelected()) {
				yes.setSelected(false);
			}
		}
		
		if(cash.isSelected()) {
			chk.setText("������ ���õǾ����ϴ�.");
			paymentSel = cash.getText();
			if((e.getSource().equals(card) && card.isSelected()) || 
					(e.getSource().equals(bank) && bank.isSelected())) {
				cash.setSelected(false);
			}
		}
		if(card.isSelected()) {
			chk.setText("ī�尡 ���õǾ����ϴ�.");
			paymentSel = card.getText();
			if((e.getSource().equals(cash) && cash.isSelected()) || 
					(e.getSource().equals(bank) && bank.isSelected())) {
				card.setSelected(false);
			}
		}
		if(bank.isSelected()) {
			chk.setText("������ü�� ���õǾ����ϴ�.");
			paymentSel = bank.getText();
			if((e.getSource().equals(cash) && cash.isSelected()) || 
					(e.getSource().equals(card) && card.isSelected())) {
				bank.setSelected(false);
			}
		}
		
		if(no.isSelected() && e.getSource().equals(no)) {
			center.removeAll();
			center.add(noScroll);
			inTable(no.getText());
		} else if(yes.isSelected() && e.getSource().equals(yes)) {
			center.removeAll();
			center.add(yesScroll);
			inTable(yes.getText());
		}
		center.setVisible(false);
		center.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(noPay)) {
			if(e.getClickCount() == 2) {
				remove(false);
				insertT.setText("");
				int rowNum = noPay.getSelectedRow();
				String info = "";
				for(int i = 0; i < noPay.getColumnCount()-5; i++) {
					info += noPay.getValueAt(rowNum, i);
					info += "  ";
				}
				selInfo.setText(info);
				insert.setEnabled(true);
			}
		}
		//��Ŭ��
		if(e.getSource().equals(noPay)) {
			if(noPay.getSelectedRowCount() > 0) {
				if(SwingUtilities.isRightMouseButton(e)) {
					int rowNum = noPay.getSelectedRow();
					if(!String.valueOf(noPay.getValueAt(rowNum, 5)).equals("����")) {
						pop.show(noPay, e.getX(), e.getY());
					}
				}
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
	
	private Patient save(int rowNum) {
		Patient p = new Patient();
		p.setName(String.valueOf(noPay.getValueAt(rowNum, 0)));
		p.setIdNum(String.valueOf(noPay.getValueAt(rowNum, 1)));
		Recode r = new Recode();
		r.setReDate(String.valueOf(noPay.getValueAt(rowNum, 2)));
		r.setMoney(insertT.getText());
		p.setRecode(r);
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(insert)) {
			if(String.valueOf(noPay.getValueAt(noPay.getSelectedRow(), 5)).equals("����")) {
				if(insertT.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�������� �Է����ּ���.","������ �� �Է�",JOptionPane.ERROR_MESSAGE);
				} else {
					int rowNum = noPay.getSelectedRow();
					Patient p = save(rowNum);
					dao.nDao.update(p, String.valueOf(noPay.getValueAt(rowNum, 8)), "������", null);
					insertT.setText("");
					inTable(no.getText());
				}
			} else {
				msg.turn("����");
			}
		}
		
		if(e.getSource().equals(receipt)) {
			remove(true);
		}
		
		if(e.getSource().equals(pay)) {
			if(card.isSelected() || cash.isSelected() || bank.isSelected()) {
				int rowNum = noPay.getSelectedRow();
				Patient p = save(rowNum);
				dao.nDao.update(p, String.valueOf(noPay.getValueAt(rowNum, 8)), "����", paymentSel);
				inTable(no.getText());
				chk.setText("������ �Ϸ�Ǿ����ϴ�.");
			}
		} else if(e.getSource().equals(cancle)) {
			noPay.clearSelection();
			remove(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(insertT)) {
			insertT.setText(insertT.getText().replaceAll("[^0-9]", ""));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}


}
