package com.spacecorpshandbook.ostium.lambda.handler

import java.io.{IOException, InputStream, OutputStream}
import java.util

import com.amazonaws.services.lambda.runtime.Context
import com.google.gson.{Gson, JsonObject, JsonParser}
import org.apache.commons.io.IOUtils
import com.spacecorpshandbook.ostium.core.model.{Appointment, CancelResponse}
import com.spacecorpshandbook.ostium.lambda.proxy.ApiGatewayProxyResponse


/**
  * Amazon Lambda handler adapter for the Cancellation application
  */
class CancellationHandler {

  def cancelAppointment(request: InputStream, response: OutputStream, context: Context): Unit = {

    val appointment: Appointment = convertStreamToAppointment(request, context)

    val cancelResponse: CancelResponse = cancelTheAppointment(appointment)

    val output = convertAppointmentToString(response, cancelResponse)

    IOUtils.write(output, response, "UTF-8")
  }

  private[this] def convertStreamToAppointment(request: InputStream, context: Context): Appointment = {


    val parser: JsonParser = new JsonParser
    val gson: Gson = new Gson
    var inputObj: JsonObject = null
    val logger = context.getLogger

    try {

      inputObj = parser.parse(IOUtils.toString(request, "UTF-8")).getAsJsonObject

      val body: String = inputObj.get("body").getAsString
      val appointment: Appointment = gson.fromJson(body, classOf[Appointment])

      appointment

    } catch {

      case e: IOException =>

        logger.log("Error while reading request\n" + e.getMessage)
        throw new RuntimeException(e.getMessage)

    }
  }

  private[this] def cancelTheAppointment(appointment: Appointment): CancelResponse = {

    val cancelResponse = new CancelResponse

    cancelResponse.setMessage("Cancelled appointment with id " + appointment.getAppointmentId)

    cancelResponse
  }

  private[this] def convertAppointmentToString(outputStream: OutputStream, cancelResponse: CancelResponse): String = {

    val apiGatewayProxyResponse = new ApiGatewayProxyResponse

    val gson: Gson = new Gson

    apiGatewayProxyResponse.setBody(gson.toJson(cancelResponse))

    apiGatewayProxyResponse.setStatusCode("200")

    val headerValues = new util.HashMap[String, String]

    headerValues put("Content-Type", "application/json")

    apiGatewayProxyResponse.setHeaders(headerValues)

    val output: String = gson.toJson(apiGatewayProxyResponse)

    output
  }
}