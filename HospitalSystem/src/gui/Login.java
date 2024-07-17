package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.Dao;
import dto.Doctor;

public class Login implements ActionListener {
	Msg msg = new Msg();
	
	JFrame frame = new JFrame();
	JPanel north = new JPanel();
	JPanel south = new JPanel();
	JPanel center = new JPanel();

	JButton log = new JButton("로그인");
	JButton doctor = new JButton("의사");
	JButton nurse = new JButton("간호사");
	
	JTextField idt = new JTextField(20);
	JTextField pwt = new JTextField(20);
	JTextField number = new JTextField(20);
	
	JButton exit = new JButton("종료");
	JButton insert = new JButton("입력");
	
	//frame on
	public Login() {
		frame();
		frame.setVisible(true);
	}
	
	private void add() {
		log.removeActionListener(this);
		exit.removeActionListener(this);
		nurse.removeActionListener(this);
		doctor.removeActionListener(this);
		insert.removeActionListener(this);
		
		log.addActionListener(this);
		exit.addActionListener(this);
		nurse.addActionListener(this);
		doctor.addActionListener(this);
		insert.addActionListener(this);
	}
	
	//기본 프레임 구성
	private void frame() {
		add();
		frame.setBounds(0, 0, 400, 200);
		frame.setResizable(false);
		frame.setTitle("hospital program");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(north, "North");
		frame.add(south, "South");
		main();
		frame.add(center, "Center");
	}
	
	//메인 패널
	private void main() {
		JLabel title = new JLabel("프로그램에 접속하신 것을 환영합니다.");
		JLabel id = new JLabel("  ID ");
		JLabel pw = new JLabel("PW");
		
		center.setLayout(new GridLayout(4,0));
		
		JPanel[] panel =  new JPanel[4];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		panel[0].add(title);
		panel[1].add(id);
		panel[1].add(idt);
		panel[2].add(pw);
		panel[2].add(pwt);
		panel[3].add(log);
		panel[3].add(exit);
		
		idt.setText("");
		pwt.setText("");
	}
	
	//의사 간호사 버튼
	public void menu() {
		center.removeAll();
		center.setLayout(new GridLayout(3,0));
		JPanel[] panel =  new JPanel[3];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		panel[1].add(nurse);
		panel[1].add(doctor);
	}
	
	//의사 로그인
	private void docLogin() {
		center.removeAll();
		
		center.setLayout(new GridLayout(3,0));
		
		JPanel[] panel = new JPanel[3];
		
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		
		JLabel num = new JLabel("의사번호");
		panel[1].add(num);
		panel[1].add(number);
		panel[1].add(insert);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(idt.getText().equals("dntmdgns") && pwt.getText().equals("wkdcodbs")) {
			if(e.getSource().equals(log)) {
				center.removeAll();
				menu();
				frame.add(center,"Center");
			}
		} else {
			if(e.getSource().equals(log)) {
				msg.turn("로그인");
				idt.setText("");
				pwt.setText("");
			}
		}
		if(e.getSource().equals(nurse)) {
			NurseFrame nur = new NurseFrame();
			frame.dispose();
			nur.turn();
		} else if(e.getSource().equals(doctor)) {
			docLogin();
		}
		center.setVisible(false);
		center.setVisible(true);
		
		if(e.getSource().equals(exit)) {
			frame.dispose();
		}
		
		if(e.getSource().equals(insert)) {
			Dao dao = new Dao();
			Doctor doctor = dao.dDao.select(number.getText());
			if(doctor != null) {
				frame.dispose();
				JOptionPane.showMessageDialog(null, doctor.getDocName()+"님 환영합니다!","의사 접속", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "존재하지 않는 의사 번호입니다","의사번호 오류",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
