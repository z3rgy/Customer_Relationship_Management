package CustomerSystem;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CustomerSystem extends JFrame{

	public static final String String = null;
  
		MenuMain menuMain = new MenuMain();
		West west = new West();
		ShowTable showTable = new ShowTable();
		Buttons buttons = new Buttons();
		FileDialog read_open;
		FileDialog save_open;
		int updateRow;
		JPanel panelWest = new JPanel();
		Container contentPane;
		WestCard westCard = new WestCard();
		int count = 0;
		
		public CustomerSystem() {
			ImageIcon icon = new ImageIcon("images/image.png");
			 
			JOptionPane.showMessageDialog(null, null, "������ ���� �ý���",
			JOptionPane.NO_OPTION, icon);
			
			OUTTER: while(count<5){
			
				String password = JOptionPane.showInputDialog("������ �ý���" + "\n" + "�н����� �Է�");
				String passwd = "1111";
				
				if(password == null) {
					break OUTTER;
				}
				else if (password.equals(passwd)) {
	 
					setTitle("������ �ý���");
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					contentPane = getContentPane();
					contentPane.add(menuMain.mb, BorderLayout.NORTH);
					contentPane.add(buttons, BorderLayout.SOUTH);
					contentPane.add(showTable.scroll, BorderLayout.CENTER); 
					contentPane.add(panelWest, BorderLayout.WEST);
					panelWest.add(west, BorderLayout.NORTH);
					west.setBackground(Color.ORANGE);
					panelWest.add(westCard, BorderLayout.CENTER);
					panelWest.setBackground(Color.YELLOW);
					panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
					
					setSize(1300, 1000);
					setLocation(400, 30);
					setVisible(true);
					break OUTTER;
				}else {
					if(count==4){
						JOptionPane.showMessageDialog(null, "�н����� 5ȸ ����." + "\n" + "���α׷��� �����մϴ�!",
													  "�н����� ���� ����",JOptionPane.ERROR_MESSAGE
													 );
						System.exit(0);
					}else {
						JOptionPane.showMessageDialog(null, "�н����尡 ���� �ʽ��ϴ�." + "\n" + "'Ȯ��' ��ư�� ��������!",
													  "�н����� ���� ����",JOptionPane.ERROR_MESSAGE
													 );
						count += 1;
						continue OUTTER;
					}
				}
			}
		}
 
		public void westCardReset() {
			westCard.ageLabel.setText(null);
			westCard.sexLabel.setText(null);
			westCard.chulLabel.setText(null);
			westCard.birthLabel.setText(null);
		}
		
		class MenuMain extends JPanel implements ActionListener, ItemListener{

			JMenuBar mb;
			JMenu file,sort,help;
			JMenuItem fnew,fopen,fsave,exit,proinfo;
			JCheckBoxMenuItem num, name, chulsort, jobsort;
			
			FileDialog readOpen, saveOpen;
			String fileDir, fileName, saveFileName;
			
			public MenuMain() {
				mb = new JMenuBar();
				file = new JMenu("����"); sort = new JMenu("����"); help = new JMenu("����");
				fnew = new JMenuItem("������"); fopen = new JMenuItem("����"); fsave = new JMenuItem("����");
				exit = new JMenuItem("������");
				proinfo = new JMenuItem("����");
				num = new JCheckBoxMenuItem("��ȣ");
				name = new JCheckBoxMenuItem("�̸�");  
				chulsort = new JCheckBoxMenuItem("�������");
				jobsort = new JCheckBoxMenuItem("����");
				
				mb.add(file);mb.add(sort);mb.add(help);
				file.add(fnew);file.add(fopen);file.add(fsave);file.addSeparator();file.add(exit);
				sort.add(num);sort.add(name);sort.add(chulsort);sort.add(jobsort);
				help.add(proinfo);
				
				fnew.addActionListener(this);
				fopen.addActionListener(this);
				fsave.addActionListener(this);
				exit.addActionListener(this);
				num.addItemListener(this);
				name.addItemListener(this);
				chulsort.addItemListener(this);
				jobsort.addItemListener(this);
				proinfo.addActionListener(this);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("������")){
					file_new();
				}
				if(e.getActionCommand().equals("����")){
					file_open();
				}
				if(e.getActionCommand().equals("����")){
					file_save();
				}
				if(e.getActionCommand().equals("����")){
					proInfo();
				}
				if(e.getActionCommand().equals("������")){
					exit();
				}
				
			}

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource().equals(num)) {
					Sort(0);
				}if(e.getSource().equals(name)) {
					Sort(1);
				}if(e.getSource().equals(chulsort)) {
					Sort(7);
				}if(e.getSource().equals(jobsort)) {
					Sort(9);
				}
			}

			public void proInfo() {
				JOptionPane.showMessageDialog(null,
											  "�� ���� ���α׷� CRM ������Ʈ"+"\n"+"2021/04/12"+"\n"+"������ : ����",
											  "CRM 2021", JOptionPane.PLAIN_MESSAGE
											 );
			}

			public void file_new() {
				int yes_no_select = JOptionPane.showConfirmDialog(null, "���� ���ο� ������ ����ðڽ��ϱ�?",
				"������ Ȯ��â", JOptionPane.YES_NO_OPTION);
				if(yes_no_select == JOptionPane.YES_OPTION) {
					westCardReset();
					showTable.datamodel.setRowCount(0);  
					west.tf[0].setText(null);
					west.tf[1].setText(null);
					west.tf[2].setText(null);
					west.tf[3].setText(null);
					west.tf[4].setText(null);
					return;
				}else {
					return;
				}
			}

			public void Sort(int x) {
				int y = x;
				int row = showTable.table.getRowCount();
				int col = showTable.table.getColumnCount();
				
				String[][] arr = new String[row][col];
				for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
				arr[i][j] = (String) showTable.table.getValueAt(i, j);}}
				
				if (y == 0)   Arrays.sort(arr, (a, b) -> Integer.compare(Integer.parseInt(a[y]), Integer.parseInt(b[y])));
		         else Arrays.sort(arr, (a, b) -> a[y].compareTo(b[y]));
					
				for(int i = 0; i < row; i++) {
					for(int j = 0; j < col; j++) {
						showTable.table.setValueAt(arr[i][j],i,j);
					}
				}
				num.setState(false);
				name.setState(false);
				chulsort.setState(false);
				jobsort.setState(false);
			}
		}

		public void file_open() {
			StringTokenizer st;
			Vector<String> vec;
			read_open = new FileDialog(CustomerSystem.this,"����",FileDialog.LOAD);
			read_open.setVisible(true);
			
			String fileDir = read_open.getDirectory();
			String fileName = read_open.getFile();
			String readfilename = fileDir + "\\"+ fileName;
			
			try {
				BufferedReader read = new BufferedReader(new FileReader(readfilename));
				String line = null;
				showTable.data.removeAllElements();
				
				while((line=read.readLine())!=null) {
					
					st=new StringTokenizer(line,",");
					vec=new Vector<String>();
			
				while(st.hasMoreTokens()) {
					vec.add(st.nextToken().trim());
				}
					showTable.data.addElement(vec);
				}
				showTable.datamodel.fireTableDataChanged();
				read.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}

		public void file_save() {
			save_open = new FileDialog(CustomerSystem.this, "����", FileDialog.SAVE);
			save_open.setVisible(true);
			
			String fileDir = save_open.getDirectory();
			String fileName = save_open.getFile();
			String savefilename = fileDir + "\\"+ fileName;
			
			String str = "";
			String temp = "";
			
			try {
				BufferedWriter save = new BufferedWriter(new FileWriter(savefilename));
				
				for(int i=0;i<showTable.table.getRowCount();i++) {
					temp=showTable.data.elementAt(i).toString();
					str=str+temp.substring(1, temp.length()-1)+"\n";
				}
				save.write(str);
				save.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}

		public void exit() {
			
			int yes_no_select = JOptionPane.showConfirmDialog(null, "���� ���� �Ͻðڽ��ϴ�?",
															  "���� Ȯ��â", JOptionPane.YES_NO_OPTION
															 );
			
			if(yes_no_select == JOptionPane.YES_OPTION) {
				System.exit(0);
			}else {
				return;
			}
		}  

		class West extends JPanel{
		
			JLabel la;
			JTextField tf[];
			JComboBox<String> jobname;
			
			public West() {
				
				setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "�Է�"));
				setLayout(new GridLayout(6,2,5,40));
				String[] text = {"��ȣ","�̸�","�ڵ��� ��ȣ","�̸���","�ֹι�ȣ"};
				String[] jobtext = {"�л�", "ȸ���", "����","��Ÿ" };
				tf = new JTextField[6];
				
				for(int i=0; i<text.length;i++) {
					la = new JLabel(text[i]);
					tf[i] = new JTextField(20);
					la.setHorizontalAlignment(JLabel.CENTER);
					add(la);add(tf[i]);
				}
				JLabel job = new JLabel("����");
				add(job);
				job.setHorizontalAlignment(JLabel.CENTER);
				
				jobname = new JComboBox(jobtext);
				
				add(jobname);
				setPreferredSize(new Dimension(350,500));
			}
		}

		class WestCard extends JPanel implements ActionListener{
		  
			JLabel ageLabel = new JLabel();
			JLabel sexLabel = new JLabel();
			JLabel chulLabel = new JLabel();
			JLabel birthLabel = new JLabel();
			JPanel p = new JPanel();
			JPanel search = new JPanel();  //�˻�ī�带 ����� ���� JPanel
			CardLayout card;
			JTextField jf = new JTextField(10);
			JTable table2 = showTable.table;
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(showTable.table.getModel());
			JRadioButton searchRadio[] = new JRadioButton[4];
		
		  
			public WestCard() {
			  
				card = new CardLayout();
				setLayout(card);
				  
				p.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "�Ż�����"));
				p.setLayout(new GridLayout(4, 2, 5, 5)); 
				p.add(new JLabel("����: ", JLabel.LEFT));
				p.add(ageLabel); 
				p.add(new JLabel("����: ", JLabel.LEFT));
				p.add(sexLabel);
				p.add(new JLabel("�������: ", JLabel.LEFT));
				p.add(chulLabel); 
				p.add(new JLabel("�������: ", JLabel.LEFT));
				p.add(birthLabel); 
				p.setPreferredSize(new Dimension(350,200));
				p.setBackground(Color.YELLOW);
				add(p, "ù��° ī��");
				 
				JButton btn = new JButton("�˻�");
				search.setBorder(new TitledBorder(new LineBorder(Color.pink, 2), "�˻�"));
				 
				ButtonGroup group = new ButtonGroup();
				String search_name[] = {"�̸�","����","�������","�������"};
				JButton search_btn = new JButton("ã��");
				JButton exit_btn = new JButton("������");
				
				search_btn.setBounds(25, 130, 70, 30);
				exit_btn.setBounds(115, 130, 110, 30);
				search.setLayout(null);
				int x = -70;
				search.setPreferredSize(new Dimension(350,200)); 
				for(int i = 0; i < searchRadio.length; i++){
					searchRadio[i] = new JRadioButton(search_name[i]);
					searchRadio[i].setBounds(x += 80,30,80,30);
					group.add(searchRadio[i]);
					search.add(searchRadio[i]);
				}
				searchRadio[0].setSelected(true);
				jf.setBounds(25, 80, 200, 30);
				  
				search.add(jf);
				search.add(search_btn);
				search.add(exit_btn);
				search.setBackground(Color.YELLOW);
				add(search, "�ι�° ī��");
				search_btn.addActionListener(this);
				exit_btn.addActionListener(this);
			}
		 
			@Override
			public void actionPerformed(ActionEvent e) {
			  
				if(e.getActionCommand().equals("������")) {
					showTable.datamodel.setDataVector(showTable.data,showTable.column_name);
					card.next(this);
					jf.setText(null);
					west.tf[0].setText(null);
					west.tf[1].setText(null);
					west.tf[2].setText(null);
					west.tf[3].setText(null);
					west.tf[4].setText(null);
					showTable.datamodel.fireTableDataChanged();
					showTable.setsize();
				}
				
				if(e.getActionCommand().equals("ã��")){
					 Vector<Vector<String>> findData = new Vector<Vector<String>>();
					
					int num = 0;
					if(searchRadio[0].isSelected()) {//�̸�
						num = 1;
					}
					if(searchRadio[1].isSelected()) {//����
						num = 9;
					}
					if(searchRadio[2].isSelected()) {//�������
						num = 7;
					}
					if(searchRadio[3].isSelected()) {//����
						num = 8;
					}
					for(int i = 0; i < showTable.data.size(); i++) {
						if(showTable.data.elementAt(i).get(num).contains(jf.getText().trim())) {
							findData.addElement(showTable.data.elementAt(i));
						}
					}
					  
					if(findData.isEmpty()) {
						JOptionPane.showMessageDialog(null, "�˻��Ͻ� ������ �����ϴ�", "���޽���", JOptionPane.ERROR_MESSAGE);  
						jf.setText(null);;
					} else {	
						showTable.datamodel.setDataVector(findData, showTable.column_name);
						showTable.datamodel.fireTableDataChanged();
						showTable.setsize();
					}
				}
			}
		}

		class ShowTable extends MouseAdapter{
		  
			DefaultTableModel datamodel;
			JTable table;
			JScrollPane scroll;
			String[] colName = {"��ȣ","�̸�","�ڵ��� ��ȣ","�̸���","�ֹι�ȣ","����","����","�������","����","����"};
			Vector<Vector<String>> data;
			Vector<String> column_name;
		
			public ShowTable() {
			  
				data = new Vector<Vector<String>>();
				column_name = new Vector<String>();
				
				for(int i=0;i<colName.length;i++) {
					column_name.add(colName[i]);
				}
				
				datamodel = new DefaultTableModel(data, column_name);
				table = new JTable(datamodel);
				scroll = new JScrollPane(table);
				setsize();
				  
				table.addMouseListener(this);
//				table.setRowSorter(new TableRowSorter(datamodel));
			
			}
		
			@Override
			public void mouseClicked(MouseEvent e) {
				
				updateRow = table.getSelectedRow();
				
				for(int i=0;i<5;i++) {
					west.tf[i].setText((String)showTable.table.getValueAt(updateRow, i));
				}
				
				west.jobname.setSelectedItem((String)showTable.table.getValueAt(updateRow, 9)); 
				west.tf[4].setEditable(false);
				buttons.addBtn.setEnabled(false);
				westCard.ageLabel.setText((String)showTable.table.getValueAt(updateRow, 5));
				westCard.sexLabel.setText((String)showTable.table.getValueAt(updateRow, 6));
				westCard.chulLabel.setText((String)showTable.table.getValueAt(updateRow, 7));
				westCard.birthLabel.setText((String)showTable.table.getValueAt(updateRow, 8));
			}
			
			public void setsize() {
				
				table.getColumnModel().getColumn(0).setPreferredWidth(40); //��ȣ
				table.getColumnModel().getColumn(1).setPreferredWidth(80); //�̸�
				table.getColumnModel().getColumn(2).setPreferredWidth(120); //����
				table.getColumnModel().getColumn(3).setPreferredWidth(150); //����
				table.getColumnModel().getColumn(4).setPreferredWidth(150); //�ι�
				table.getColumnModel().getColumn(5).setPreferredWidth(50); //����
				table.getColumnModel().getColumn(6).setPreferredWidth(50); //����
				table.getColumnModel().getColumn(7).setPreferredWidth(60); //���
				table.getColumnModel().getColumn(8).setPreferredWidth(100); //����
				table.getColumnModel().getColumn(9).setPreferredWidth(50);  //�۾�
				
				DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();
				tableCell.setHorizontalAlignment(SwingConstants.CENTER);
				
				for(int i=0;i<table.getColumnCount();i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(tableCell);
				}
			}
		}

		class Buttons extends JPanel implements ActionListener{

			Vector<String> vector;
			JButton addBtn, updateBtn, delBtn, btn, preBtn, nextBtn;
			String age; String sex; String birthplace; String bday;
			String juminNum;

			public Buttons() {
			  
				setLayout(new GridLayout(1,6,5,0));
				addBtn = new JButton("�߰�");
				updateBtn = new JButton("����");
				preBtn = new JButton("����");
				nextBtn = new JButton("����");
				delBtn = new JButton("����");
				btn = new JButton("�˻�");
				
				addBtn.setBackground(Color.CYAN);
				updateBtn.setBackground(Color.LIGHT_GRAY);
				delBtn.setBackground(Color.PINK);
				btn.setBackground(Color.MAGENTA);
				
				add(addBtn);add(updateBtn);add(preBtn);
				add(nextBtn);add(delBtn);add(btn);
				
				addBtn.addActionListener(this);
				updateBtn.addActionListener(this);
				delBtn.addActionListener(this);
				btn.addActionListener(this);
				preBtn.addActionListener(this);
				nextBtn.addActionListener(this);
				
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("�߰�")) {
					addData();
				}else if (e.getActionCommand().equals("����")) {
					upData();
				}else if (e.getActionCommand().equals("����")){
					delData();
				}else if (e.getActionCommand().equals("�˻�")) {
					search();
				}else if (e.getActionCommand().equals("����")) {
					previous();
				}else if (e.getActionCommand().equals("����")) {
					next();
				}
			
			}

			public void previous() {
				
				if(updateRow==0) {
					showTable.table.removeRowSelectionInterval(0, showTable.table.getRowCount()-1);
					updateRow = showTable.table.getRowCount()-1;
				}else {
					showTable.table.removeRowSelectionInterval(0, showTable.table.getRowCount()-1);
					updateRow -= 1;
				}
				
				show();
				
			}

			public void next() {
				
				if(updateRow == showTable.table.getRowCount()-1) {
					showTable.table.removeRowSelectionInterval(0, showTable.table.getRowCount()-1);
					updateRow =0;
				}else {
					showTable.table.removeRowSelectionInterval(0, showTable.table.getRowCount()-1);
					updateRow += 1;
				}
				
				show();
				
			}

			public void show() {
				
				showTable.table.addRowSelectionInterval(updateRow, updateRow);
				west.tf[0].setText((String)showTable.table.getValueAt(updateRow, 0));
				west.tf[1].setText((String)showTable.table.getValueAt(updateRow, 1));
				west.tf[2].setText((String)showTable.table.getValueAt(updateRow, 2));
				west.tf[3].setText((String)showTable.table.getValueAt(updateRow, 3));
				west.tf[4].setText((String)showTable.table.getValueAt(updateRow, 4));
				west.jobname.setSelectedItem((String)showTable.table.getValueAt(updateRow, 9)); 
				west.tf[4].setEditable(false);
				buttons.addBtn.setEnabled(false);
				westCard.ageLabel.setText((String)showTable.table.getValueAt(updateRow, 5));
				westCard.sexLabel.setText((String)showTable.table.getValueAt(updateRow, 6));
				westCard.chulLabel.setText((String)showTable.table.getValueAt(updateRow, 7));
				westCard.birthLabel.setText((String)showTable.table.getValueAt(updateRow, 8));
//				DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();
//				tableCell.setHorizontalAlignment(SwingConstants.CENTER);
				
			}

			public void search() {
				westCard.card.next(westCard);
			}



			public void addData() {
				Vector<String> vector = new Vector<String>();
				juminNum = west.tf[4].getText();
				updateRow=showTable.table.getSelectedRow();
				if(west.tf[0].getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "��ȣ�� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					
					west.tf[0].setText(null);
					west.tf[0].requestFocus();
					return;
					
				} 
				if(west.tf[1].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					west.tf[1].setText(null);
					west.tf[1].requestFocus();
					return;
				}
				if(west.tf[2].getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "�ڵ��� ��ȣ�� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					west.tf[2].setText(null);
					west.tf[2].requestFocus();
					return;
					
				}
				if(west.tf[2].getText()!= null) {
					
					boolean p = pcheck();
					if(p==false) {
						JOptionPane.showMessageDialog(null, "�ڵ��� �Է��� �ٽ� Ȯ�����ּ���",
													  "�ڵ��� �Է¿���", JOptionPane.ERROR_MESSAGE
													 );
						west.tf[2].requestFocus();
						return; 
					}else {	//
						
					}
				}
			
				if(west.tf[3].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					west.tf[3].setText(null);
					west.tf[3].requestFocus();
					return;
				}else {
					boolean checke = emailCheck();
					if(checke == false) {
						JOptionPane.showMessageDialog(null, "�ùٸ� �̸����� �ƴմϴ�.", 
													  "���޼���", JOptionPane.ERROR_MESSAGE
													 );
						return;
					}
				}
				String regex="[0-9]{6}-[1234][0-9]{6}";
				boolean check = Pattern.matches(regex, juminNum);
				//�ֹι�ȣ 13�ڸ� Ȯ��
				if(check==false) {//�ֹι�ȣ 13�ڸ� �ƴҰ��
					JOptionPane.showMessageDialog(null, "�ùٸ� �ֹι�ȣ ������ �ƴմϴ�.", 
												  "�Է¿���", JOptionPane.INFORMATION_MESSAGE
												 ); 
					return;
				}else {
					boolean c = juminCheck();
					if(c) {	//�ֹ� ��ȿ�� ����
						String[][] localeCode = {
							{"����","00","08"},{"�λ�","09","12"},
							{"��õ","13","15"},{"���","16","25"},
							{"����","26","34"},{"���","35","39"},
							{"����","40","41"},{"�泲","42","47"},
							{"�泲","42","47"},{"����","44","44"},
							{"����","96","96"},{"����","48","45"},
							{"����","55","64"},{"����","65","66"},
							{"�뱸","67","70"},{"���","71","80"},
							{"�泲","81","84"},{"�泲","86","90"},
							{"���","85","85"},{"����","91","95"}
						};
	  
						Calendar cal = Calendar.getInstance(Locale.KOREA); //�ѱ� �ð� ��������
						int year = cal.get(Calendar.YEAR); //�ѱ� ������ �����ͼ� �������ֱ�
						String myyear = juminNum.substring(0, 2); //substring���� �Է��� �ֹι�ȣ ������ ������
						int myold = Integer.parseInt(myyear); //���� ���ڿ��� �����κ�ȯ
						int gendercode = Integer.parseInt(juminNum.substring(7, 8));//substring(�����ּ�, �����ּ�+�����ð���); 
						if(gendercode == 1 || gendercode == 2 ) { //���� �Է��� �ֹ� 7��° ���ڰ� 1�̰ų� 2��
							myold = myold + 1900; 
						}else if(gendercode == 3 || gendercode == 4 ) { //���� �Է��� �ֹ� 7��° ���ڰ� 3�̰ų� 4��
							myold = myold + 2000;
						}
						int a = year - myold + 1;
						age = Integer.toString(a);  
						if((juminNum.charAt(7)-'0') % 2 == 0) {
							sex = "����";
						}else {
							sex = "����";
						}
						String localeStinrg = juminNum.substring(8, 10); //�Է��� �ֹι�ȣ 8,9��° ����
						int locale = Integer.parseInt(localeStinrg);
						for(int j = 0; j <20; j++) {
							if((locale >= Integer.parseInt(localeCode[j][1])) &&
								(locale <= Integer.parseInt(localeCode[j][2]))) {
								
								birthplace = localeCode[j][0]; 
							}
						}
						bday=(myold+"/"+juminNum.substring(2, 4)+"/"+juminNum.substring(4, 6));
	  
	
						
					}else { //�ֹ� ��ȿ�� üũ Ʋ�����
						JOptionPane.showMessageDialog(null, "��Ȯ�� �ֹι�ȣ �ƴ�!", 
													  "���޼���", JOptionPane.ERROR_MESSAGE
													 );
						west.tf[4].setText(null);
						west.tf[4].requestFocus(); 
						return;
					}
					
					int reply = JOptionPane.showConfirmDialog(null, 
							  "�Է��Ͻ� ������ �����ʴϱ�?"+"\n"+"�̸�: "+west.tf[1].getText()+"\n"+
							  "��ȯ��ȣ: "+west.tf[2].getText()+"\n"+"�̸���: "+west.tf[3].getText()+"\n"+
							  "�ֹε�Ϲ�ȣ: "+west.tf[4].getText()+"\n"+"����: "+(String)west.jobname.getSelectedItem(),
							  "�Է� ���� Ȯ��", JOptionPane.YES_NO_OPTION
							 );

					if (reply == JOptionPane.YES_OPTION) { // show data to jTable
						vector.add(west.tf[0].getText());
						vector.add(west.tf[1].getText());
						vector.add(west.tf[2].getText());
						vector.add(west.tf[3].getText());
						vector.add(west.tf[4].getText());
						vector.add(age);
						vector.add(sex);
						vector.add(birthplace);
						vector.add(bday);
						vector.add((String)west.jobname.getSelectedItem());
						showTable.data.addElement(vector);
						showTable.datamodel.fireTableDataChanged();
						JOptionPane.showMessageDialog(null, west.tf[1].getText()+ "�� �߰� ����", 
						"�� �߰�", JOptionPane.INFORMATION_MESSAGE);
						west.tf[0].setText(null);
						west.tf[1].setText(null);
						west.tf[2].setText(null);
						west.tf[3].setText(null);
						west.tf[4].setText(null);
						west.jobname.setSelectedIndex(0);
					}
				}
			}

			public boolean pcheck() {
				
				String hpNum = west.tf[2].getText(); // �ڵ�����ȣ�ʵ尪
				String hpnum_pattern = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$";
				boolean checkHP = hpNum.matches(hpnum_pattern);
				if (checkHP) {
					return true;
				}else {
					return false;
				}
				
			}
			
			public boolean emailCheck() {	// �̸��� üũ
				
				String regexemail = "^\\w+(\\.\\w)?@\\w+\\.\\w+(\\.\\w+)?";
				boolean check1 = west.tf[3].getText().matches(regexemail);
				if(check1 == true) {
					return true;
				}else {
					return false;
				}
				
			}

			public boolean juminCheck() {
			 
				int [] weight = {2,3,4,5,6,7,0,8,9,2,3,4,5};
				int sum = 0;
				for(int i=0;i<13;i++) {
					if(juminNum.charAt(i)=='-') {
						continue;
					}
					sum=sum+((juminNum.charAt(i)-48)*weight[i]);
				}
				
				int temp = 11-(sum%11);
				int result = temp%10;
				
				if (result==(juminNum.charAt(13)-48)) {
					return true;
				}else {
					return false;
				}
			}
			
	
			public void upData() {
				updateRow=showTable.table.getSelectedRow();
				if(west.tf[0].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ȣ�� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					west.tf[0].setText(null);
					west.tf[0].requestFocus();
					return;
				} 
				else if(west.tf[1].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					west.tf[1].setText(null);
					west.tf[1].requestFocus();
					return;
				}
				else if(west.tf[2].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�ڵ��� ��ȣ�� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					west.tf[2].setText(null);
					west.tf[2].requestFocus();
					return;
				}
				if(west.tf[2].getText()!= null) {
					boolean p = pcheck();
					if(p==false) {
						JOptionPane.showMessageDialog(null, "�ڵ��� �Է��� �ٽ� Ȯ�����ּ���",
													  "�ڵ��� �Է¿���", JOptionPane.ERROR_MESSAGE
													 );
						west.tf[2].requestFocus();
						return; 
					}else {	//üũ
						
					}
				}
	 			if(west.tf[3].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
					west.tf[3].setText(null);
					west.tf[3].requestFocus();
					return;
	 			}
	 			if(west.tf[3].getText() != null) {
					boolean check = emailCheck();
					if(check == false) {
						JOptionPane.showMessageDialog(null, "�ùٸ� �̸����� �ƴմϴ�.", 
													  "���޼���", JOptionPane.ERROR_MESSAGE
													 );
						return;
	 				}else {
						showTable.table.setValueAt(west.tf[0].getText(), updateRow, 0);
						showTable.table.setValueAt(west.tf[1].getText(), updateRow, 1);
						showTable.table.setValueAt(west.tf[2].getText(), updateRow, 2);
						showTable.table.setValueAt(west.tf[3].getText(), updateRow, 3);
						showTable.table.setValueAt(west.tf[4].getText(), updateRow, 4);
						showTable.table.setValueAt(west.jobname.getSelectedItem(), updateRow, 9);
						
						west.tf[0].setText(null);
						west.tf[1].setText(null);
						west.tf[2].setText(null);
						west.tf[3].setText(null);
						west.tf[4].setText(null);
						west.tf[4].setEditable(true);
						buttons.addBtn.setEnabled(true);
						west.jobname.setSelectedIndex(0);
						west.tf[0].requestFocus();
						westCardReset();
						showTable.table.removeRowSelectionInterval(updateRow, updateRow);
						JOptionPane.showMessageDialog(null, "������Ʈ �Ϸ�", 
													  "������Ʈ �Ϸ�", JOptionPane.INFORMATION_MESSAGE
													 );
	 				}
	 			}
			}


			public void delData() {
				JTable tb = showTable.table;
				int deleteRow = tb.getSelectedRow();
				
	 			if(deleteRow==-1) {
					JOptionPane.showMessageDialog(null, "������ ���� �������ּ���.", 
												  "���޼���", JOptionPane.ERROR_MESSAGE
												 );
	 				return;
	 			}else {
					int yes_no_select = JOptionPane.showConfirmDialog(null, "���� �����ϰڽ��ϴ�?",
																	  "���� Ȯ��â", JOptionPane.YES_NO_OPTION
																	 );
					if(yes_no_select == JOptionPane.YES_OPTION) {
						//DefaultTableModel model = (DefaultTableModel) tb.getModel();
						
						showTable.datamodel.removeRow(deleteRow);
						west.tf[0].setText(null);
						
						west.tf[2].setText(null);
						west.tf[3].setText(null);
						west.tf[4].setText(null);  
						west.tf[4].setEditable(true);
						buttons.addBtn.setEnabled(true);
						west.tf[0].requestFocus();
						westCardReset();
						JOptionPane.showMessageDialog(null, west.tf[1].getText()+" ȸ���� ���� �Ǿ����ϴ�.",
                                "ȸ�� ����",JOptionPane.INFORMATION_MESSAGE);
						west.tf[1].setText(null);
					}else {
						west.tf[0].setText(null);
						west.tf[1].setText(null);
						west.tf[2].setText(null);
						west.tf[3].setText(null);
						west.tf[4].setText(null);
						west.tf[4].setEditable(true);
						buttons.addBtn.setEnabled(true);
						west.tf[0].requestFocus();
						showTable.table.removeRowSelectionInterval(0, updateRow);
						return;
					}
	 			}
			}
		}
		
		
		public static void main(String[] args) {
			
				CustomerSystem cs = new CustomerSystem();
				
		}
		
}
