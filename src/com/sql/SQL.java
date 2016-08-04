package com.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.date.Student;
import com.image.ImageFile;

public class SQL {
	private Connection conn;
	private static Statement stat;
	private ResultSet result;
	private List<Student> student = new ArrayList<Student>();
	private List<Student> student1 = new ArrayList<Student>();
	private String name[]={"ѧ��ѧ��:","ѧ������:","ѧ���Ա�:","ѧ������:","Ӣ��ɼ�:","��ѧ�ɼ�:","���ĳɼ�:"};	
	public String[] getName() {
		return name;
	}
	//�����½�������ݿ��·��
	private String url="jdbc:mysql://127.0.0.1:3306/mysql?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";	
	public SQL(){
		try {
			Class.forName("com.mysql.jdbc.Driver");//�������ݿ�����			
			conn = DriverManager.getConnection(url);//�������ݿ�
			stat = conn.createStatement();//����������ݿ�ķ���
			//�������
			if(!judgeIsTableExists()){
			create();
			}
			//ɾ�����
			//stat.executeUpdate("drop table studen1;");
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}	
	
	//�ж��Ƿ���ڱ��
	public boolean judgeIsTableExists(){
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rsTable = meta.getTables(null, null,"STUDENT",null);
			if(rsTable.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void showModification(String s){
		try {
			result = stat.executeQuery("select * from studen1 where num ='"+s+"';");
			//System.out.println(result.getString(1)+result.getString(2));
			while (result.next()){
				 System.out.println(result.getString(1)+result.getString(2)+result.getString(3));
			 }
		
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	public void showsearch(String s) {
		removeStudent1();
		try {
			result =stat.executeQuery("select * from studen1 where num ='"+s+"';");
			while (result.next()) {
				
				Student obj = new Student(result.getString(1), result.getString(2), result.getString(3),
						Integer.parseInt(result.getString(4)), Integer.parseInt(result.getString(5)),
						Integer.parseInt(result.getString(6)), Integer.parseInt(result.getString(7)));
				student1.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
	}
	
	
	//�޸�����
	public void modificationMassage(String massage,String num){
		int is =0;
		try {
			is = stat.executeUpdate("update studen1 set name = '"+massage+"' where num ='"+num+"';");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	//ɾ������
	public void deleteMeassage(String num){
		int is = -1;
		try {
			is = stat.executeUpdate("delete from studen1 where num ='"+num+"';");
			if(is!=-1)JOptionPane.showMessageDialog(null, "ɾ���ɹ�!", "�ɹ�......", JOptionPane.OK_OPTION,ImageFile.okmassage);
			else JOptionPane.showMessageDialog(null, "ɾ��ʧ��!", "ʧ��......", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		Iterator <Student> it = student.iterator();
		while(it.hasNext()){
			Student t = (Student)it.next();
			if(t.getNum().equals(num)){
				it.remove();
			}			
		}
	}
	
	//������ݵĺ���
	public void addStudent(JTextField textsfield[],String s){
		int isCreate = -1;
		String str = "insert into studen1 values('"+textsfield[0].getText()+"','"+textsfield[1].getText()+"','"+s+"','"+textsfield[3].getText()+"',"+textsfield[4].getText()+","+textsfield[5].getText()+","+textsfield[6].getText()+");";
		try {
			isCreate = stat.executeUpdate(str);
			Student obj = new Student(textsfield[0].getText(),textsfield[1].getText(),s,
					Integer.parseInt(textsfield[3].getText()),Integer.parseInt(textsfield[4].getText()),
					Integer.parseInt(textsfield[5].getText()),Integer.parseInt(textsfield[6].getText()));
			student.add(obj);
			if(isCreate!=-1){
				JOptionPane.showMessageDialog(null, "��ӳɹ�!", "�ɹ�......", JOptionPane.OK_OPTION,ImageFile.okmassage);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "���ʧ��!", "ʧ��......", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showStudent(String showMsg) {
		removeStudent();
		try {
			result = stat.executeQuery(showMsg);
			while (result.next()) {
				Student obj = new Student(result.getString(1), result.getString(2), result.getString(3),
						Integer.parseInt(result.getString(4)), Integer.parseInt(result.getString(5)),
						Integer.parseInt(result.getString(6)), Integer.parseInt(result.getString(7)));
				student.add(obj);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
	}	
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public static Statement getStat() {
		return stat;
	}
	public static void setStat(Statement stat) {
		SQL.stat = stat;
	}
	public ResultSet getResult() {
		return result;
	}
	public void setResult(ResultSet result) {
		this.result = result;
	}
	public void removeStudent() {
		for (int i = 0; i < student.size();) {
			student.remove(0);
		}
	}
	public List<Student> getStudent() {
		return student;
	}
	public void removeStudent1() {
		for (int i = 0; i < student1.size();) {
			student1.remove(0);
		}
	}
	public List<Student> getStudent1() {
		return student1;
	}	
	
	public void create(){
		int  isCreate = -1;
		String str = "create table studen1 (num char(20) primary key,name char(20) unique,sex char(5),age SMALLINT,english INT,math INT,china INT);"; 
		try {
			isCreate = stat.executeUpdate(str);
			if(isCreate == -1){
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ��!�����Ƿ�����", "ʧ��......", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
//	public void judge(){
//		int isCreate = -1;
//		//�������ݿ�����
//		 String str = "create table studen1 (num char(20),name char(20),sex char(5),age SMALLINT,english INT,math INT,china INT);"; 
//		 String str1 = "insert into studen1 values('2013083222','������','��','18',89,80,78);";
//		 String str3 = "insert into studen1 values('2013083225','Ҧ��','��','18',89,80,78);";
//		 String str2 = "select * from studen1;";
//		 String string=null;
//		 try {
//			 //�������ݿ����
//			//isCreate = stat.executeUpdate(str);
//			 //�������
//			//isCreate = stat.executeUpdate(str1);
//			//isCreate = stat.executeUpdate(str3);
//			 //��ʾ���ݿ��е�����
//			 result = stat.executeQuery(str2);
//			 while (result.next()){
//				 System.out.println(result.getString(1)+result.getString(2)+result.getString(3));
//			 }
//		} catch (SQLException e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
//		 if(isCreate!=-1){
//			 System.out.println("�����ɹ���");
//		 }
//		 else
//			 System.out.println("����ʧ�ܣ�");
//	}
}
