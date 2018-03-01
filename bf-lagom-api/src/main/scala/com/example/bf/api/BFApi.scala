package com.example.bf.api

import com.example.bf.api.BitF.{get, path}
import com.example.bf.response.{StatResponse, TickerResponse}

/**
  * https://github.com/michaelgrosner/tribeca
  * https://github.com/timmolter/XChange
  *
  */

trait BFApi {

  @get("pubticker/{symbol}")
  def pubticker(@path("symbol") symbol: String): Call[TickerResponse]

  @get("stats/{symbol}")
  def stats(@path("symbol") symbol: String): Call[Array[StatResponse]]

  @get("/lendbook/{currency}")
  def lendbook(@path("symbol") symbol: String): Call[Array[StatResponse]]

}


object BitF {

  type get = retrofit2.http.GET
  type path = retrofit2.http.Path
  val API_URL = "https://api.bitfinex.com/v1/"

  /**
    * Gives innermost bid and asks and information on the most recent trade, as well as high, low and volume of the last 24 hours.
    *
    * @param symbol For example: BTCUSD.
    * @return Ticker entity.
    */
  def getTicker(symbol: String): String = {

    val retrofit = new Retrofit.Builder()
      .baseUrl(API_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    val bitfinex = retrofit.create(classOf[BitfinexApi])
    val response = bitfinex.pubticker(symbol).execute()
    " "
  }

  /**
    * Various statistics about the requested pair.
    *
    * @param symbol For example: BTCUSD.
    * @return An array of the Stat entity.
    */
  def stats(symbol: String): String = {

    val retrofit = new Retrofit.Builder()
      .baseUrl(API_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    val bitfinex = retrofit.create(classOf[BitfinexApi])
    val response = bitfinex.stats(symbol).execute()
    " "
  }

  /**
    * Get the full margin funding book.
    *
    * @param symbol For example: USD.
    * @return
    */
  def fundingbook(symbol: String): String = {

    val retrofit = new Retrofit.Builder()
      .baseUrl(API_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    val bitfinex = retrofit.create(classOf[BitfinexApi])
    val response = bitfinex.lendbook(symbol).execute()
    " "
  }

}
