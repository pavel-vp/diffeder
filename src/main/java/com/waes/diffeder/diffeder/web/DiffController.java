package com.waes.diffeder.diffeder.web;

import com.waes.diffeder.diffeder.model.DiffRequestData;
import com.waes.diffeder.diffeder.model.DiffResponse;
import com.waes.diffeder.diffeder.model.DiffObjectType;
import com.waes.diffeder.diffeder.service.DiffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * RestController of the difference task.
 */
@RestController
@RequestMapping("/v1/diff/{id}")
public class DiffController {

    private final DiffServiceImpl diffService;

    @Autowired
    public DiffController(DiffServiceImpl diffService) {
        this.diffService = diffService;
    }

    @PostMapping("/left")
    public void setDiffLeft(@PathVariable String id, @RequestBody DiffRequestData diffRequestData,
                            HttpServletResponse response) {
        diffService.setObject(id, diffRequestData, DiffObjectType.LEFT);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PostMapping("/right")
    public void setDiffRight(@PathVariable String id, @RequestBody DiffRequestData diffRequestData,
                            HttpServletResponse response) {
        diffService.setObject(id, diffRequestData, DiffObjectType.RIGHT);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping()
    public @ResponseBody DiffResponse checkDiff(@PathVariable String id) {
        return diffService.checkDiff(id);
    }


}
