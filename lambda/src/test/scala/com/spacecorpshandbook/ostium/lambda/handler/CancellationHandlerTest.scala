package com.spacecorpshandbook.ostium.lambda.handler

import com.spacecorpshandbook.ostium.core.model.Appointment
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CancellationHandlerTest {

  @Test def givenAppointmentWithIdWhenCancelledThenTheIdIsEchoedBack() {

    val id: String = "abcd"

    val appointment: Appointment = new Appointment
    appointment.appointmentId = id

    val handler: CancellationHandler = new CancellationHandler
    val result: String = handler.cancelAppointment(appointment)

    assertThat(result, containsString(id))
  }
}