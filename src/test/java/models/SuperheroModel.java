package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class SuperheroModel {
    private String birthDate;
    private String city;
    private String fullName;
    private Gender gender;
    private Integer id;
    private String mainSkill;
    private String phone;
}
