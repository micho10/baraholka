package com.example.bf.model

trait FundingContract {

  def rate: Float
  def amount: Float
  def period: Float
  def timestamp: Double
  def frr: Frr

}
