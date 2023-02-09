package taskManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;
import com.toedter.calendar.*;

public class taskManagementSystem {
	public taskManagementSystem (){
		//Components BGCOLOR 
		Color compBgColor = new Color(178,176,176);
		
		//Current Date and Time
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd ; HH:mm");
		String currentDateTime = sdf.format(cal.getTime());
		
		//Welcome Frame
		JFrame wFrame = new JFrame ();
		wFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wFrame.setSize(860,490);
		wFrame.setLocation(360, 150);
		ImageIcon wpImage = new ImageIcon("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\welcomePage.png");
		JLabel welcomePage = new JLabel (wpImage);
		welcomePage.setBounds(-5, 0, 860, 490);
		Image img =wpImage.getImage();
		Image imgScale = img.getScaledInstance(welcomePage.getWidth(), welcomePage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon (imgScale);
		
		//welcomePage Design
		KButton continues = new KButton();
		continues.setText("CONTINUE");
		continues.setBounds(40, 340, 270, 50);
		continues.setkBorderRadius(50);
		continues.setBorderPainted(false);
		continues.setkStartColor(new Color(246,234,65));
		continues.setkEndColor(new Color(119,0,127));
		wFrame.add(continues);
		welcomePage.setIcon(scaledIcon);
		wFrame.add(welcomePage);
		wFrame.setUndecorated(true);
		
		//Main GUI frame
		JFrame frame = new JFrame ("Task Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1060,690);
		frame.setLocation(250, 50);
		frame.setUndecorated(true);
		frame.setEnabled(false);

		//Main Frame Design
		ImageIcon bgImage = new ImageIcon("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\BG1.png");
		JLabel bgImageContainer = new JLabel(bgImage);
		bgImageContainer.setBounds(0,0,1060,720);
		
		//Edit Task Frame Design
		JLabel bgImageContainer2 = new JLabel(bgImage);
		bgImageContainer2.setBounds(0,0,680,620);
		
		//Add Task Frame Design
		ImageIcon bgImage2 = new ImageIcon("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\BG2.png");
		JLabel bgImageContainer3 = new JLabel(bgImage2);
		bgImageContainer3.setBounds(0,0,680,620);
		
		//Main Frame Exit Button
		JButton exit = new JButton("X");
		exit.setFont(new Font(null, Font.BOLD, 26));
		exit.setForeground(Color.white);
		exit.setBounds(1017, -7, 45, 45);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setBorder(null);
		frame.add(exit);
		
		//Main Frame Minimize Button
		JButton minimize = new JButton("_");
		minimize.setFont(new Font(null, Font.BOLD, 30));
		minimize.setForeground(Color.white);
		minimize.setBounds(975, -23, 45, 45);
		minimize.setOpaque(false);
		minimize.setContentAreaFilled(false);
		minimize.setBorderPainted(false);
		minimize.setBorder(null);
		frame.add(minimize);

		//JTable Construction
		DefaultTableModel model = new DefaultTableModel() { // Makes Table Uneditable

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		JTable table = new JTable();
		table.setModel(model);
		table.setPreferredScrollableViewportSize(new Dimension(720, 600));
		table.setDragEnabled(false);

		//Table Container
		KGradientPanel panel = new KGradientPanel();
		KGradientPanel panel2 = new KGradientPanel();
		panel.setkBorderRadius(0);
		panel2.setkBorderRadius(0);
		panel.setkStartColor(new Color(72,0,85));
		panel.setkEndColor(new Color(0,0,0));
		panel2.setkStartColor(new Color(72,0,85));
		panel2.setkEndColor(new Color(0,0,0));
		panel2.setBounds(40,30,740,640);
		panel2.setBackground(compBgColor);
		panel.setBounds(50,40,720,620);
		
		//ScrollPane for table
		JScrollPane scrollPane = new JScrollPane(table);
		table.setBackground(Color.white);
		scrollPane.setColumnHeaderView(table.getTableHeader());
		scrollPane.setOpaque(true);
		model.addColumn("Name");
		model.addColumn("Type");
		model.addColumn("Due");
		model.addColumn("Subject");
		model.addColumn("Description");
		panel.add(scrollPane, BorderLayout.CENTER);
		TableColumn column;
		for (int i = 0; i < 5; i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i == 4) {
		    	column.setMinWidth(300);  
		    	column.setMaxWidth(300); 
		        column.setPreferredWidth(300);
		    }
		    else if (i == 0) {
		    	column.setMinWidth(120);  
		    	column.setMaxWidth(120); 
		        column.setPreferredWidth(120);
		    }
		    else {
		    	column.setMinWidth(100);  
		    	column.setMaxWidth(100); 
		        column.setPreferredWidth(100);
		    }
		}

		//add Task Frame to appear
		Color bgColorTaskFrame = new Color(178,176,176);
		final JFrame addTaskFrame = new JFrame("Add Task"); 
		addTaskFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addTaskFrame.setSize(670,260);
		addTaskFrame.setLocation(450,200);
		addTaskFrame.getContentPane().setBackground(new Color(220,237,193));
		addTaskFrame.setLayout(null);
		addTaskFrame.setVisible(false);
		addTaskFrame.setUndecorated(true);
		
		//edit Task Frame to appear
		final JFrame editTaskFrame = new JFrame ("Edit Task");
		editTaskFrame.getContentPane().setBackground(new Color (255,211,182));
		editTaskFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editTaskFrame.setSize(670,260);
		editTaskFrame.setLocation(450,200);
		editTaskFrame.setLayout(null);
		editTaskFrame.setVisible(false);
		editTaskFrame.setUndecorated(true);
		
		//Task History Frame to appear
		final JFrame historyFrame = new JFrame ("Task History");
		historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		historyFrame.setLayout(new BorderLayout());
		historyFrame.setVisible(false);
		
		//History Log
		JLabel historyLog = new JLabel();
		historyFrame.add(historyLog, BorderLayout.NORTH);
		
		//Task Name
		Color bgcomp = new Color(171,208,221);
		JLabel insertName = new JLabel ("Name:");
		insertName.setForeground(Color.white);
		insertName.setFont(new Font("Arial", Font.BOLD,15));
		insertName.setBounds(50,30,80,20);
		JTextField taskName = new JTextField ();
		taskName.setBounds(100,25,200,30);
		taskName.setBackground(bgcomp);
		
		//Task Type
		JLabel type = new JLabel ("Type :");
		type.setForeground(Color.white);
		type.setFont(new Font("Arial", Font.BOLD,15));
		type.setBounds(50,65,80,20);
		String taskTypes[]= {"...","Assignment", "Activity", "Project", "Quiz", "Exam","Review"};
		JComboBox selectType = new JComboBox(taskTypes);
		selectType.setBounds(100,60,200,30);
		selectType.setBackground(bgcomp);
	
		//Date Picker
		JLabel dateLabel = new JLabel("Due   :");
		dateLabel.setForeground(Color.white);
		dateLabel.setFont(new Font("Arial", Font.BOLD,15));
		dateLabel.setBounds(50,105,80,20);
		JDateChooser pickDate = new JDateChooser();
		pickDate.setBounds(100,100,200,30);
		pickDate.setBackground(bgcomp);
		
		//Description Input
		JLabel insertDescription = new JLabel ("Description:");
		insertDescription.setForeground(Color.white);
		insertDescription.setFont(new Font("Arial", Font.BOLD,15));
		insertDescription.setBounds(340,20,100,20);
		JTextArea description = new JTextArea ();
		description.setBounds(340,45,280,130);
		description.setBackground(bgcomp);
		
		//Subject Input
		JLabel insertSubject = new JLabel ("Subject :");
		insertSubject.setForeground(Color.white);
		insertSubject.setFont(new Font("Arial", Font.BOLD,15));
		insertSubject.setBounds(50,145,80,20);
		JTextField subjectName = new JTextField ();
		subjectName.setBounds(130,140,170,30);
		subjectName.setBackground(bgcomp);
		
		//ADD and CLOSE Button
		KButton addButton = new KButton ();
		addButton.setText("ADD");
		addButton.setBounds(230, 200, 90, 30);
		addButton.setFont(new Font("Arial", Font.BOLD,13));
		KButton cancelButton = new KButton ();
		cancelButton.setText("CANCEL");
		cancelButton.setBounds(330, 200, 90, 30);
		cancelButton.setFont(new Font("Arial", Font.BOLD,13));
		
		//Components of editTaskButton
				//Task Name2
				JLabel insertName2 = new JLabel ("Name:");
				insertName2.setFont(new Font("Arial", Font.BOLD,15));
				insertName2.setForeground(Color.white);
				insertName2.setBounds(50,30,80,20);
				JTextField taskName2 = new JTextField ();
				taskName2.setBounds(100,25,200,30);
				taskName2.setBackground(bgcomp);
				
				//Task Type2
				JLabel type2 = new JLabel ("Type :");
				type2.setFont(new Font("Arial", Font.BOLD,15));
				type2.setForeground(Color.white);
				type2.setBounds(50,65,80,20);
				String taskTypes2[]= {"...","Assignment", "Activity", "Project", "Quiz", "Exam","Review"};
				JComboBox selectType2 = new JComboBox(taskTypes);
				selectType2.setBounds(100,60,200,30);
				selectType2.setBackground(bgcomp);
			
				//Date Picker2
				JLabel dateLabel2 = new JLabel("Due   :");
				dateLabel2.setForeground(Color.white);
				dateLabel2.setFont(new Font("Arial", Font.BOLD,15));
				dateLabel2.setBounds(50,105,80,20);
				JDateChooser pickDate2 = new JDateChooser();
				pickDate2.setBounds(100,100,200,30);
				pickDate2.setBackground(bgcomp);
				
				//Description Input2
				JLabel insertDescription2 = new JLabel ("Description:");
				insertDescription2.setForeground(Color.white);
				insertDescription2.setFont(new Font("Arial", Font.BOLD,15));
				insertDescription2.setBounds(340,20,100,20);
				JTextArea description2 = new JTextArea ();
				description2.setBounds(340,45,280,130);
				description2.setBackground(bgcomp);
				
				//Subject Input2
				JLabel insertSubject2 = new JLabel ("Subject :");
				insertSubject2.setForeground(Color.white);
				insertSubject2.setFont(new Font("Arial", Font.BOLD,15));
				insertSubject2.setBounds(50,145,80,20);
				JTextField subjectName2 = new JTextField ();
				subjectName2.setBounds(130,140,170,30);
				subjectName2.setBackground(bgcomp);
				
				//Confirm to edit a task and close Button
				KButton confirmButton = new KButton();
				confirmButton.setText("EDIT");
				confirmButton.setBounds(230, 200, 90, 30);
				confirmButton.setFont(new Font("Arial", Font.BOLD,13));
				KButton cancelButton2 = new KButton ();
				cancelButton2.setText("CANCEL");
				cancelButton2.setBounds(330, 200, 90, 30);
				cancelButton2.setFont(new Font("Arial", Font.BOLD,13));

		//Main Title
		JLabel title = new JLabel ("Students' Task Management System");
		title.setBounds(10, 2, 600, 30);
		title.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25 ));
		title.setForeground(Color.white);
		
		//Add a Task Button
		KButton addTask = new KButton();
		addTask.setText("Add Task");
		addTask.setkBorderRadius(50);
		addTask.setBorderPainted(false);
		addTask.setBounds(815, 50, 200, 50);
		addTask.setkStartColor(new Color(246,234,65));
		addTask.setkEndColor(new Color(119,0,127));

		//Edit a Task Button
		KButton editTask = new KButton ();
		editTask.setText("Edit Task");
		editTask.setkBorderRadius(50);
		editTask.setBorderPainted(false);
		editTask.setBounds(815, 110, 200, 50);
		editTask.setEnabled(false);
		editTask.setkStartColor(new Color(246,234,65));
		editTask.setkEndColor(new Color(119,0,127));
		
		//Delete a Task Button
		KButton deleteTask = new KButton ();
		deleteTask.setText("Delete Task");
		deleteTask.setkBorderRadius(50);
		deleteTask.setBorderPainted(false);
		deleteTask.setBounds(815, 170, 200, 50);
		deleteTask.setEnabled(false);
		deleteTask.setkStartColor(new Color(246,234,65));
		deleteTask.setkEndColor(new Color(119,0,127));
		
		//Mark as Done Button
		KButton doneTask = new KButton ();
		doneTask.setText("Mark as Done");
		doneTask.setkBorderRadius(50);
		doneTask.setBorderPainted(false);
		doneTask.setBounds(815, 230, 200, 50);
		doneTask.setEnabled(false);
		doneTask.setkStartColor(new Color(246,234,65));
		doneTask.setkEndColor(new Color(119,0,127));
		
		//History Button
		KButton historyButton = new KButton ();
		historyButton.setText("View Task History");
		historyButton.setkBorderRadius(50);
		historyButton.setBorderPainted(false);
		historyButton.setBounds(815, 560, 200, 50);	
		historyButton.setkStartColor(new Color(246,234,65));
		historyButton.setkEndColor(new Color(119,0,127));
		
		//JCalendar
		JLabel currentDate = new JLabel ("Current Date:");
		currentDate.setForeground(Color.white);
		currentDate.setBounds(790, 298, 200, 50);
		currentDate.setFont(new Font("Arial", Font.ITALIC, 15 ));
		JCalendar calendar = new JCalendar();
		KGradientPanel calendarPanel = new KGradientPanel();
		calendarPanel.setBounds(790, 340, 240, 180);
		calendarPanel.setkBorderRadius(0);
		calendarPanel.setkStartColor(new Color(13,181,182));
		calendarPanel.setkEndColor(new Color(55,4,57));
		calendarPanel.add(calendar);
		
		//Credits
		JLabel createdBy = new JLabel ("Created By: Danilo Llaga");
		createdBy.setForeground(Color.white);
		createdBy.setBounds(850, 650, 200, 20);
		createdBy.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		table.setAutoCreateRowSorter(true);
		
		//Set App Icon
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\icon.png");
		frame.setIconImage(icon);
		historyFrame.setIconImage(icon);
		
		//Main GUI frame components
		frame.add(title);
		frame.add(addTask);
		frame.add(editTask);
		frame.add(deleteTask);
		frame.add(doneTask);
		frame.add(calendarPanel);
		frame.add(currentDate);
		frame.add(historyButton);
		frame.add(createdBy);
		frame.add(panel);
		frame.add(panel2);
		frame.add(bgImageContainer);
		
		//addTaskFrame components
		addTaskFrame.add(insertName);
		addTaskFrame.add(taskName);
		addTaskFrame.add(type);
		addTaskFrame.add(selectType);
		addTaskFrame.add(dateLabel);
		addTaskFrame.add(pickDate);
		addTaskFrame.add(insertSubject);
		addTaskFrame.add(subjectName);
		addTaskFrame.add(description);
		addTaskFrame.add(insertDescription);
		addTaskFrame.add(addButton);
		addTaskFrame.add(cancelButton);
		addTaskFrame.add(bgImageContainer3);
		
		//editTaskFrame components
		editTaskFrame.add(insertName2);
		editTaskFrame.add(taskName2);
		editTaskFrame.add(type2);
		editTaskFrame.add(selectType2);
		editTaskFrame.add(dateLabel2);
		editTaskFrame.add(pickDate2);
		editTaskFrame.add(insertSubject2);
		editTaskFrame.add(subjectName2);
		editTaskFrame.add(description2);
		editTaskFrame.add(insertDescription2);
		editTaskFrame.add(confirmButton);
		editTaskFrame.add(cancelButton2);
		editTaskFrame.add(bgImageContainer2);
		
		//create a new file for saving data in the table
		File myFile = new File("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\file.txt");

		//continue Welcome Page
		continues.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(true);
				wFrame.dispose();
			}
			
		});
		
		//Exit Main Frame
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Save Table Data to File 
				try {
					FileWriter fw = new FileWriter(myFile);
					BufferedWriter bw = new BufferedWriter (fw);
					for(int i = 0; i<table.getRowCount(); i++) {
						for (int j =0; j<table.getColumnCount(); j++) {
							bw.write(table.getValueAt(i, j).toString() + "~");
						}
						bw.newLine();
					}
					bw.close();
					fw.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				addTaskFrame.dispose();
				editTaskFrame.dispose();
				historyFrame.dispose();
			}
			
		});
		
		//MiniMize Main Frame
		minimize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
			
		});
		
		//when addTask is clicked it creates a new frame with new set of Components
		addTask.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				addTaskFrame.setVisible(true);
			}
		});
		
		//when editTask is clicked it creates a new frame with new set of Components
		editTask.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
			editTaskFrame.setVisible(true);					
			int row = table.getSelectedRow();
			String taskname2 =(String) model.getValueAt(row, 0);
			String subjectname2 =(String) model.getValueAt(row, 3);
			String descriptions2 =(String) model.getValueAt(row, 4);
	        taskName2.setText(taskname2);
	        subjectName2.setText(subjectname2);
	        description2.setText(descriptions2);
			}
		});
		
		//when taskHistory is clicked it creates a new frame with new set of Components
		historyButton.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e){
				historyFrame.setVisible(true);	
				StringBuilder sb = new StringBuilder();
				try(BufferedReader br = new BufferedReader( new FileReader("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt"))) {
					String line;
					while((line = br.readLine()) != null) {
						sb.append(line).append("<br>");
					}
				
				historyLog.setText("<html>" + sb.toString() + "<html>");
				historyFrame.pack();
				historyFrame.setSize(400, 700);
				historyFrame.setLocation(1080, 100);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		
		//addButton ActionListener
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Get Data
				String TaskName = taskName.getText();
				String PickDate = ((JTextField) pickDate.getDateEditor().getUiComponent()).getText();
				String Description = description.getText();
				String SubjectName = subjectName.getText();
				int SelectTypeIndex = selectType.getSelectedIndex();
				String SelectType = null;
				for (int i = 0; i<8; i++) {
					if (SelectTypeIndex == i) {
						SelectType = taskTypes[i];
					}
				}
				
				//if Empty
				if  (TaskName.equals("")||SelectType.equals("")||PickDate.equals("")||SubjectName.equals("")||Description.equals("")) {
					JOptionPane.showMessageDialog(addTaskFrame, "Please Fill All Areas");
				}
				else{
					//Add Data to Table
					model.addRow(new Object[]{TaskName,SelectType, PickDate, SubjectName, Description });	
					
					//Add to Log
							try {
					//Creates a new event log in text file
								FileWriter fw = new FileWriter ("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt",true);
									fw.write("\n(" + currentDateTime + ") Added:\n");
									fw.append(TaskName + " - " + SelectType+ "\n---------------------------------------------------------------------------------------------------------------");
									fw.close();
								
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							//Reads the new event log to update historyLog label
							StringBuilder sb = new StringBuilder();
							try(BufferedReader br = new BufferedReader( new FileReader("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt"))) {
								String line;
								while((line = br.readLine()) != null) {
									sb.append(line).append("<br>");
								}
								//Updates JLabel
								historyLog.setText("<html>" + sb.toString() + "<html>");
								historyFrame.pack();
								historyFrame.setSize(400, 700);
								historyFrame.setLocation(1080, 100);
							}catch (IOException e1) {
								e1.printStackTrace();
							}
					
					addTaskFrame.dispose();
					pickDate.setCalendar(null);
			        selectType.setSelectedIndex(0);
			        taskName.setText("");
			        subjectName.setText("");
			        description.setText("");
			        
			        
				}
			}
			
		});
		
		//cancelButton ActionListener
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addTaskFrame.dispose();
				cancelButton.setBackground(UIManager.getColor("control"));
		        cancelButton.setForeground(null);
		        pickDate.setCalendar(null);
		        selectType.setSelectedIndex(0);
		        taskName.setText("");
		        subjectName.setText("");
		        description.setText("");
		        editTask.setEnabled(false);
				deleteTask.setEnabled(false);
				doneTask.setEnabled(false);
			}
			
		});
		
		//cancelButton2 ActionListener
		cancelButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editTaskFrame.dispose();
				cancelButton2.setBackground(UIManager.getColor("control"));
		        cancelButton2.setForeground(null);
		        pickDate2.setCalendar(null);
		        selectType2.setSelectedIndex(0);
		        taskName2.setText("");
		        subjectName2.setText("");
		        description2.setText("");
		        editTask.setEnabled(false);
				deleteTask.setEnabled(false);
				doneTask.setEnabled(false);
			}
			
		});
		
		//JTable ActionListener to enable buttons once selected
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					editTask.setEnabled(true);
					deleteTask.setEnabled(true);
					doneTask.setEnabled(true);
			}
		});
		
		//deleteTask Action Listener
		deleteTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					int a = JOptionPane.showConfirmDialog((Component)null, "Do you want to delete the selected task?", "Delete",JOptionPane.YES_NO_OPTION);
					if(a==0) {
						int row = table.getSelectedRow();
						
				//gets task name of selected row
						String name =(String) model.getValueAt(row, 0); 
						String type = (String) model.getValueAt(row, 1);
						try {
				//Creates a new event log in text file
							FileWriter fw = new FileWriter ("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt",true);
								fw.write("\n(" + currentDateTime + ") Deleted:\n");
								fw.append(name + " - " + type+ "\n---------------------------------------------------------------------------------------------------------------");
								fw.close();
							
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						//Reads the new event log to update historyLog label
						StringBuilder sb = new StringBuilder();
						try(BufferedReader br = new BufferedReader( new FileReader("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt"))) {
							String line;
							while((line = br.readLine()) != null) {
								sb.append(line).append("<br>");
							}
							//Updates JLabel
							historyLog.setText("<html>" + sb.toString() + "<html>");
							historyFrame.pack();
							historyFrame.setSize(400, 700);
							historyFrame.setLocation(1080, 100);
						}catch (IOException e1) {
							e1.printStackTrace();
						}
						model.removeRow(row);
						JOptionPane.showMessageDialog(null,"Deleted Successfully","Deleted",JOptionPane.INFORMATION_MESSAGE);
				        editTask.setEnabled(false);
						deleteTask.setEnabled(false);
						doneTask.setEnabled(false);
						
					}
				}
			}
			
		});
		
		//editTask ActionListener
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Get Data
				String TaskName2 = taskName2.getText();
				String PickDate2 = ((JTextField) pickDate2.getDateEditor().getUiComponent()).getText();
				String Description2 = description2.getText();
				String SubjectName2 = subjectName2.getText();
				int SelectTypeIndex2 = selectType2.getSelectedIndex();
				int row = table.getSelectedRow();
				String SelectType2 = null;
				for (int i = 0; i<8; i++) {
					if (SelectTypeIndex2 == i) {
						SelectType2 = taskTypes[i];
					}
				}
				
				//if Empty
				if  (TaskName2.equals("")||SelectType2.equals("")||PickDate2.equals("")||SubjectName2.equals("")||Description2.equals("")) {
					JOptionPane.showMessageDialog(editTaskFrame, "Please Fill All Areas");
				}
				else{
					//Edit Data to Table
					model.removeRow(row);
					model.addRow(new Object[]{TaskName2,SelectType2, PickDate2, SubjectName2, Description2 });	
					editTaskFrame.dispose();
					pickDate2.setCalendar(null);
			        selectType2.setSelectedIndex(0);
			        taskName2.setText("");
			        subjectName2.setText("");
			        description2.setText("");
				}
				//gets task name of seleccted row
				String name =(String) model.getValueAt(row, 0); 
				String type = (String) model.getValueAt(row, 1);
				try {
		//Creates a new event log in text file
					FileWriter fw = new FileWriter ("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt",true);
						fw.write("\n(" + currentDateTime + ") Edited:\n");
						fw.append(name + " - " + type+ "\n---------------------------------------------------------------------------------------------------------------");
						fw.close();
					
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				//Reads the new event log to update historyLog label
				StringBuilder sb = new StringBuilder();
				try(BufferedReader br = new BufferedReader( new FileReader("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt"))) {
					String line;
					while((line = br.readLine()) != null) {
						sb.append(line).append("<br>");
					}
					//Updates JLabel
					historyLog.setText("<html>" + sb.toString() + "<html>");
					historyFrame.pack();
					historyFrame.setSize(400, 700);
					historyFrame.setLocation(1080, 100);
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		//doneTask ActionListener
		doneTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					int a = JOptionPane.showConfirmDialog((Component)null, "Mark task as finished?", "Yes",JOptionPane.YES_NO_OPTION);
					if(a==0) {
						int row = table.getSelectedRow();
						
				//gets task name of selected row
						String name =(String) model.getValueAt(row, 0); 
						String type = (String) model.getValueAt(row, 1);
						try {
				//Creates a new event log in text file
							FileWriter fw = new FileWriter ("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt",true);
								fw.write("\n(" + currentDateTime + ") Finished:\n");
								fw.append(name + " - " + type + "\n---------------------------------------------------------------------------------------------------------------");
								fw.close();
							
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						//Reads the new event log to update historyLog label
						StringBuilder sb = new StringBuilder();
						try(BufferedReader br = new BufferedReader( new FileReader("C:\\Users\\danil\\eclipse-workspace\\TaskManagementSystem\\src\\taskManagementSystem\\log.txt"))) {
							String line;
							while((line = br.readLine()) != null) {
								sb.append(line).append("<br>");
							}
							//Updates JLabel
							historyLog.setText("<html>" + sb.toString() + "<html>");
							historyFrame.pack();
							historyFrame.setSize(400, 700);
							historyFrame.setLocation(1080, 100);
						}catch (IOException e1) {
							e1.printStackTrace();
						}
						model.removeRow(row);
						JOptionPane.showMessageDialog(null,"Task Marked as Done","Done",JOptionPane.INFORMATION_MESSAGE);
				        editTask.setEnabled(false);
						deleteTask.setEnabled(false);
						doneTask.setEnabled(false);
						
					}
				}
				
			}
			
		});
	
		//Frame listener for saving and loading table data
		frame.addWindowListener(new WindowAdapter() {
				
			@Override
			public void windowOpened(WindowEvent e) {
				//Load Table Data to File
				try {
					FileReader fr = new FileReader (myFile);
					BufferedReader br = new BufferedReader (fr);
					
					Object [] lines = br.lines().toArray();
					
					for(int i =0; i<lines.length; i++) {
						String [] row = lines [i].toString().split("~");
						model.addRow(row);
					}
					
					br.close();
					fr.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				 
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//After Loading all components, set Main Frame to visible
		frame.setVisible(true);

		//Check If Table is Empty
		try {
			FileReader fr = new FileReader (myFile);
			BufferedReader br = new BufferedReader (fr);
			
			Object [] lines = br.lines().toArray();
			if (lines.length == 0) {
				wFrame.setVisible(true);
			}
			
		} catch (Exception e) {
			// do nothing if exception caught
		}

	}
	
	public static void main(String[] args) {
		new taskManagementSystem();
	}

}
