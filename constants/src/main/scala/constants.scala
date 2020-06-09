package constants

/** Collects all constants. */
object Constants {

   /** Max contribution allowed for a 401k account in 2020. */
   val MaxContribution:Double = 19500.0

   /** Default annual income. */
   val DefaultIncome:Double   = 57000.0

   /** Default amount of contribution to the 401k account. */
   val DefaultContribution:Double = 19500.0

   /** Default effective tax rate when using the traditional account. */
   val DefaultTraditionalTaxRateNow:Double = 0.18

   /** Default effective tax rate when using the Roth account. */
   val DefaultRothTaxRateNow:Double = 0.25

   /** Default effective tax rate at retirement/withdrawal. */
   val DefaultTaxRateRetire:Double = 0.20

   /** Default average interest rate for the 401k account. */
   val DefaultInterestRate:Double = 0.07

   /** Default inflation rate. */
   val DefaultInflationRate:Double = 0.02
}