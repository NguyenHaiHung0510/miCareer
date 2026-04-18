package vn.com.micareer.model;

import java.io.Serializable;

public class HR extends User implements Serializable {

    private int hrId;
    private int posId;
    private int compId;
    private String emailSign;

    public HR() {
    }

    public HR(int hrId, int posId, int compId, String emailSign) {
        this.hrId = hrId;
        this.posId = posId;
        this.compId = compId;
        this.emailSign = emailSign;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public String getEmailSign() {
        return emailSign;
    }

    public void setEmailSign(String emailSign) {
        this.emailSign = emailSign;
    }

    @Override
    public String toString() {
        return "HR{" +
                "hrId=" + hrId +
                ", posId=" + posId +
                ", compId=" + compId +
                ", emailSign='" + emailSign + '\'' +
                '}';
    }
}