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
		//添加格子和位置
		this.add(new JScrollPane(table),BorderLayout.CENTER);
		
		student.showAllStudent();
		
		//设置表格是否自动布局
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//设置表格的行高
		table.setRowHeight(30);
		
		//设置表格字体居中		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		
		//设置表格的属性
		table.setDefaultRenderer(Object.class, tcr);
		//设置表格的列宽
		TableColumnModel tcm =  table.getColumnModel();
		for(int i=0;i<tcm.getColumnCount();i++){
			TableColumn tc = tcm.getColumn(i);	
			tc.setMinWidth(140);
			tc.setPreferredWidth(100);			
		}		
		
		//获取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕的高度和宽度
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		
		
		
	}
	//绘制背景图片
		protected void paintComponent( Graphics g ) {
			  setOpaque(true);
			  super.paintComponent(g);
			    g.drawImage(imagefile.addbackground,0,0,(int)(screenWidth/1.8),(int)(screenHeight/1.8),null);
			 }
}
