# Smart 401k Calculator

Ever wondered whether a traditional or Roth 401k account is more valuable to you? Here they are compared in 10->20->30->40 years time to give you an idea which account works best for you.

## Requirements

To build from source, you need

[https://www.scala-lang.org/ Scala] 2.13.2+

[https://www.scala-sbt.org/ Sbt] 1.3.10+

[https://www.scalatest.org/ Scalatest] 3/1/2+

## Usage

### Run with Compiled Executable
Download the `zip` file from the release page. Decompress, and execute the compiled artifact in `bin` as
```
./smart401kcalculator
Annual income ($, default $57,000):
Contribution to 401k ($, default $19,500):
Tax rate now for traditional 401k (%, default 18%):
Tax rate now for Roth 401k (%, default 25%):
Tax rate at retirement/withdrawal (%, default 20%):
401k account interest rate for (%, default 7%):
Inflation rate (%, default 2%):
Data:
Income:                             $57000
Contribution:                       $19500
Tax Rate Now (traditional):         18%
Tax Rate Now (Roth):                25%
Tax Rate at Retirement/Withdrawal:  20%
Interest Rate:                      7 %
Inflation Rate:                     2 %
Traditional 401k Account:  Cash now + 401k Amount in N years
**10yr:  $55823.972  **20yr:  $71051.542  **30yr:  $95526.906  **40yr:  $134866.302
Roth        401k Account:  Cash now + 401k Amount in N years
**10yr:  $54592.465  **20yr:  $73626.928  **30yr:  $104221.132  **40yr:  $153395.377
```

### Build from Source

Clone this repo. Build the Scala project. I suggest using sbt 

### Links for Tax Rates


## License 