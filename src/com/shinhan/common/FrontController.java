package com.shinhan.common;

import java.util.Scanner;

import com.shinhan.dept.DeptController;
import com.shinhan.emp.EmpController;


//FrontController 패턴 : Controller가 여러 개인 경우 사용자의 요청과 응답은 출구가 여러 개
//-> 바람직하지 않음
//하나로 통함(Front는 한 개)
//Servlet : DispatcherServlet이 있음(Spring은 FrontController가 이미 있다.)
public class FrontController {
	
	public static void main(String[] args) {
		//사용자가 emp, dept 작업 결정
		Scanner sc = new Scanner(System.in);
		CommonControllerInterface controller = null;
		boolean isStop = false;
		
		while(!isStop) {
			System.out.println("작업 선택(emp, dept, job, end) >> ");
			String job = sc.next();
			if(job.equals("end")) { isStop = true; continue; }
			
			controller = ControllerFactory.make(job);
			controller.execute(); //작업은 달라져도 사용법은 같음. (전략패턴)
		}
		
		System.out.println("========= MAIN END =========");
		sc.close();
	}
	
}
