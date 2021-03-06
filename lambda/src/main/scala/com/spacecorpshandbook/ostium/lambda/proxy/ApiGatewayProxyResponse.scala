package com.spacecorpshandbook.ostium.lambda.proxy

import java.util.Map

import com.spacecorpshandbook.ostium.core.model.CancelResponse

import scala.beans.BeanProperty

/**
  * Required response structure for the AWS API Gateway
  */
class ApiGatewayProxyResponse {

  @BeanProperty
  var statusCode: String = _

  @BeanProperty
  var headers: Map[String, String] = _

  @BeanProperty
  var body: String = _

}