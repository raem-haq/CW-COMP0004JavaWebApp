package uk.ac.ucl.model;

import uk.ac.ucl.model.DataFrame;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataLoader {
    private DataFrame dataFrame = new DataFrame();
    private String filePath;

    public DataLoader(String filePath){
        this.filePath = filePath;
    }

    public DataFrame load(){
        try {
            Reader in = new FileReader(filePath);
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            Iterable<CSVRecord> records = parser.getRecords();

            int numColumns = parser.getHeaderMap().size();
            List<String> headers = parser.getHeaderNames();

            for (String header : headers) {
                dataFrame.addColumn(header);
            }

            for (CSVRecord record : records) {
                for (int i = 0; i < numColumns; i++) {
                    dataFrame.addValue(headers.get(i), record.get(i));
                }
            }
            return dataFrame;
        } catch (IOException e) {
            return new DataFrame();
        }
    }

}
