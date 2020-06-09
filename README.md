# Smart 401k Calculator

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![My badge](https://img.shields.io/badge/Ming--Yuan-Lu-success)

Ever wondered whether a traditional or Roth 401k account is more valuable to you? Here they are compared in 10->20->30->40 years time to give you an idea which account works best for you.

## Requirements

To build from source, you need

[Scala](https://www.scala-lang.org/) 2.13.2+  
[Sbt](https://www.scala-sbt.org/) 1.3.10+  
[ScalaTest](https://www.scalatest.org/) 3.1.2+  

## Usage

### Run with Compiled Executable
Download the `zip` file from the [release page](https://github.com/mingyuanlu/smart-401k-calculator/releases/tag/v1.0.0). Decompress, and execute the compiled artifact in `bin` as
```
$ ./smart401kcalculator
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

Clone this repo. Build the Scala project. I suggest using [sbt](https://www.scala-sbt.org/). In the root directory of the repo, do
```
$ sbt compile
[info] Loading settings for project test401kcal-build from plugins.sbt ...
[info] Loading project definition from /Users/ming-yuanlu/Documents/test401kCal/project
[info] Loading settings for project smart401kCalculator from build.sbt ...
[info] Set current project to Smart401kCalculator (in build file:/Users/ming-yuanlu/Documents/test401kCal/)
[info] Executing in batch mode. For better performance use sbt's shell
[success] Total time: 6 s, completed Jun 9, 2020, 2:00:32 PM
```

After the compilation succeeded, run with 

```
$ sbt run
[info] Loading settings for project test401kcal-build from plugins.sbt ...
[info] Loading project definition from /Users/ming-yuanlu/Documents/test401kCal/project
[info] Loading settings for project smart401kCalculator from build.sbt ...
[info] Set current project to Smart401kCalculator (in build file:/Users/ming-yuanlu/Documents/test401kCal/)
[info] running smart401kCalculator.Smart401kCalculator
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
[success] Total time: 74 s (01:14), completed Jun 9, 2020, 2:02:42 PM
```

### Links for Tax Rates

You can get your effective tax rate [here](https://smartasset.com/taxes/income-taxes)

## License 

[MIT](https://github.com/mingyuanlu/smart-401k-calculator/blob/master/LICENSE) License