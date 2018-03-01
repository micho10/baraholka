package com.example.bf.request

/**
  * @param limitBids Not required. Default value is 50. Limit the number of funding bids returned.
  *                   May be 0 in which case the array of bids is empty.
  * @param limitAsks Not required. Default value is 50. Limit the number of funding offers returned.
  *                   May be 0 in which case the array of asks is empty.
  */
case class FundingBookRequest(
                               limitBids: Int = 50,
                               limitAsks: Int = 50
                             )
