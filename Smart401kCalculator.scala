// Smart 401k Calculator - Ming-Yuan Lu
import exceptions._

object Smart401kCalculator extends App {
   val usage = "scala Smart401kCalculator <Pretax income> <Current effective tax rate (%)> <Effective tax rate at retirement (%)> <Number of years to retirement
   \n <Interest rate (%, default 5)> <Inflation (%, default 2)>"
   if ( args.length != 6 ) {
      println(usage) 
      throw insufficientArgumentException
   }


}