package com.amit.service;

import com.amit.dao.CourseDao;
import com.amit.dto.CourseRequestDTO;
import com.amit.dto.CourseResponseDTO;
import com.amit.entity.CourseEntity;
import com.amit.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    // 1.Create Courses object on DB
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO){
        //Convert DTO to entity
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity save = courseDao.save(courseEntity);
        //Now convert entity to ResponseDTO
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(save);
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
    }

    //2.load all the courses from db
    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities= courseDao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(),false)
                .map(AppUtils::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    //find any particular course by Id
    public CourseResponseDTO findByCourseId(Integer courseId){
     CourseEntity entity =   courseDao.findById(courseId).orElseThrow(()->new RuntimeException(courseId +"Not valid"));
     return AppUtils.mapEntityToDTO(entity);
    }

    //Delete any Course from DB
    public void deleteCourse(Integer courseId){
        courseDao.deleteById(courseId);
    }

    //update the course
    public CourseResponseDTO updatedCourse(int courseId , CourseRequestDTO courseRequestDTO){
        CourseEntity existingCourseEntity = courseDao.findById(courseId).orElse(null);
        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());
        CourseEntity updatedCourseEntity=courseDao.save(existingCourseEntity);
        return AppUtils.mapEntityToDTO(updatedCourseEntity);


    }
}
