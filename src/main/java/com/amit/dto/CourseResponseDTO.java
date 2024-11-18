package com.amit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponseDTO {
    private int courseId;
    private String name;
    private String trainerName;
    private String duration; //days or Month
    @JsonFormat(shape =JsonFormat.Shape.STRING ,pattern = "dd-MM-yyyy") //take date as string format from UI/Postman
    private Date startDate;
    private String courseType;
    private Double fees;
    private boolean isCertificateAvailable;
    private String description ;
    private String courseUniqueCode;
}
