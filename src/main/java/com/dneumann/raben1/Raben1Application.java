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

	public static void main(String[] args) {
		ImportFromFile xml = new ImportFromXmlFile();
        ExportToFile csvWriter = new ExportToCsvFile();
        try {
            ElementList list = xml.importFromFile(args[0]);
            csvWriter.exportToFile(list, args[1]);
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        System.out.println("exported");
	}

}
