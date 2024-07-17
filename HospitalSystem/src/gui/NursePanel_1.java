package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class NursePanel_1 implements ActionListener, KeyListener, ItemListener, MouseListener{
	NursePanel_2 nPanel_2 = null;
	NursePanel_3 nPanel_3 = null;
	Combobox combo = new Combobox();
	
	Msg msg = new Msg(); //�����޼��� ���� gui Ŭ����
	DoctorSelect docSelect = new DoctorSelect();
	
	GridBagLayout grid = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	JPanel patient = new JPanel(); //ȯ�� ��� â ��ü �г�
	JPanel menu = new JPanel(new GridLayout(1,2)); //ȯ�ڵ��, ���� �� �޴� �г�
	JPanel center = new JPanel(new BorderLayout()); //center panel(����, ��Ͻ� �гθ� �ٲٱ� ���� ����)
	JPanel southMenu = new JPanel(); //����, ���� �߰� �г�
	JPanel north = new JPanel(grid); //ȯ�� ���� ���� �г�
	
	JButton save = new JButton("����");
	JButton proSave = new JButton("����");
	JButton sign = new JButton("ȯ�� ���");
	JButton progress = new JButton("����");
	
	JTextField nameT = new JTextField(20);
	JTextField idNumT = new JTextField(9);
	JTextField idNumT2 = new JTextField(9);
	JTextField telNumT = new JTextField(5);
	JTextField telNumT2 = new JTextField(5);
	JTextField telNumT3 = new JTextField(5);
	JTextField docNumT = new JTextField(20);
	
	JTextField nameT_2 =  new JTextField(20);
	JTextField idNumT_1 = new JTextField(9);
	JTextField idNumT_2 = new JTextField(9);
	
	JLabel chk = new JLabel("ȯ�ڸ� ������ּ���.");
	JLabel chk_2 = new JLabel("�˻��� ȯ�ڰ� �����ϴ�.");
	
	JTextArea addrT = new JTextArea();
	JScrollPane addrScroll = new JScrollPane(addrT);

	JRadioButton female = new JRadioButton("��");
	JRadioButton male = new JRadioButton("��");
	
	String[] col = {"ȯ�ڸ�","�ֹι�ȣ","����","��ȭ��ȣ","�ּ�"};
	String[][] row = new String[0][col.length];
	DefaultTableModel model = new DefaultTableModel(row, col) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	JTable select = new JTable(model);
	JScrollPane selScroll = new JScrollPane(select);
	
	//interface add
	public void add() {
		proSave.addActionListener(this);
		sign.addActionListener(this);
		progress.addActionListener(this);
		save.addActionListener(this);
	}
	
	//ȯ�� ���� �Է� panel
	public void patient() {
		combo.nPanel_1 = this;
		
		southMenu.setBorder(line);
		patient.setBounds(5,75,590,280);
		patient.setLayout(new BorderLayout());
		patient.add(menu,"North");
		patient.add(center, "Center");
		patient.add(southMenu,"South");
		menu.add(sign);
		menu.add(progress);
		menu.setBorder(line);
		center.setBorder(line);
	}

	//ȯ������ �Է� ����
	private void signInsert_1() {	
		combo.doctorStart(1);
		
		JLabel name = new JLabel("ȯ�ڸ�");
		JLabel doctor = new JLabel("�����ǻ�");
		JLabel telNum = new JLabel("��ȭ��ȣ");
		JLabel gender = new JLabel("����");
		JLabel idNum = new JLabel("�ֹι�ȣ");
		JLabel doctorNum = new JLabel("�ǻ��ȣ");
		combo.date(1);
		combo.time(1);
		sign.setEnabled(false);
		JPanel sex = new JPanel();
		center.add(north , "North");

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(0,3,0,5);
		grid.setConstraints(name, c);

		north.add(name);

		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		grid.setConstraints(nameT, c);

		north.add(nameT);

		c.gridx = 2;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,8);
		grid.setConstraints(gender, c);

		north.add(gender);

		c.gridx = 3;
		c.fill = GridBagConstraints.BOTH;
		grid.setConstraints(sex, c);

		north.add(sex);
		sex.add(female);
		sex.add(male);

		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(0,3,0,5);
		grid.setConstraints(telNum, c);

		north.add(telNum);

		JPanel telNumP = new JPanel();
		telNumP.setLayout(new BoxLayout(telNumP, BoxLayout.X_AXIS));
		JLabel hypen2 = new JLabel("  -  ");
		JLabel hypen3 = new JLabel("  -  ");
		telNumP.add(telNumT);
		telNumP.add(hypen2);
		telNumP.add(telNumT2);
		telNumP.add(hypen3);
		telNumP.add(telNumT3);

		telNumT.addKeyListener(this);
		telNumT2.addKeyListener(this);
		telNumT3.addKeyListener(this);

		c.gridx = 1;
		c.fill = GridBagConstraints.BOTH;
		grid.setConstraints(telNumP, c);

		north.add(telNumP);

		c.gridx = 2;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,8);
		grid.setConstraints(idNum, c);

		north.add(idNum);

		JPanel jumin = new JPanel();
		jumin.setLayout(new BoxLayout(jumin, BoxLayout.X_AXIS));
		JLabel hypen = new JLabel("  -   ");
		jumin.add(idNumT);
		jumin.add(hypen);
		jumin.add(idNumT2);

		idNumT.addKeyListener(this);
		idNumT2.addKeyListener(this);

		c.gridx = 3;
		c.fill = GridBagConstraints.BOTH;
		grid.setConstraints(jumin, c);

		north.add(jumin);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5,3,0,5);
		c.fill = GridBagConstraints.CENTER;
		grid.setConstraints(doctor, c);

		north.add(doctor);

		c.gridx = 1;
		c.fill = GridBagConstraints.BOTH;

		grid.setConstraints(combo.doc_1, c);

		north.add(combo.doc_1);

		c.gridx = 2;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5,0,0,8);
		grid.setConstraints(doctorNum, c);

		north.add(doctorNum);

		c.gridx = 3;
		c.fill = GridBagConstraints.CENTER;
		grid.setConstraints(docNumT, c);

		north.add(docNumT);
		
		female.addItemListener(this);
		male.addItemListener(this);

		nameT.setText("");
		idNumT.setText("");
		idNumT2.setText("");
		telNumT.setText("");
		telNumT2.setText("");
		telNumT3.setText("");
		addrT.setText("");
		chk.setText("ȯ�ڸ� ������ּ���.");
		docNumT.setText("");
		
		docNumT.setEnabled(false);
		female.setSelected(false);
		male.setSelected(false);
		
		idNumT.addKeyListener(this);
		idNumT2.addKeyListener(this);
		telNumT.addKeyListener(this);
		telNumT2.addKeyListener(this);
		telNumT3.addKeyListener(this);

	}
	
	//ȯ�ڵ�� panel
	private void signInsert_2() {
		JLabel reDate = new JLabel(" �湮��  ");
		JLabel addr = new JLabel("      �ּ�       ");
		JPanel middle = new JPanel();
		middle.setLayout(new GridLayout(1,2,10,0));

		addrT.setLineWrap(true);

		JPanel[] panel = new JPanel[2];

		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel(new BorderLayout());
			middle.add(panel[i]);
		}


		panel[0].add(addr,"West");
		panel[0].add(addrScroll,"Center");

		JPanel n = new JPanel();

		panel[0].add(n,"North");
		
		combo.add(1);
		
		panel[1].add(reDate,"West");
		panel[1].add(combo.reDateList_1,"Center");

		JPanel n1 = new JPanel();

		panel[1].add(n1,"North");

		center.add(middle,"Center");
	}
	
	//ȯ�� ���� panel
	public void proInsert_1() {
		JPanel p = new JPanel();
		JLabel idNum = new JLabel("�ֹι�ȣ");
		
		JPanel p1 = new JPanel();
		
		JLabel name = new JLabel("    ȯ�ڸ�     ");
		JPanel north = new JPanel(new GridLayout(1,2,5,0));
		JLabel hypen = new JLabel("-");
		JPanel[] panel = new JPanel[4];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			if(i < 2) {
				north.add(panel[i]);
				panel[i].setLayout(new BorderLayout());
			}
		}
		
		center.add(north, "North");
		center.add(p,"East");
		
		panel[0].add(panel[2],"Center");
		panel[0].add(name,"West");
		
		panel[2].add(nameT_2);
		
		panel[1].add(idNum,"West");
		
		panel[1].add(panel[3],"Center");
		
		panel[3].add(idNumT_1);	
		panel[3].add(hypen);		
		panel[3].add(idNumT_2);
		
		nameT_2.addKeyListener(this);
		
		nameT_2.setText("");
		idNumT_1.setText("");
		idNumT_2.setText("");
		
		idNumT_1.setEnabled(false);
		idNumT_2.setEnabled(false);
		
		idNumT_1.addKeyListener(this);
		idNumT_2.addKeyListener(this);
	}

	//ȯ�������г�_2
	public void proInsert_2() {
		combo.start_2();
		combo.time(1);
		combo.add(2);
		
		combo.hour.setEnabled(false);
		combo.minute.setEnabled(false);
		
		tableChk(model);
		JPanel middle = new JPanel();
		JLabel title = new JLabel("   ȯ�ڼ���   ");
		JLabel doctorNum = new JLabel("  �ǻ��ȣ  ");

		center.add(middle,"Center");

		middle.setLayout(new BorderLayout());

		middle.add(title,"West");
		middle.add(selScroll,"Center");

		select.addMouseListener(this);

		JLabel reDate = new JLabel("��¥");

		JPanel south = new JPanel();

		middle.add(south,"South");

		south.add(reDate);
		south.add(combo.reDateList_2);

	}

	//ȯ�� ���� ���� �г� �޼��� ȣ��
	public void proInsert() {
		north.removeAll();
		center.removeAll();
		combo.reDateList_1.removeAll();
		combo.reDateList_2.removeAll();
		combo.doc_1.removeAll();
		southMenu.removeAll();
		JPanel chkP = new JPanel();
		chkP.add(chk_2);
		center.add(chkP,"South");
		southMenu.add(proSave);
		proInsert_1();
		proInsert_2();
	}

	//ȯ�� ��� �г� ���� �޼��� ȣ��
	public void centerPanel() {
		north.removeAll();
		center.removeAll();
		combo.reDateList_1.removeAll();
		combo.reDateList_2.removeAll();
		combo.doc_1.removeAll();
		southMenu.removeAll();
		southMenu.add(save);
		signInsert_1();
		signInsert_2();
		JPanel southMsg = new JPanel();
		southMsg.add(chk);
		center.add(southMsg, "South");
	}
	
	//jtable �� ����
	private void tableChk(DefaultTableModel m) {
		while(model.getRowCount() != 0) {
			model.removeRow(0);
		}
		chk_2.setText("�˻��� ȯ�ڰ� �����ϴ�.");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//sign, progress ��ư �Է� �� �г� �ٲ�
		if(e.getSource().equals(sign)) {
			sign.setEnabled(false);
			progress.setEnabled(true);
			centerPanel();
		} else if(e.getSource().equals(progress)) {
			sign.setEnabled(true);
			progress.setEnabled(false);
			proInsert();
		}

		//���� ���������� �̵�
		if(e.getSource().equals(proSave)) {
			if(nameT_2.getText().equals("")) {
				msg.turn("��������");
				proInsert();
			} else {
				Patient p = new Patient();
				Recode r = new Recode();
				p.setName(nameT_2.getText());
				p.setIdNum(idNumT_1.getText()+idNumT_2.getText());
				String date = combo.dateSave(2);
				r.setReDate(date);
				p.setRecode(r);
				docSelect.nPanel_2 = this.nPanel_2;
				docSelect.label(p);
				docSelect.turn();
				proInsert();
			}
		}

		//save Ŭ���� database�� �����ϱ� ���� dto�� �� ����
		if(e.getSource().equals(save)) {
			if(nameT.getText().equals("") || idNumT.getText().equals("") || idNumT2.getText().equals("") ||
					telNumT.getText().equals("") || telNumT2.getText().equals("")|| telNumT3.getText().equals("") ||
					docNumT.getText().equals("")) {
				msg.turn("�Է¿���");
			} else {
				String days = "";
				days += combo.year.getSelectedItem();
				days += combo.month.getSelectedItem();
				days += combo.day.getSelectedItem();
				Patient p = new Patient();
				Recode r = new Recode();
				Doctor d = new Doctor();
				p.setName(nameT.getText());
				p.setIdNum(idNumT.getText()+idNumT2.getText());
				p.setAddr(addrT.getText());
				p.setTelNum(telNumT.getText()+telNumT2.getText()+telNumT3.getText());
				r.setReDate(combo.dateSave(1));
				d.setDocName(String.valueOf(combo.doctorList.getSelectedItem()));
				d.setDocNum(docNumT.getText());
				
				if(female.isSelected()) {
					p.setGender("F");
				} else if(male.isSelected()) {
					p.setGender("M");
				}
				
				r.setDc(d);
				p.setRecode(r);
				centerPanel();
				int count = combo.dao.pDao.chk(p); //ȯ�� ó�� ��Ͻ� ���� ȯ�ڰ� �ִ��� üũ
				
				if(count > 0) { //���� ȯ�� ������ �����޼����� ����
					msg.turn("����");
				} else { //������ ���̺� insert �۾�
					combo.dao.pDao.insert(p);
					combo.dao.dDao.insert(p);
					chk.setText("�����Ǿ����ϴ�.");
					nPanel_2.patientList(days, nPanel_2.model, "����");
					nPanel_3.tableManage();
				}
			}
		}
		
		center.setVisible(false);
		center.setVisible(true);
		southMenu.setVisible(false);
		southMenu.setVisible(true);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		//idnumt2�� ���� �Է½� ���� ���� female, male �ڵ� ����
		if(e.getSource().equals(idNumT2)) {
			if(idNumT2.getText().length() == 1) {
				if(idNumT2.getText().equals("2") || idNumT2.getText().equals("4")) {
					female.setSelected(true);
				} else if(idNumT2.getText().equals("1") || idNumT2.getText().equals("3")) {
					male.setSelected(true);
				}
			}
		}
		
		//�Է°��� ���� jtable�� �� �߰�
		if(e.getSource().equals(nameT_2)) {
			combo.reDateList_1.removeAll();
			combo.reDateList_2.removeAll();
			combo.time(1);
			combo.start_2();
			combo.add(2);
			tableChk(model);
			if(nameT_2.getText().length() != 0) {
				ArrayList<Patient> patient = combo.dao.pDao.allList(nameT_2.getText());
				for(int i = 0; i < patient.size(); i++) {
					Patient p = patient.get(i);
					String[] row_1 = {p.getName(), p.getIdNum(), p.getGender(), p.getTelNum(), p.getAddr()};
					model.addRow(row_1);
				}
				select.setVisible(false);
				select.setVisible(true);
			}
			int count = model.getRowCount();
			if(count > 0) {
				chk_2.setText("�˻� ��� : "+count+"��");
			}
		}
		
		if(e.getSource().equals(idNumT) || e.getSource().equals(idNumT2) || e.getSource().equals(idNumT_1) ||
				e.getSource().equals(idNumT_2) || e.getSource().equals(telNumT) || e.getSource().equals(telNumT2) ||
				e.getSource().equals(telNumT3)) {
			JTextField text = (JTextField)e.getSource();
			text.setText(text.getText().replaceAll("[^0-9]", ""));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//�� �ʵ忡 �Է°� ����
		if(e.getSource().equals(telNumT)) {
			if(telNumT.getText().length() > 2) {
				e.consume();
			}
		} else if(e.getSource().equals(telNumT2)) {
			if(telNumT2.getText().length() > 3) {
				e.consume();
			}
		} else if(e.getSource().equals(telNumT3)) {
			if(telNumT3.getText().length() > 3) {
				e.consume();
			}
		} else if(e.getSource().equals(idNumT)) {
			if(idNumT.getText().length() > 5) {
				e.consume();
			}
		} else if(e.getSource().equals(idNumT2)) {
			if(idNumT2.getText().length() > 6) {
				e.consume();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		//female üũ �� male üũ �� female üũ ���
		if(female.isSelected()) {
			if(e.getSource().equals(male) && male.isSelected()) {
				female.setSelected(false);
			}
		}
		//male üũ �� female üũ �� male üũ ���
		if(male.isSelected()) {
			if(e.getSource().equals(female) && female.isSelected()) {
				male.setSelected(false);
			}
		}	
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		//Ŭ�� �� �� �Է�
		if(e.getSource().equals(select)) {
			if(e.getClickCount() == 2) {
				String chkNum = String.valueOf(select.getValueAt(select.getSelectedRow(), 1));
				String num = "";
				for(int i = 0; i < chkNum.length(); i++) {
					if(i < 6) {
						num += chkNum.charAt(i);
					} else if(chkNum.charAt(i) == '-') {
						idNumT_1.setText(num);
						num = "";
					} else {
						num += chkNum.charAt(i);
					}
				}
				idNumT_2.setText(num);
				
				idNumT_1.setEnabled(false);
				idNumT_2.setEnabled(false);
				
				nameT_2.setText(String.valueOf(select.getValueAt(select.getSelectedRow(), 0)));
				
				combo.reDateList_2.remove(combo.year_2);
				
				combo.yearChk(combo.dateRemove(idNumT_1.getText()+"-"+idNumT_2.getText()), 2);
				
				combo.reDateList_2.add(combo.year_2, 0);
				
				combo.year_2.setVisible(false);
				combo.year_2.setVisible(true);
				
				combo.year_2.setEnabled(true);
				combo.month_2.setEnabled(true);
				combo.day_2.setEnabled(true);
				combo.hour.setEnabled(true);
				combo.minute.setEnabled(true);
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
