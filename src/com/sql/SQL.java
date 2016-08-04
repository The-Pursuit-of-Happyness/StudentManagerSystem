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
	private String name[]={"学生学号:","学生姓名:","学生性别:","学生年龄:","英语成绩:","数学成绩:","语文成绩:"};	
	public String[] getName() {
		return name;
	}
	//定义登陆本地数据库的路径
	private String url="jdbc:mysql://127.0.0.1:3306/mysql?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";	
	public SQL(){
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动			
			conn = DriverManager.getConnection(url);//链接数据库
			stat = conn.createStatement();//赋予操作数据库的方法
			//创建表格
			if(!judgeIsTableExists()){
			create();
			}
			//删除表格
			//stat.executeUpdate("drop table studen1;");
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}	
	
	//判断是否存在表格
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
			// TODO 自动生成的 catch 块
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
		} // executeQuery会返回结果的集合，否则返回空值
	}
	
	
	//修改数据
	public void modificationMassage(String massage,String num){
		int is =0;
		try {
			is = stat.executeUpdate("update studen1 set name = '"+massage+"' where num ='"+num+"';");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	//删除数据
	public void deleteMeassage(String num){
		int is = -1;
		try {
			is = stat.executeUpdate("delete from studen1 where num ='"+num+"';");
			if(is!=-1)JOptionPane.showMessageDialog(null, "删除成功!", "成功......", JOptionPane.OK_OPTION,ImageFile.okmassage);
			else JOptionPane.showMessageDialog(null, "删除失败!", "失败......", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
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
	
	//添加数据的函数
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
				JOptionPane.showMessageDialog(null, "添加成功!", "成功......", JOptionPane.OK_OPTION,ImageFile.okmassage);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "添加失败!", "失败......", JOptionPane.ERROR_MESSAGE);
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
		} // executeQuery会返回结果的集合，否则返回空值
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
				JOptionPane.showMessageDialog(null, "数据库连接失败!请检查是否连接", "失败......", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
//	public void judge(){
//		int isCreate = -1;
//		//定义数据库语言
//		 String str = "create table studen1 (num char(20),name char(20),sex char(5),age SMALLINT,english INT,math INT,china INT);"; 
//		 String str1 = "insert into studen1 values('2013083222','夏文齐','男','18',89,80,78);";
//		 String str3 = "insert into studen1 values('2013083225','姚鸿','男','18',89,80,78);";
//		 String str2 = "select * from studen1;";
//		 String string=null;
//		 try {
//			 //进行数据库操作
//			//isCreate = stat.executeUpdate(str);
//			 //添加数据
//			//isCreate = stat.executeUpdate(str1);
//			//isCreate = stat.executeUpdate(str3);
//			 //显示数据库中的数据
//			 result = stat.executeQuery(str2);
//			 while (result.next()){
//				 System.out.println(result.getString(1)+result.getString(2)+result.getString(3));
//			 }
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		 if(isCreate!=-1){
//			 System.out.println("创建成功！");
//		 }
//		 else
//			 System.out.println("创建失败！");
//	}
}
