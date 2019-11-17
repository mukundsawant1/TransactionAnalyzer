package co.transanalysis.format;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import co.transanalysis.exceptions.BlankDateException;
import co.transanalysis.exceptions.DateInvalidFormatException;
import co.transanalysis.model.Transaction;
import co.transanalysis.utils.CommonUtils;
import co.transanalysis.utils.Constants;
import co.transanalysis.utils.functions.ExceptionThrowingFunction;

public class TransactionDataFormatter {

	private List<Transaction> transactions = null;

	public TransactionDataFormatter() {

		transactions = new ArrayList<Transaction>();

	}

	public List<Transaction> getAllTransactions(List<String> transactionsDataList) {

		return transactionsDataList.stream().map(line -> line.split(Constants.FILE_SEPERATOR))
				.map(ExceptionThrowingFunction.uncheckedThrow(this::toTransaction)).collect(Collectors.toList());

	}

	private Transaction toTransaction(String[] transactionData) throws BlankDateException, DateInvalidFormatException {

		Transaction transactionDto = new Transaction();
		transactionDto.setId(transactionData[0].trim().toUpperCase());
		transactionDto.setDate(CommonUtils.stringToDateConvert(transactionData[1].trim()));
		transactionDto.setAmount(Double.parseDouble(transactionData[2].trim()));
		transactionDto.setTransactionType(transactionData[4].trim().toUpperCase());
		transactionDto.setMerchant(transactionData[3].trim().toUpperCase());
		try {
			transactionDto.setRelatedTransaction(transactionData[5].trim().toUpperCase());
		} catch (ArrayIndexOutOfBoundsException E) {
		}
		return transactionDto;
	}

}
