package com.spacecorpshandbook.ostium.core.model

import scala.beans.BeanProperty

/**
  * Represents the base response object from the Cancellation application
  */
class CancelResponse {

  @BeanProperty
  var message: String = ""
}
