package com.spacecorpshandbook.ostium.lambda.handler

import java.util

import com.google.gson.Gson
import com.spacecorpshandbook.ostium.core.model.{Appointment, CancelResponse}
import com.spacecorpshandbook.ostium.lambda.proxy.ApiGatewayProxyResponse

/**
  * Amazon Lambda handler adapter for the Cancellation application
  */
class CancellationHandler {

  def cancelAppointment(appointment: Appointment): ApiGatewayProxyResponse = {

    val apiGatewayProxyResponse = new ApiGatewayProxyResponse
    val cancelResponse = new CancelResponse

    cancelResponse.setMessage("Cancelled appointment with id " + appointment.appointmentId)

    val gson : Gson = new Gson

    apiGatewayProxyResponse.setBody(gson.toJson(cancelResponse))

    apiGatewayProxyResponse.setStatusCode("200")

    val headerValues = new util.HashMap[String, String]

    headerValues put ("Content-Type", "application/json")

    apiGatewayProxyResponse.setHeaders(headerValues)

    return apiGatewayProxyResponse
  }
}