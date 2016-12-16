package com.spacecorpshandbook.ostium.lambda.handler

import java.io.{ByteArrayInputStream, InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.Context
import com.google.gson.{Gson, JsonObject, JsonParser}
import com.spacecorpshandbook.ostium.core.model.Appointment
import org.apache.commons.io.IOUtils
import org.hamcrest.CoreMatchers.containsString
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.Matchers.{any, argThat}
import org.mockito.Mockito.{mock, when}
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(classOf[PowerMockRunner])
@PrepareForTest(Array {
  classOf[IOUtils]
})
class CancellationHandlerTest {

  var mockContext: Context = _
  var mockOutputStream: OutputStream = _
  var mockInputStream: InputStream = _
  var inputJsonObject: JsonObject = _
  var inputObj: JsonObject = _
  var gson: Gson = _

  @Before
  def setup() {

    gson = new Gson
    mockContext = mock(classOf[Context])
    mockOutputStream = mock(classOf[OutputStream])
    mockInputStream = mock(classOf[InputStream])

    val classLoader: ClassLoader = classOf[CancellationHandlerTest].getClassLoader

    val requestStream = classLoader.getResourceAsStream("ApiProxyRequestTemplate.json")

    val parser: JsonParser = new JsonParser

    inputObj = parser.parse(IOUtils.toString(requestStream, "UTF-8")).getAsJsonObject

    /*
    Need to power mock the static after it's use above
     */
    PowerMockito.mockStatic(classOf[IOUtils])
  }

  @Test
  def givenAppointmentWithIdWhenCancelledThenTheIdIsEchoedBack() {

    val id: String = "abcd"
    val expectedResponseMessage: String = "Cancelled appointment with id " + id

    val appointment: Appointment = new Appointment
    appointment.setAppointmentId(id)

    inputObj.remove("body")
    inputObj.addProperty("body", gson.toJson(appointment))

    /*
   Need to mock the IOUtils.toString method to return the input string as this will also be mocked. Quick reserach didn't turn
   up a way to just mock static method IOUtils.write
    */
    when(IOUtils.toString(any(classOf[InputStream]), any(classOf[String]))).thenReturn(inputObj.toString)

    val handler: CancellationHandler = new CancellationHandler
    handler.cancelAppointment(mockInputStream, mockOutputStream, mockContext)

    PowerMockito.verifyStatic()
    IOUtils.write(argThat(containsString(expectedResponseMessage)), any(classOf[OutputStream]), org.mockito.Matchers.eq("UTF-8"))
  }
}