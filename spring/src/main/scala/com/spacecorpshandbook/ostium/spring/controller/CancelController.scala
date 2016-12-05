package com.spacecorpshandbook.ostium.spring.controller

import com.spacecorpshandbook.ostium.core.model.{Appointment, CancelResponse}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.{RequestBody, RequestMapping, RequestMethod, RestController}

@RestController
@Configuration
@EnableAutoConfiguration
@RequestMapping(Array("/"))
class CancelController {

  @RequestMapping(value = Array("appointment"), method = Array(RequestMethod.DELETE))
  def cancelAppointment(@RequestBody appointment: Appointment): CancelResponse = {

    val response : CancelResponse = new CancelResponse()

    response.setMessage("Appointment cancelled for id: " + appointment.appointmentId)

    return response
  }
}