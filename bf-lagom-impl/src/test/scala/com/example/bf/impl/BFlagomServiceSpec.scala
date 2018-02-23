package com.example.bf.impl

import com.example.bf.api.BFlagomService
import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}

class BFlagomServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  private val server = ServiceTest.startServer(
    ServiceTest.defaultSetup
      .withCassandra()
  ) { ctx =>
    new BFlagomApplication(ctx) with LocalServiceLocator
  }

  val client = server.serviceClient.implement[BFlagomService]

}
