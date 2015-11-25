package shiyaowu_CSCI201_Assignment3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalPage extends JFrame{
	
	private Calendar myCal;
	private JLabel monthLabel;
	private JLabel yearLabel;
	private JButton monthLeftBtn;
	private JButton monthRightBtn;
	private JButton [] dayButton;
	private JLabel eventArea;
	
	private ArrayList<Event> myEvent;
	
	private JLabel eventTitle;
	private JTextField titleTF;
	private JLabel eventLoc;
	private JTextField locTF;
	private JLabel startLabel;
	private JComboBox<?> startHour;
	private JComboBox<?> startMin;
	private JComboBox<?> startHalf;
	private JLabel endLabel;
	private JComboBox<?> endHour;
	private JComboBox<?> endMin;
	private JComboBox<?> endHalf;
	private JButton createBtn;
	private JButton createCancelBtn;
	
	private JLabel EMeventTitle;
	private JTextField EMtitleTF;
	private JLabel EMeventLoc;
	private JTextField EMlocTF;
	private JLabel EMstartLabel;
	private JComboBox<?> EMstartHour;
	private JComboBox<?> EMstartMin;
	private JComboBox<?> EMstartHalf;
	private JLabel EMendLabel;
	private JComboBox<?> EMendHour;
	private JComboBox<?> EMendMin;
	private JComboBox<?> EMendHalf;
	private JLabel EMYear;
	private JLabel EMMonth;
	private JLabel EMDay;
	private JComboBox<?> EMyr;
	private JComboBox<?> EMmo;
	private JComboBox<?> EMda;
	private JButton EMcreateBtn;
	private JButton EMcreateCancelBtn;
	
	private JLabel editTitle;
	private JTextField editTitleTF;
	private JLabel editLoc;
	private JTextField editLocTF;
	private JLabel editStartLabel;
	private JComboBox<?> editStartHour;
	private JComboBox<?> editStartMin;
	private JComboBox<?> editStartHalf;
	private JLabel editEndLabel;
	private JComboBox<?> editEndHour;
	private JComboBox<?> editEndMin;
	private JComboBox<?> editEndHalf;
	private JButton saveBtn;
	private JButton editCancelBtn;
	
	private int numEvents;
	private int selectedDate;
	
	private JButton deleteBtn;
	private JButton editBtn;
	private JButton cancelBtn;
	
	private Event selectedEvent;
		
	public CalPage()
	{
		super("My Calendar Page");
		setSize(550, 1000);
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myCal = Calendar.getInstance();
		System.out.println(myCal.get(Calendar.MONTH));
		initiateComponents();
		constructGUI();

		setActions();
		/*Calendar myCal = Calendar.getInstance();
		System.out.println(myCal.getTime());
		int mon = myCal.get(Calendar.MONTH);
		System.out.println(mon);
		myCal.add(Calendar.DAY_OF_MONTH, -5);
		System.out.println(myCal.getTime());
		myCal=Calendar.getInstance();
		System.out.println(myCal.getTime());
		myCal.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(myCal.get(Calendar.DAY_OF_WEEK));
		System.out.println(myCal.getTime());*/

		setVisible(true);
	}
	
	public void setActions()
	{
		
		
		monthLeftBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				myCal.add(Calendar.MONTH, -1);
				int monthNum = myCal.get(Calendar.MONTH);
				if (monthNum==0)
				{
					monthLabel.setText("January");
				}
				else if (monthNum ==1)
				{
					monthLabel.setText("February");
				}
				else if (monthNum ==2)
				{
					monthLabel.setText("March");
				}
				else if (monthNum ==3)
				{
					monthLabel.setText("April");
				}
				else if (monthNum ==4)
				{
					monthLabel.setText("May");
				}
				else if (monthNum ==5)
				{
					monthLabel.setText("June");
				}
				else if (monthNum ==6)
				{
					monthLabel.setText("July");
				}
				else if (monthNum ==7)
				{
					monthLabel.setText("August");
				}
				else if (monthNum ==8)
				{
					monthLabel.setText("September");
				}
				else if (monthNum ==9)
				{
					monthLabel.setText("October");
				}
				else if (monthNum ==10)
				{
					monthLabel.setText("November");
				}
				else if (monthNum ==11)
				{
					monthLabel.setText("December");
				}
				
				yearLabel.setText(Integer.toString(myCal.get(Calendar.YEAR)));

				Date theDate=myCal.getTime();
				Calendar cal1=Calendar.getInstance();
				cal1.setTime(theDate);
				cal1.set(Calendar.DAY_OF_MONTH, 1);
				
				int firstDay=cal1.get(Calendar.DAY_OF_WEEK)-1;
				
				int daysInThisMonth=cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
				int countDay=1;
				for (int i=firstDay; i<firstDay+daysInThisMonth; i++)
				{
					dayButton[i].setText(Integer.toString(countDay));
					countDay++;
					int indexDate=Integer.parseInt(dayButton[i].getText());
					dayButton[i].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							selectedDate=indexDate;
						}
						
					});
					dayButton[i].setEnabled(true);
					dayButton[i].setBackground(null);
					dayButton[i].setOpaque(true);
				}
				
				cal1.add(Calendar.DATE, -firstDay);
				int firstGreyLastMonth=cal1.get(Calendar.DAY_OF_MONTH);
				for (int i=0; i<firstDay; i++)
				{
					dayButton[i].setText(Integer.toString(firstGreyLastMonth));
					firstGreyLastMonth++;
					dayButton[i].setEnabled(false);
				}
				
				Calendar currentDate=Calendar.getInstance();
				
				System.out.println(myCal.getTime());
				System.out.println(currentDate.getTime());
				
				if (myCal.get(Calendar.MONTH)==currentDate.get(Calendar.MONTH) 
						&& myCal.get(Calendar.YEAR)==currentDate.get(Calendar.YEAR))
				{
				
					int currentDay=currentDate.get(Calendar.DAY_OF_MONTH);
					dayButton[firstDay-1+currentDay].setBackground(Color.YELLOW);
					dayButton[firstDay-1+currentDay].setContentAreaFilled(false);
					dayButton[firstDay-1+currentDay].setOpaque(true);
				}
				int firstDayNextMonth=1;
				for (int i=firstDay+daysInThisMonth; i<42; i++)
				{
					dayButton[i].setText(Integer.toString(firstDayNextMonth));
					firstDayNextMonth++;
					dayButton[i].setEnabled(false);
				}
			}
			
		});
		
		
		monthRightBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				myCal.add(Calendar.MONTH, 1);
				int monthNum = myCal.get(Calendar.MONTH);
				if (monthNum==0)
				{
					monthLabel.setText("January");
				}
				else if (monthNum ==1)
				{
					monthLabel.setText("February");
				}
				else if (monthNum ==2)
				{
					monthLabel.setText("March");
				}
				else if (monthNum ==3)
				{
					monthLabel.setText("April");
				}
				else if (monthNum ==4)
				{
					monthLabel.setText("May");
				}
				else if (monthNum ==5)
				{
					monthLabel.setText("June");
				}
				else if (monthNum ==6)
				{
					monthLabel.setText("July");
				}
				else if (monthNum ==7)
				{
					monthLabel.setText("August");
				}
				else if (monthNum ==8)
				{
					monthLabel.setText("September");
				}
				else if (monthNum ==9)
				{
					monthLabel.setText("October");
				}
				else if (monthNum ==10)
				{
					monthLabel.setText("November");
				}
				else if (monthNum ==11)
				{
					monthLabel.setText("December");
				}
				
				yearLabel.setText(Integer.toString(myCal.get(Calendar.YEAR)));

				Date theDate=myCal.getTime();
				Calendar cal1=Calendar.getInstance();
				cal1.setTime(theDate);
				cal1.set(Calendar.DAY_OF_MONTH, 1);
				
				int firstDay=cal1.get(Calendar.DAY_OF_WEEK)-1;
				
				int daysInThisMonth=cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
				int countDay=1;
				for (int i=firstDay; i<firstDay+daysInThisMonth; i++)
				{
					dayButton[i].setText(Integer.toString(countDay));
					countDay++;
					int indexDate=Integer.parseInt(dayButton[i].getText());
					dayButton[i].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							selectedDate=indexDate;
						}
						
					});
					dayButton[i].setEnabled(true);
					dayButton[i].setBackground(null);
					dayButton[i].setOpaque(true);
				}
				
				cal1.add(Calendar.DATE, -firstDay);
				int firstGreyLastMonth=cal1.get(Calendar.DAY_OF_MONTH);
				for (int i=0; i<firstDay; i++)
				{
					dayButton[i].setText(Integer.toString(firstGreyLastMonth));
					firstGreyLastMonth++;
					dayButton[i].setEnabled(false);
				}
				
				Calendar currentDate=Calendar.getInstance();
				
				if (myCal.get(Calendar.MONTH)==currentDate.get(Calendar.MONTH) 
						&& myCal.get(Calendar.YEAR)==currentDate.get(Calendar.YEAR))
				{
				
					int currentDay=currentDate.get(Calendar.DAY_OF_MONTH);
					dayButton[firstDay-1+currentDay].setBackground(Color.YELLOW);
					dayButton[firstDay-1+currentDay].setContentAreaFilled(false);
					dayButton[firstDay-1+currentDay].setOpaque(true);
				}
				int firstDayNextMonth=1;
				for (int i=firstDay+daysInThisMonth; i<42; i++)
				{
					dayButton[i].setText(Integer.toString(firstDayNextMonth));
					firstDayNextMonth++;
					
					dayButton[i].setEnabled(false);
				}
			}
			
		});
		

		
	}
	
	public void constructGUI()
	{
		JPanel mainPage=new JPanel();
		mainPage.setLayout(new CardLayout());
		JPanel calenPage=new JPanel();
		JPanel eventCreatePage=new JPanel();
		
		JPanel topBar = new JPanel (new BorderLayout());
		topBar.add(monthLeftBtn, BorderLayout.WEST);
		topBar.add(monthRightBtn, BorderLayout.EAST);
		
		JPanel monthYear=new JPanel(new FlowLayout());
		monthYear.add(monthLabel);
		monthYear.add(yearLabel);
		
		topBar.add(monthYear, BorderLayout.CENTER);
		JLabel hint=new JLabel("Click on dates to REFRESH");
		hint.setForeground(Color.RED);
		topBar.add(hint,BorderLayout.SOUTH);
		calenPage.add(topBar, BorderLayout.NORTH);
		
		JPanel calendarPage = new JPanel ();
		calendarPage.setLayout(new GridLayout(7, 7));
		calendarPage.add(new JLabel("Sun"));
		calendarPage.add(new JLabel("Mon"));
		calendarPage.add(new JLabel("Tue"));
		calendarPage.add(new JLabel("Wed"));
		calendarPage.add(new JLabel("Thu"));
		calendarPage.add(new JLabel("Fri"));
		calendarPage.add(new JLabel("Sat"));
		
		for (int i=0; i<42; i++)
		{
			dayButton[i]=new JButton();
			
			calendarPage.add(dayButton[i]);
		}
		Date theDate=myCal.getTime();
		Calendar cal1=Calendar.getInstance();
		cal1.setTime(theDate);
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		int firstDay=cal1.get(Calendar.DAY_OF_WEEK)-1;
		int daysInThisMonth=cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
		int countDay=1;
		for (int i=firstDay; i<firstDay+daysInThisMonth; i++)
		{
			dayButton[i].setText(Integer.toString(countDay));
			countDay++;
			int indexDate=Integer.parseInt(dayButton[i].getText());
			dayButton[i].addMouseListener(new DoubleClicked("second", mainPage, indexDate));
			dayButton[i].addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					selectedDate=indexDate;
				}
				
			});
			dayButton[i].setEnabled(true);
		}
		
		cal1.add(Calendar.DATE, -firstDay);
		int firstGreyLastMonth=cal1.get(Calendar.DAY_OF_MONTH);
		for (int i=0; i<firstDay; i++)
		{
			dayButton[i].setText(Integer.toString(firstGreyLastMonth));
			firstGreyLastMonth++;
			int indexDate=Integer.parseInt(dayButton[i].getText());
			dayButton[i].addMouseListener(new DoubleClicked("second", mainPage, indexDate));
			dayButton[i].setEnabled(false);
		}
		
		Calendar currentDate=Calendar.getInstance();
		if (myCal.get(Calendar.MONTH)==currentDate.get(Calendar.MONTH))
		{
		
			int currentDay=currentDate.get(Calendar.DAY_OF_MONTH);
			dayButton[firstDay-1+currentDay].setBackground(Color.YELLOW);
			dayButton[firstDay-1+currentDay].setContentAreaFilled(false);
			dayButton[firstDay-1+currentDay].setOpaque(true);
		}
		int firstDayNextMonth=1;
		for (int i=firstDay+daysInThisMonth; i<42; i++)
		{
			dayButton[i].setText(Integer.toString(firstDayNextMonth));
			firstDayNextMonth++;
			int indexDate=Integer.parseInt(dayButton[i].getText());
			dayButton[i].addMouseListener(new DoubleClicked("second", mainPage, indexDate));

			dayButton[i].setEnabled(false);
		}
		
		calenPage.add(calendarPage, BorderLayout.CENTER);
		
		Object hours[]={1,2,3,4,5,6,7,8,9,10,11,12};
		Object mins[]={00,15,30,45};
		Object half[]={"AM", "PM"};
		
		JPanel eventEditPage=new JPanel();
		eventEditPage.setLayout(new  BoxLayout(eventEditPage, BoxLayout.Y_AXIS));
		editTitle=new JLabel("Title: ");
		editTitleTF=new JTextField(15);
		editLoc=new JLabel("Location: ");
		editLocTF=new JTextField(15);
		editStartLabel=new JLabel("Start: ");
		
		editStartHour=new JComboBox<Object>(hours);
		editStartMin=new JComboBox<Object>(mins);
		editStartHalf=new JComboBox<Object>(half);
		
		editEndLabel=new JLabel("End: ");
		editEndHour=new JComboBox<Object>(hours);
		editEndMin=new JComboBox<Object>(mins);
		editEndHalf=new JComboBox<Object>(half);
		
		saveBtn=new JButton("Save");
		editCancelBtn=new JButton("Cancel");
		
		JPanel editTitleBar=new JPanel();
		editTitleBar.setLayout(new FlowLayout());
		editTitleBar.add(editTitle);
		editTitleBar.add(editTitleTF);
		
		JPanel editLocBar=new JPanel();
		editLocBar.setLayout(new FlowLayout());
		editLocBar.add(editLoc);
		editLocBar.add(editLocTF);
		
		JPanel editStartBar=new JPanel();
		editStartBar.setLayout(new FlowLayout());
		editStartBar.add(editStartLabel);
		editStartBar.add(editStartHour);
		editStartBar.add(editStartMin);
		editStartBar.add(editStartHalf);
		
		JPanel editEndBar=new JPanel();
		editEndBar.setLayout(new FlowLayout());
		editEndBar.add(editEndLabel);
		editEndBar.add(editEndHour);
		editEndBar.add(editEndMin);
		editEndBar.add(editEndHalf);
		
		eventEditPage.add(editTitleBar);
		eventEditPage.add(editLocBar);
		eventEditPage.add(editStartBar);
		eventEditPage.add(editEndBar);
		eventEditPage.add(saveBtn);
		eventEditPage.add(editCancelBtn);
		
		Object years[]=new Object[200];
		for (int i=1900; i<2100; i++)
		{
			years[i-1900]=i;
		}
		
		Object months[]={1,2,3,4,5,6,7,8,9,10,11,12};
		Object days[]=new Object[31];
		for (int i=0; i<31; i++)
		{
			days[i]=i+1;
		}
		
		JPanel EMPage=new JPanel();
		EMPage.setLayout(new BoxLayout(EMPage, BoxLayout.Y_AXIS));
		EMeventTitle=new JLabel("Title: ");
		EMtitleTF=new JTextField(15);
		EMeventLoc=new JLabel("Location: ");
		EMlocTF=new JTextField(15);
		EMstartLabel=new JLabel("Start: ");
		EMstartHour=new JComboBox<Object>(hours);
		EMstartMin=new JComboBox<Object>(mins);
		EMstartHalf=new JComboBox<Object>(half);
		EMyr=new JComboBox<Object>(years);
		EMmo=new JComboBox<Object>(months);
		EMda=new JComboBox<Object>(days);
		EMendLabel=new JLabel("End: ");
		EMendHour=new JComboBox<Object>(hours);
		EMendMin=new JComboBox<Object>(mins);
		EMendHalf=new JComboBox<Object>(half);
		EMYear=new JLabel("Year: ");
		EMMonth=new JLabel("Month: ");
		EMDay=new JLabel("Day: ");
		EMcreateBtn=new JButton("Create");
		EMcreateCancelBtn=new JButton("Cancel");
		
		JPanel EMtitleBar=new JPanel();
		EMtitleBar.setLayout(new FlowLayout());
		EMtitleBar.add(EMeventTitle);
		EMtitleBar.add(EMtitleTF);
		
		JPanel EMLocBar=new JPanel();
		EMLocBar.setLayout(new FlowLayout());
		EMLocBar.add(EMeventLoc);
		EMLocBar.add(EMlocTF);
		
		JPanel EMY=new JPanel();
		EMY.setLayout(new FlowLayout());
		EMY.add(EMYear);
		EMY.add(EMyr);
		
		JPanel EMM=new JPanel();
		EMM.setLayout(new FlowLayout());
		EMM.add(EMMonth);
		EMM.add(EMmo);
		
		JPanel EMD=new JPanel();
		EMD.setLayout(new FlowLayout());
		EMD.add(EMDay);
		EMD.add(EMda);
		
		JPanel EMS=new JPanel();
		EMS.setLayout(new FlowLayout());
		EMS.add(EMstartLabel);
		EMS.add(EMstartHour);
		EMS.add(EMstartMin);
		EMS.add(EMstartHalf);
		
		JPanel EME=new JPanel();
		EME.setLayout(new FlowLayout());
		EME.add(EMendLabel);
		EME.add(EMendHour);
		EME.add(EMendMin);
		EME.add(EMendHalf);
		
		JPanel EMButtons=new JPanel();
		EMButtons.setLayout(new FlowLayout());
		EMButtons.add(EMcreateBtn);
		EMButtons.add(EMcreateCancelBtn);
		
		EMPage.add(EMtitleBar);
		EMPage.add(EMLocBar);
		EMPage.add(EMY);
		EMPage.add(EMM);
		EMPage.add(EMD);
		EMPage.add(EMS);
		EMPage.add(EME);
		EMPage.add(EMButtons);
		
		EMcreateBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (EMtitleTF.getText().equals("") || EMlocTF.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please complete the form to save", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				String title=EMtitleTF.getText();
				String location=EMlocTF.getText();
				int year=(int)EMyr.getSelectedItem();
				int month=((int)EMmo.getSelectedItem())-1;
				int day=(int)EMda.getSelectedItem();
				String start=Integer.toString((int)EMstartHour.getSelectedItem())
						+":"+Integer.toString((int)EMstartMin.getSelectedItem())
						+(String)(EMstartHalf.getSelectedItem());
				String end=Integer.toString((int)EMendHour.getSelectedItem())
						+":"+Integer.toString((int)EMendMin.getSelectedItem())
						+(String)EMendHalf.getSelectedItem(); 
				Event evt=new Event(title,location,year,month,day,start,end);
				myEvent.add(evt);
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "first");
				}
			}
			
		});
		
		
		EMcreateCancelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "first");	
			}
			
		});
		
		
		JPanel aboutPage=new JPanel();
		aboutPage.setLayout(new BorderLayout());
		aboutPage.add(new JLabel("Shiyao Wu"), BorderLayout.NORTH);
		aboutPage.add(new JLabel("Miller MW, Completed on 10.5"), BorderLayout.SOUTH);
		BufferedImage myPic = null;
		try {
			myPic=ImageIO.read(new File("pic.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel picLabel=new JLabel(new ImageIcon(myPic));
		aboutPage.add(picLabel, BorderLayout.CENTER);
		
		eventCreatePage.setLayout(new GridLayout(6,1));
		
		eventTitle=new JLabel("Title: ");
		titleTF=new JTextField(15);
		eventLoc=new JLabel("Location: ");
		locTF=new JTextField(15);
		startLabel=new JLabel("Start: ");

		
		
		
		startHour=new JComboBox<Object>(hours);
		startMin=new JComboBox<Object>(mins);
		startHalf=new JComboBox<Object>(half);
		
		endLabel=new JLabel("End: ");
		endHour=new JComboBox<Object>(hours);
		endMin=new JComboBox<Object>(mins);
		endHalf=new JComboBox<Object>(half);
		
		createBtn=new JButton("Create");
		createCancelBtn=new JButton("Cancel");
		
		JPanel createTitleBar=new JPanel();
		createTitleBar.setLayout(new FlowLayout());
		createTitleBar.add(eventTitle);
		createTitleBar.add(titleTF);
		
		JPanel createLocBar=new JPanel();
		createLocBar.setLayout(new FlowLayout());
		createLocBar.add(eventLoc);
		createLocBar.add(locTF);
		
		JPanel createStartBar=new JPanel();
		createStartBar.setLayout(new FlowLayout());
		createStartBar.add(startLabel);
		createStartBar.add(startHour);
		createStartBar.add(startMin);
		createStartBar.add(startHalf);
		
		JPanel createEndBar=new JPanel();
		createEndBar.setLayout(new FlowLayout());
		createEndBar.add(endLabel);
		createEndBar.add(endHour);
		createEndBar.add(endMin);
		createEndBar.add(endHalf);
		
		eventCreatePage.add(createTitleBar);
		eventCreatePage.add(createLocBar);
		eventCreatePage.add(createStartBar);
		eventCreatePage.add(createEndBar);
		eventCreatePage.add(createBtn);
		eventCreatePage.add(createCancelBtn);
		
		
		JPanel eventField=new JPanel();
		eventField.setLayout(new BoxLayout(eventField, BoxLayout.Y_AXIS));
		
		
		calenPage.add(eventField, BorderLayout.SOUTH);
		
		JPanel editBox=new JPanel();
		editBox.setLayout(new BoxLayout(editBox, BoxLayout.Y_AXIS));
		editBox.add(new JLabel("Would you like to edit or delete this event?"));
		deleteBtn=new JButton("Delete");
		editBtn=new JButton("Edit");
		cancelBtn=new JButton("Cancel");
		
		JPanel buttonBar=new JPanel();
		buttonBar.setLayout(new FlowLayout());
		buttonBar.add(deleteBtn);
		buttonBar.add(editBtn);
		buttonBar.add(cancelBtn);
		editBox.add(buttonBar);
		
		deleteBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				myEvent.remove(selectedEvent);
				selectedEvent=null;
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "first");
				mainPage.revalidate();
			}
			
		});
		
		cancelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "first");
			}
			
		});
		
		editBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				editTitleTF.setText(selectedEvent.getTitle());
				editLocTF.setText(selectedEvent.getLocation());
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "fourth");
			}
			
		});
		
		saveBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (editTitleTF.getText().equals("") || editLocTF.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please complete the form to save", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String title=editTitleTF.getText();
					String location=editLocTF.getText();
					int year=selectedEvent.getYear();
					int month=selectedEvent.getMonth();
					String start=Integer.toString((int)editStartHour.getSelectedItem())
							+":"+Integer.toString((int)editStartMin.getSelectedItem())
							+(String)(editStartHalf.getSelectedItem());
					String end=Integer.toString((int)editEndHour.getSelectedItem())
							+":"+Integer.toString((int)editEndMin.getSelectedItem())
							+(String)editEndHalf.getSelectedItem(); 
					Event evt=new Event(title,location,year,month,selectedDate,start,end);
					myEvent.add(evt);
					myEvent.remove(selectedEvent);
					CardLayout c1=(CardLayout)mainPage.getLayout();
					c1.show(mainPage, "first");
				}
			}
			
		});
		
		
		editCancelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "first");				
			}
			
		});
		
		for (int i=0; i<42; i++)
		{
			dayButton[i].addMouseListener(new DateSelected(i, eventField, mainPage, "first"));
			dayButton[i].addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					mainPage.revalidate();
				}
				
			});
		}
		
		JMenuBar menuBar=new JMenuBar();
		JMenu eventMng=new JMenu("Event Manager");
		JMenu exp=new JMenu("Export");
		JMenu abt=new JMenu("About");
		JMenuItem EMItem=new JMenuItem("Event Manager");
		JMenuItem About=new JMenuItem("About");
		
		About.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "sixth");	
			}
			
		});
		
		EMItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c1=(CardLayout)mainPage.getLayout();
				c1.show(mainPage, "fifth");	
			}
			
		});
		
		eventMng.add(EMItem);
		abt.add(About);
		menuBar.add(eventMng);
		menuBar.add(exp);
		menuBar.add(abt);
		setJMenuBar(menuBar);
		
		mainPage.add(calenPage,"first");
		mainPage.add(eventCreatePage, "second");
		mainPage.add(editBox, "third");
		mainPage.add(eventEditPage, "fourth");
		mainPage.add(EMPage, "fifth");
		mainPage.add(aboutPage, "sixth");
		add(mainPage);
		
		createBtn.addMouseListener(new SingleClicked("first", mainPage));
		createCancelBtn.addMouseListener(new SingleClicked("first", mainPage));
		createBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (titleTF.getText().equals("") || locTF.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please complete the form to save", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String title=titleTF.getText();
					String location=locTF.getText();
					int year=myCal.get(Calendar.YEAR);
					int month=myCal.get(Calendar.MONTH);
					String start=Integer.toString((int)startHour.getSelectedItem())
							+":"+Integer.toString((int)startMin.getSelectedItem())
							+(String)(startHalf.getSelectedItem());
					String end=Integer.toString((int)endHour.getSelectedItem())
							+":"+Integer.toString((int)endMin.getSelectedItem())
							+(String)endHalf.getSelectedItem(); 
					Event evt=new Event(title,location,year,month,selectedDate,start,end);
					myEvent.add(evt);
					JButton evtBtn=new JButton(evt.print());
					evtBtn.setBorderPainted(false);
					evtBtn.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							selectedEvent=evt;
							CardLayout c1=(CardLayout)mainPage.getLayout();
							c1.show(mainPage, "third");
						}
						
					});
					for (int i=0; i<42; i++)
					{
						if(Integer.parseInt(dayButton[i].getText())==selectedDate
								&& dayButton[i].isEnabled()==true)
						{
							String tempStr=dayButton[i].getText();
							dayButton[i].setForeground(Color.RED);
						}
					}
					//mainPage.revalidate();
					//mainPage.repaint();
					//System.out.println(evt.print());
					//System.out.println(evt.getYear()+" "+evt.getMonth()+" "+evt.getDate());
					//System.out.println(myCal.get(Calendar.MONTH));

				}

			}
			
		});
		
	}
	
	public void initiateComponents()
	{
		numEvents=0;
		myEvent=new ArrayList<Event>();
		dayButton=new JButton[42];
		monthLeftBtn=new JButton ("<");
		monthRightBtn=new JButton (">");
		
		int monthNum = myCal.get(Calendar.MONTH);
		if (monthNum==0)
		{
			monthLabel=new JLabel("January");
		}
		else if (monthNum ==1)
		{
			monthLabel=new JLabel("February");
		}
		else if (monthNum ==2)
		{
			monthLabel=new JLabel("March");
		}
		else if (monthNum ==3)
		{
			monthLabel=new JLabel("April");
		}
		else if (monthNum ==4)
		{
			monthLabel=new JLabel("May");
		}
		else if (monthNum ==5)
		{
			monthLabel=new JLabel("June");
		}
		else if (monthNum ==6)
		{
			monthLabel=new JLabel("July");
		}
		else if (monthNum ==7)
		{
			monthLabel=new JLabel("August");
		}
		else if (monthNum ==8)
		{
			monthLabel=new JLabel("September");
		}
		else if (monthNum ==9)
		{
			monthLabel=new JLabel("October");
		}
		else if (monthNum ==10)
		{
			monthLabel=new JLabel("November");
		}
		else if (monthNum ==11)
		{
			monthLabel=new JLabel("December");
		}
		
		yearLabel=new JLabel(Integer.toString(myCal.get(Calendar.YEAR)));

	}
	
	
	class DoubleClicked implements MouseListener{
		private String index;
		private JPanel jp;
		private int dateIndex;
		
		public DoubleClicked(String indexString, JPanel jpa, int date)
		{
			this.index=indexString;
			this.jp=jpa;
			this.dateIndex=date;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount()==2)
			{
				titleTF.setText("");
				locTF.setText("");
				selectedDate=dateIndex;
				CardLayout c1=(CardLayout)jp.getLayout();
				c1.show(jp, index);
				//System.out.println(selectedDate);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	class SingleClicked implements MouseListener
	{
		private String index;
		private JPanel jp;
		
		public SingleClicked(String indexString, JPanel jpa)
		{
			this.index=indexString;
			this.jp=jpa;
		}		

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			jp.revalidate();
			CardLayout c1=(CardLayout)jp.getLayout();
			c1.show(jp, index);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	
	class DateSelected implements MouseListener
	{
		private int index;
		private String indexString;
		private JPanel jp;
		private JPanel mp;
		public DateSelected(int i, JPanel j, JPanel m, String s)
		{
			this.index=i;
			this.jp=j;
			this.mp=m;
			this.indexString=s;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			for (int j=0; j<42; j++)
			{
				if (dayButton[j].getBackground()!=Color.YELLOW)
				{
					dayButton[j].setBackground(null);
					dayButton[j].setOpaque(true);
				}
			}
			
			dayButton[index].setBackground(Color.BLUE);
			dayButton[index].setOpaque(true);
			
			boolean isEmpty=true;
			jp.removeAll();
			if (myEvent.size()!=0)
			{
				Iterator<Event>it=myEvent.iterator();
				while(it.hasNext())
				{
					Event evt=it.next();
					if (myCal.get(Calendar.YEAR)== evt.getYear()
							&& myCal.get(Calendar.MONTH)==evt.getMonth()
							&& Integer.parseInt(dayButton[index].getText())==evt.getDate())
					{
						isEmpty=false;
						dayButton[index].setForeground(Color.RED);
						dayButton[index].setOpaque(true);						JLabel evtLabel=new JLabel((String)evt.print());
						evtLabel.addMouseListener(new MouseListener(){

							@Override
							public void mouseClicked(MouseEvent e) {
								selectedEvent=evt;
								CardLayout c1=(CardLayout)mp.getLayout();
								c1.show(mp, "third");
								System.out.println("###########");	
							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
						});
						jp.add(evtLabel);
						System.out.println(evt.print());
					}
				}
			}
			if (isEmpty==true)
			{
				dayButton[index].setForeground(Color.BLACK);
				dayButton[index].setOpaque(true);
				jp.add(new JLabel("No events"));
				System.out.println("No events");
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main (String[]args)
	{
		CalPage cal=new CalPage();
	}

}
