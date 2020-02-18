package Project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Project extends JFrame{
	private static final long serialVersionUID = 1L;
	int studentNum;
	ArrayList<StudentInfo> arrStudent;
	
	public Project() {
		super("���� ���� ���α׷�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SubjectInfo info = new SubjectInfo("�ڹ�","����",3.0,3.0);
		SubjectInfo info2 = new SubjectInfo("���ͻ�ȸ","����",3.0,3.0);
		SubjectInfo info3 = new SubjectInfo("��� ���� ����","����",3.0,3.5);
		SubjectInfo info4 = new SubjectInfo("FreshMan","��Ÿ",1.0,3.5);
		SubjectInfo info5 = new SubjectInfo("C���","����",3.0,4.5);
		SubjectInfo info6 = new SubjectInfo("���̽�","����",3.0,4.0);
		
		ArrayList<SubjectInfo> array = new ArrayList<>();
		array.add(info);
		array.add(info2);
		array.add(info3);
		array.add(info4);
		array.add(info5);
		array.add(info6);
		
		StudentInfo st1 = new StudentInfo(2013211670, array);
		StudentInfo st2 = new StudentInfo(2013211745, array);
		
		ArrayList<Integer> arrayStudentNum = new ArrayList<>();
		arrayStudentNum.add(st1.getStudentNum());
		arrayStudentNum.add(st2.getStudentNum());
		
		arrStudent = new ArrayList<>();
		arrStudent.add(st1);
		arrStudent.add(st2);
		
		Container c = this.getContentPane();
		setLayout(null);
		
		JTextField studentNumField = new JTextField();
		JButton search = new JButton("�˻�");
		JButton score  = new JButton("����");
		JButton graduate  = new JButton("���� ���");
		JButton subject  = new JButton("�̼� ����");	
		studentNumField.setBounds(60, 30, 200, 20);
		search.setBounds(270, 25, 70, 30);
		score.setBounds(30, 70, 90, 50);
		graduate.setBounds(130, 70, 90, 50);
		subject.setBounds(230, 70, 90, 50);
		
		score.setEnabled(false);
		graduate.setEnabled(false);
		subject.setEnabled(false);
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				for(int i=0;i<arrayStudentNum.size();i++) {
					if(Integer.parseInt(studentNumField.getText()) == arrayStudentNum.get(i)) {
						studentNum = Integer.parseInt(studentNumField.getText());
					}
				}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(Project.this, "�й��� �߸� �Է��ϼ̽��ϴ�.\n �ٽ� �Է����ּ���", "��� �޽���", JOptionPane.WARNING_MESSAGE);
				}
				if(studentNum == Integer.parseInt(studentNumField.getText())) {
					score.setEnabled(true);
					graduate.setEnabled(true);
					subject.setEnabled(true);
					JOptionPane.showMessageDialog(Project.this, "��ϵ� �й��Դϴ�.");
					return;
				}
				else
					JOptionPane.showMessageDialog(Project.this, "��ϵ��� ���� �й��Դϴ�.", "��� �޽���", JOptionPane.WARNING_MESSAGE);
					score.setEnabled(false);
					graduate.setEnabled(false);
					subject.setEnabled(false);
			}
		});
		
		
		
		subject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentInfo info = null;
				for(int i=0;i<arrStudent.size();i++) {
					if(studentNum == arrStudent.get(i).studentNum){
						info = arrStudent.get(i);
					}
				}
				ScoreDialog scoreDialog = new ScoreDialog(Project.this, "�̼� ����", info);
				scoreDialog.setVisible(true);
			}
		});
		
		score.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentInfo info = null;
				for(int i=0;i<arrStudent.size();i++) {
					if(studentNum == arrStudent.get(i).studentNum){
						info = arrStudent.get(i);
					}
				}
				SubScoreDialog subScoreDialog = new SubScoreDialog(Project.this,"����",info);
				subScoreDialog.setVisible(true);
				
			}
		});
		
		graduate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentInfo info = null;
				for(int i=0;i<arrStudent.size();i++) {
					if(studentNum == arrStudent.get(i).studentNum){
						info = arrStudent.get(i);
					}
				}
				GraduateDialog graduDialog = new GraduateDialog(Project.this,"�������",info);
				graduDialog.setVisible(true);
				
			}
		});
		
		c.add(studentNumField);
		c.add(search);
		c.add(score);
		c.add(graduate);
		c.add(subject);
		
		this.setSize(380,250);
		this.setVisible(true);
	}
	class ScoreDialog extends JDialog {
		private JTable table;
		private JTable table2;
		private JTable table3;
		JPanel panel = new JPanel();

		public ScoreDialog(JFrame frame, String title, StudentInfo info) {
			super(frame,title);		
			panel.setLayout(new BorderLayout());
			panel.setSize(getWidth(), getHeight());
			JScrollPane main = new JScrollPane(panel);
			main.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
			this.setLayout(new BorderLayout());
			
			DefaultTableModel major = new DefaultTableModel();
			major.addColumn("�����");
			major.addColumn("����");    
			major.addColumn("����"); 
			for(int i=0;i<info.studentSubject.size();i++) {
				if((info.studentSubject.get(i).getDivision().equals("����"))){
					String[] java = {info.getStudentSubject().get(i).getSubjectName(),String.valueOf(info.getStudentSubject().get(i).getScore()),info.getStudentSubject().get(i).getSubjectEnglishScore()};
					major.addRow(java);
				}
			}
			table = new JTable(major);	   
		    JScrollPane areaScrollPane = new JScrollPane(table);
		    areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    areaScrollPane.setPreferredSize(new Dimension(250, 250));
		    areaScrollPane.setBorder(
		        BorderFactory.createCompoundBorder(
		            BorderFactory.createCompoundBorder(
		                            BorderFactory.createTitledBorder("����"),
		                            BorderFactory.createEmptyBorder(5,5,5,5)),
		            areaScrollPane.getBorder()));
		
		    
		    DefaultTableModel sub = new DefaultTableModel();
		    sub.addColumn("�����");
			sub.addColumn("����");    
			sub.addColumn("����"); 
			for(int i=0;i<info.studentSubject.size();i++) {
				if((info.studentSubject.get(i).getDivision().equals("����"))){
					String[] java = {info.getStudentSubject().get(i).getSubjectName(),String.valueOf(info.getStudentSubject().get(i).getScore()),info.getStudentSubject().get(i).getSubjectEnglishScore()};
					sub.addRow(java);
				}
			}
			table2 = new JTable(sub);	   
	        JScrollPane areaScrollPane1 = new JScrollPane(table2);
	        areaScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        areaScrollPane1.setPreferredSize(new Dimension(250, 250));
	        areaScrollPane1.setBorder(
	        	BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("����"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                areaScrollPane1.getBorder()));
	        
	        DefaultTableModel other = new DefaultTableModel();
		    other.addColumn("�����");
			other.addColumn("����");    
			other.addColumn("����"); 
			for(int i=0;i<info.studentSubject.size();i++) {
				if((info.studentSubject.get(i).getDivision().equals("��Ÿ"))){
					String[] java = {info.getStudentSubject().get(i).getSubjectName(),String.valueOf(info.getStudentSubject().get(i).getScore()),info.getStudentSubject().get(i).getSubjectEnglishScore()};
					other.addRow(java);
				}
			}
			table3 = new JTable(other);	   
	        JScrollPane areaScrollPane2 = new JScrollPane(table3);
	        areaScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        areaScrollPane2.setPreferredSize(new Dimension(250, 250));
	        areaScrollPane2.setBorder(
	        	BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("��Ÿ"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                areaScrollPane2.getBorder()));
		    
	        panel.add(areaScrollPane,BorderLayout.NORTH);
	        panel.add(areaScrollPane1,BorderLayout.CENTER);
	        panel.add(areaScrollPane2,BorderLayout.SOUTH);
	        add(main);
			this.setSize(350, 600);
		}
	}  
	
	class SubScoreDialog extends JDialog{
		JPanel panel = new JPanel();
		public SubScoreDialog(JFrame frame, String title,StudentInfo info) {
			super(frame,title);
			panel.setLayout(new GridLayout(0,2));
			panel.setSize(getWidth(), getHeight());
			int divinum = 0;
			double diviTotal = 0;
			double total = 0;
			double totalScore = 0;
			for(int i=0;i<info.studentSubject.size();i++) {
				total += info.studentSubject.get(i).getScore();
				totalScore += info.studentSubject.get(i).getSubjectScore();
				if((info.studentSubject.get(i).getDivision().equals("����"))){
					diviTotal += info.studentSubject.get(i).getSubjectScore();
					divinum++;
				}
				
			}
			
			JLabel label1 = new JLabel("  �� ����");
			label1.setFont(new Font("Serif",Font.BOLD,25));
			JLabel slabel1 = new JLabel(String.valueOf((int)total));
			slabel1.setFont(new Font("Serif",Font.BOLD,25));
			JLabel label2 = new JLabel("  �� ����");
			label2.setFont(new Font("Serif",Font.BOLD,25));
			JLabel slabel2 = new JLabel(String.valueOf(Math.round(totalScore/info.studentSubject.size()*100)/100.0));
			slabel2.setFont(new Font("Serif",Font.BOLD,25));
			JLabel label3 = new JLabel("  ���� ����");
			label3.setFont(new Font("Serif",Font.BOLD,25));
			JLabel slabel3 = new JLabel(String.valueOf(Math.round(diviTotal/divinum*100)/100.0));
			slabel3.setFont(new Font("Serif",Font.BOLD,25));
			
			panel.add(label1);
			panel.add(slabel1);
			panel.add(label2);
			panel.add(slabel2);
			panel.add(label3);
			panel.add(slabel3);
			
			add(panel);
			this.setSize(300,200);
		}
	}
	
	class GraduateDialog extends JDialog {
		private JPanel inPanel = new JPanel();
		private JTable table3;
		JPanel panel = new JPanel();
		int division;
		int sub;
		DefaultListModel listModel = new DefaultListModel();
		 JList myList= new JList();
		 
		public GraduateDialog(JFrame frame, String title, StudentInfo info) {
			super(frame,title);		
			panel.setLayout(new BorderLayout());
			panel.setSize(getWidth(), getHeight());
			JScrollPane main = new JScrollPane(panel);
			main.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
			this.setLayout(new BorderLayout());
			for(int i=0;i<info.studentSubject.size();i++) {
				if((info.studentSubject.get(i).getDivision().equals("����"))){
					division += (int) info.studentSubject.get(i).getScore();
				}
			}
			JLabel label = new JLabel();
			label.setText("63���� �� "+division+"�� �̼�");   
		    label.setBorder(
		        BorderFactory.createCompoundBorder(
		            BorderFactory.createCompoundBorder(
		                            BorderFactory.createTitledBorder("����"),
		                            BorderFactory.createEmptyBorder(5,5,5,5)),
		            label.getBorder()));

			for(int i=0;i<info.studentSubject.size();i++) {
				if((info.studentSubject.get(i).getDivision().equals("����"))){
					sub += (int) info.studentSubject.get(i).getScore();
				}
			}
			JLabel label1 = new JLabel();
			label1.setText("12���� �� "+sub+"�� �̼�");   
			label1.setPreferredSize(new Dimension());
	        label1.setBorder(
	        	BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("����"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                label1.getBorder()));
	        inPanel.setLayout(new BorderLayout());
	        
	        
	        listModel.addElement("����� �̼�");
	        myList.setModel(listModel);
	        JButton bt = new JButton("�߰�");
	        JButton bt1 = new JButton("����");
	        JScrollPane areaScrollPane2 = new JScrollPane(myList);
	        areaScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        areaScrollPane2.setPreferredSize(new Dimension(250,210));
	        inPanel.setPreferredSize(new Dimension(250,300));
	        
	        bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String add = JOptionPane.showInputDialog("�߰� �� ������ �Է��ϼ���.");
					listModel.addElement(add);
				}
			});
	        bt1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = myList.getSelectedIndex();
					listModel.remove(index);
				}
			});
	        
	        
	        inPanel.add(areaScrollPane2,BorderLayout.NORTH);
	        inPanel.add(bt,BorderLayout.CENTER);
	        inPanel.add(bt1,BorderLayout.SOUTH);
	        inPanel.setPreferredSize(new Dimension(250, 300));
	        inPanel.setBorder(
	        	BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("��Ÿ"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                inPanel.getBorder()));
		    
	        panel.add(label,BorderLayout.NORTH);
	        panel.add(label1,BorderLayout.CENTER);
	        panel.add(inPanel,BorderLayout.SOUTH);
	        add(main);
			this.setSize(350, 450);
		}
	}  

	class StudentInfo {
		int studentNum;
		ArrayList<SubjectInfo> studentSubject;
		public StudentInfo(int num, ArrayList<SubjectInfo> sub) {
			studentNum = num;
			studentSubject = sub;
		}
		public int getStudentNum() {
			return studentNum;
		}
		public ArrayList<SubjectInfo> getStudentSubject(){
			return studentSubject;
		}
	}
	
	class SubjectInfo{
		String subjectName;
		String division;
		double score;
		double subjectScore;
		String subjectEnglishScore;

		public SubjectInfo(String subject,String divi,double sco, double subjectSco) {
			subjectName = subject;
			division= divi;
			score= sco;
			subjectScore= subjectSco;
			if(subjectSco == 4.5) {
				subjectEnglishScore = "A+";
			}
			else if(subjectSco == 4.0) {
				subjectEnglishScore = "A";
			}
			else if(subjectSco == 3.5) {
				subjectEnglishScore = "B+";
			}
			else if(subjectSco == 3.0) {
				subjectEnglishScore = "B";
			}
			else if(subjectSco == 2.5) {
				subjectEnglishScore = "C+";
			}
			else if(subjectSco == 2.0) {
				subjectEnglishScore = "C";
			}
			else if(subjectSco == 1.5) {
				subjectEnglishScore = "D+";
			}
			else if(subjectSco == 1.0) {
				subjectEnglishScore = "D";
			}
			else 
				subjectEnglishScore = "F";
		}
	
		public String getSubjectName() {
			return subjectName;
		}
		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}
		public String getDivision() {
			return division;
		}
		public void setDivision(String division) {
			this.division = division;
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public double getSubjectScore() {
			return subjectScore;
		}
		public void setSubjectScore(double subjectScore) {
			this.subjectScore = subjectScore;
		}
		public String getSubjectEnglishScore() {
			return subjectEnglishScore;
		}

		public void setSubjectEnglishScore(String subjectEnglishScore) {
			this.subjectEnglishScore = subjectEnglishScore;
		}
	}
	public static void main(String[] args) {
		new Project();
	}
}