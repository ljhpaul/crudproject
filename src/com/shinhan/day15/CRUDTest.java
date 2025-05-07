package com.shinhan.day15;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//CRUD(Create Read Update Delete)
//Read(Select)
public class CRUDTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		f1();
//		f2();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select DEPARTMENT_ID "부서ID", max(SALARY) "최대급여", min(SALARY) "최소급여"
				from EMPLOYEES
				group by DEPARTMENT_ID
				having max(SALARY) != min(SALARY)
				order by 1
				""";
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int a = rs.getInt(1);
				int b = rs.getInt(2);
				int c = rs.getInt(3);
				System.out.println(a + "\t" + b + "\t" + c);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
	}
	
	private static void f2() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hr";
		String userpass = "hr";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = """
				select DEPARTMENT_ID "부서ID", count(*) "인원수"
				from EMPLOYEES
				group by DEPARTMENT_ID
				having count(*) >= 5
				order by 2 desc
				""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, userpass);
			st = conn.createStatement();
			rs = st.executeQuery(sql);	//rs는 표(테이블)와 비슷하다.
			while(rs.next()) {
				int deptid = rs.getInt(1);
				int cnt = rs.getInt(2);
				System.out.printf("부서ID: %d, 인원수: %d\n", deptid, cnt);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private static void f1() throws ClassNotFoundException, SQLException {
		//1. JDBC Driver 준비(class path 추가)
		System.out.println("1. JDBC Driver 준비(class path 추가)");
		//2. JDBC Driver 메모리에 load
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("2. JDBC Driver 메모리 load 성공");
		//3. Connection
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hr";
		String userpass = "hr";
		Connection conn = DriverManager.getConnection(url, userid, userpass);
		System.out.println("3. Connection 성공");
		//4. SQL문 보낼 통로 얻기
		Statement st = conn.createStatement();
		System.out.println("4. SQL문 보낼 통로 얻기 성공");
		//5. SQL문 생성, 실핼
		String sql = """
				select * 
				from employees
				where department_id = 80
				""";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			int empid = rs.getInt("employee_id");
			String fname = rs.getString("first_name");
			Date hdate = rs.getDate("hire_date");
			double comm = rs.getDouble("COMMISSION_PCT");
			System.out.printf("직원번호: %d, 이름: %s, 입사일: %s, 커미션여부: %f\n", empid, fname, hdate, comm);
		}
		rs.close();
		st.close();
		conn.close();
		
	}

}
