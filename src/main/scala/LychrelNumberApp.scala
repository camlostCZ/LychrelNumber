package main.scala

import scala.annotation.tailrec
import scala.math.BigInt


object LychrelNumberApp extends LychrelData with App {
  val path = searchForPalindrome(BigInt(number), steps = max_steps)
  val result = path.head.toString
  println(s"Number:\n${result}\nSize: ${result.length}")

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
  lazy val number = 196
  lazy val max_steps = 5
}
