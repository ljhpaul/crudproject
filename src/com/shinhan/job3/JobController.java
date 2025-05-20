package com.shinhan.job3;
import java.util.Scanner;

/**
 * JobController 클래스는 프로그램의 흐름을 제어합니다.
 */
public class JobController {
    public static void main(String[] args) {
        JobDAO jobDAO = new JobDAO();
        JobView jobView = new JobView();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Job Management ===");
            System.out.println("1. List Jobs");
            System.out.println("2. Add Job");
            System.out.println("3. Update Job");
            System.out.println("4. Delete Job");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    jobView.displayJobs(jobDAO.getAllJobs());
                    break;
                case 2:
                    if (jobDAO.addJob(jobView.getJobDetails())) {
                        jobView.displayMessage("Job added successfully.");
                    } else {
                        jobView.displayMessage("Failed to add job.");
                    }
                    break;
                case 3:
                    if (jobDAO.updateJob(jobView.getJobDetails())) {
                        jobView.displayMessage("Job updated successfully.");
                    } else {
                        jobView.displayMessage("Failed to update job.");
                    }
                    break;
                case 4:
                    if (jobDAO.deleteJob(jobView.getJobId())) {
                        jobView.displayMessage("Job deleted successfully.");
                    } else {
                        jobView.displayMessage("Failed to delete job.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
