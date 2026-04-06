package vn.com.micareer.model;

public class EmailTemplate {
    private long tmplId;
    private long typeId;
    private String subj;
    private String body;
    private String desc;

    // Getters and Setters
    public long getTmplId() { return tmplId; }
    public void setTmplId(long tmplId) { this.tmplId = tmplId; }
    public long getTypeId() { return typeId; }
    public void setTypeId(long typeId) { this.typeId = typeId; }
    public String getSubj() { return subj; }
    public void setSubj(String subj) { this.subj = subj; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
}
