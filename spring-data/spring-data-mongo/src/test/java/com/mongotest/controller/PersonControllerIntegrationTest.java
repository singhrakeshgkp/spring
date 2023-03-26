package com.mongotest.controller;


import com.mongotest.model.Person;
import com.mongotest.repo.PersonRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@DataMongoTest
public class PersonControllerIntegrationTest {
    /*
    Fore more details about web layer refer below testing guide
    https://spring.io/guides/gs/testing-web/

    */

    @Autowired
    private MockMvc mvc;
    @SpyBean
    private PersonRepo personRepo;

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    /*Approach-1 Testing web layer using testRestTemplate*/
    @Test
    public void addPerson() {

        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        Person person = new Person("194", "rakesh singh", 30);
        HttpEntity<Person> httpEntity = new HttpEntity<>(person, headers);
        //when
        ResponseEntity<Person> resEntity = testRestTemplate.exchange("/api/person", HttpMethod.POST, httpEntity, Person.class);
        //then
        System.out.println(resEntity.getStatusCode());
        assertEquals(HttpStatus.CREATED,resEntity.getStatusCode());
    }

    @Test
    public  void getPersonById(){
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type",MediaType.APPLICATION_JSON_VALUE);
        Person p = new Person("111","suresh singh",22);
        HttpEntity<Person> httpEntity = new HttpEntity<>(p,headers);
        ResponseEntity<Person> resEntity=testRestTemplate.exchange("/api/person",HttpMethod.POST,httpEntity, Person.class);
        //when
        ResponseEntity<Person>getResponse=testRestTemplate.
                exchange("/api/person/{id}",HttpMethod.GET,httpEntity,Person.class,
                        Map.of("id",p.getId()));
        //then
        assertEquals(p.getId(),getResponse.getBody().getId());
    }


    @Test
    public void getPersons(){
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("content-type",MediaType.APPLICATION_JSON_VALUE);
        Person p = new Person("111","suresh singh",22);
        HttpEntity<Person> httpEntity = new HttpEntity<>(p,headers);
        ResponseEntity<Person> savedpersonEntity=testRestTemplate.exchange("/api/person",HttpMethod.POST,httpEntity, Person.class);

        //When
       ResponseEntity<List<Person>> resEntity= testRestTemplate.exchange("/api/person", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Person>>() {
        });
        //then
        assertTrue(resEntity.getBody().size()>0);
    }

    @Test
    public void updatePerson(){
        //given
         /*1.- insert person
         * 2.- update person
         * 3.- validate the updated data*/
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type",MediaType.APPLICATION_JSON_VALUE);
        Person p = new Person("R121","suresh singh",22);
        HttpEntity<Person> httpEntity = new HttpEntity<>(p,headers);
        ResponseEntity<Person> savedPersonEntity=testRestTemplate.exchange("/api/person",HttpMethod.POST,httpEntity, Person.class);

        //when
        Person savedPerson = savedPersonEntity.getBody();
        savedPerson.setName("rakesh singh");
        savedPerson.setAge(30);
        httpEntity = new HttpEntity<>(savedPerson,headers);
        ResponseEntity<Person> updatedPersonEntity=testRestTemplate.exchange("/api/person",HttpMethod.PUT,httpEntity, Person.class);
        //then
        assertEquals(p.getId(),updatedPersonEntity.getBody().getId());
        assertEquals("rakesh singh", updatedPersonEntity.getBody().getName());
        assertEquals(30,updatedPersonEntity.getBody().getAge());
    }
    /*Approach -2 Testing web layer using Mock MVC */
    @Test
    public void addPersonMockMvc() {

        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        Person person = new Person("194", "rakesh singh", 30);
        HttpEntity<Person> httpEntity = new HttpEntity<>(person, headers);
        //when
        ResponseEntity<Person> resEntity = testRestTemplate.exchange("/api/person", HttpMethod.POST, httpEntity, Person.class);
        //then
        System.out.println(resEntity.getStatusCode());
        assertEquals(HttpStatus.CREATED,resEntity.getStatusCode());
    }
    @Test
    public void getPersonByIdMockMvc() throws  Exception{

       mvc.perform(get("/api/person/004"))
               .andDo(result->System.out.print(result.getResponse()))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("004")));
    }

    @Test
    public void getPersonByIdFailureCase() throws  Exception{
        mvc.perform(get("/api/person/5554"))
                .andDo(result->System.out.print(result.getResponse()))
                .andExpect(status().isNotFound());

    }
}
