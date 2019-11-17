package co.transanalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.transanalysis.exceptions.ArgumentsNotPassedException;
import co.transanalysis.exceptions.BlankDateException;
import co.transanalysis.exceptions.DateInvalidFormatException;
import co.transanalysis.exceptions.ExtensionDoesNotExist;
import co.transanalysis.exceptions.FileDoesNotExistException;
import co.transanalysis.exceptions.FileNotAllowedException;
import co.transanalysis.messages.ExceptionMessages;
import co.transanalysis.processor.file.FileProcessor;
import co.transanalysis.processor.transaction.TransactionProcessor;
import co.transanalysis.utils.CommonUtils;
import co.transanalysis.utils.Constants;

public class TransactionAnalysisEntry {

	static TransactionProcessor processorA = new TransactionProcessor();
	static FileProcessor csvProcess  = null;
	static List<String> transactionsRaw = new ArrayList<String>();
	static Date fromDate, toDate;
	static String merchantName;

	public static void main(String[] args) throws IOException, FileDoesNotExistException, ExtensionDoesNotExist,
			FileNotAllowedException, ArgumentsNotPassedException, BlankDateException, DateInvalidFormatException {

		readUserInput();
		readFilesFromArgs(args);
		processorA.getformattedData(transactionsRaw);
		processorA.calculateAverage(fromDate, toDate, merchantName);

	}

	public static void readUserInput() throws IOException, BlankDateException, DateInvalidFormatException {

		System.out.println("Welcome to Transaction Analysis System:");
		System.out.println("Please input the details:");
		System.out.println("Note: Allowed date format is " + Constants.TRANSACTION_DATE_FORMAT);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("fromDate: ");
			fromDate = CommonUtils.stringToDateConvert(br.readLine());
			System.out.println("toDate: ");
			toDate = CommonUtils.stringToDateConvert(br.readLine());
			System.out.println("merchant: ");
			merchantName = br.readLine().toUpperCase();
		}

	}

	public static void readFilesFromArgs(String[] args) throws ArgumentsNotPassedException, ExtensionDoesNotExist,
			FileNotAllowedException, IOException, FileDoesNotExistException {

		if (args.length < 1) {
			throw new ArgumentsNotPassedException(ExceptionMessages.CSV_FILES_MISSING);
		}
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
			csvProcess = new FileProcessor(args[i]);
			transactionsRaw.addAll(csvProcess.readData());
		}

	}

}
