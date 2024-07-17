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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.Dao;
import dto.Doctor;
import dto.Patient;
import dto.Recode;

public class DoctorSelect implements ItemListener, ActionListener{
	public NursePanel_2 nPanel_2 = null;
	JFrame frame = new JFrame();
	JPanel north = new JPanel();
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	JPanel com = new JPanel();
	
	JPanel all = new JPanel();
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	JButton exit = new JButton("����");
	JButton receipt = new JButton("����");
	
	JLabel getName = new JLabel();
	JLabel jumin = new JLabel();
	String reDate;
	JLabel dateIn = new JLabel();
	
	
	JTextField docNumT = new JTextField(20);
	
	JComboBox<String> allList;
	
	String[] progressIn = {"<����>","����","����"};
	
	JComboBox<String> proSel = new JComboBox<String>(progressIn);
	
	String days = null;
	
	//frame on
	public void turn() {
		frame();
		frame.setVisible(true);
	}
	
	//�� �� �ޱ�
	public void label(Patient p) {
		getName.setText(p.getName());
		jumin.setText(p.getIdNum());
		reDate = p.getRecode().get(0).getReDate();
		String chk = "";
		for(int i = 0; i < reDate.length(); i++) {
			if(i == 4 || i == 6) {
				chk += "/";
			} else if(i == 8) {
				chk += "   ";
			} else if(i == 10) {
				chk += ":";
			}
			chk += reDate.charAt(i);
		}
		array();
		dateIn.setText(chk);
	}
	
	//�⺻ ������
	private void frame() {
		frame.remove(com);
		
		all.removeAll();
		
		docChk();
		
		frame.add(all);
		
		all.setBounds(0,0,500,300);
		all.setLayout(null);
		
		frame.setBounds(0,0,500,300);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setTitle("receipt program");
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		centerPanel();
		southPanel();
		northPanel();
		
		all.add(north);
		all.add(center);
		all.add(south);
		
	}
	
	//ȯ�� ���� �г�
	private void northPanel() {
		north.removeAll();
		north.setLayout(new GridLayout(4,0));
		north.setBounds(10,10,465,100);
		
		JLabel title = new JLabel("ȯ�� ����");
		JLabel name = new JLabel("ȯ�ڸ� :");
		JLabel idNum = new JLabel("�ֹι�ȣ :");
		JLabel date = new JLabel("��¥ :");
		
		JPanel[] panel = new JPanel[4];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			north.add(panel[i]);
			panel[i].setBorder(line);
		}
		
		panel[0].setBackground(Color.pink);
		
		panel[0].add(title);
		panel[1].add(name);
		panel[1].add(getName);
		panel[2].add(idNum);
		panel[2].add(jumin);
		panel[3].add(date);
		panel[3].add(dateIn);
	}
	
	//�ǻ�, �������� ���� �г�
	private void centerPanel() {
		center.removeAll();
		center.setBounds(10,120,465,80);
		center.setLayout(new GridLayout(2,0));
		center.setBorder(line);
		
		JLabel docSel = new JLabel("�����ǻ�");
		JLabel docNum = new JLabel("�ǻ��ȣ");
		JLabel progress = new JLabel("��������");
		
		JPanel[] panel = new JPanel[2];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			panel[i].setBorder(line);
			panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			center.add(panel[i]);
		}
		
		panel[0].add(docSel);
		panel[0].add(allList);
		panel[0].add(docNum);
		panel[0].add(docNumT);
		
		docNumT.setText("");
		
		panel[1].add(progress);
		panel[1].add(proSel);
		
		docNumT.setEnabled(false);
		
	}
	
	//����, ���� ��ư �߰�
	private void southPanel() {
		south.removeAll();
		south.setBounds(10,210,465,50);
		south.add(receipt);
		south.add(exit);
		receipt.addActionListener(this);
		exit.addActionListener(this);
	}
	
	//��¥ ����
	private void array() {
		days = "";
		for(int i = 0; i < reDate.length(); i++) {
			if(i < 8) {
				days += reDate.charAt(i);
			}
		}
	}
	
	//���� Ȯ�� �г� �߰�
	private void complete() {
		all.removeAll();
		
		com.removeAll();
		
		com.setBounds(10,10,465,240);
		com.setLayout(new GridLayout(2,0));
		com.setBorder(line);
		
		JPanel[] panel = new JPanel[2];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			com.add(panel[i]);
			panel[i].setLayout(new BorderLayout());
		}
		
		JLabel complete = new JLabel("�����Ǿ����ϴ�.");
		complete.setHorizontalAlignment(JLabel.CENTER);
		panel[0].add(complete,"South");
		
		JPanel p = new JPanel();
		
		p.add(exit);
		
		panel[1].add(p,"North");
		
		all.add(com);
	}
	
	//�ǻ� combobox����
	private void docChk() {
		Dao dao = new Dao();
		ArrayList<Doctor> doctor = dao.dDao.show(reDate);
		String[] doctorList;
		if(doctor != null) {
			doctorList = new String[doctor.size()+1];
			doctorList[0] = "<�ǻ缱��>";
			for(int i = 0; i < doctor.size(); i++) {
				doctorList[i+1] = doctor.get(i).getDocName();
			}
		} else {
			doctorList = new String[1];
			doctorList[0] = "<�ǻ缱��>";
		}
		allList = new JComboBox<String>(doctorList);
		allList.addItemListener(this);
	}

	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//�ǻ� ���� �� �ڵ����� ��ȣ �Է�
		if(e.getSource().equals(allList)) {
			if(!allList.getSelectedItem().equals("<����>")) {
				Dao dao = new Dao();
				ArrayList<Doctor> doctor = dao.dDao.show(reDate);
				for(int i = 0; i < doctor.size(); i++) {
					if(allList.getSelectedItem().equals(doctor.get(i).getDocName())) {
						docNumT.setText(doctor.get(i).getDocNum());
						break;
					}
				}
			} else {
				docNumT.setText("");
			}
		}
		docNumT.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//������ ����
		if(e.getSource().equals(exit)) {
			frame.dispose();
			exit.removeActionListener(this);
			receipt.removeActionListener(this);
			String date = "";
			date += nPanel_2.combo.year_3.getSelectedItem();
			date += nPanel_2.combo.month_3.getSelectedItem();
			date += nPanel_2.combo.day_3.getSelectedItem();
			nPanel_2.patientList(date, nPanel_2.model, nPanel_2.nowProgress);
		}
		
		if(e.getSource().equals(receipt)) { //���� �� dao�� ����
			Dao dao = new Dao();
			Patient p = new Patient();
			Recode r = new Recode();
			Doctor d = new Doctor();
			p.setName(getName.getText());
			p.setIdNum(jumin.getText());
			r.setReDate(reDate);
			r.setProgress(String.valueOf(proSel.getSelectedItem()));
			d.setDocName(String.valueOf(allList.getSelectedItem()));
			d.setDocNum(docNumT.getText());
			r.setDc(d);
			p.setRecode(r);
			dao.nDao.insert(p);
			dao.dDao.insert(p);
			
			complete();
			
			all.setVisible(false);
			all.setVisible(true);
		}
	}
}

