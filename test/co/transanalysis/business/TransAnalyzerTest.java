package co.transanalysis.business;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import co.transanalysis.exceptions.BlankDateException;
import co.transanalysis.exceptions.DateInvalidFormatException;
import co.transanalysis.model.Transaction;
import co.transanalysis.utils.CommonUtils;
import co.transanalysis.vo.StatisticsVo;

public class TransAnalyzerTest {

	List<Transaction> transactions = new ArrayList<Transaction>();

	@Before
	public void initiateTransactions() throws BlankDateException, DateInvalidFormatException {
		System.out.println("Before");
		Transaction t1 = new Transaction();

		t1.setId("ABCDEF");
		t1.setDate(CommonUtils.stringToDateConvert("20/08/2018 00:00:01"));
		t1.setAmount(Double.parseDouble("12.00"));
		t1.setTransactionType("PAYMENT");
		t1.setMerchant("MUKASHI");
		try {
			t1.setRelatedTransaction("");
		} catch (ArrayIndexOutOfBoundsException E) {
		}

		transactions.add(t1);

		Transaction t2 = new Transaction();

		t1.setId("ABCDEF");
		t1.setDate(CommonUtils.stringToDateConvert("20/08/2018 10:23:01"));
		t1.setAmount(Double.parseDouble("24.00"));
		t1.setTransactionType("PAYMENT");
		t1.setMerchant("MUKASHI");
		try {
			t1.setRelatedTransaction("");
		} catch (ArrayIndexOutOfBoundsException E) {
		}

		transactions.add(t2);

		Transaction t3 = new Transaction();

		t1.setId("ABCDEF");
		t1.setDate(CommonUtils.stringToDateConvert("20/08/2018 11:22:01"));
		t1.setAmount(Double.parseDouble("36.00"));
		t1.setTransactionType("PAYMENT");
		t1.setMerchant("MUKASHI");
		try {
			t1.setRelatedTransaction("");
		} catch (ArrayIndexOutOfBoundsException E) {
		}
		transactions.add(t3);

	}

	@Test
	public void testNumberOfTransaction() throws BlankDateException, DateInvalidFormatException {
		System.out.println("Test");
		StatisticsVo transactionStats = new StatisticsVo();
		Date fromDate = CommonUtils.stringToDateConvert("20/08/2018 00:00:01");
		Date toDate = CommonUtils.stringToDateConvert("20/08/2018 23:59:00");
		String merchant = "Mukashi";
		TransactionAnalyzer analyzerA = new TransactionAnalyzer(transactions);
		transactionStats = analyzerA.calculateAverage(fromDate, toDate, merchant);
		System.out.println(transactionStats);

	}

}
