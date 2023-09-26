package com.utiitsl.mytest.repo;

import com.utiitsl.mytest.entity.Robot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends CrudRepository<Robot, Long> {
}
