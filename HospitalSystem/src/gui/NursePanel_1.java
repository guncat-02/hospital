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
	
	Msg msg = new Msg(); //오류메세지 관련 gui 클래스
	DoctorSelect docSelect = new DoctorSelect();
	
	GridBagLayout grid = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	LineBorder line = new LineBorder(Color.black, 1); //테두리 설정
	
	JPanel patient = new JPanel(); //환자 등록 창 전체 패널
	JPanel menu = new JPanel(new GridLayout(1,2)); //환자등록, 접수 등 메뉴 패널
	JPanel center = new JPanel(new BorderLayout()); //center panel(접수, 등록시 패널만 바꾸기 위해 설정)
	JPanel southMenu = new JPanel(); //저장, 접수 추가 패널
	JPanel north = new JPanel(grid); //환자 정보 관련 패널
	
	JButton save = new JButton("저장");
	JButton proSave = new JButton("접수");
	JButton sign = new JButton("환자 등록");
	JButton progress = new JButton("접수");
	
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
	
	JLabel chk = new JLabel("환자를 등록해주세요.");
	JLabel chk_2 = new JLabel("검색된 환자가 없습니다.");
	
	JTextArea addrT = new JTextArea();
	JScrollPane addrScroll = new JScrollPane(addrT);

	JRadioButton female = new JRadioButton("여");
	JRadioButton male = new JRadioButton("남");
	
	String[] col = {"환자명","주민번호","성별","전화번호","주소"};
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
	
	//환자 정보 입력 panel
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

	//환자정보 입력 관리
	private void signInsert_1() {	
		combo.doctorStart(1);
		
		JLabel name = new JLabel("환자명");
		JLabel doctor = new JLabel("진료의사");
		JLabel telNum = new JLabel("전화번호");
		JLabel gender = new JLabel("성별");
		JLabel idNum = new JLabel("주민번호");
		JLabel doctorNum = new JLabel("의사번호");
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
		chk.setText("환자를 등록해주세요.");
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
	
	//환자등록 panel
	private void signInsert_2() {
		JLabel reDate = new JLabel(" 방문일  ");
		JLabel addr = new JLabel("      주소       ");
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
	
	//환자 접수 panel
	public void proInsert_1() {
		JPanel p = new JPanel();
		JLabel idNum = new JLabel("주민번호");
		
		JPanel p1 = new JPanel();
		
		JLabel name = new JLabel("    환자명     ");
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

	//환자접수패널_2
	public void proInsert_2() {
		combo.start_2();
		combo.time(1);
		combo.add(2);
		
		combo.hour.setEnabled(false);
		combo.minute.setEnabled(false);
		
		tableChk(model);
		JPanel middle = new JPanel();
		JLabel title = new JLabel("   환자선택   ");
		JLabel doctorNum = new JLabel("  의사번호  ");

		center.add(middle,"Center");

		middle.setLayout(new BorderLayout());

		middle.add(title,"West");
		middle.add(selScroll,"Center");

		select.addMouseListener(this);

		JLabel reDate = new JLabel("날짜");

		JPanel south = new JPanel();

		middle.add(south,"South");

		south.add(reDate);
		south.add(combo.reDateList_2);

	}

	//환자 접수 관련 패널 메서드 호출
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

	//환자 등록 패널 관련 메서드 호출
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
	
	//jtable 값 삭제
	private void tableChk(DefaultTableModel m) {
		while(model.getRowCount() != 0) {
			model.removeRow(0);
		}
		chk_2.setText("검색된 환자가 없습니다.");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//sign, progress 버튼 입력 시 패널 바꿈
		if(e.getSource().equals(sign)) {
			sign.setEnabled(false);
			progress.setEnabled(true);
			centerPanel();
		} else if(e.getSource().equals(progress)) {
			sign.setEnabled(true);
			progress.setEnabled(false);
			proInsert();
		}

		//접수 프레임으로 이동
		if(e.getSource().equals(proSave)) {
			if(nameT_2.getText().equals("")) {
				msg.turn("접수오류");
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

		//save 클릭시 database에 저장하기 위해 dto에 값 저장
		if(e.getSource().equals(save)) {
			if(nameT.getText().equals("") || idNumT.getText().equals("") || idNumT2.getText().equals("") ||
					telNumT.getText().equals("") || telNumT2.getText().equals("")|| telNumT3.getText().equals("") ||
					docNumT.getText().equals("")) {
				msg.turn("입력오류");
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
				int count = combo.dao.pDao.chk(p); //환자 처음 등록시 동일 환자가 있는지 체크
				
				if(count > 0) { //동일 환자 있으면 오류메세지를 띄운다
					msg.turn("오류");
				} else { //없으면 테이블에 insert 작업
					combo.dao.pDao.insert(p);
					combo.dao.dDao.insert(p);
					chk.setText("접수되었습니다.");
					nPanel_2.patientList(days, nPanel_2.model, "접수");
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
		//idnumt2에 숫자 입력시 값에 따라 female, male 자동 선택
		if(e.getSource().equals(idNumT2)) {
			if(idNumT2.getText().length() == 1) {
				if(idNumT2.getText().equals("2") || idNumT2.getText().equals("4")) {
					female.setSelected(true);
				} else if(idNumT2.getText().equals("1") || idNumT2.getText().equals("3")) {
					male.setSelected(true);
				}
			}
		}
		
		//입력값에 따라 jtable에 행 추가
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
				chk_2.setText("검색 결과 : "+count+"건");
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
		//각 필드에 입력값 제한
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
		//female 체크 후 male 체크 시 female 체크 취소
		if(female.isSelected()) {
			if(e.getSource().equals(male) && male.isSelected()) {
				female.setSelected(false);
			}
		}
		//male 체크 후 female 체크 시 male 체크 취소
		if(male.isSelected()) {
			if(e.getSource().equals(female) && female.isSelected()) {
				male.setSelected(false);
			}
		}	
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		//클릭 시 값 입력
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
