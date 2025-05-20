package com.shinhan.job3;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JobDAO 클래스는 Jobs 테이블에 대한 데이터베이스 작업을 수행합니다.
 */
public class JobDAO {

    /**
     * 모든 Job 데이터를 조회합니다.
     * 
     * @return JobDTO 리스트
     */
    public List<JobDTO> getAllJobs() {
        List<JobDTO> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                JobDTO job = new JobDTO();
                job.setJobId(rs.getString("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setMinSalary(rs.getInt("min_salary"));
                job.setMaxSalary(rs.getInt("max_salary"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    /**
     * 새로운 Job 데이터를 추가합니다.
     * 
     * @param job JobDTO 객체
     * @return 성공 여부
     */
    public boolean addJob(JobDTO job) {
        String query = "INSERT INTO jobs (job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, job.getJobId());
            pstmt.setString(2, job.getJobTitle());
            pstmt.setInt(3, job.getMinSalary());
            pstmt.setInt(4, job.getMaxSalary());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 기존 Job 데이터를 수정합니다.
     * 
     * @param job JobDTO 객체
     * @return 성공 여부
     */
    public boolean updateJob(JobDTO job) {
        String query = "UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, job.getJobTitle());
            pstmt.setInt(2, job.getMinSalary());
            pstmt.setInt(3, job.getMaxSalary());
            pstmt.setString(4, job.getJobId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Job 데이터를 삭제합니다.
     * 
     * @param jobId Job ID
     * @return 성공 여부
     */
    public boolean deleteJob(String jobId) {
        String query = "DELETE FROM jobs WHERE job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, jobId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
