package com.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.image.ImageFile;
import com.sql.SQL;

public class CountJPanel extends JPanel {
	private JLabel label;
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	private SQL sql;
	private JTable table;
    private StudentModel student;
	
	public CountJPanel(SQL sql){
		this.sql = sql;
		
		student = new StudentModel(sql);
		table = new JTable(student);
		
		this.setLayout(new BorderLayout());
		//��Ӹ��Ӻ�λ��
		this.add(new JScrollPane(table),BorderLayout.CENTER);
		
		student.showAllStudent();
		
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
		
		//��ȡ��Ļ��С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//��ȡ��Ļ�ĸ߶ȺͿ��
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		
		
		
	}
	//���Ʊ���ͼƬ
		protected void paintComponent( Graphics g ) {
			  setOpaque(true);
			  super.paintComponent(g);
			    g.drawImage(imagefile.addbackground,0,0,(int)(screenWidth/1.8),(int)(screenHeight/1.8),null);
			 }
}
