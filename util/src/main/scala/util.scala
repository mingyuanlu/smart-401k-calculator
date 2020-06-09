package util

import exceptions._
import constants._
/** Contains utility functions.*/
object Utilities {

   /** Alias for `scala.math.pow` method. */
   val exp = scala.math.pow _
   //class InvalidDollarAmountException extends Exception

   /** Checks if the number of arguments passed to app is 7.
    *  @param args The array of arguments
    *  @throws [[exceptions.InsufficientArgumentException]]
    *  @return `Unit`
    */
   @deprecated
   def checkArguments(args:Array[String]):Unit = {
     if (args.length != 7) throw new InsufficientArgumentException
   }

   /** Checks if the income provided is valid.
    *  @param arg String representing the income amount
    *  @throws [[exceptions.InvalidDollarAmountException]] if `arg` is not a valid dollar amount
    *  @return `Unit`
    */ 
   @deprecated
   def checkIncome(arg:String):Unit = {
      try {
         val dollar = arg.toDouble
         if (dollar < 0) { throw new InvalidDollarAmountException}
      } catch {
         case _:Exception => throw new InvalidDollarAmountException 
      }
   }

   /** Checks if the contribution to the 401k account is valid.
    * @param arg String representing the contribution amount.
    * @throws [[exceptions.InvalidDollarAmountException]] if `arg` is not a valid dollar amount
    * @throws [[exceptions.ContributionOverMaxException]] is the contribution is over the limit set by [[constants.Constants.MaxContribution]]
    * @return `Unit`
    */
   @deprecated
   def checkContributionAmount(arg:String):Unit = {
      try {
         val dollar = arg.toDouble
         if (dollar < 0) throw new InvalidDollarAmountException
      } catch {
         case _:Exception => { throw new InvalidDollarAmountException }
      }

      if(arg.toDouble > Constants.MaxContribution) throw new ContributionOverMaxException

   }

   /** Checks if the rate is valid.
    *  @param arg String representing the rate.
    *  @throws [[exceptions.InvalidRateException]] if `arg` is not a valid rate
    *  @return `Unit`
    */
   @deprecated
   def checkRate(arg:String):Unit = {
      try {
         val rate =  arg.toDouble * 0.01
         if (rate < 0 || rate > 1.0) throw new InvalidRateException
      } catch {
         case _:Exception => { throw new InvalidRateException }
      }
   }

   /** Checks if hte number of years is a valid number
    *  @param arg String representing the number of years
    *  @throws [[exceptions.InvalidNYearsException]]
    *  @return `Unit`
    */
   @deprecated
   def checkNYears(arg:String):Unit = {
      try {
         val nYears = arg.toDouble
         if (nYears < 0) throw new InvalidNYearsException
      } catch {
         case _:Exception => { throw new InvalidNYearsException }
      }   
   }

}

/** Enumeration of the 401k account types.*/
object AccountType extends Enumeration {
   type AccountType = Value
   val Traditional, Roth = Value
}

import AccountType._

/** Tagging trait of all 401k acounts considered. */
trait RetirementAccount {
   def getReturnInNYears(nYears:Double):Double
}

/** Class for a traditional 401k account. 
 * 
 *  Usage:
 *  {{{
 *  scala> val account = new TraditionalAccount(19500, 0.07, 0.02, 0.20)
 *  val account: util.TraditionalAccount = TraditionalAccount(19500.0,0.07,0.02,0.2)
 *  scala> account.getReturnInNYears(10)
 *  val res0: Double = 25073.971744742426
 *  }}}
 * 
 * @constructor Creates a new traditional 401k account
 * @param contributedAmount Dollar amount contributed to account
 * @param interestRate Average annual interest rate 
 * @param inflation Inflation rate
 * @param taxtRateAtWithdrawal Tax rate to be applied when money is wthdrew from account
 */
case class TraditionalAccount(val contributedAmount:Double, val interestRate:Double, val inflation:Double, val taxRateAtWithdrawal:Double) extends RetirementAccount {

   /** Computes the total value of the account in a given number of years. Includes interest, inflation, and tax at withdrawal.
    *  
    *  @param nYears Number of years
    *  @return Total dollar amount value of the account after nYears
    */
   override def getReturnInNYears(nYears:Double):Double = contributedAmount * Utilities.exp((1.0+interestRate) * (1.0 - inflation), nYears) * (1.0 - taxRateAtWithdrawal)

}

/** Class for a Roth 401k account. 
 * 
 *  Usage:
 *  {{{
 *  scala> val account = new RothAccount(19500, 0.07, 0.02)
 *  val account: util.RothAccount = RothAccount(19500.0,0.07,0.02)
 *  scala> account.getReturnInNYears(10)
 *  val res0: Double = 31342.46468092803
 *  }}}
 * 
 * @constructor Creates a new Roth 401k account
 * @param contributedAmount Dollar amount contributed to account
 * @param interestRate Average annual interest rate 
 * @param inflation Inflation rate
 */
case class RothAccount(val contributedAmount:Double, val interestRate:Double, val inflation:Double) extends RetirementAccount {

   /** Computes the total value of the account in a given number of years. Includes interest and inflation.
    *  
    *  @param nYears Number of years
    *  @return Total dollar amount value of the account after nYears
    */ 
   override def getReturnInNYears(nYears:Double):Double = contributedAmount /** (1.0-taxRateAtContribution)*/ * Utilities.exp((1.0+interestRate) * (1.0 - inflation), nYears)

}

/** Class for a financial portfolio. Currently consists of taxed cash and a 401k account.
 * 
 *  Usage
 *  {{{
 *  scala> val portfolio = new Portfolio(AccountType.Traditional, 57000, 19500, 0.18, 0.20, 0.07, 0.02)
 *  val portfolio: util.Portfolio = util.Portfolio@2522d4ba
 *  scala> portfolio.getTotalValue(10)
 *  val res3: Double = 55823.971744742434
 *  }}}
 *  @constructor Creates a new portfolio that allocates the 401k contribution amount to the retirement account, and the rest to cash.
 *  @param income Income dollar amount
 *  @param contribution Amount contributed to the 401k account
 *  @param taxRateNow Effective tax rate currently applicable to the user
 *  @param taxRateRtire Effective tax rate applicable to the user when the money will be withdrawal from the 401k account
 *  @param interestRate Average annual interest rate of the 401k account
 *  @param inflation Inflation rate
 */
class Portfolio(val accountType:AccountType, val income:Double, val contribution:Double, val taxRateNow:Double, val taxRateRetire:Double, val interestRate:Double, val inflation:Double){

/*
   //var account[T<:RetirementAccount] = None:Option[T]
   var account = None:Option[RetirementAccount]
   //var cash:Double = 0.

   if (accountType == Traditional){

      account = TraditionalAccount(contribution, interestRate, inflation, taxRateRetire)
      cash = (income - contribution) * (1.0 - taxRateNow)

   } else if (accountType == Roth) {
   //if (accountType == Roth){

      account = RothAccount(contribution, interestRate, inflation)
      cash = income * (1.0 - taxRateNow) - contribution

   } else {
      println("Invalid account type!")
      System.exit(1)
   }
*/

    /** Could be a traditional or Roth account */
   val account = {
      accountType match {
         case Traditional => TraditionalAccount(contribution, interestRate, inflation, taxRateRetire)
         case Roth => RothAccount(contribution, interestRate, inflation)          
      }
   }

   /** Take-home cash. */
   val cash:Double = { 
      accountType match {
         case Traditional => (income - contribution) * (1.0 - taxRateNow) 
         case Roth => income * (1.0 - taxRateNow) - contribution 
      }
   }

   /** Computes total value of the portfolio by combining the take-home cash and the value of the 401k account in a given number of years
    * 
    *  @param nYears Number of years
    *  @return Total dollar amount value of the portfolio
    */
   def getTotalValue(nYears:Double):Double = cash + account.getReturnInNYears(nYears)


}
