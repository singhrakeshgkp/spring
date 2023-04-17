package com.mongotest.controller;

import com.mongotest.repo.PersonRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest1 {
/*@ExtendWith(SpringExtension.class)(part of junit5)or @RunWith(part of junit 4) annotation is not required if you are using @SpringBootTest
* as it is already annotated with this annotation
* */
    @MockBean
    PersonRepo personRepo;
    @Test
    public void getPerfonById(){

        System.out.println("Person Repo :- "+personRepo);
        personRepo.findById("004");
    }
}
