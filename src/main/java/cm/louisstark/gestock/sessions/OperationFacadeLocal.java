/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.Operation;
import cm.louisstark.gestock.entities.SessionOp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lstark
 */
@Local
public interface OperationFacadeLocal {

    void create(Operation operation);

    void edit(Operation operation);

    void remove(Operation operation);

    Operation find(Object id);

    List<Operation> findAll();

    List<Operation> findRange(int[] range);
    
    List<Operation> findAllBy_session(SessionOp cycleEntreprise);
    
    List<Operation> findAllBy_session_membre(SessionOp cycleEntreprise, Membre membre);

    int count();
    
    long nextId();

    
}
