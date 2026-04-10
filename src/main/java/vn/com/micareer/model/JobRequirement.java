package vn.com.micareer.model;

public class JobRequirement {

    private long jobPostId;
    private long skillId;

    public long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }
}