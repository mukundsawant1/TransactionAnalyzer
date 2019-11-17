package co.transanalysis.utils;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

import co.transanalysis.exceptions.BlankDateException;
import co.transanalysis.exceptions.DateInvalidFormatException;
import co.transanalysis.exceptions.ExtensionDoesNotExist;
import co.transanalysis.exceptions.FileNotAllowedException;
import co.transanalysis.messages.ExceptionMessages;

public class CommonUtils {

	public static Optional<String> getExtensionFromFileName(String fileName) {

		return Optional.ofNullable(fileName).filter(file -> file.contains("."))
				.map(file -> file.substring(fileName.lastIndexOf(".") + 1));
	}

	public static boolean isAllowedExtension(Optional<String> extension)
			throws ExtensionDoesNotExist, FileNotAllowedException {

		String extensionTemp = extension.get();
		Optional.ofNullable(extensionTemp)
				.orElseThrow(() -> new ExtensionDoesNotExist(ExceptionMessages.EXTENSION_DOESNOT_EXIST));
		
		if (Constants.FILE_TYPES.contains(extensionTemp)) {
			return true;
		} else {
			throw new FileNotAllowedException(ExceptionMessages.FILE_NOT_ALLOWED);
		}

	}

	public static Date stringToDateConvert(String inputDate) throws BlankDateException, DateInvalidFormatException {

		LocalDateTime dateTime = null;
		Optional<String> inputDateA = Optional.ofNullable(inputDate);
		
		if (!inputDateA.filter(date -> !date.isEmpty()).isPresent()) {
			throw new BlankDateException(ExceptionMessages.EMPTY_DATE);
		}

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.TRANSACTION_DATE_FORMAT);
			dateTime = LocalDateTime.parse(inputDate, formatter);	
		} catch (DateTimeParseException e) {
			throw new DateInvalidFormatException(inputDate +ExceptionMessages.DATE_NOT_VALID);
		}
		return java.sql.Timestamp.valueOf(dateTime);

	}

	public static int areDatesEqual(Date date1, Date date2) {

		return date1.compareTo(date2);

	}

	public static double roundValue(double value) {

		DecimalFormat df2 = new DecimalFormat(Constants.NUMBER_FORMAT);
		return Double.parseDouble(df2.format(new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue()));

	}
	
	public static boolean isFileNotEmpty(File file) {
		 
		if(file.length()==0) {
			 return false;
		 }
		 return true;
		 
	}

}
