package com.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.image.ImageFile;

public class HelpJPanel extends JPanel {
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	
	public HelpJPanel(){
		
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
			   //显示帮助信息
			    g.setFont(new Font("华文楷体",2,30));
			    g.drawString("使用帮助", 300, 50);
			    g.setColor(Color.BLUE);
			    g.setFont(new Font("华文楷体",0,20));
			    g.drawString("一、使用说明", 100, 80);
			    g.drawString("二、开发者信息", 100, 200);
			    g.setColor(Color.black);
			    g.drawString("1、本软件为免费使用的小工具软件，代码开源", 120, 115);
			    g.drawString("2、本软件为鼠标操作的小型MySQL数据库学生成绩管理系统", 120, 140);
			    g.drawString("3、本软件为安全性比较差，不能用于重要数据的存储", 120, 165);
			    //开发者信息
			    g.drawString("1、开发人员姓名：夏文齐", 120, 235);
			    g.drawString("2、开发人员练习方式：15898195729", 120, 260);
			    g.drawString("3、意见反馈到邮箱1025582613@qq.com", 120, 285);
			    g.drawString("2015-5-30", 520, 360);
		}
}
