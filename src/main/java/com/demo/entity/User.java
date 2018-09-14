package com.demo.entity;

import lombok.*;
import lombok.extern.log4j.Log4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j
public class User {

  public int id;

  public String name;

  public String email;

}
