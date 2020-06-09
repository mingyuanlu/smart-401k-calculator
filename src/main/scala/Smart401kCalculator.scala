package smart401kCalculator
/** Provides the main source code of the calculator.
 *  ==Overview==
 *  With the distributed binary `smart401kCalculator`, in your console do
 *  {{{
 *  $ ./smart401kCalculator
 *  }}}
 *  . Follow the console prompt to enter data.
 * 
 *  With the source code, use `sbt` in the console to build
 *  {{{
 *  $ sbt clean compile
 *  $ sbt run
 *  }}}
 *  , or `compile` and `run` in the `sbt` shell. Follow the console prompt to enter data.
 */

import scala.io.StdIn._
import exceptions._
import constants._
import util._
import AccountType._

/** Main function. Prints the comparison result to the console. Returns nothing. */
object Smart401kCalculator extends App {

   /*
   val name = readLine("What's your name? ")

   val usage = "scala Smart401kCalculator <Pretax income> <Amount contributed to 401k account (max $19,500)> <Current effective tax rate (%)> <Effective tax rate at retirement (%)>  <Interest rate (%, default 5)> <Inflation (%, default 2)> <Number of years to retirement>"
   //throw new InsufficientArgumentException   
   
   try {
      Utilities.checkArguments(args)
   } catch {
      case err:InsufficientArgumentException => { println(usage); System.exit(1) }
   }

   try {
      Utilities.checkIncome(args(0))
   } catch {
      case err:InvalidDollarAmountException => {
         println("Invalid income dollar amount.")
         System.exit(1)
      }
   }
   */
   //val income = args(0).toDouble
   print("Annual income ($, default $57,000):")
   val income = try { readFloat() }
   catch { 
      case err:NumberFormatException => { if (err.getMessage == "empty String"){ Constants.DefaultIncome } else throw new NumberFormatException }
   }
   /*
   try{
      Utilities.checkContributionAmount(args(1))
      //val contribution = args(1).toDouble
   } catch {
      case err:ContributionOverMaxException => { 
         println(s"Amount contributed to 401k account can't exceed ${Constants.MaxContribution}. Set to ${Constants.MaxContribution}."); 
         //val contribution = Constants.MaxContribution
      } 
      case err:InvalidDollarAmountException => {
         println("Invalid contribution dollar amount.")
         System.exit(1)
      }
   }
   */

   print("Contribution to 401k ($, default $19,500):")
   val tempContribution = try { readFloat() }
   catch {
      case err:NumberFormatException => { if (err.getMessage() == "empty String"){ Constants.DefaultContribution } else throw new NumberFormatException }
   }
   if(tempContribution > Constants.MaxContribution) println("Contribution larger than cap. Set to cap value $19,500...")
   val contribution = if (tempContribution > Constants.MaxContribution) Constants.MaxContribution else tempContribution

   //println(s"contribution: $contribution")
   /*
   for (r <- Range(2, 6)){

      try{
         Utilities.checkRate(args(r))       
      } catch {
         case err:InvalidRateException => {
            println(s"Invalid rate: ${args(r)}%")
            System.exit(1)
         }
      }      
   }

   
   val taxRateNow    = args(2).toDouble * 0.01
   val taxRateRetire = args(3).toDouble * 0.01
   val interestRate  = args(4).toDouble * 0.01
   val inflationRate = args(5).toDouble * 0.01
   */

   print("Tax rate now for traditional 401k (%, default 18%):")
   val traditionalTaxRateNow =  try { readDouble() * 0.01 }
   catch {
      case err:NumberFormatException => { if (err.getMessage() == "empty String"){ Constants.DefaultTraditionalTaxRateNow } else throw new NumberFormatException }
   }

   print("Tax rate now for Roth 401k (%, default 25%):")
   val rothTaxRateNow = try { readDouble() * 0.01 }
   catch {
      case err:NumberFormatException => { if (err.getMessage() == "empty String"){ Constants.DefaultRothTaxRateNow } else throw new NumberFormatException }
   }

   print("Tax rate at retirement/withdrawal (%, default 20%):")
   val taxRateRetire = try { readDouble() * 0.01 }
   catch {
      case err:NumberFormatException => { if (err.getMessage() == "empty String"){ Constants.DefaultTaxRateRetire } else throw new NumberFormatException }
   }

   print("401k account interest rate for (%, default 7%):")
   val interestRate = try { readDouble() * 0.01 }
   catch {
      case err:NumberFormatException => { if (err.getMessage() == "empty String"){ Constants.DefaultInterestRate } else throw new NumberFormatException }
   }

   print("Inflation rate (%, default 2%):")
   val inflationRate = try { readDouble() * 0.01 }
   catch {
      case err:NumberFormatException => { if (err.getMessage() == "empty String"){ Constants.DefaultInflationRate } else throw new NumberFormatException }
   }

   /*
   try {
      Utilities.checkNYears(args(6))      
   } catch {
      case err:InvalidNYearsException => {
         println("Invalid number of years.")
         System.exit(1)
      }
   }

   val nYears = args(6).toDouble
   */   

   val length = 36
   println(
   (
   s"Data:\n" +
   s"%-${length}s$$%-8.0f\n" +
   s"%-${length}s$$%-5.0f\n" +
   s"%-${length}s%-2.0f%%\n" +
   s"%-${length}s%-2.0f%%\n" +
   s"%-${length}s%-2.0f%%\n" +
   s"%-${length}s%-2.0f%%\n" +
   s"%-${length}s%-2.0f%%")
   .format(
   "Income: ", income,
   "Contribution: ", contribution,
   "Tax Rate Now (traditional): ", traditionalTaxRateNow*100,
   "Tax Rate Now (Roth): ", rothTaxRateNow*100,
   "Tax Rate at Retirement/Withdrawal: ", taxRateRetire*100,
   "Interest Rate:", interestRate*100,
   "Inflation Rate:", inflationRate*100)
   )

   val numYears = Seq(10, 20, 30, 40)

   val portfolios = Seq(
   new Portfolio(Traditional, income, contribution, traditionalTaxRateNow, taxRateRetire, interestRate, inflationRate),
   new Portfolio(Roth, income, contribution, rothTaxRateNow, taxRateRetire, interestRate, inflationRate)
   )

   for(p <- portfolios){  
      println(f"${p.accountType}%-11s 401k Account:  Cash now + 401k Amount in N years") 
      //print("N  ")
      for(ny <- numYears){
         print(f"**${ny}%-2dyr:  $$${p.getTotalValue(ny)}%-3.3f  ")
      }
      println("")
   }


}