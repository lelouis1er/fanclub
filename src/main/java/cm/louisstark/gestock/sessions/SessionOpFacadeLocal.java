/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.SessionOp;
import cm.louisstark.gestock.entities.FanClub;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Louis Stark
 */
@Local
public interface SessionOpFacadeLocal {

    void create(SessionOp session);

    void edit(SessionOp session);

    void remove(SessionOp session);

    SessionOp find(Object id);

    List<SessionOp> findAll();
    
    List<SessionOp> findAllBy_societe(FanClub f);

    List<SessionOp> findRange(int[] range);

    int count();
    
    int nextId();
    
}
