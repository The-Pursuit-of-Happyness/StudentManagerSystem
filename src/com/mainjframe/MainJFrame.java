package com.mainjframe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.image.ImageFile;
import com.panel.JTalePane;
import com.sql.SQL;

public class MainJFrame extends JFrame{
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	private JLabel aheadimage;
	private Image img;
	private SQL sql;
	private JTalePane jtablepane;

	public MainJFrame(SQL sql){	
		this.sql = sql;	
		jtablepane = new JTalePane(sql);
		
		//获取屏幕大小
		final Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕的高度和宽度
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();			
		
		
		//更改鼠标图标
		img= kit.getImage("src\\com\\image\\mouse.png");
		Cursor dynamiteCursor = kit.createCustomCursor(img, new Point(5,5), "dynamite stick");
		this.setCursor(dynamiteCursor);
		
		//添加菜单
		this.setJMenuBar(new MyMenu(jtablepane));
		
		imagefile = new ImageFile();
		
		this.setLayout(new BorderLayout());
		aheadimage = new JLabel(imagefile.aheaditem);
		
		this.add(jtablepane,BorderLayout.CENTER);
		this.add(aheadimage,BorderLayout.NORTH);
		
		this.setTitle("学生信息管理体统");
		this.setIconImage(imagefile.icon);
		this.setResizable(false);//不允许改变窗体大小
		this.setVisible(true);
		this.setSize((int)(screenWidth/1.792),(int)(screenHeight/1.3));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}
}
