package com.waes.diffeder.diffeder.dao;

import com.waes.diffeder.diffeder.model.DiffObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to manage CRUD operation with DiffObject, where id is the key field.
 */
@Repository
public interface DiffDao extends JpaRepository<DiffObject, String> {
}

