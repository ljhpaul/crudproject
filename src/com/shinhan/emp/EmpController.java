package com.shinhan.emp;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;


//MVC2모델
//FrontController -> Controller선택 -> Service -> DAO -> DB
//                <-               <-         <-     <-     
public class EmpController implements CommonControllerInterface {
	
	static Scanner sc = new Scanner(System.in);
	static EmpService empService = new EmpService();
	
	@Override
	public void execute() {
		boolean isStop = false;
		while(!isStop) {
			menuDisplay();
			int job = sc.nextInt();
			switch(job) {
			case 1->{ f_selectAll(); }
			case 2->{ f_selectById(); }
			case 3->{ f_selectByDept(); }
			case 4->{ f_selectByjob(); }
			case 5->{ f_selectByjobAndDept(); }
			case 6->{ f_selectByCondition(); }
			case 7->{ f_deleteByEmpId(); }
			case 8->{ f_insertEmp(); }
			case 9->{ f_updateEmp(); }
			case 10->{ f_spCall(); }
			case 99->{ isStop=true; }
			}
		}
		System.out.println("========= Good Bye =========");
	}
	
	
	private static void f_spCall() {
		System.out.print("조회할 직원ID>> ");
		int employee_id = sc.nextInt();
		EmpDTO emp = empService.execute_sp(employee_id);
		String message = "해당직원은 존재하지 않습니다.";
		if(emp != null) {
			message = emp.getEmail() + "---" + emp.getSalary();
		}
		EmpView.display(message);
	}


	private static void f_updateEmp() {
		System.out.print("수정할 직원ID>> ");
		int employee_id = sc.nextInt();
		EmpDTO exist_emp = empService.selectById(employee_id); 
		if(exist_emp == null) { 
			EmpView.display("존재하지 않는 직원입니다."); return;
		}
		EmpView.display("========= 존재하는 직원 정보입니다. =========");
		EmpView.display(exist_emp);
		int result = empService.updateEmp(makeEmp(employee_id));
		EmpView.display(result + "건 수정");
	}

	private static void f_insertEmp() {
		System.out.print("신규직원ID>> ");
		int employee_id = sc.nextInt();
		int result = empService.insertEmp(makeEmp2(employee_id));
		EmpView.display(result + "건 입력");
	}
	
	private static EmpDTO makeEmp(int employee_id) {
		System.out.print("성>> ");
		String first_name = sc.next();

		System.out.print("이름>> ");
		String last_name = sc.next();

		System.out.print("EMAIL>> ");
		String email = sc.next();

		System.out.print("전화번호>> ");
		String phone_number = sc.next();
		
		System.out.print("입사일(yyy-mm-dd)>> ");
		String hdate = sc.next();
		Date hire_date = null;
		
		if(!hdate.equals("0"))
			hire_date = DateUtil.convertToSQLDate(DateUtil.ConvertToDate(hdate));
		
		System.out.print("직원ID(FK:IT_PROG)>> ");
		String job_id = sc.next();
		
		System.out.print("급여>> ");
		Double salary = Double.parseDouble(sc.next());
		
		System.out.print("커미션여부(0.x)>> ");
		Double commission_pct = Double.parseDouble(sc.next());
		
		System.out.print("매니저ID(FK:100)>> ");
		Integer manager_id = sc.nextInt();
		
		System.out.print("부서ID(FK:60)>> ");
		Integer department_id = sc.nextInt();
		
		if(first_name.equals("0")) first_name = null;
		if(last_name.equals("0")) last_name = null;
		if(email.equals("0")) email = null;
		if(phone_number.equals("0")) phone_number = null;
		if(job_id.equals("0")) job_id = null;
		if(salary == 0) salary = null;
		if(commission_pct == 0) commission_pct = null;
		if(manager_id == 0) manager_id = null;
		if(department_id == 0) department_id = null;
		
		EmpDTO emp = EmpDTO.builder()
				.employee_id(employee_id)
				.first_name(first_name)
				.last_name(last_name)
				.email(email)
				.phone_number(phone_number)
				.hire_date(hire_date)
				.job_id(job_id)
				.salary(salary)
				.commission_pct(commission_pct)
				.manager_id(manager_id)
				.department_id(department_id)
				.build();
		
		return emp;
	}
	
	private static EmpDTO makeEmp2(int employee_id) {
		System.out.print("성>> ");
		String first_name = sc.next();
		System.out.print("이름>> ");
		String last_name = sc.next();
		System.out.print("EMAIL>> ");
		String email = sc.next();
		System.out.print("전화번호>> ");
		String phone_number = sc.next();
		System.out.print("입사일(yyy-mm-dd)>> ");
		Date hire_date = DateUtil.convertToSQLDate(DateUtil.ConvertToDate(sc.next()));
		System.out.print("직원ID(FK:IT_PROG)>> ");
		String job_id = sc.next();
		System.out.print("급여>> ");
		double salary = Double.parseDouble(sc.next());
		System.out.print("커미션여부(0.x)>> ");
		double commission_pct = Double.parseDouble(sc.next());
		System.out.print("매니저ID(FK:100)>> ");
		int manager_id = sc.nextInt();
		System.out.print("부서ID(FK:60)>> ");
		int department_id = sc.nextInt();
		
		EmpDTO emp = EmpDTO.builder()
				.employee_id(employee_id)
				.first_name(first_name)
				.last_name(last_name)
				.email(email)
				.phone_number(phone_number)
				.hire_date(hire_date)
				.job_id(job_id)
				.salary(salary)
				.commission_pct(commission_pct)
				.manager_id(manager_id)
				.department_id(department_id)
				.build();
		
		return emp;
	}

	private static void f_deleteByEmpId() {
		System.out.print("삭제할 ID>> ");
		int empid = sc.nextInt();
		int result = empService.deleteByEmpId(empid);
		EmpView.display(result+"건 삭제");
	}


	private static void f_selectByCondition() {
		//=부서, like 직책, >= 급여, >= 입사일
		System.out.print("조회할 부서ID(10, 20, 30)>> ");
		String[] str_deptid = sc.next().split(",");
		Integer[] deptArr = Arrays.stream(str_deptid)
				.map(Integer::parseInt).toArray(Integer[]::new);
		
		System.out.print("조회할 직책ID>> ");
		String jobid = sc.next();
		System.out.print("조회할 salary(이상)>> ");
		int salary = sc.nextInt();
		System.out.print("조회할 입사일(yyy-mm-dd 이상)>> ");
		String hdate = sc.next();
		
		List<EmpDTO> emplist = empService.selectByCondition(deptArr, jobid, salary, hdate);
		EmpView.display(emplist);
	}

	private static void f_selectByjobAndDept() {
		System.out.print("조회할 직책ID, 부서ID>> ");
		String data = sc.next();
		String[] arr = data.split(",");
		String jobid = arr[0];
		int deptid = Integer.parseInt(arr[1]);
		List<EmpDTO> emplist = empService.selectByJobAndDept(jobid, deptid);
		EmpView.display(emplist);
	}

	private static void f_selectByjob() {
		System.out.print("조회할 직책ID>> ");
		String jobid = sc.next();
		List<EmpDTO> emplist = empService.selectByJob(jobid);
		EmpView.display(emplist);
	}

	private static void f_selectByDept() {
		System.out.print("조회할 부서ID>> ");
		int deptid = sc.nextInt();
		List<EmpDTO> emplist = empService.selectByDept(deptid);
		EmpView.display(emplist);
	}

	private static void f_selectById() {
		System.out.print("조회할 ID>> ");
		int empid = sc.nextInt();
		EmpDTO emp = empService.selectById(empid);
		EmpView.display(emp);
	}

	private static void f_selectAll() {
		List<EmpDTO> emplist = empService.selectAll();
		EmpView.display(emplist);
	}


	private static void menuDisplay() {
		System.out.println("------------------------------------------------------------");
		System.out.println("1.모두조회 2.조회(직원번호) 3.조회(부서) 4.조회(직책) 5.조회(부서,직책)");
		System.out.println("6.조회(부서, 직책, 급여, 입사일) 7.삭제(직원번호) 8.입력 9.수정 10.sp호출");
		System.out.println("99.종료");
		System.out.println("------------------------------------------------------------");
		System.out.print("작업 선택>> ");
	}
	
}
