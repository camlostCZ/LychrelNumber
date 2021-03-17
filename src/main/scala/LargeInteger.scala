package main.scala

import scala.annotation.tailrec


/**
 * Defines a new integer type for operations with large integers.
 * Uses string to store its value.
 *
 * @bug Currently, only + operation is supported. No -, *, /. %.
 *
 * @param value value of the integer
 */
class LargeInteger(val value: String = "0") {

  /**
   * Provides 'add' operation.
   *
   * @param that Number to be added to this.
   * @return Sum of two numbers.
   */
  def add(that: LargeInteger): LargeInteger = {
    val size = List(this.value.length, that.value.length).max
    if (size == 0)
      new LargeInteger("0")
    else {
      val paddedThis = LargeInteger.leftPad(this.value, size, '0')
      val paddedThat = LargeInteger.leftPad(that.value, size, '0')
      new LargeInteger(add_routine(List(paddedThis.reverse, paddedThat.reverse)))
    }
  }


  /** Operator +. */
  def +(that: LargeInteger): LargeInteger = this.add(that)


  /** Operator + for string. */
  def +(s: String): LargeInteger = this.add(new LargeInteger(s))


  /**
   * Computes sum of list of numbers stored in strings.
   * Expects two number of same length in reverse order (192 should be passes as "291"). The
   * shorter numbers has to be left-padded with zeroes.
   *
   * @param numbers List of strings containing numbers
   * @param carry Number of 10s from previous operation (ex. 6 + 7 = 13, so carry is 1)
   * @param acc Accumulator string used in recursion
   * @return Sum of list of numbers
   */
  @tailrec
  private def add_routine(numbers: List[String], carry: Int = 0, acc: String = ""): String = {
    if (numbers.head.isEmpty) {
      val carryStr = if (carry > 0) carry.toString else ""
      carryStr + acc
    }
    else {
      val total = numbers.map(_.head.asDigit).sum + carry
      add_routine(numbers.map(_.tail), total / 10, (total % 10).toString + acc)
    }
  }


  /** Returns value as string. */
  override def toString: String = this.value
}


object LargeInteger {
  private def leftPad(s: String, len: Int, elem: Char): String =
    elem.toString * (len - s.length()) + s

  def apply(value: String): LargeInteger = new LargeInteger(value)
}