package com.amit.dao;

import com.amit.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<CourseEntity,Integer> {


}
