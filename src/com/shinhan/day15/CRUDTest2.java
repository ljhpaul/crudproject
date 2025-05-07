package com.shinhan.day15;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDTest2 {
	
	public static void main(String[] args) throws SQLException {
		//모두 성공하면 commit, 하나라도 실패하면 rollback
		//insert
		//update
		Connection conn = null;
		Statement st1 = null;
		Statement st2 = null;
		String sql1 = """
				insert into emp1(employee_id, first_name, last_name, email, hire_date, job_id) 
				values(1, 'aa', 'bb', 'cc', sysdate, 'IT')
				""";
		String sql2 = """
				update emp1 set salary = 2000 where employee_id = 198
				""";
		
		conn = DBUtil.getConnection();
		conn.setAutoCommit(false);
		st1 = conn.createStatement();
		int result1 = st1.executeUpdate(sql1);	//commit
		st2 = conn.createStatement();
		int result2 = st2.executeUpdate(sql2);	//
		
		if(result1 == 0 && result2 == 0) {
			conn.commit();
			System.out.println("commit success");
		} else {
			conn.rollback();
			System.out.println("commit failed");
		}
		
		DBUtil.dbDisconnect(conn, st2, null);
	}
	
	public static void f4() throws SQLException {
		Connection conn = null;
		Statement st = null;
		int result = 0;	//DML 건수
		String sql = """
				delete emp1
				where employee_id = 999
				""";
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		result = st.executeUpdate(sql);	//DML 실행(1이상이면 성공, 0이면 실패)
										//자동으로 commit
		System.out.println(result>=1?"delete success":"delete fail");
	}
	
	public static void f3() throws SQLException {
		Connection conn = null;
		Statement st = null;
		int result = 0;	//DML 건수
		String sql = """
				update emp1
				set department_id = (select department_id
                     			 	 from employees
                     			 	 where employee_id = 102),
					salary = (select salary
							  from employees
							  where employee_id = 103)
				where employee_id = 999
				""";
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		result = st.executeUpdate(sql);	//DML 실행(1이상이면 성공, 0이면 실패)
										//자동으로 commit
		System.out.println(result>=1?"update success":"update fail");
	}
	
	public static void f2() throws SQLException {
		Connection conn = null;
		Statement st = null;
		int result = 0;	//DML 건수
		String sql = """
				insert into emp1 values(28, '이', '정헌', 'jhpaul', 
				'010.2708.2016', '2025/04/23', 'MAN', 5000, null, null, 1)
				""";
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		result = st.executeUpdate(sql);	//DML 실행(1이상이면 성공, 0이면 실패)
										//자동으로 commit
		System.out.println(result>=1?"insert success":"insert fail");
	}
	
	public static void f1() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select ename, sal, mgr
				from emp
				where mgr = (select empno from emp where ename = 'KING')
				""";
		
		conn = DBUtil.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery(sql);	//DQL 실행
		while(rs.next()) {
			String ename = rs.getString(1);
			int sal = rs.getInt(2);
			int mgr = rs.getInt(3);
			System.out.println(ename + "\t" + sal + "\t" + mgr);
		}
		
		DBUtil.dbDisconnect(conn, st, rs);
	}
	
}
