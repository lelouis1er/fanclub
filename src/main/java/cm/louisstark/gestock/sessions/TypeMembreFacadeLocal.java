/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.TypeMembre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lstark
 */
@Local
public interface TypeMembreFacadeLocal {

    void create(TypeMembre typeMembre);

    void edit(TypeMembre typeMembre);

    void remove(TypeMembre typeMembre);

    TypeMembre find(Object id);

    List<TypeMembre> findAll();

    List<TypeMembre> findRange(int[] range);

    int count();

    public Integer nextId();
    
}
