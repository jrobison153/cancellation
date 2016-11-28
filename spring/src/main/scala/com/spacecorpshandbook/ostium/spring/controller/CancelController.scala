package com.spacecorpshandbook.ostium.spring.controller

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Configuration
@EnableAutoConfiguration
@RequestMapping(Array("/"))
class CancelController {

  @RequestMapping(value = Array("appointment"), method = Array(RequestMethod.DELETE))
  def cancelAppointment: String = {

    return "Hello world"
  }
}