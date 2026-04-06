package vn.com.micareer.model;

public class JobRequirement {

     private String jobPostId;
    private String skillId;

    public JobRequirement() {
    }

    public JobRequirement(String jobPostId, String skillId) {
        this.jobPostId = jobPostId;
        this.skillId = skillId;
    }

    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }
}
