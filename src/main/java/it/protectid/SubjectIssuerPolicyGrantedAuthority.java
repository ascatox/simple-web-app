package it.protectid;

import org.mitre.openid.connect.client.SubjectIssuerGrantedAuthority;

import java.util.Objects;

public class SubjectIssuerPolicyGrantedAuthority extends SubjectIssuerGrantedAuthority {
    /**
     * @param subject
     * @param issuer
     */
    private String ppm;

    public SubjectIssuerPolicyGrantedAuthority(String subject, String issuer) {
        super(subject, issuer);
    }
    public SubjectIssuerPolicyGrantedAuthority(String subject, String issuer, String ppm) {
        super(subject, issuer);
        this.ppm = ppm;
    }
    public String getPpm() {
        return ppm;
    }

    public void setPpm(String ppm) {
        this.ppm = ppm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectIssuerPolicyGrantedAuthority)) return false;
        if (!super.equals(o)) return false;
        SubjectIssuerPolicyGrantedAuthority that = (SubjectIssuerPolicyGrantedAuthority) o;
        return Objects.equals(getPpm(), that.getPpm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPpm());
    }
}
