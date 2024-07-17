package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.Dao;
import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class Manage_2 implements ActionListener{
	NursePanel_2 nurse = null;
	Combobox combo;
	
	private JFrame frame = new JFrame();
	private JPanel all = new JPanel();
	private JPanel north = new JPanel();
	private JPanel south = new JPanel();
	
	private JPanel doctor = new JPanel();
	private JPanel save = new JPanel();
	
	private String reDate = "";
	private String docNum = "";
	
	private JButton update = new JButton("변경");
	private JButton exit = new JButton("종료");
	private JButton cancle = new JButton("취소");
	private JButton receipt = new JButton("접수");
	
	private LineBorder line = new LineBorder(Color.black, 1); //테두리 설정
	
	public JLabel[] insert = new JLabel[5];
	
	public JTextField docNumT = new JTextField(20);
	
	private JTextArea reasonT = new JTextArea();
	private JScrollPane reasonScroll = new JScrollPane(reasonT);
	
	public void turn (String word) {
		frame(word);
		frame.setVisible(true);
	}
	
	//버튼 action
	private void actionSave() {
		receipt.removeActionListener(this);
		update.removeActionListener(this);
		exit.removeActionListener(this);
		cancle.removeActionListener(this);
		
		update.addActionListener(this);
		exit.addActionListener(this);
		cancle.addActionListener(this);
		receipt.addActionListener(this);
	}
	
	//기본프레임
	private void frame(String word) {
		north.setBounds(10, 10, 465, 125);

		north.setLayout(new GridLayout(5,0));

		south.setBounds(10, 140, 465, 110);
		south.setBorder(line);
		south.setLayout(new BorderLayout());
		
		south.removeAll();
		northPanel();
		if(word.equals("예약")) {
			southPanel();
		} else if(word.equals("취소")) {
			southPanel_2();
		} else if(word.equals("접수")) {
			southPanel_3();
		}
		
		frame.setBounds(0,0,500,300);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);

		all.setBounds(0,0,500,300);
		all.setLayout(null);

		frame.add(all);
		
		all.add(north);
		all.add(south);
	}
	
	//라벨 작업
	public void label(Patient p) {
		docNum = p.getRecode().get(0).getDc().getDocNum();
		String date = "";
		for(int i = 0; i < insert.length; i++) {
			insert[i] = new JLabel();
		}
		
		reDate = p.getRecode().get(0).getReDate();
		
		for(int i = 0; i < reDate.length(); i++) {
			if(i == 4 || i == 6) {
				date += '/';
			} else if(i == 8) {
				date += "  ";
			} else if(i == 10) {
				date += ":";
			}
			date += reDate.charAt(i);
		}
		
		insert[0].setText(p.getName());
		insert[1].setText(p.getIdNum());
		insert[2].setText(date);
		insert[3].setText(p.getRecode().get(0).getProgress());
		insert[4].setText(p.getRecode().get(0).getDc().getDocName());
	}
	
	//north panel 관리
	private void northPanel() {
		JPanel[] panel = new JPanel[5];
		JLabel[] label = new JLabel[5];
		
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			north.add(panel[i]);
			panel[i].setBorder(line);
			label[i] = new JLabel();
			panel[i].add(label[i]);
			panel[i].add(insert[i]);
		}
		
		label[0].setText("환자명 : ");
		label[1].setText("주민번호 : ");
		label[2].setText("접수 / 예약일 :");
		label[3].setText("접수상태 : ");
		label[4].setText("의사이름 / 번호 : ");

		panel[4].add(new JLabel(" / "+docNum));
	}
	
	//south panel 관리
	private void southPanel() {
		combo.reDateList_4.removeAll();
		combo.doc_4.removeAll();
		actionSave();
		docNumT.setEnabled(false);
		
		combo.startDate_4();
		combo.time(4);
		
		combo.doctorStart(4);
		
		south.add(combo.reDateList_4,"North");
		
		JLabel upTitle = new JLabel("변경날짜");
		
		combo.reDateList_4.add(upTitle);
		combo.add(4);
		
		JLabel doctorUpdate = new JLabel("변경의사");
		
		JLabel doctorNum = new JLabel("의사번호");
		
		doctor.add(doctorUpdate);
		doctor.add(combo.doc_4);
		doctor.add(doctorNum);
		doctor.add(docNumT);
		
		south.add(doctor,"Center");
		
		save.add(update);
		save.add(exit);
		south.add(save,"South");
	}
	
	//취소패널
	private void southPanel_2() {
		actionSave();
		reasonT.setLineWrap(true);
		
		JPanel[] panel = new JPanel[3];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
		}
		
		reasonT.setText("");
		
		south.add(panel[0], "North");
		south.add(panel[1],"Center");
		south.add(panel[2], "South");
		
		JLabel reason = new JLabel("취소 사유");
		
		panel[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		panel[1].setLayout(new BoxLayout(panel[1],BoxLayout.X_AXIS));
		
		panel[0].add(reason);
		panel[1].add(reasonScroll);
		
		panel[2].add(cancle);
		panel[2].add(exit);
		
		south.add(panel[2],"South");
	}
	
	//접수 패널
	private void southPanel_3() {
		actionSave();
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		
		south.add(panel_2, "Center");
		
		JLabel msg = new JLabel("접수하시겠습니까?");
		
		south.add(panel_1,"North");
		
		panel_1.add(msg);
		
		panel_2.add(receipt);
		panel_2.add(exit);
		
	}
	
	//완료 패널
	private void complete() {
		all.removeAll();
		
		JPanel complete = new JPanel();
		complete.setBounds(10, 10, 465, 250);
		complete.setLayout(new GridLayout(4,0));
		
		JPanel[] panel = new JPanel[4];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			complete.add(panel[i]);
		}
		
		JLabel comple = new JLabel("완료되었습니다.");
		
		panel[1].add(comple);
		panel[2].add(exit);
		
		all.add(complete);
	}
	
	//north값 저장
	private Patient saveInfo() {
		Patient p = new Patient();
		Recode r = new Recode();
		p.setName(insert[0].getText());
		p.setIdNum(insert[1].getText());
		r.setReDate(reDate);
		p.setRecode(r);
		return p;
	}
	
	//날짜 정리
	private String dateArray() {
		String dateChk = "";
		for(int i = 0; i < reDate.length(); i++) {
			if(i < 8) {
				dateChk += reDate.charAt(i);
			}
		}
		return dateChk;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Dao dao = new Dao();
		//update버튼
		if(e.getSource().equals(update)) {
			if(!combo.year_4.getSelectedItem().equals("YEAR") && !combo.month_4.getSelectedItem().equals("MONTH") &&
					!combo.day_4.getSelectedItem().equals("DAY") && !combo.hour_4.getSelectedItem().equals("HOUR") &&
					!combo.minute_4.getSelectedItem().equals("MINUTE") && !docNumT.getText().equals("")) {
				String date = combo.dateSave(4);
				Patient p = saveInfo();
				Doctor d = new Doctor();
				d.setDocName(String.valueOf(combo.doctorList_4.getSelectedItem()));
				d.setDocNum(docNumT.getText());
				p.getRecode().get(0).setDc(d);
				dao.nDao.udpate(p, date, "예약", docNum);
				complete();
				all.setVisible(false);
				all.setVisible(true);
			}
		}
		
		//exit 버튼
		if(e.getSource().equals(exit)) {
			frame.dispose();
			all.removeAll();
			nurse.patientList(dateArray(), nurse.model_all, nurse.nowProgress);
		}
		
		//cancle 버튼
		if(e.getSource().equals(cancle)) {
			if(!reasonT.getText().equals("")) {
				Patient p = saveInfo();
				dao.nDao.delete(p, docNum);
				dao.nDao.insertCancel(p, reasonT.getText());
				complete();
				all.setVisible(false);
				all.setVisible(true);
			}
		}
		
		//receipt 버튼
		if(e.getSource().equals(receipt)) {
			Patient p = saveInfo();
			dao.nDao.update(p, docNum, "진행", null);
			complete();
			all.setVisible(false);
			all.setVisible(true);
		}
	}
}
