package com.zimug.boot.launch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
@AllArgsConstructor
public class LombokPOJO {

  private String name;

  private Integer age;

}
