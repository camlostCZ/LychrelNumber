package main.scala

import scala.annotation.tailrec


object LychrelNumberApp extends LychrelData with App {
  val path = searchForPalindrome(LargeInteger(number.toString), steps = max_steps)
  val result = path.head.toString
  println(s"Number:\n${result}\nSize: ${result.length}")

  @tailrec
  def searchForPalindrome(number: LargeInteger, path: List[LargeInteger] = Nil, steps: Int = 10): List[LargeInteger] = {
    val revNumber = number.value.reverse
    val newPath = number :: path
    if (steps == 1 || number.value == revNumber)
      newPath
    else
      searchForPalindrome(number + revNumber, newPath, steps - 1)
  }
}

trait LychrelData {
  lazy val number = 196
  lazy val max_steps = 500000
}
