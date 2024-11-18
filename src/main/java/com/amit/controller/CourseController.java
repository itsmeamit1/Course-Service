package com.amit.controller;

import com.amit.dto.CourseRequestDTO;
import com.amit.dto.CourseResponseDTO;
import com.amit.dto.ServiceResponse;
import com.amit.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseController  {

    @Autowired
    private CourseService service;

    @PostMapping
    public ServiceResponse<CourseResponseDTO>  addCourse(@RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO newCourse = service.onboardNewCourse(courseRequestDTO);
        return new ServiceResponse<>(HttpStatus.CREATED,newCourse);
    }

    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourses(){
        List<CourseResponseDTO> responseDTOS= service.viewAllCourses();
        return new ServiceResponse<>(HttpStatus.OK ,responseDTOS);
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK,service.findByCourseId(courseId));
    }

    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseByRequestParam(@RequestParam(required=false) Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK,service.findByCourseId(courseId));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        service.deleteCourse(courseId);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO>  updateCourse(@PathVariable int courseId ,@RequestBody CourseRequestDTO course){
        return new ServiceResponse<>(HttpStatus.OK,service.updatedCourse(courseId,course));
    }
}


