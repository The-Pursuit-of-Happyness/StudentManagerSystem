package com.image;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageFile {
	//设置右上角图标
	public static Image icon = null;	
	public static Icon registerbackground;
	public static Icon aheaditem;
	public static Image addbackground = null;
	public static Icon okmassage;
	public ImageFile(){			
		try {
			aheaditem = new ImageIcon("src\\com\\image\\aheaditem.png");
			icon = ImageIO.read(new File("src\\com\\image\\icon.png"));
			registerbackground = new ImageIcon("src\\com\\image\\registerbackground.png");
			addbackground = ImageIO.read(new File("src\\com\\image\\addbackground.png"));
			okmassage = new ImageIcon("src\\com\\image\\okmassage.png");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
