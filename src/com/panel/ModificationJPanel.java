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
	
	private String name[]={"ѧ��ѧ��:","ѧ������:","ѧ���Ա�:","ѧ������:","Ӣ��ɼ�:","��ѧ�ɼ�:","���ĳɼ�:"};	
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
		
		okButton = new JButton("ȷ��");
		cancleButton = new JButton("ȡ��");
		okButton.setBounds(380,350,120,40);
		cancleButton.setBounds(520,350,120,40);
		this.add(okButton);
		this.add(cancleButton);
		
		namesJLabel[0] = new JLabel(name[0]);
		namesJLabel[0].setBounds(200,20,120,35);
		namesJLabel[0].setFont(new Font("���Ŀ���",0,25));
		textsfield[0] = new JTextField();
		textsfield[0].setBounds(320,20,180,35);
		this.add(textsfield[0]);
		this.add(namesJLabel[0]);
		searchButton = new JButton("����");
		searchButton.setBounds(520,20,60,30);
		this.add(searchButton);
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//��ѧ�Ų���Ҫ�޸ĵ���Ϣ������ʾ����
				if(e.getSource() == searchButton){
					try {
						//sql.showModification(textsfield[0].getText());
						sql.setResult(sql.getStat().executeQuery("select * from studen1 where num ='"+textsfield[0].getText()+"';"));
						while (sql.getResult().next()){
						for(int i=1;i<name.length;i++){
							if(i==2){								
								if(sql.getResult().getString(i+1).equals("��")){
									manButton.setSelected(true);
									famanButton.setSelected(false);
								}else if(sql.getResult().getString(i+1).equals("Ů")){
									manButton.setSelected(false);
									famanButton.setSelected(true);
								}										
							}else{
								textsfield[i].setText(sql.getResult().getString(i+1));
							}								
						}
						}
						} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				}
			}
		});
		
		//ȡ���޸���Ϣ
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
		
		//��ʾ�ı���Ϣ
		for(int i=1;i<name.length;i++){
			namesJLabel[i] = new JLabel(name[i]);
			namesJLabel[i].setBounds(200,60+40*i,120,35);
			namesJLabel[i].setFont(new Font("���Ŀ���",0,25));
			if(i==2){
				sexButton = new ButtonGroup();
				manButton = new JRadioButton("��",false);
				manButton.setBounds(320,140,80,35);
				manButton.setFont(new Font("���Ŀ���",0,25));
				//���ñ���Ϊ͸����
				manButton.setOpaque(false);
				famanButton = new JRadioButton("Ů",false);
				famanButton.setBounds(420,140,80,35);
				famanButton.setFont(new Font("���Ŀ���",0,25));
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
			
			modification[i] = new JButton("�޸�");
			modification[i].setBounds(520,60+40*i,60,30);
			this.add(modification[i]);
			this.add(namesJLabel[i]);			
		}
		
		//�޸�����
		modification[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[1]){
						String s;						
						s =JOptionPane.showInputDialog(null, "�������޸ĺ��������", "�޸���Ϣ", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
							textsfield[1].setText(s);
						sql.modificationMassage(s,textsfield[0].getText());
					}
				}
			}
		});
		
		//�޸��Ա�
		modification[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[2]){
					String s ;
					s = JOptionPane.showInputDialog(null, "�������޸ĺ���Ա�", "�޸���Ϣ", JOptionPane.OK_CANCEL_OPTION);
					if(s.equals("��")||s.equals("Ů")){
						if(s.equals("��")){
							manButton.setSelected(true);
							famanButton.setSelected(false);
						}else if(s.equals("Ů")){
							manButton.setSelected(false);
							famanButton.setSelected(true);
						}	
						sql.modificationMassage(s,textsfield[0].getText());
					}
				}
			}
		});
		
		//�޸�����
		modification[3].addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			if(e.getSource() == modification[3]){
					String s;
					s =JOptionPane.showInputDialog(null, "�������޸ĺ�����䣺", "�޸���Ϣ", JOptionPane.OK_CANCEL_OPTION);
					if(s!=null){
					textsfield[3].setText(s);
					sql.modificationMassage(s,textsfield[0].getText());
					}
				}
			}
		});
		//�޸�Ӣ��ɼ�
		modification[4].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[4]){
						String s;
						s =JOptionPane.showInputDialog(null, "�������޸ĺ��Ӣ��ɼ���", "�޸���Ϣ", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
						textsfield[4].setText(s);
						sql.modificationMassage(s,textsfield[0].getText());
						}
					}
			}
		});
		//�޸���ѧ�ɼ�
		modification[5].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[5]){
						String s;
						s =JOptionPane.showInputDialog(null, "�������޸ĺ����ѧ�ɼ���", "�޸���Ϣ", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
						textsfield[5].setText(s);
						sql.modificationMassage(s,textsfield[0].getText());
						}
					}
			}
		});
		//�޸����ĳɼ�
		modification[6].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == modification[6]){
						String s;
						s =JOptionPane.showInputDialog(null, "�������޸ĺ�����ĳɼ���", "�޸���Ϣ", JOptionPane.OK_CANCEL_OPTION);
						if(s!=null){
							textsfield[6].setText(s);					
							sql.modificationMassage(s,textsfield[0].getText());
						}
					}
			}
		});
		
		//��ȡ��Ļ��С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//��ȡ��Ļ�ĸ߶ȺͿ��
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		this.setLayout(null);
	}
	//���Ʊ���ͼƬ
		protected void paintComponent( Graphics g ) {
			  setOpaque(true);
			  super.paintComponent(g);
			    g.drawImage(imagefile.addbackground,0,0,(int)(screenWidth/1.8),(int)(screenHeight/1.8),null);
			 }
}
