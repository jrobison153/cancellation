package com.spacecorpshandbook.ostium.lambda.handler

import com.spacecorpshandbook.ostium.core.model.Appointment

/**
  * Amazon Lambda handler adapter for the Cancellation application
  */
class CancellationHandler {

  def cancelAppointment(appointment: Appointment): String = {

    return "Cancelled appointment with id " + appointment.appointmentId
  }
}