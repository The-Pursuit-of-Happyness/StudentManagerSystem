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
			   //��ʾ������Ϣ
			    g.setFont(new Font("���Ŀ���",2,30));
			    g.drawString("ʹ�ð���", 300, 50);
			    g.setColor(Color.BLUE);
			    g.setFont(new Font("���Ŀ���",0,20));
			    g.drawString("һ��ʹ��˵��", 100, 80);
			    g.drawString("������������Ϣ", 100, 200);
			    g.setColor(Color.black);
			    g.drawString("1�������Ϊ���ʹ�õ�С������������뿪Դ", 120, 115);
			    g.drawString("2�������Ϊ��������С��MySQL���ݿ�ѧ���ɼ�����ϵͳ", 120, 140);
			    g.drawString("3�������Ϊ��ȫ�ԱȽϲ����������Ҫ���ݵĴ洢", 120, 165);
			    //��������Ϣ
			    g.drawString("1��������Ա������������", 120, 235);
			    g.drawString("2��������Ա��ϰ��ʽ��15898195729", 120, 260);
			    g.drawString("3���������������1025582613@qq.com", 120, 285);
			    g.drawString("2015-5-30", 520, 360);
		}
}
