package com.shinhan.job3;
/**
 * JobDTO 클래스는 Jobs 테이블의 데이터를 저장하기 위한 데이터 전송 객체입니다.
 */
public class JobDTO {
    private String jobId;       // Job ID
    private String jobTitle;    // Job Title
    private int minSalary;      // Minimum Salary
    private int maxSalary;      // Maximum Salary

    // 기본 생성자
    public JobDTO() {}

    // 매개변수 생성자
    public JobDTO(String jobId, String jobTitle, int minSalary, int maxSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    // Getter와 Setter 메서드
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "JobDTO [jobId=" + jobId + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + "]";
    }
}
