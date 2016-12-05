package com.spacecorpshandbook.ostium.spring.controller

import com.google.gson.Gson
import com.spacecorpshandbook.ostium.core.model.Appointment
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc._
import org.hamcrest.CoreMatchers._
import org.junit.{Before, Test}

class CancelControllerTest {

  @Before
  def setup(): Unit = {

    RestAssuredMockMvc.standaloneSetup(new CancelController)
  }

  @Test
  def givenAppointmentWithIdWhenDeletedTheIdIsEchoedBack(): Unit = {

    val actualAppointmentId = "123456"

    val appointment: Appointment = new Appointment
    appointment.appointmentId = actualAppointmentId

    val gson: Gson = new Gson

    given.
      body(gson.toJson(appointment))
      .contentType(ContentType.JSON)
      .when
      .delete("/appointment")
      .then
      .statusCode(200)
      .body("message", containsString(actualAppointmentId))

  }
}
