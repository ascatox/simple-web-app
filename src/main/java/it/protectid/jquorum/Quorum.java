package it.protectid.jquorum;

import it.protectid.model.authority.Pdc;
import it.protectid.model.authority.Pip;
import it.protectid.model.policy.Ppa;
import it.protectid.model.policy.Ppm;

import java.util.Collection;

/**
 * @author clod16
 */
public interface Quorum {


    public String insertPIP(Pip pip, String sig);

    public String deletePIP(Pip pip, String sig);

    public Collection<Pip> getPIP();

    public Pip getPIPById(String id, String sig);


    public String insertPPM(Ppm ppm, String sig);

    public String deletePPM(Ppm ppm, String sig);

    public String insertPPA(Ppa ppa);

    public String deletePPA(Ppa ppa, String sig);

    public String insertPDC(Pdc pdc);

    public String deletePDC(Pdc pdc, String id);
}
