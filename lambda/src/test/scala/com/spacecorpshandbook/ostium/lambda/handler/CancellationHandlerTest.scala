package com.spacecorpshandbook.ostium.lambda.handler

import com.google.gson.Gson
import com.spacecorpshandbook.ostium.core.model.{Appointment, CancelResponse}
import com.spacecorpshandbook.ostium.lambda.proxy.ApiGatewayProxyResponse
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CancellationHandlerTest {

  @Test def givenAppointmentWithIdWhenCancelledThenTheIdIsEchoedBack() {

    val id: String = "abcd"

    val appointment: Appointment = new Appointment
    appointment.appointmentId = id

    val handler: CancellationHandler = new CancellationHandler
    val result: ApiGatewayProxyResponse = handler.cancelAppointment(appointment)

    val cancelResponseAsString = result.getBody

    val gson : Gson = new Gson

    val cancelResponse = gson.fromJson(cancelResponseAsString, classOf[CancelResponse])

    val message = cancelResponse.getMessage()

    assertThat(message, containsString(id))
  }
}