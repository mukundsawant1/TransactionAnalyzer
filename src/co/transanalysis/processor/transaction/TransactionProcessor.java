package co.transanalysis.processor.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.transanalysis.business.TransactionAnalyzer;
import co.transanalysis.exceptions.BlankDateException;
import co.transanalysis.exceptions.DateInvalidFormatException;
import co.transanalysis.format.TransactionDataFormatter;
import co.transanalysis.model.Transaction;
import co.transanalysis.vo.StatisticsVo;

public class TransactionProcessor {

	private TransactionDataFormatter dataFormatterA = null;
	private List<Transaction> transactionList = null;
	private TransactionAnalyzer anlyzerA = null;
	private StatisticsVo transactionStats=null;
	
	public TransactionProcessor() {
		
	    dataFormatterA =  new TransactionDataFormatter();
		transactionList = new ArrayList<Transaction>();
		transactionStats= new StatisticsVo();
		
	}

	public void getformattedData(List<String> transactionsRaw) throws BlankDateException, DateInvalidFormatException {

		transactionList = dataFormatterA.getAllTransactions(transactionsRaw);

	}

	public StatisticsVo calculateAverage(Date fromDate, Date toDate, String merchant) throws BlankDateException, DateInvalidFormatException {

		anlyzerA = new TransactionAnalyzer(transactionList);
		transactionStats = anlyzerA.calculateAverage(fromDate, toDate, merchant);
		
		return transactionStats;

	}

}
