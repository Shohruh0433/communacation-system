package uz.developer.communication_system.payload.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class Passport {

    private Long id;
    private String firstName;
    private String lastName;
    private String givenByWhom;
    private String passportSeria;
    private String  passportNumber;
    private String dateOfBirth;
    private String dateOfIssue;
    private String dateOfExpire;
    private Long pinfl;
    private String placeOfPlace;

    public Passport() {
    }




}
