package com.spacecorpshandbook.ostium.spring

import org.springframework.boot.builder.SpringApplicationBuilder

object CancellationApp {

  def main(args: Array[String]) {

    /*
      TODO: don't think args.toString accomplishes what I need but it does fix the compile error for now
     */
    new SpringApplicationBuilder(classOf[CancellationAppConfig]).web(true).run(args.toString)
  }
}