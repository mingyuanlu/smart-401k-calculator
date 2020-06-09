/** Provides utility methods for internal use in the object `Utilities`. Provides the `AccountType` enumeration. Provides classes for various 401k account types. Provides the `Portfolio` class to represent the  whole financial porfolio considered for the user.
 * 
 *  ==Overview==
 *  To use the methods in the [[util.Utilities]] object:
 *  {{{
 *  scala> import util._
 *  scala> Utilities.checkArguments(args:Array[String])
 *  scala> Utilities.exp(2,4)
 *  val res0: Double = 16.0
 *  }}}
 * 
 *  An enumeration [[util.AccountType]] provides enumerations refering to each 401k account type. 
 * 
 *  A tagging trait [[util.RetirementAccount]] is provided for all account classes.
 *  Two 401k account are supported as classes - [[util.TraditionalAccount]] and [[util.RothAccount]]
 * 
 *  A class representing the financial portfolio of the user is provided as [[util.Porfolio]]. Only after-tax cash and the total 401k account value are considered now.
 */
package object util {}