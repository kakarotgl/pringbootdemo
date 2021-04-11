package com.zimug.boot.launch.model;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@PropertySource(value="classpath:/otherconfig/employee.properties",encoding = "utf-8")
public class Employee {

  @Value("#{'${employee.names}'.split('\\|')}")
  private List<String> employeeNames;


  @Value("#{'${employee.names}'.split('\\|')[0]}")
  private String firstEmployeeName;

  @Value ("#{${employee.age}}")
  private Map<String, Integer> employeeAge;

  //@Value ("#{${employee.age}.two}")
  @Value ("#{${employee.age}['two']}")  //这样写也可以
  private String employeeAgeTwo;

  @Value ("#{${employee.age}['five'] ?: 31}")
  private Integer ageWithDefaultValue;

  @Value ("#{systemProperties['java.home']}")
  private String javaHome;

  @Value ("#{systemProperties['user.dir']}")
  private String userDir;

}
