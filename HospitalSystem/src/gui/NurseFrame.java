package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.Dao;

public class NurseFrame implements ActionListener{
	NursePanel_1 nPanel_1 = new NursePanel_1();
	NursePanel_2 nPanel_2 = new NursePanel_2();
	NursePanel_3 nPanel_3 = new NursePanel_3();
	NursePanel_4 nPanel_4 = new NursePanel_4();
	Search sear = new Search();
	Dao dao = new Dao();
	
	JFrame frame = new JFrame();
	
	LineBorder line = new LineBorder(Color.black, 1); //�׵θ� ����
	
	JPanel exitMenu = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //����, �ڷΰ��� ��ư�� ���� �г�
	JButton exit = new JButton("����");
	JButton back = new JButton("�ڷΰ���");
	
	JPanel searPanel = new JPanel(); //�˻�â �г�
	JButton search = new JButton("�˻�"); //�˻���ư
	JTextField searchT = new JTextField(40); //�˻��ʵ�
	
	//frame on
	public void turn() {
		nPanel_1.nPanel_2 = this.nPanel_2;
		nPanel_1.nPanel_3 = this.nPanel_3;
		nPanel_3.dao = this.dao;
		
		nPanel_1.add();
		nPanel_2.button();
		nPanel_3.infoPanel();
		nPanel_4.titlePanel();
		nPanel_4.selectPanel();
		nPanel_4.moneyIn();
		nPanel_4.paymentIn();
		
		
		frame();
		frame.setVisible(true);
	}

	//�⺻ ������
	private void frame() {
		nPanel_1.centerPanel();
		search();
		nPanel_1.patient();
		exit();
		
		frame.setBounds(5,0,1200,750);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("nurse program");
		frame.setLayout(null);

		frame.add(searPanel);

		frame.add(nPanel_1.patient);
		frame.add(nPanel_2.allList);
		frame.add(nPanel_2.listMenu);
		frame.add(exitMenu);
		
		frame.add(nPanel_3.info);
		frame.add(nPanel_3.memo);
		frame.add(nPanel_3.memoList);
		frame.add(nPanel_4.title);
		frame.add(nPanel_4.select);
		frame.add(nPanel_4.money);
		frame.add(nPanel_4.payment);
	}
	
	//�ڷΰ���/ ���� ���
	private void exit() {
		exitMenu.setBounds(600,25,580,40);
		exitMenu.setBorder(line);
		exitMenu.add(back);
		exitMenu.add(exit);

		back.addActionListener(this);
		exit.addActionListener(this);
	}
	
	//�˻�â panel
	private void search() {
		JLabel searTitle = new JLabel("ȯ�� ��ȸ");
		searPanel.setBounds(5,25,590,40);
		searPanel.add(searTitle);
		searPanel.add(searchT);
		searPanel.add(search);
		searPanel.setBorder(line);
		
		search.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back)) {
			frame.setVisible(false);
			Login log = new Login();
			log.menu();
		} else if(e.getSource().equals(exit)) {
			System.exit(0);
		} else if(e.getSource().equals(search)) {
			sear.turn(searchT.getText());
			searchT.setText("");
		}
	}
}
