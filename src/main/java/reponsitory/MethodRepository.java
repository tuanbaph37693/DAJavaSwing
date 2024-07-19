/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package reponsitory;

import java.util.List;

/**
 *
 * @author Admin
 * @param <EntityType>
 */
public interface MethodRepository<EntityType> {

    List<EntityType> selectBySQL(String sql, Object... args);

    List<EntityType> getAll(int page, int limit);

    EntityType getByID(String id);

    void add(EntityType o);

    void update(EntityType o, String id);

    void remove(String id);
    
    void unremove(String id);
    
    List<EntityType> getListRemove();
}
