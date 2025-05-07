package com.shinhan.emp;

import java.util.List;

//EmpData를 display하려는 목적, 웹으로 변경되면 필요없어짐 --> JSP로 만들 예정
public class EmpView {
	//여러건 출력
	public static void display(List<EmpDTO> emplist) {
		System.out.println("=====직원 여러건 조회=====");
		if(emplist.size() == 0) {
			System.out.println("알림: 해당하는 직원이 존재하지 않습니다.");
		} else {
			emplist.stream().forEach(emp->System.out.println(emp));
		}
	}
	
	//한 건 출력
	public static void display(EmpDTO emp) {
		if(emp == null) {
			System.out.println("알림: 해당하는 직원이 존재하지 않습니다.");
		} else {
			System.out.println("직원정보: " + emp);
		}
	}
	
	//메시지 출력
	public static void display(String message) {
		System.out.println("알림: " + message);
	}
}
