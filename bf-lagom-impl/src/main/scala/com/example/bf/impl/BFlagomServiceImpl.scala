package com.example.bf.impl

import com.example.bf.api.BFlagomService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry

/**
  * Implementation of the BFlagomService.
  */
class BFlagomServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends BFlagomService {

  override def sayBF: ServiceCall[String, String] = ???

}
