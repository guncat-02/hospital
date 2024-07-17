package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Msg implements ActionListener{
	JFrame frame = new JFrame();
	JPanel north = new JPanel();
	JPanel south = new JPanel();
	JPanel center = new JPanel(new GridLayout(5,0));
	JPanel[] panel = new JPanel[5];
	
	JButton back = new JButton("뒤로가기");
	
	public void turn(String word) {
		frame(word);
		frame.setVisible(true);
	}
	
	private void frame(String word) {
		if(word.equals("오류")) {
			error();
		} else if(word.equals("접수오류")) {
			proError();
		} else if(word.equals("입력오류")) {
			inError();
		} else if(word.equals("검색")) {
			searError();
		} else if(word.equals("수납")) {
			payError();
		} else if(word.equals("로그인")) {
			logError();
		}
		frame.setBounds(0, 0, 400, 230);
		frame.setResizable(false);
		frame.setTitle("hospital program");
		frame.add(north, "North");
		frame.add(south, "South");
		frame.add(center, "Center");
		
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		back.addActionListener(this);
	}
	
	private void searError() {
		center.removeAll();

		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		
		JLabel msg = new JLabel("검색 결과가 없습니다.");
		JLabel msg2 = new JLabel("다시 입력해주세요.");
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}
	
	private void error() {
		center.removeAll();
		JLabel msg = new JLabel("최초 방문자가 아닙니다.");
		JLabel msg2 = new JLabel("접수에서 다시 진행해주세요.");
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}
	
	private void proError() {
		center.removeAll();
		JLabel msg = new JLabel("접수 기록이 없습니다");
		JLabel msg2 = new JLabel("최초 등록 후 접수해주세요.");
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}
	
	private void inError() {
		center.removeAll();
		JLabel msg = new JLabel("입력되지 않은 항목이 존재합니다");
		JLabel msg2 = new JLabel("입력 후 등록해주세요.");
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}
	
	private void payError() {
		center.removeAll();
		JLabel msg = new JLabel("수납금 입력이 완료된 환자입니다.");
		JLabel msg2 = new JLabel("수납금을 입력할 수 없습니다.");
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}

	//비밀번호, 아이디 오류
	private void logError() {
		center.removeAll();
		JLabel msg = new JLabel("존재하지 않는 아이디/비밀번호 입니다.");
		JLabel msg2 = new JLabel("다시 입력해주세요.");
		
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back)) {
			center.removeAll();
			frame.dispose();
		}
	}
}
