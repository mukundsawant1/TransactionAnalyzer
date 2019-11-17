# TransactionAnalyzer
Financial transaction analysis system
-------------------------------------------------------------------
The goal of the system is to display statistic information about processed transactions.

A transaction record will contain the following fields:
ID - A string representing the transaction id
Date - The date and time when the transaction took place (format "DD/MM/YYYY hh:mm:ss")
Amount - The value of the transaction (dollars and cents)
Merchant - The name of the merchant this transaction belongs to
Type - The type of the transaction, which could be either PAYMENT or REVERSAL
Related Transaction - In the case of a REVERSAL transaction, this field will contain the ID of the transaction it is reversing.

#The Problem
-----------------------------
The system will be Initialised with an input file in CSV format containing a list of transaction records.

Once initialised, the system should report the total number of transactions and the average transaction value for a specific merchant in a specific date range.

An additional requirement is that, if a transaction record has a REVERSAL transaction, then it should not be included in the computed statistics, even if the reversing transaction is outside of the requested date range.

#Input CSV Example:
-----------------------------
ID, Date, Amount, Merchant, Type, Related Transaction
WLMFRDGD, 20/08/2018 12:45:33, 59.99, Kwik-E-Mart, PAYMENT,
YGXKOEIA, 20/08/2018 12:46:17, 10.95, Kwik-E-Mart, PAYMENT,
LFVCTEYM, 20/08/2018 12:50:02, 5.00, MacLaren, PAYMENT,
SUOVOISP, 20/08/2018 13:12:22, 5.00, Kwik-E-Mart, PAYMENT,
AKNBVHMN, 20/08/2018 13:14:11, 10.95, Kwik-E-Mart, REVERSAL, YGXKOEIA
JYAPKZFZ, 20/08/2018 14:07:10, 99.50, MacLaren, PAYMENT,

#Given the above CSV file and the following input arguments:
-----------------------------
fromDate: 20/08/2018 12:00:00
toDate: 20/08/2018 13:00:00
merchant: Kwik-E-Mart

#The output will be:
-----------------------------
Number of transactions = 1
Average Transaction Value = 59.99

#Assumptions
-----------------------------
For the sake of simplicity, you can assume that Transaction records are listed in correct time order.
The input file is well formed and is not missing data.

#Considerations
-----------------------------
From text file , only PAYMENT and REVERSAL type of transactions will only be coming in file.

Transaction id will be unique in input file

ParallelStream has been used by considering that large csv files can be an input for processing

Application.properties could have been used to declare some of the values rather than using constant file.

Multiple CSV files are allowed , they need to be passed as arguments.

Blanks line will be skipped from input CSV

Any wrong data will not be allowed

Loose coupling has not been provided by using interfaces as project is not much complex to maintain after finishing development.

Program will exit if input data format is not valid.


#Improvement
-----------------------------
FileValidator class could have been added separately to valid the data if we do not assume about data is correct always.

#Instructions to run the program
-----------------------------

1) Clone the project from Git Repository
2) For testing purpose , hamcrest-all-1.3.jar and junit-4.12.jar files need to add in   build path.
3) TransactionAnalysisEntry is the entry point of program which exists in package co.transanalysis
4) Minimum one argument must be passed as csv files to get processed by the program , 
Multiple csv files are allowed as a argument.
