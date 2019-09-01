package com.waes.diffeder.diffeder.service;

import com.waes.diffeder.diffeder.model.DiffRequestData;
import com.waes.diffeder.diffeder.model.DiffResponse;
import com.waes.diffeder.diffeder.model.DiffObjectType;

/**
 * Interface describes functionallity of service
 */
public interface IDiffService {
    /**
     * Method puts Object in storage with parameters
     *
     * @param id - a key
     * @param diffRequestData - JSON Base64 encoded data
     * @param diffObjectType - type of storing object (LEFT or RIGHT)
     */
    void setObject(String id, DiffRequestData diffRequestData, DiffObjectType diffObjectType);

    /**
     * Method checks the previously stored objects an compares its left and right sides
     *
     * @param id - a key
     * @return - DiffResponse object
     */
    DiffResponse checkDiff(String id);
}
