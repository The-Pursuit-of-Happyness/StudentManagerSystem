package com.panel;
import javax.swing.table.AbstractTableModel;

import com.date.Student;
import com.sql.SQL;

public class StudentModel extends AbstractTableModel {		
		private SQL sql;
		
		public StudentModel(SQL sql) {
			this.sql = sql;
		}

		public void showAllStudent() {
			String opSql = "select * from studen1;";
			//显示学生信息
			sql.showStudent(opSql);
			//数据改变后自己监听
			this.fireTableStructureChanged();
		}
		
		public int getRowCount() {
			return sql.getStudent().size();
		}
		
		public String getColumnName(int column) {
			return sql.getName()[column];
		}

		public int getColumnCount() {
			return sql.getName().length;
		}
		public Object getValueAt(int rowIndex,int columnIndex) {
			Student stu = sql.getStudent().get(rowIndex);
			switch (columnIndex) {
			case 0:
				return stu.getNum();
			case 1:
				return stu.getName();
			case 2:
				return stu.getSex();
			case 3:
				return stu.getAge();
			case 4:
				return stu.getEnglish();
			case 5:
				return stu.getMath();
			case 6:
				return stu.getChina();
			}
			return null;
		}

	}

