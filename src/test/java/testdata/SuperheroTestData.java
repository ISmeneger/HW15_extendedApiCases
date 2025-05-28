package testdata;

import models.Gender;
import models.SuperheroModel;

public class SuperheroTestData {

    public static final SuperheroModel CREATE_NEW_SUPERHERO = SuperheroModel.builder()
            .birthDate("1989-05-26")
            .city("Moscow")
            .fullName("Ivan Ivanov")
            .gender(Gender.M)
            .id(100)
            .mainSkill("QA")
            .phone("+79111115535")
            .build();

    public static final SuperheroModel SUPERHERO_WITHOUT_PHONE = SuperheroModel.builder()
            .birthDate("1986-08-18")
            .city("Tula")
            .fullName("Petr Petrov")
            .gender(Gender.M)
            .id(256)
            .mainSkill("Engineer")
            .phone(null)
            .build();

    public static final SuperheroModel UPDATE_SUPERHERO = SuperheroModel.builder()
            .birthDate("1991-05-26")
            .city("Belgrade")
            .fullName("Alexander Victorov")
            .gender(Gender.M)
            .id(257)
            .mainSkill("Manager")
            .phone("+79111115535")
            .build();
}
