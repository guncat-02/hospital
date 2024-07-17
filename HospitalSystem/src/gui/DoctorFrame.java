package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dto.Doctor;

public class DoctorFrame {
	Doctor doctor = null;
	DoctorPanel_1 dPanel_1 = new DoctorPanel_1();
	JFrame frame = new JFrame();
	
	JPanel mainMenu = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	LineBorder line = new LineBorder(Color.black, 1); //테두리 설정
	
	JButton exit = new JButton("종료");
	JButton back = new JButton("뒤로가기");
	
	public void turn(Doctor d) {
		doctor = d;
		
		dPanel_1.titleManage(doctor);
		dPanel_1.patientPanel();
		dPanel_1.infoPanel();
		
		frame();
		frame.setVisible(true);
	}
	
	private void frame() {
		mainPanel();
		frame.setBounds(5,0,1200,750);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("doctor program");
		frame.setLayout(null);
		
		frame.add(dPanel_1.title);
		frame.add(dPanel_1.patient);
		frame.add(dPanel_1.info);
		frame.add(mainMenu);
	}
	
	private void mainPanel() {
		mainMenu.setBounds(10,10,1165,40);
		mainMenu.setBorder(line);
		
		mainMenu.add(back);
		mainMenu.add(exit);
	}
}
