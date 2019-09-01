package com.waes.diffeder.diffeder;

import com.waes.diffeder.diffeder.dao.DiffDao;
import com.waes.diffeder.diffeder.model.DiffEqualsResult;
import com.waes.diffeder.diffeder.model.DiffObject;
import com.waes.diffeder.diffeder.model.DiffObjectType;
import com.waes.diffeder.diffeder.model.DiffResponse;
import com.waes.diffeder.diffeder.service.DiffServiceImpl;
import com.waes.diffeder.diffeder.service.DiffUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class DifferedServiceTest {

    private DiffServiceImpl diffService;
    private DiffDao diffDao;

    @Before
    public void setUp() {
        diffDao = Mockito.mock(DiffDao.class);
        Mockito.when(diffDao.findById("1_" + DiffObjectType.LEFT.toString()))
                .thenReturn(Optional.of(new DiffObject("1_" + DiffObjectType.LEFT.toString(),"12345")));
        Mockito.when(diffDao.findById("1_" + DiffObjectType.RIGHT.toString()))
                .thenReturn(Optional.of(new DiffObject("1_" + DiffObjectType.RIGHT.toString(),"123456")));

        Mockito.when(diffDao.findById("2_" + DiffObjectType.LEFT.toString()))
                .thenReturn(Optional.of(new DiffObject("2_" + DiffObjectType.LEFT.toString(),"12345")));
        Mockito.when(diffDao.findById("2_" + DiffObjectType.RIGHT.toString()))
                .thenReturn(Optional.of(new DiffObject("2_" + DiffObjectType.RIGHT.toString(),"12345")));

        diffService = new DiffServiceImpl(diffDao);
    }

    @Test
    public void diff_test_calc_diffs() {
        List<Pair<Integer, Integer>> result = DiffUtil.calculateDiff("28345", "12346");
        assertEquals(result.get(0).getFirst().intValue(), 0);
        assertEquals(result.get(0).getSecond().intValue(), 2);
        assertEquals(result.get(1).getFirst().intValue(), 4);
        assertEquals(result.get(1).getSecond().intValue(), 1);
    }

    @Test
    public void diff_test_concat() {
        List<Pair<Integer, Integer>> result = DiffUtil.calculateDiff("28345", "12346");
        String diff = DiffUtil.concatToString(result);
        assertEquals(diff, "Position: 0, length: 2; Position: 4, length: 1");
    }

    @Test
    public void diff_test_no_left() {
        DiffResponse resp = diffService.checkDiff("1111");
        assertEquals(resp.getDiffResult(), DiffEqualsResult.NO_LEFT);
    }

    @Test
    public void diff_test_diff_sizes() {
        DiffResponse resp = diffService.checkDiff("1");
        assertEquals(resp.getDiffResult(), DiffEqualsResult.DIFF_SIZES);
    }

    @Test
    public void diff_test_equals() {
        DiffResponse resp = diffService.checkDiff("2");
        assertEquals(resp.getDiffResult(), DiffEqualsResult.EQUALS);
    }

}
