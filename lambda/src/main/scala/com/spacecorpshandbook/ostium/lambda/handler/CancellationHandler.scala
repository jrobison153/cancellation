package com.spacecorpshandbook.ostium.lambda.handler

import java.util

import com.google.gson.{Gson, JsonObject, JsonParser}
import temp.{ApiGatewayProxyResponse, Appointment, CancelResponse}
import java.io.{IOException, InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.Context
import org.apache.commons.io.IOUtils

/**
  * Amazon Lambda handler adapter for the Cancellation application
  */
class CancellationHandler {

  def cancelAppointment(request: InputStream, response: OutputStream, context: Context): Unit = {

    val logger = context.getLogger
    val parser: JsonParser = new JsonParser
    var inputObj: JsonObject = null
    val gson: Gson = new Gson

    try {

      inputObj = parser.parse(IOUtils.toString(request, "UTF-8")).getAsJsonObject

      System.out.println(inputObj.toString)
    } catch {

      case e: IOException =>

        logger.log("Error while reading request\n" + e.getMessage)
        throw new RuntimeException(e.getMessage)

    }

    val body: String = inputObj.get("body").getAsString
    val appointment: Appointment = gson.fromJson(body, classOf[Appointment])

    val apiGatewayProxyResponse = new ApiGatewayProxyResponse
    val cancelResponse = new CancelResponse

    cancelResponse.setMessage("Cancelled appointment with id " + appointment.getAppointmentId)

    apiGatewayProxyResponse.setBody(gson.toJson(cancelResponse))

    apiGatewayProxyResponse.setStatusCode("200")

    val headerValues = new util.HashMap[String, String]

    headerValues put("Content-Type", "application/json")

    apiGatewayProxyResponse.setHeaders(headerValues)

    System.out.println("+++++ message before returning: " + apiGatewayProxyResponse.getBody)

    val output: String = gson.toJson(apiGatewayProxyResponse)

    IOUtils.write(output, response, "UTF-8")
  }

}