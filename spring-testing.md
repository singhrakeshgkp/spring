# Spring Boot Testing

##### @SpringBootTest :- 
<p>This annotation is used to load complete application context for end to end integration testing,The @SpringBootTest annotation can be used when we need to bootstrap the entire container. The annotation works by creating the ApplicationContext that will be utilized in our tests.</p>

##### @RunWith(SpringRunner.class):- 
<p>You need this annotation to just enable spring boot features like @Autowire, @MockBean etc.. during junit testing</p>
