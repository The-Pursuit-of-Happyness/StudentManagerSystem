package com.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.image.ImageFile;
import com.sql.SQL;

public class ModificationJPanel extends JPanel {
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	private JButton okButton;
	private JButton cancleButton;
	
	private String name[]={"学生学号:","学生姓名:","学生性别:","学生年龄:","英语成绩:","数学成绩:","语文成绩:"};	
	private JLabel namesJLabel[] = new JLabel[10];
	private JTextField textsfield[] = new JTextField[10];
	private JButton modification[] = new JButton[10];
	private ButtonGroup sexButton;
	private JRadioButton manButton;
	private JRadioButton famanButton;
	private JButton searchButton;
	private SQL sql;
	
	public ModificationJPanel(final SQL sql){
		this.sql = sql;
		
		okButton = new JButton("确定");
		cancleButton = new JButton("取消");
		okButton.setBounds(380,350,120,40);
		cancleButton.setBounds(520,350,120,40);
		this.add(okButton);
		this.add(cancleButton);
		
		namesJLabel[0] = new JLabel(name[0]);
		namesJLabel[0].setBounds(200,20,120,35);
		namesJLabel[0].setFont(new Font("华文楷体",0,25));
		textsfield[0] = new JTextField();
		textsfield[0].setBounds(320,20,180,35);
		this.add(textsfield[0]);
		this.add(namesJLabel[0]);
		searchButton = new JButton("查找");
		searchButton.setBounds(520,20,60,30);
		this.add(searchButton);
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//按学号查找要修改的信息，并显示出来
				if(e.getSource() == searchButton){
					try {
						//sql.showModification(textsfield[0].getText());
						sql.setResult(sql.getStat().executeQuery("select * from studen1 where num ='"+textsfield[0].getText()+"';"));
						while (sql.getResult().next()){
						for(int i=1;i<name.length;i++){
							if(i==2){								
								if(sql.getResult().getString(i+1).equals("男")){
									manButton.setSelected(true);
									famanButton.setSelected(false);
								}else if(sql.getResult().getString(i+1).equals("女")){
									manButton.setSelected(false);
									famanButton.setSelected(true);
								}										
							}else{
								textsfield[i].setText(sql.getResult().getString(i+1));
							}								
						}
						}
						} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
		});
		
		//取消修改信息
		cancleButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource()== cancleButton){
					for(int j=0;j<name.length;j++){
						if(j!=2){
							textsfield[j].setText("");
						}
						else if(j==2){
							sexButton.clearSelection();
						}
					}
				}
			}
		});
		
		//显示文本信息
		for(int i=1;i<name.length;i++){
			namesJLabel[i] = new JLabel(name[i]);
			namesJLabel[i].setBounds(200,60+40*i,120,35);
			namesJLabel[i].setFont(new Font("华文楷体",0,25));
			if(i==2){
				sexButton = new ButtonGroup();
				manButton = new JRadioButton("男",false);
				manButton.setBounds(320,140,80,35);
				manButton.setFont(new Font("华文楷体",0,25));
				//设置背景为透明的
				manButton.setOpaque(false);
				famanButton = new JRadioButton("女",false);
				famanButton.setBounds(420,140,80,35);
				famanButton.setFont(new Font("华文楷体",0,25));
				famanButton.setOpaque(false);
				sexButton.add(manButton);
				sexButton.add(famanButton);			
				this.add(manButton);
				this.add(famanButton);
			}else{
				textsfield[i] = new JTextField();
				textsfield[i].setBounds(320,60+40*i,180,35);
				this.add(textsfield[i]);
			}
			
			modification[i] = new JButton("修改");
			modification[i].setBounds(520,60+40*i,60,30);
			this.add(modification[i]);
			this.add(namesJLabel[i]);			
		}
		
		//修改姓名
		modification[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[1]){
						String s;						
						s =JOptionPane.showInputDialog(null, "请输入修改后的姓名：", "修改信息", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
							textsfield[1].setText(s);
						sql.modificationMassage(s,textsfield[0].getText());
					}
				}
			}
		});
		
		//修改性别
		modification[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[2]){
					String s ;
					s = JOptionPane.showInputDialog(null, "请输入修改后的性别：", "修改信息", JOptionPane.OK_CANCEL_OPTION);
					if(s.equals("男")||s.equals("女")){
						if(s.equals("男")){
							manButton.setSelected(true);
							famanButton.setSelected(false);
						}else if(s.equals("女")){
							manButton.setSelected(false);
							famanButton.setSelected(true);
						}	
						sql.modificationMassage(s,textsfield[0].getText());
					}
				}
			}
		});
		
		//修改年龄
		modification[3].addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			if(e.getSource() == modification[3]){
					String s;
					s =JOptionPane.showInputDialog(null, "请输入修改后的年龄：", "修改信息", JOptionPane.OK_CANCEL_OPTION);
					if(s!=null){
					textsfield[3].setText(s);
					sql.modificationMassage(s,textsfield[0].getText());
					}
				}
			}
		});
		//修改英语成绩
		modification[4].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[4]){
						String s;
						s =JOptionPane.showInputDialog(null, "请输入修改后的英语成绩：", "修改信息", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
						textsfield[4].setText(s);
						sql.modificationMassage(s,textsfield[0].getText());
						}
					}
			}
		});
		//修改数学成绩
		modification[5].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[5]){
						String s;
						s =JOptionPane.showInputDialog(null, "请输入修改后的数学成绩：", "修改信息", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
						textsfield[5].setText(s);
						sql.modificationMassage(s,textsfield[0].getText());
						}
					}
			}
		});
		//修改语文成绩
		modification[6].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[6]){
						String s;
						s =JOptionPane.showInputDialog(null, "请输入修改后的语文成绩：", "修改信息", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
							textsfield[6].setText(s);					
							sql.modificationMassage(s,textsfield[0].getText());
						}
					}
			}
		});
		
		//获取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕的高度和宽度
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		this.setLayout(null);
	}
	//绘制背景图片
		protected void paintComponent( Graphics g ) {
			  setOpaque(true);
			  super.paintComponent(g);
			    g.drawImage(imagefile.addbackground,0,0,(int)(screenWidth/1.8),(int)(screenHeight/1.8),null);
			 }
}
