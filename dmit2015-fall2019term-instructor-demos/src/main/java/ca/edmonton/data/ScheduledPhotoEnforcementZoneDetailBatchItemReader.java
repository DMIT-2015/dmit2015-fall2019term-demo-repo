package ca.edmonton.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ScheduledPhotoEnforcementZoneDetailBatchItemReader extends AbstractItemReader {
	
	@Inject
	private JobContext jobContext;
	
	private BufferedReader reader;
		
	@Override
	public void open(Serializable checkpoint) throws Exception {
		Properties jobParametes = jobContext.getProperties();
		String inputFile = jobParametes.getProperty("input_file");
		reader = new BufferedReader(new FileReader(inputFile));
		// Skip the first line as it contains field name headers
		reader.readLine();
	}


	/**
	 * Read lines of data and store each into a String object.
	 * Once all lines have been read then return null to trigger the end of the file.
	 * 
	 */
	@Override
	public String readItem() throws Exception {
		try {
			String line = reader.readLine();
			return line;
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
