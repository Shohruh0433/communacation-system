package uz.developer.communication_system.payload.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor

public class Passport {

    private Long id;
    private String firstName;
    private String lastName;
    private String givenByWhom;
    private String passportSeria;
    private String  passportNumber;
    private Date birthDate;
    private String dateOfIssue;
    private String dateOfExpire;
    private Long pinfl;
    private String placeOfPlace;
    private boolean legal;


    public Passport() {
    }


}
