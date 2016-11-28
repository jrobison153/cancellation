package com.spacecorpshandbook.ostium.spring.app

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@ComponentScan(basePackages = Array("com.spacecorpshandbook.ostium.spring"))
@EnableAutoConfiguration
@EnableEurekaClient
class CancelConfig

object CancellationApp {

  def main(args: Array[String]) {

    new SpringApplicationBuilder(classOf[CancelConfig]).web(true).run(args.toString)
  }
}