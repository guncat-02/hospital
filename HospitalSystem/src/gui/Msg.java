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
	
	JButton back = new JButton("�ڷΰ���");
	
	public void turn(String word) {
		frame(word);
		frame.setVisible(true);
	}
	
	private void frame(String word) {
		if(word.equals("����")) {
			error();
		} else if(word.equals("��������")) {
			proError();
		} else if(word.equals("�Է¿���")) {
			inError();
		} else if(word.equals("�˻�")) {
			searError();
		} else if(word.equals("����")) {
			payError();
		} else if(word.equals("�α���")) {
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
		
		JLabel msg = new JLabel("�˻� ����� �����ϴ�.");
		JLabel msg2 = new JLabel("�ٽ� �Է����ּ���.");
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}
	
	private void error() {
		center.removeAll();
		JLabel msg = new JLabel("���� �湮�ڰ� �ƴմϴ�.");
		JLabel msg2 = new JLabel("�������� �ٽ� �������ּ���.");
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
		JLabel msg = new JLabel("���� ����� �����ϴ�");
		JLabel msg2 = new JLabel("���� ��� �� �������ּ���.");
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
		JLabel msg = new JLabel("�Էµ��� ���� �׸��� �����մϴ�");
		JLabel msg2 = new JLabel("�Է� �� ������ּ���.");
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
		JLabel msg = new JLabel("������ �Է��� �Ϸ�� ȯ���Դϴ�.");
		JLabel msg2 = new JLabel("�������� �Է��� �� �����ϴ�.");
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			center.add(panel[i]);
		}
		panel[1].add(msg);
		panel[2].add(msg2);
		panel[3].add(back);
	}

	//��й�ȣ, ���̵� ����
	private void logError() {
		center.removeAll();
		JLabel msg = new JLabel("�������� �ʴ� ���̵�/��й�ȣ �Դϴ�.");
		JLabel msg2 = new JLabel("�ٽ� �Է����ּ���.");
		
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
