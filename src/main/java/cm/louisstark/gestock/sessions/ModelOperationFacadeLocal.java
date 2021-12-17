/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.louisstark.gestock.sessions;

import cm.louisstark.gestock.entities.ModelOperation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author lstark
 */
@Local
public interface ModelOperationFacadeLocal {

    void create(ModelOperation modelOperation);

    void edit(ModelOperation modelOperation);

    void remove(ModelOperation modelOperation);

    ModelOperation find(Object id);

    List<ModelOperation> findAll();

    List<ModelOperation> findRange(int[] range);

    int count();

    public Integer nextId();
    
}
