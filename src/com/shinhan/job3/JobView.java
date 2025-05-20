package com.shinhan.job3;
import java.util.List;
import java.util.Scanner;

/**
 * JobView 클래스는 사용자와의 상호작용을 담당합니다.
 */
public class JobView {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Job 리스트를 출력합니다.
     * 
     * @param jobs JobDTO 리스트
     */
    public void displayJobs(List<JobDTO> jobs) {
        System.out.println("=== Job List ===");
        for (JobDTO job : jobs) {
            System.out.println(job);
        }
    }

    /**
     * Job 데이터를 입력받습니다.
     * 
     * @return JobDTO 객체
     */
    public JobDTO getJobDetails() {
        System.out.print("Enter Job ID: ");
        String jobId = scanner.nextLine();

        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter Minimum Salary: ");
        int minSalary = scanner.nextInt();

        System.out.print("Enter Maximum Salary: ");
        int maxSalary = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return new JobDTO(jobId, jobTitle, minSalary, maxSalary);
    }

    /**
     * Job ID를 입력받습니다.
     * 
     * @return Job ID
     */
    public String getJobId() {
        System.out.print("Enter Job ID: ");
        return scanner.nextLine();
    }

    /**
     * 메시지를 출력합니다.
     * 
     * @param message 출력할 메시지
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
