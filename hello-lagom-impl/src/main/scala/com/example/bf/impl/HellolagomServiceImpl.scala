package com.example.bf.impl

import com.example.bf
import com.example.bf.api.HellolagomService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the HellolagomService.
  */
class HellolagomServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends HellolagomService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the hello-lagom entity for the given ID.
    val ref = persistentEntityRegistry.refFor[HellolagomEntity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the hello-lagom entity for the given ID.
    val ref = persistentEntityRegistry.refFor[HellolagomEntity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }


  override def greetingsTopic(): Topic[bf.api.GreetingMessageChanged] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream(HellolagomEvent.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent(helloEvent: EventStreamElement[HellolagomEvent]): bf.api.GreetingMessageChanged = {
    helloEvent.event match {
      case GreetingMessageChanged(msg) => bf.api.GreetingMessageChanged(helloEvent.entityId, msg)
    }
  }
}
