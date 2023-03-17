### Setting up hosted cassandra database
<hr/>

- Sign up on datastax official website(u can signup github or google)
- create new database

### Connecting spring boot application to hosted cassandra database
<hr/>

- download secure connection bundle file(astradb dashboard->connect tab->scroll down->driver tab->select java and download bundle)
- copy and past bundle in resources folder
- generate token, and copy client id, secret key and token
- configure the required properties in application.properties file
- create properties config file ```DataStaxAstraDbProperties.java```
- annotate spring boot main application class with ```@EnableConfigurationProperties``` annotation
- create ```CqlSessionBuilderCustomizer``` bean
- now run the application.


