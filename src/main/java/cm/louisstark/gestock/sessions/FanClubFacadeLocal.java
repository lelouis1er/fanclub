/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.FanClub;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lstark
 */
@Local
public interface FanClubFacadeLocal {

    void create(FanClub fanClub);

    void edit(FanClub fanClub);

    void remove(FanClub fanClub);

    FanClub find(Object id);

    List<FanClub> findAll();

    List<FanClub> findRange(int[] range);

    int count();
    
    int nextId();
    
}
