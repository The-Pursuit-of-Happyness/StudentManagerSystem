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
		
		//��ȡ��Ļ��С
		final Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//��ȡ��Ļ�ĸ߶ȺͿ��
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();			
		
		
		//�������ͼ��
		img= kit.getImage("src\\com\\image\\mouse.png");
		Cursor dynamiteCursor = kit.createCustomCursor(img, new Point(5,5), "dynamite stick");
		this.setCursor(dynamiteCursor);
		
		//��Ӳ˵�
		this.setJMenuBar(new MyMenu(jtablepane));
		
		imagefile = new ImageFile();
		
		this.setLayout(new BorderLayout());
		aheadimage = new JLabel(imagefile.aheaditem);
		
		this.add(jtablepane,BorderLayout.CENTER);
		this.add(aheadimage,BorderLayout.NORTH);
		
		this.setTitle("ѧ����Ϣ������ͳ");
		this.setIconImage(imagefile.icon);
		this.setResizable(false);//������ı䴰���С
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
