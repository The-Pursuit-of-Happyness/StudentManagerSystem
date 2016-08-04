package com.date;

public class Student {
	private String num;
	private String name;
	private String sex;
	private int age;
	private int english;
	private int math;
	private int china;	
	public Student(String num,String name,String sex,int age,int english,int math,int china){
		this.num = num;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.english = english;
		this.math = math;
		this.china = china;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getChina() {
		return china;
	}
	public void setChina(int china) {
		this.china = china;
	}
}
