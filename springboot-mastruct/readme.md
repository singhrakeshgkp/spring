# Configure mapstruct
  - Create new spring boot application and add mapstruct dependency
  - Create source and target model classess
  - create an interface with required mapping methods and also anotate the interface with  ``` @Mapper ``` annotation. Note- can also use abstract class
 
### Implicit type conversion
   - Between all primitive data types and their corresponding wrapper types.
   - Between all java primitive numbers type and their corresponding wrapper types.
   - Converting from larger data types to smaller ones (e.g. from long to int) can cause a value or precision loss. The Mapper and MapperConfig annotations have a          method typeConversionPolicy to control warnings / errors. Due to backward compatibility reasons the default value is ReportingPolicy.IGNORE
   - Between all java primitive types(including wrappers) and String.
   - Between enum type and string
   - Between big number types (java.math.BigInteger, java.math.BigDecimal) and Java primitive types (including their wrappers) as well as String. A format string as        understood by java.text.DecimalFormat can be specified
   - For more details refer map construct documentation ``` implicit type conversion  ``` section.
   
 ### Mapping Map to Bean
     ```
      @Mapper
      public interface CustomerMapper {
      @Mapping(target = "name", source = "customerName")
      Customer toCustomer(Map<String, String> map);
      }
      ```
### Getting mapper instance
   - Using mapper factory (no dependency injection)
     ```
     StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
     ```
   - Using dependency injection(useful if using DI framework such as spring)
     ```
     @Mapper(componentModel = MappingConstants.ComponentModel.CDI)
      public interface CarMapper {
        CarDto carToCarDto(Car car);
      }
     ```
     ```
     @Inject
     private CarMapper mapper;
     ```
     -  you can also choose the injection strategy using @mapping or @mapconfig annotation
        ```
        @Mapper(componentModel = MappingConstants.ComponentModel.CDI, uses = EngineMapper
        .class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
        public interface CarMapper {
          CarDto carToCarDto(Car car);
        }
        ```
     
 
