package exceptions

 
//class InvalidArgumentValue extends Exception

/** Exception to be thrown when the dollar amount provided is invalid.
 *  Typical reason: can't convert provided String to Double
 */
class InvalidDollarAmountException extends Exception

/** Exception to be thrown when the rate provided is invalid.
 *  Typical reason: can't convert provided String to Double 
 */
class InvalidRateException extends Exception

/** Exception to be thrown when the number of years provided is invalid.
 *  Typical reason: can't convert provided String to Double 
 */
class InvalidNYearsException extends Exception

/** Exception to be thrown when the contribution to the 401k account is more than the set limit. */
class ContributionOverMaxException extends Exception

/** Exception to be thrown when the number of arguments provided to the app is insufficient. */
class InsufficientArgumentException extends Exception
