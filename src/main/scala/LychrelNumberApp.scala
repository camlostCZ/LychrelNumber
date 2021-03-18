package main.scala

import scala.annotation.tailrec
import scala.math.BigInt


object LychrelNumberApp extends LychrelData with App {
  type ParameterMap = Map[String, String]

  val params = paramsToMap(Map(), args.toList)
  if (params.contains("INVALID")) {
    println(params("INVALID"))
    println(help())
  } else if (params.contains("number")) {
    val path = searchForPalindrome(
      BigInt(params("number")),
      steps = params.getOrElse("steps", max_steps.toString).toInt
    )
    val result = path.head
    if (params.contains("path"))
      path.reverse.foreach(println(_))
    else
      println(result)
    println(s"Size: ${result.toString.length}, steps: ${path.length}")
  }
  else
    println(help())


  @tailrec
  def paramsToMap(map: ParameterMap, lst: List[String]): ParameterMap = {
    lst match {
      case Nil => map
      case "-p" :: tail =>
        paramsToMap(map ++ Map("path" -> ""), tail)
      case "-s" :: value :: tail if value.matches("""^\d+$""") =>
        paramsToMap(map ++ Map("steps" -> value), tail)
      case value :: tail if value.matches("""^\d+$""") =>
        paramsToMap(map ++ Map("number" -> value), tail)
      case param =>
        Map("INVALID" -> s"Error: Invalid parameter ${param.mkString(", ")}.\n")
    }
  }

  def help(): String =
    """
      |Usage:
      |        LychrelNumberApp <number> [-s <max_steps>] [-p]
      |""".stripMargin

  @tailrec
  def searchForPalindrome(number: BigInt, path: List[BigInt] = Nil, steps: Int = 10): List[BigInt] = {
    val revNumberStr = number.toString.reverse
    val newPath = number :: path
    if (steps == 1 || number.toString == revNumberStr)
      newPath
    else
      searchForPalindrome(number + BigInt(revNumberStr), newPath, steps - 1)
  }
}

trait LychrelData {
  lazy val max_steps = 50
  /* HP Omen (laptop), Core i5@4.5 GHz 10th gen.: 100 000 steps ~ 19 min 59 secs */
}
