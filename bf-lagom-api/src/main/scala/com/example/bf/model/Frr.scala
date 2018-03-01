package com.example.bf.model

sealed trait Frr { def name: String}
case object Yes extends Frr { val name = "Yes"}
case object No extends Frr { val name = "No"}
