package it.protectid.policy;

public class PolicyRequest {

    public Ppm ppm;
    public String fcn;

    public PolicyRequest() {
    }

    public PolicyRequest(Ppm ppm, String fcn) {
        this.ppm = ppm;
        this.fcn = fcn;
    }

    public Ppm getPpm() {
        return ppm;
    }

    public void setPpm(Ppm ppm) {
        this.ppm = ppm;
    }

    public String getFcn() {
        return fcn;
    }

    public void setFcn(String fcn) {
        this.fcn = fcn;
    }

    @Override
    public String toString() {
        return "PolicyRequest{" +
                "ppm=" + ppm +
                ", fcn='" + fcn + '\'' +
                '}';
    }
}
