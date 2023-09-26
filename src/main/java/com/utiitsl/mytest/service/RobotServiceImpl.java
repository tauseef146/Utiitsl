package com.utiitsl.mytest.service;
import com.utiitsl.mytest.entity.Robot;
import com.utiitsl.mytest.repo.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotServiceImpl implements RobotService {
    @Autowired
    private RobotRepository robotRepository;

    @Override
    public String walk(double distance) {
        Robot robot = robotRepository.findById(1L).orElse(new Robot());

        // Calculate battery usage for walking
        double batteryUsage = distance + (robot.getCarryingWeight() * 0.03);

        // Update battery level
        robot.setBatteryLevel(robot.getBatteryLevel() - batteryUsage);
        robotRepository.save(robot);

        // Check for low battery and return appropriate message
        if (robot.getBatteryLevel() < 0.2 * 7) {
            return "LOW BATTERY. Robot walked " + distance + " Km. Robot Battery Balance: " + robot.getBatteryLevel() + "%";
        }

        return "Robot walked " + distance + " Km. Robot Battery Balance: " + robot.getBatteryLevel() + "%";
    }

    @Override
    public String carry(double weight) {
        Robot robot = robotRepository.findById(1L).orElse(new Robot());

        // Check if the weight is within the limit
        if (weight > 15) {
            return "OVER WEIGHT. Robot cannot carry more than 15 Kg.";
        }

        // Calculate battery usage for carrying
        double batteryUsage = weight * 0.03;

        // Check if there's enough battery
        if (robot.getBatteryLevel() < batteryUsage) {
            return "LOW BATTERY. Robot cannot carry " + weight + " Kg. Robot Battery Balance: " + robot.getBatteryLevel() + "%";
        }

        // Update carrying weight and battery level
        robot.setCarryingWeight(robot.getCarryingWeight() + weight);
        robot.setBatteryLevel(robot.getBatteryLevel() - batteryUsage);
        robotRepository.save(robot);

        // Check for low battery and return appropriate message
        if (robot.getBatteryLevel() < 0.2 * 7) {
            return "LOW BATTERY. Robot is carrying " + weight + " Kg. Robot Battery Balance: " + robot.getBatteryLevel() + "%";
        }

        return "Robot is carrying " + weight + " Kg. Robot Battery Balance: " + robot.getBatteryLevel() + "%";
    }
}
