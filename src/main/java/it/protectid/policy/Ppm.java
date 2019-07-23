package it.protectid.policy;

import javax.persistence.*;

/**
 * @author ascatox
 * @author clod16
 */
@Entity
@Table(name = "ppm")
@NamedQueries({
        @NamedQuery(name = Ppm.QUERY_ALL, query = "select p from Ppm p"),
        @NamedQuery(name = Ppm.QUERY_BY_ID, query = "select p FROM Ppm p WHERE p.id = :" + Ppm.PARAM_ID),
        @NamedQuery(name = Ppm.QUERY_BY_DP, query = "select p FROM Ppm p WHERE p.dp = :" + Ppm.PARAM_DP),

})
public class Ppm {

    public static final String QUERY_ALL = "Ppm.getAll";
    public static final String PARAM_DP = "dp";
    public static final String QUERY_BY_DP = "Ppm.queryByDp";
    public static final String QUERY_BY_ID = "Ppm.queryById";
    public static final String PARAM_ID = "id";

    private String id;
    private String dp;
    private String model;
    private String sig;

    public Ppm() {
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dp")
    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "sig")
    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }
}
