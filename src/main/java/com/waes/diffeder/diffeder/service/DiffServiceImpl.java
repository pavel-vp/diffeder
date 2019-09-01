package com.waes.diffeder.diffeder.service;

import com.waes.diffeder.diffeder.dao.DiffDao;
import com.waes.diffeder.diffeder.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation class
 */
@Service
public class DiffServiceImpl implements IDiffService {

    private DiffDao diffDao;

    @Autowired
    public DiffServiceImpl(DiffDao diffDao) {
        this.diffDao = diffDao;
    }

    @Override
    public void setObject(String id, DiffRequestData diffRequestData, DiffObjectType diffObjectType) {
        byte[] decodedData = Base64.getDecoder().decode(diffRequestData.getData());
        DiffObject diffObject = new DiffObject(id + "_" + diffObjectType.name(), new String(decodedData));
        diffDao.save(diffObject);
    }

    @Override
    public DiffResponse checkDiff(String id) {
        Optional<DiffObject> leftObject = diffDao.findById(id + "_" + DiffObjectType.LEFT.toString());
        Optional<DiffObject> rightObject = diffDao.findById(id + "_" + DiffObjectType.RIGHT.toString());
        if (!leftObject.isPresent()) {
            return new DiffResponse(DiffEqualsResult.NO_LEFT, null);
        }
        if (!rightObject.isPresent()) {
            return new DiffResponse(DiffEqualsResult.NO_RIGHT, null);
        }
        if (rightObject.get().getData().equals(leftObject.get().getData())) {
            return new DiffResponse(DiffEqualsResult.EQUALS, null);
        }
        if (rightObject.get().getData().length() != leftObject.get().getData().length()) {
            return new DiffResponse(DiffEqualsResult.DIFF_SIZES, null);
        }
        List<Pair<Integer, Integer>> differenceList = DiffUtil.calculateDiff(rightObject.get().getData(), leftObject.get().getData());

        return new DiffResponse(DiffEqualsResult.DIFFERENT, DiffUtil.concatToString(differenceList));
    }

}
