package com.spacecorpshandbook.ostium.lambda.proxy

import java.util.Map

import scala.beans.BeanProperty

/**
  * Created by justin on 12/5/16.
  */
class Headers {

  @BeanProperty
  var headers: Map[String, String] = null

}
