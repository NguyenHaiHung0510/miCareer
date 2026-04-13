package vn.com.micareer.model;
public class JobRequirement {

    private int jobPostId;
    private int skillId;

    public JobRequirement() {
    }

    public JobRequirement(int jobPostId, int skillId) {
        this.jobPostId = jobPostId;
        this.skillId = skillId;
    }

    public int getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(int jobPostId) {
        this.jobPostId = jobPostId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}