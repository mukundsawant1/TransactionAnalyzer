package co.transanalysis.processor.file;


import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.Test;
import co.transanalysis.exceptions.ExtensionDoesNotExist;
import co.transanalysis.exceptions.FileDoesNotExistException;
import co.transanalysis.exceptions.FileNotAllowedException;

public class FileProcessorTest {

	@Test
	public void isAbleToReadDataFromFile() throws ExtensionDoesNotExist, FileNotAllowedException, IOException, FileDoesNotExistException {
		FileProcessor fp= new FileProcessor("./source/csv1.csv");
		assertTrue(fp.readData().size() > 0 );
	}
	
}
