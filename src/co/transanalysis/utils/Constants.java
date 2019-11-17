package co.transanalysis.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {

public static final List<String> FILE_TYPES=Stream.of("csv").collect(Collectors.toList()) ;
public static final String TRANSACTION_DATE_FORMAT="dd/MM/yyyy HH:mm:ss";
public static final String NUMBER_FORMAT="#.##";
public static final String FILE_SEPERATOR=",";

}
