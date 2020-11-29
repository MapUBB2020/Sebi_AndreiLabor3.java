package com.company.FileRepo;

import com.company.repository.ICrudRepository;

import java.util.List;

public interface CrudFileRepository<E> {


    /**
     * @param entity - must not be null
     * @return the entity if it was added to the file.txt or null if the entity already exists
     */
    List<E> insert(List<E> entity);

    /**
     *
     * @return list of entity
     *
     */
    List<E> read_to_list();

    /**
     *
     * @param entity - entity must not be null
     * @return list with updated entity
     */
    List<E> edit(List<E> entity);
}
