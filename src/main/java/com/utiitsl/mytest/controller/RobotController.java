package com.utiitsl.mytest.controller;
import com.utiitsl.mytest.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/robot")
public class RobotController {
    @Autowired
    private RobotService robotService;

    @PostMapping("/walk")
    public String walk(@RequestParam double distance) {
        return robotService.walk(distance);
    }

    @PostMapping("/carry")
    public String carry(@RequestParam double weight) {
        return robotService.carry(weight);
    }
}
