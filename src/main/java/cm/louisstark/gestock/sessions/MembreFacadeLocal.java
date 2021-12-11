/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.entities.Membre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lstark
 */
@Local
public interface MembreFacadeLocal {

    void create(Membre membre);

    void edit(Membre membre);

    void remove(Membre membre);

    Membre find(Object id);

    List<Membre> findAll();

    List<Membre> findRange(int[] range);

    int count();

    public List<Membre> findAllBy_societe(FanClub fanClub);

    public Integer nextId();
    
}
