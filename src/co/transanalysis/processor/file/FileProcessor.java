package co.transanalysis.processor.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.transanalysis.exceptions.ArgumentsNotPassedException;
import co.transanalysis.exceptions.ExtensionDoesNotExist;
import co.transanalysis.exceptions.FileDoesNotExistException;
import co.transanalysis.exceptions.FileNotAllowedException;
import co.transanalysis.messages.ExceptionMessages;
import co.transanalysis.utils.CommonUtils;

public class FileProcessor {

	private String fileName;

	public FileProcessor(String fileName) {
		this.fileName = fileName;
	}

	public List<String> readData()
			throws ExtensionDoesNotExist, FileNotAllowedException, IOException, FileDoesNotExistException {

		String fileName = this.fileName;
		List<String> transactions = new ArrayList<String>();
		final File transactionFile = new File(fileName);
		Optional<String> fileExtension = CommonUtils.getExtensionFromFileName(fileName);

		if (transactionFile.isFile() && CommonUtils.isAllowedExtension(fileExtension) && CommonUtils.isFileNotEmpty(transactionFile)) {
			try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
				transactions = br.lines().skip(1).filter(line -> !line.isEmpty()).collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return transactions;
	}

}
