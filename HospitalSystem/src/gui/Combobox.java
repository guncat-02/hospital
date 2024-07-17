package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import dao.Dao;
import dto.Doctor;

public class Combobox implements ItemListener{
	NursePanel_1 nPanel_1 = null;
	NursePanel_2 nPanel_2 = null;
	Dao dao = new Dao();
	Manage_2 m2 = null;
	
	JPanel reDateList_1 = new JPanel();
	JPanel reDateList_2 = new JPanel();
	JPanel reDateList_3 = new JPanel();
	JPanel reDateList_4 = new JPanel();
	
	JPanel doc_1 = new JPanel();// 의사 관련 combobox를 추가하는 패널
	JPanel doc_4 = new JPanel();
	
	JComboBox<String> doctorList; //의사 목록 combobox
	JComboBox<String> doctorList_4;
	
	JComboBox<String> year, month, day, hour, minute;

	JComboBox<String> year_2, month_2, day_2;
	
	JComboBox<String> year_3, month_3, day_3;
	
	JComboBox<String> year_4, month_4, day_4, hour_4, minute_4;
	
	//의사 콤보박스 초기값
	public void doctorStart(int num) {
		this.doc_1.setLayout(new BoxLayout(this.doc_1, BoxLayout.X_AXIS));
		this.doc_4.setLayout(new BoxLayout(this.doc_4, BoxLayout.X_AXIS));
		if(num == 1) {
			String[] doc = {"<의사선택>"};
			doctorList = new JComboBox<String>(doc);
			doctorList.addItemListener(this);
			this.doc_1.add(doctorList);
		} else if(num == 4) {
			String[] doc = {"<의사선택>"};
			doctorList_4 = new JComboBox<String>(doc);
			doctorList_4.addItemListener(this);
			this.doc_4.add(doctorList_4);
		}
	}
	
	//날짜 패널에 저장
	public void add(int num) {
		if(num == 1) {
			reDateList_1.add(year);
			reDateList_1.add(month);
			reDateList_1.add(day);
			reDateList_1.add(hour);
			reDateList_1.add(minute);
		} else if(num == 2) {
			reDateList_2.add(year_2);
			reDateList_2.add(month_2);
			reDateList_2.add(day_2);
			reDateList_2.add(hour);
			reDateList_2.add(minute);
		} else if(num == 3) {
			reDateList_3.add(year_3);
			reDateList_3.add(month_3);
			reDateList_3.add(day_3);
		} else if(num == 4) {
			reDateList_4.add(year_4);
			reDateList_4.add(month_4);
			reDateList_4.add(day_4);
			reDateList_4.add(hour_4);
			reDateList_4.add(minute_4);
		}
	}
	
	//날짜 저장
	public String dateSave(int num) {
		String date = "";
		if(num == 1) {
			date += String.valueOf(year.getSelectedItem());
			date += String.valueOf(month.getSelectedItem());
			date += String.valueOf(day.getSelectedItem());
			date += String.valueOf(hour.getSelectedItem());
			date += String.valueOf(minute.getSelectedItem());
		} else if(num == 2) {
			date += year_2.getSelectedItem();
			date += month_2.getSelectedItem();
			date += day_2.getSelectedItem();
			date += hour.getSelectedItem();
			date += minute.getSelectedItem();
		} else if(num == 3) {
			date += year_3.getSelectedItem();
			date += month_3.getSelectedItem();
			date += day_3.getSelectedItem();
		} else if(num == 4) {
			date += String.valueOf(year_4.getSelectedItem());
			date += String.valueOf(month_4.getSelectedItem());
			date += String.valueOf(day_4.getSelectedItem());
			date += String.valueOf(hour_4.getSelectedItem());
			date += String.valueOf(minute_4.getSelectedItem());
		}
		return date;
	}
	
	//의사 선택
	private void selDoc(String reDate, int num) {
		ArrayList<Doctor> doctor = dao.dDao.show(reDate);
		String[] doctorChk;
		if(doctor != null) {
			doctorChk = new String[doctor.size()+1];
			doctorChk[0] = "<의사선택>";
			for(int i = 0; i < doctor.size(); i++) {
				doctorChk[i+1] = doctor.get(i).getDocName();
			}
		} else {
			doctorChk = new String[1];
			doctorChk[0] = "<의사선택>";
		}
		JComboBox<String> chk = new JComboBox<String> (doctorChk);
		if(num == 1) {
			doctorList = chk;
			doctorList.addItemListener(this);
		} else if(num == 4) {
			doctorList_4 = chk;
			doctorList_4.addItemListener(this);
		}
	}

	//날짜 반환(1,3)
	public void date(int num) {
		String[] yearIn = new String[41];
		yearIn[0] = "YEAR";
		if(num == 1) {
			int yNum = 2024;
			for(int i = 1; i < yearIn.length; i++) {
				yearIn[i] = String.valueOf(yNum);
				yNum--;
			}
			year = new JComboBox<String>(yearIn);
			year.addItemListener(this);
		} else if(num == 3) {
			int yNum = 2025;
			for(int i = 1; i < yearIn.length; i++) {
				yearIn[i] = String.valueOf(yNum);
				yNum--;
			}
			year_3 = new JComboBox<String>(yearIn);
			year_3.addItemListener(this);
		}
		String[] monthIn = new String[13];
		monthIn[0] = "MONTH";
		for(int i = 1; i < monthIn.length; i++) {
			if(i < 10) {
				monthIn[i] = "0"+String.valueOf(i);
			} else {
				monthIn[i] = String.valueOf(i);
			}
		}
		month = new JComboBox<String>(monthIn);
		month_3 = new JComboBox<String>(monthIn);

		String[] daySel = {"DAY"};
		day = new JComboBox<String>(daySel);
		day_3 = new JComboBox<String>(daySel);

		month.addItemListener(this);
		month_3.addItemListener(this);
	}
	
	//day에 /제거
	public String dateRemove(String idNum) {
		String date = dao.nDao.chkReDate(idNum);
		String remove = "";	
		for(int i = 0; i < date.length(); i++) {
			if(date.charAt(i) != '/') {
				remove += date.charAt(i);
			}
		}
		return remove;
	}
	
	//날짜 초기값
	public void startDate_4() {
		String[] monthSel = {"MONTH"};
		String[] daySel = {"DAY"};
		yearChk(dateRemove(m2.insert[1].getText()), 4);

		month_4 = new JComboBox<String>(monthSel);
		day_4 = new JComboBox<String>(daySel);
	}

	//연도 변환
	public void yearChk(String date, int num) {
		String y = "";
		for(int i = 0; i < date.length(); i++) {
			if(i == 0) {
				if(date.charAt(i) == '2' || date.charAt(i) == '1' || date.charAt(i) == '0') {
					y += "20";
				} else {
					y += "19";
				}
			}
			if(i < 2) {
				y += date.charAt(i);
			}
		}
		String[] yearIn = new String[2026-Integer.parseInt(y)+1];
		yearIn[0] = "YEAR";
		int yNum = 2025;
		for(int i = 1; i < yearIn.length; i++) {
			yearIn[i] = String.valueOf(yNum);
			yNum --;
		}

		JComboBox<String> yearChk = new JComboBox<String>(yearIn);
		if(num == 2) {
			year_2 = yearChk;
			year_2.addItemListener(this);
		} else if(num == 4) {
			year_4 = yearChk;
			year_4.addItemListener(this);
		}
	}

	//month 변환
	public void monthChk(String date, boolean b, int num) {
		String m = "";
		for(int i = 0; i < date.length(); i++) {
			if(i > 2 && i < 4) {
				m += date.charAt(i);
			}
		}
		if(b) {
			String[] monthIn = new String[13-Integer.valueOf(m)+1];
			monthIn[0] = "MONTH";
			int monNum = Integer.valueOf(m);
			for(int i = 1; i < monthIn.length; i++) {
				if(monNum < 10) {
					monthIn[i] = "0"+String.valueOf(monNum);
				} else {
					monthIn[i] = String.valueOf(monNum);
				}
				monNum++;
			}
			JComboBox<String> monthChk = new JComboBox<String>(monthIn);
			if(num == 2) {
				month_2 = monthChk;
				month_2.addItemListener(this);
			} else if(num == 4){
				month_4 = monthChk;
				month_4.addItemListener(this);
			}
		} else {
			String[] monthIn = new String[13];
			monthIn[0] = "MONTH";
			for(int i = 1; i < monthIn.length; i++) {
				if(i < 10) {
					monthIn[i] = "0"+String.valueOf(i);
				} else {
					monthIn[i] = String.valueOf(i);
				}
			}
			JComboBox<String> monthChk = new JComboBox<String>(monthIn);
			if(num == 2) {
				month_2 = monthChk;
				month_2.addItemListener(this);
			} else if(num == 4){
				month_4 = monthChk;
				month_4.addItemListener(this);
			}
		}
	}

	//일수변환_2
	public void dayChk(String date,int lastDay, boolean b, int num) {
		String d = "";
		for(int i = 0; i < date.length(); i++) {
			if(i > 3) {
				d += date.charAt(i);
			}
		}
		if(b) {
			String[] dIn = new String[lastDay-Integer.valueOf(d)+1];
			dIn[0] = "DAY";
			int dayNum = Integer.valueOf(d)+1;
			for(int i = 1; i < dIn.length; i++) {
				if(dayNum < 10) {
					dIn[i] = "0"+String.valueOf(dayNum);
				} else {
					dIn[i] = String.valueOf(dayNum);
				}
				dayNum++;
			}
			JComboBox<String> dayChk = new JComboBox<String>(dIn);
			if(num == 2) {
				day_2 = dayChk;
				day_2.addItemListener(this);
			} else if(num == 4){
				day_4 = dayChk;
				day_4.addItemListener(this);
			}
		} else {
			String[] dIn = new String[lastDay+1];
			dIn[0] = "DAY";
			for(int i = 1; i < dIn.length; i++) {
				if(i < 10) {
					dIn[i] = "0"+String.valueOf(i);
				} else {
					dIn[i] = String.valueOf(i);
				}
			}
			if(num == 2) {
				day_2 = new JComboBox<String>(dIn);
				day_2.addItemListener(this);
			} else if(num == 4){
				day_4 = new JComboBox<String>(dIn);
				day_4.addItemListener(this);
			}
		}
	}

	//시간
	public void time(int num) {
		String[] hourIn = new String[10];
		hourIn[0] = "HOUR";
		int time = 9;
		for(int i = 1; i < hourIn.length; i++) {
			if(time == 9) {
				hourIn[i] = "0"+String.valueOf(time);
			} else {
				hourIn[i] = String.valueOf(time);
			}
			time++;
		}
		
		String[] minuteIn = new String[60];
		minuteIn[0] = "MINUTE";
		for(int i = 1; i < minuteIn.length; i++) {
			if(i-1 < 10) {
				minuteIn[i] = "0"+String.valueOf(i-1);
			} else {
				minuteIn[i] = String.valueOf(i);
			}
		}
		if(num == 1) {
			hour = new JComboBox<String>(hourIn);
			minute = new JComboBox<String>(minuteIn);
			minute.addItemListener(this);
			hour.addItemListener(this);
		} else if(num == 4) {
			hour_4 = new JComboBox<String>(hourIn);
			minute_4 = new JComboBox<String>(minuteIn);
			hour_4.addItemListener(this);
			minute_4.addItemListener(this);
		}
	}

	//일 수 변환
	public void dayIn(int lastDay, int num) {
		String[] dIn = new String[lastDay+1];
		dIn[0] = "DAY";
		for(int i = 1; i < dIn.length; i++) {
			if(i < 10) {
				dIn[i] = "0"+String.valueOf(i);
			} else {
				dIn[i] = String.valueOf(i);
			}
		}
		if(num == 1) {
			day = new JComboBox<String>(dIn);
			day.addItemListener(this);
		} else if(num == 3){
			day_3 = new JComboBox<String>(dIn);
			day_3.addItemListener(this);
		}
	}

	//날짜 초기값
	public void start_2() {
		String[] yearSel = {"YEAR"};
		String[] monthSel = {"MONTH"};
		String[] daySel = {"DAY"};

		year_2 = new JComboBox<String>(yearSel);
		month_2 = new JComboBox<String>(monthSel);
		day_2 = new JComboBox<String>(daySel);

		reDateList_2.add(year_2);
		reDateList_2.add(month_2);
		reDateList_2.add(day_2);

		year_2.setEnabled(false);
		month_2.setEnabled(false);
		day_2.setEnabled(false);
		
		hour.setEnabled(false);
		minute.setEnabled(false);

		year_2.addItemListener(this);
		month_2.addItemListener(this);
		day_2.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		//의사 combobox 선택 시 자동으로 의사번호 입력
		if(e.getSource().equals(doctorList)) {
			if(!doctorList.getSelectedItem().equals("의사선택")) {
				ArrayList<Doctor> doctor = dao.dDao.show(dateSave(1));
				int index = doctorList.getSelectedIndex();
				if(index != 0) {
					for(int i = 0; i < doctor.size(); i++) {
						if(doctorList.getSelectedItem().equals(doctor.get(i).getDocName())) {
							nPanel_1.docNumT.setText(doctor.get(i).getDocNum());
							break;
						}
					}
				} else {
					nPanel_1.docNumT.setText("");
				}
			}
		}
		
		//의사 combobox 선택 시 자동으로 의사번호 입력_4
		if(e.getSource().equals(doctorList_4)) {
			if(!doctorList_4.getSelectedItem().equals("의사선택")) {
				ArrayList<Doctor> doctor = dao.dDao.show(dateSave(4));
				int index = doctorList_4.getSelectedIndex();
				if(index != 0) {
					for(int i = 0; i < doctor.size(); i++) {
						if(doctorList_4.getSelectedItem().equals(doctor.get(i).getDocName())) {
							m2.docNumT.setText(doctor.get(i).getDocNum());
							break;
						}
					}
				} else {
					m2.docNumT.setText("");
				}
			}
		}

		//연도, 월 입력 시 자동으로 day 체크 - 1
		if(e.getSource().equals(year) || e.getSource().equals(month)) {
			reDateList_1.remove(day);
			if(!year.getSelectedItem().equals("YEAR") && !month.getSelectedItem().equals("MONTH")) {
				String dateWord = "";
				dateWord += String.valueOf(year.getSelectedItem());
				dateWord += String.valueOf(month.getSelectedItem());
				dateWord += "01";
				dayIn(dao.nDao.chk(dateWord), 1);
			} else if(year.getSelectedItem().equals("YEAR") || month.getSelectedItem().equals("MONTH")) {
				String[] daySel = {"DAY"};
				day = new JComboBox<String>(daySel);
			}
			reDateList_1.add(day,2);
			day.setVisible(false);
			day.setVisible(true);
			
		}
		
		//연도, 월 입력 시 자동으로 day 체크 - 3
		if(e.getSource().equals(year_3) || e.getSource().equals(month_3)) {
			reDateList_3.remove(day_3);
			if(!year_3.getSelectedItem().equals("YEAR") && !month_3.getSelectedItem().equals("MONTH")) {
				String dateWord = "";
				dateWord += String.valueOf(year_3.getSelectedItem());
				dateWord += String.valueOf(month_3.getSelectedItem());
				dateWord += "01";
				dayIn(dao.nDao.chk(dateWord), 3);
			} else if(year_3.getSelectedItem().equals("YEAR") || month_3.getSelectedItem().equals("MONTH")) {
				String[] daySel = {"DAY"};
				day_3 = new JComboBox<String>(daySel);
			}
			reDateList_3.add(day_3);
			day_3.setVisible(false);
			day_3.setVisible(true);
		}
		
		//날짜 체크
		if(e.getSource().equals(year_2)) {
			reDateList_2.remove(month_2);
			if(year_2.getSelectedItem().equals("YEAR")) {
				String[] monthSel = {"MONTH"};
				month_2 = new JComboBox<String>(monthSel);
			} else {
				if(year_2.getSelectedIndex() == year_2.getItemCount()-1) {
					monthChk(dateRemove(nPanel_1.idNumT_1.getText()+"-"+nPanel_1.idNumT_2.getText()),true, 2);
				} else {
					monthChk(dateRemove(nPanel_1.idNumT_1.getText()+"-"+nPanel_1.idNumT_2.getText()),false, 2);
				}
			}
			reDateList_2.add(month_2, 1);
			month_2.setVisible(false);
			month_2.setVisible(true);
		}
		
		//날짜 체크
		if(e.getSource().equals(year_4)) {
			reDateList_4.remove(month_4);
			if(year_4.getSelectedItem().equals("YEAR")) {
				String[] monthSel = {"MONTH"};
				month_4 = new JComboBox<String>(monthSel);
			} else {
				if(year_4.getSelectedIndex() == year_4.getItemCount()-1) {
					monthChk(dateRemove(m2.insert[1].getText()),true, 4);
				} else {
					monthChk(dateRemove(m2.insert[1].getText()),false, 4);
				}
			}
			reDateList_4.add(month_4, 2);
			month_4.setVisible(false);
			month_4.setVisible(true);
		}

		//day 자동 체크 2
		if(e.getSource().equals(year_2) || e.getSource().equals(month_2)) {
			reDateList_2.remove(day_2);
			if(!year_2.getSelectedItem().equals("YEAR") && !month_2.getSelectedItem().equals("MONTH")) {
				String dateWord = "";
				dateWord += String.valueOf(year_2.getSelectedItem());
				dateWord += String.valueOf(month_2.getSelectedItem());
				dateWord += "01";
				if(year_2.getSelectedIndex() == year_2.getItemCount()-1 && month_2.getSelectedIndex() == 1) {
					dayChk(dateRemove(nPanel_1.idNumT_1.getText()+"-"+nPanel_1.idNumT_2.getText()),dao.nDao.chk(dateWord), true, 2);
				} else {
					dayChk(dateRemove(nPanel_1.idNumT_1.getText()+"-"+nPanel_1.idNumT_2.getText()),dao.nDao.chk(dateWord), false, 2);
				}
			} else if(year_2.getSelectedItem().equals("YEAR") || month_2.getSelectedItem().equals("MONTH")) {
				String[] daySel = {"DAY"};
				day_2 = new JComboBox<String>(daySel);
			}
			reDateList_2.add(day_2,2);
			day_2.setVisible(false);
			day_2.setVisible(true);
		}

		//day 자동 체크 2
		if(e.getSource().equals(year_4) || e.getSource().equals(month_4)) {
			reDateList_4.remove(day_4);
			if(!year_4.getSelectedItem().equals("YEAR") && !month_4.getSelectedItem().equals("MONTH")) {
				String dateWord = "";
				dateWord += String.valueOf(year_4.getSelectedItem());
				dateWord += String.valueOf(month_4.getSelectedItem());
				dateWord += "01";
				if(year_4.getSelectedIndex() == year_4.getItemCount()-1 && month_4.getSelectedIndex() == 1) {
					dayChk(dateRemove(m2.insert[1].getText()),dao.nDao.chk(dateWord), true, 4);
				} else {
					dayChk(dateRemove(m2.insert[1].getText()),dao.nDao.chk(dateWord), false, 4);
				}
			} else if(year_4.getSelectedItem().equals("YEAR") || month_4.getSelectedItem().equals("MONTH")) {
				String[] daySel = {"DAY"};
				day_4 = new JComboBox<String>(daySel);
			}
			reDateList_4.add(day_4,3);
			day_4.setVisible(false);
			day_4.setVisible(true);
		}
		
		//날짜에 따라 의사 콤보 박스 변경
		if(e.getSource().equals(year) || e.getSource().equals(month) || e.getSource().equals(day) ||
				e.getSource().equals(hour) || e.getSource().equals(minute)) {
			doc_1.remove(doctorList);
			if(!year.getSelectedItem().equals("YEAR") && !month.getSelectedItem().equals("MONTH") && 
					!day.getSelectedItem().equals("DAY") && !hour.getSelectedItem().equals("HOUR") && 
					!minute.getSelectedItem().equals("MINUTE")) {
				selDoc(dateSave(1),1);
			} else {
				String[] doc = {"<의사선택>"};
				doctorList = new JComboBox<String>(doc);
			}
			doc_1.add(doctorList);
			doctorList.addItemListener(this);
			doctorList.setVisible(false);
			doctorList.setVisible(true);
		}
		
		//날짜에 따라 의사 콤보 박스 변경 4
		if(e.getSource().equals(year_4) || e.getSource().equals(month_4) || e.getSource().equals(day_4) ||
				e.getSource().equals(hour_4) || e.getSource().equals(minute_4)) {
			doc_4.remove(doctorList_4);
			if(!year_4.getSelectedItem().equals("YEAR") && !month_4.getSelectedItem().equals("MONTH") && 
					!day_4.getSelectedItem().equals("DAY") && !hour_4.getSelectedItem().equals("HOUR") && 
					!minute_4.getSelectedItem().equals("MINUTE")) {
				selDoc(dateSave(4),4);
			} else {
				String[] doc = {"<의사선택>"};
				doctorList_4 = new JComboBox<String>(doc);
			}
			doc_4.add(doctorList_4);
			doctorList_4.addItemListener(this);
			doctorList_4.setVisible(false);
			doctorList_4.setVisible(true);
		}

		//table 정보 불러오기
		if(e.getSource().equals(year_3) || e.getSource().equals(month_3) || e.getSource().equals(day_3)) {
			nPanel_2.delTable(nPanel_2.model_all);
			if(!year_3.getSelectedItem().equals("YEAR") && !month_3.getSelectedItem().equals("MONTH") &&
					!day_3.getSelectedItem().equals("DAY")) {
				nPanel_2.patientList(dateSave(3), nPanel_2.model_all ,nPanel_2.nowProgress);
			}
		}

	}

}
