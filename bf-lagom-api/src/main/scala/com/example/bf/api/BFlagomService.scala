package com.example.bf.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * The BF-lagom service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and consume the BFlagomService.
  * <p>
  * This interface not only defines how the service is invoked and implemented, it also defines the metadata that describes
  * how the interface is mapped down onto an underlying transport protocol. Generally, the service descriptor, its implementation
  * and consumption should remain agnostic to what transport is being used, whether that’s REST, websockets, or some other
  * transport.
  */
trait BFlagomService extends Service {

  /* ******** Name descriptor ******** */
  def sayBF: ServiceCall[String, String]

  override final def descriptor = {
    import Service._
    named("bf").withCalls(
      call(sayBF)
      // Custom name for the call identifier
//      namedCall("bf", sayBF)
    )
  }


  /* ******** Path descriptor ******** */
//  def getOrder(orderId: Long): ServiceCall[NotUsed, Order]
//
//  override def descriptor = {
//    import Service._
//    named("orders").withCalls(
//      pathCall("/order/:id", getOrder _)
//    )
//  }
//
//  /* Multiple parameters are possible. They'll be passed to the service call method in the order they're extracted from the URL */
//  def getItem(orderId: Long, itemId: String): ServiceCall[NotUsed, Item]
//
//  override def descriptor = {
//    import Service._
//    named("orders").withCalls(
//      pathCall("/order/:orderId/item/:itemId", getItem _)
//    )
//  }
//
//  /* Query string parameters can also be extracted from the path, using a & separated list after a ? at the end of the path */
//  def getItems(orderId: Long, pageNo: Int, pageSize: Int): ServiceCall[NotUsed, Seq[Item]]
//
//  override def descriptor = {
//    import Service._
//    named("orders").withCalls(
//      pathCall("/order/:orderId/items?pageNo&pageSize", getItems _)
//    )
//  }


  /* ******** REST identifiers ******** */
//  def addItem(orderId: Long): ServiceCall[Item, NotUsed]
//  def getItem(orderId: Long, itemId: String): ServiceCall[NotUsed, Item]
//  def deleteItem(orderId: Long, itemId: String): ServiceCall[NotUsed, NotUsed]
//
//  def descriptor = {
//    import Service._
//    import com.lightbend.lagom.scaladsl.api.transport.Method
//    named("orders").withCalls(
//      restCall(Method.POST,   "/order/:orderId/item",         addItem _),
//      restCall(Method.GET,    "/order/:orderId/item/:itemId", getItem _),
//      restCall(Method.DELETE, "/order/:orderId/item/:itemId", deleteItem _)
//    )
//  }

}
