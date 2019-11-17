package co.transanalysis.business;

import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.omg.IOP.TransactionService;

import co.transanalysis.exceptions.BlankDateException;
import co.transanalysis.exceptions.DateInvalidFormatException;
import co.transanalysis.model.Transaction;
import co.transanalysis.utils.CommonUtils;
import co.transanalysis.utils.TransactionTypes;
import co.transanalysis.vo.StatisticsVo;

public class TransactionAnalyzer {

	List<Transaction> transactionList = null;
	StatisticsVo transactionStats = new StatisticsVo();

	public TransactionAnalyzer(List<Transaction> transactionListA) {

		this.transactionList = transactionListA;

	}

	public StatisticsVo calculateAverage(Date fromDate, Date toDate, String merchant)
			throws BlankDateException, DateInvalidFormatException {

		Set<String> reversalCodes = getReversalCodes();

		DoubleSummaryStatistics stats = transactionList.parallelStream()
				.filter(transaction -> transaction.getTransactionType().equals(TransactionTypes.PAYMENT.toString()))
				.filter(transaction -> areDatesEqual(transaction.getDate(), fromDate)
						|| transaction.getDate().after(fromDate))
				.filter(transaction -> areDatesEqual(transaction.getDate(), toDate)
						|| transaction.getDate().before(toDate))
				.filter(transaction -> transaction.getMerchant().equalsIgnoreCase(merchant))
				.filter(transaction -> !reversalCodes.contains(transaction.getId())).mapToDouble(Transaction::getAmount)
				.summaryStatistics();

		transactionStats.setNumOfTransactions(stats.getCount());
		transactionStats.setAvaerageVal(CommonUtils.roundValue(stats.getAverage()));
		
		return transactionStats;

	}

	private boolean areDatesEqual(Date date1, Date date2) {

		if (CommonUtils.areDatesEqual(date1, date2) == 0) {
			return true;
		}
		return false;

	}

	private Set<String> getReversalCodes() {

		return transactionList.parallelStream()
				.filter(transaction -> transaction.getTransactionType().equals(TransactionTypes.REVERSAL.toString()))
				.map(Transaction::getRelatedTransaction).collect(Collectors.toSet());

	}
}
