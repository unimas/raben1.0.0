package com.dneumann.raben1;

import com.dneumann.raben1.entities.ElementList;
import com.dneumann.raben1.exporters.ExportToFile;
import com.dneumann.raben1.exporters.implementations.ExportToCsvFile;
import com.dneumann.raben1.importers.ImportFromFile;
import com.dneumann.raben1.importers.implementations.ImportFromXmlFile;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import java.io.IOException;

@SpringBootApplication
public class Raben1Application {

    public static final String DEFAULT_INPUT_FILE = "input.xml";
    public static final String DEFAULT_OUTPUT_FILE = "output.csv";

    public static void main(String[] args) {
        cliExecution(args);
	}

    public static void cliExecution(String[] args) {
        String inputFile = DEFAULT_INPUT_FILE;
        String outputFile = DEFAULT_OUTPUT_FILE;

        if (args.length >= 1) {
            inputFile = args[0];
        }
        if (args.length >= 2) {
            outputFile = args[1];
        }
        ImportFromFile xml = new ImportFromXmlFile();
        ExportToFile csvWriter = new ExportToCsvFile();
        try {
            ElementList list = xml.importFromFile(inputFile);
            csvWriter.exportToFile(list, outputFile);
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        System.out.println("exported");

    }

}
