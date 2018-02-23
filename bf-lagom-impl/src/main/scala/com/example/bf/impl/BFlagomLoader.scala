package com.example.bf.impl

import com.example.bf.api.BFlagomService
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

class BFlagomLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new BFlagomApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new BFlagomApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[BFlagomService])
}

abstract class BFlagomApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[BFlagomService](wire[BFlagomServiceImpl])

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = BFlagomSerializerRegistry

//  // Register the hello-lagom persistent entity
//  persistentEntityRegistry.register(wire[BFlagomEntity])
}
