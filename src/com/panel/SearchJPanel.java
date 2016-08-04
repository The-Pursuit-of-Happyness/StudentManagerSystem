package com.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.image.ImageFile;
import com.sql.SQL;

public class SearchJPanel extends JPanel implements Runnable{
	
	private JButton searchButton;
	private JLabel label;
	private JTextField searchJTextField;
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	private SQL sql;	
	private JTable table;
    private SearchModel student;
    private JPanel panel;
    private JPanel panelahead;
    
	public SearchJPanel(final SQL sql){
		this.sql = sql;
		//��ȡ��Ļ��С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//��ȡ��Ļ�ĸ߶ȺͿ��
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		label = new JLabel("��ѯ��Ϣ:");
		searchJTextField = new JTextField(25);		
		
		student = new SearchModel(sql);
		table = new JTable(student);
		
		student.showSearchStudent();
		
		//���ñ���Ƿ��Զ�����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//���ñ����и�
		table.setRowHeight(30);
				
		//���ñ���������		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
				
		//���ñ�������
		table.setDefaultRenderer(Object.class, tcr);
		//���ñ����п�
		TableColumnModel tcm =  table.getColumnModel();
		for(int i=0;i<tcm.getColumnCount();i++){
			TableColumn tc = tcm.getColumn(i);	
			tc.setMinWidth(140);
			tc.setPreferredWidth(100);		
		}
		
		this.setLayout(new BorderLayout());
		searchButton  = new JButton("��ѯһ��");
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == searchButton){
					sql.showsearch(searchJTextField.getText());
					repaint();
				}
			}
		});
		
		panel = new JPanel();	
		//��Ӹ��Ӻ�λ��
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);	
		panel.repaint();
		this.add(panel,BorderLayout.CENTER);
		
		//��Ӳ�ѯ��ť�����
		label.setFont(new Font("���Ŀ���",0,screenHeight/25));
		panelahead = new JPanel();
		panelahead.setLayout(new BorderLayout());
		panelahead.add(label,BorderLayout.WEST);
		panelahead.add(searchJTextField,BorderLayout.CENTER);
		panelahead.add(searchButton,BorderLayout.EAST );
		this.add(panelahead,BorderLayout.NORTH);
		new Thread(this).start();
	}
	
	//���Ʊ���ͼƬ
	protected void paintComponent( Graphics g ) {
		  setOpaque(true);
		  super.paintComponent(g);
		    g.drawImage(imagefile.addbackground,0,0,(int)(screenWidth/1.8),(int)(screenHeight/1.8),null);
		 }

	public void run() {
		while(true){
		try {
			Thread.sleep(50);
			this.repaint();
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		}		
	}
}
