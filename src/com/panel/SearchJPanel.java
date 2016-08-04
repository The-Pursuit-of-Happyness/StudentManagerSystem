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
		//获取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕的高度和宽度
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		label = new JLabel("查询信息:");
		searchJTextField = new JTextField(25);		
		
		student = new SearchModel(sql);
		table = new JTable(student);
		
		student.showSearchStudent();
		
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
		
		this.setLayout(new BorderLayout());
		searchButton  = new JButton("查询一下");
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == searchButton){
					sql.showsearch(searchJTextField.getText());
					repaint();
				}
			}
		});
		
		panel = new JPanel();	
		//添加格子和位置
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);	
		panel.repaint();
		this.add(panel,BorderLayout.CENTER);
		
		//添加查询按钮等组件
		label.setFont(new Font("华文楷体",0,screenHeight/25));
		panelahead = new JPanel();
		panelahead.setLayout(new BorderLayout());
		panelahead.add(label,BorderLayout.WEST);
		panelahead.add(searchJTextField,BorderLayout.CENTER);
		panelahead.add(searchButton,BorderLayout.EAST );
		this.add(panelahead,BorderLayout.NORTH);
		new Thread(this).start();
	}
	
	//绘制背景图片
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		}		
	}
}
