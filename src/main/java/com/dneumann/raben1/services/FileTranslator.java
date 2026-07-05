package com.dneumann.raben1.services;

import com.dneumann.raben1.exporters.ExportToFile;
import com.dneumann.raben1.importers.ImportFromFile;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;

@Service
public class FileTranslator {
    ImportFromFile importer;
    ExportToFile exporter;

    public FileTranslator(ImportFromFile importer, ExportToFile exporter) {
        this.importer = importer;
        this.exporter = exporter;
    }

    public void translateFile(String input, String output) throws IOException, SAXException {
        exporter.exportToFile(importer.importFromFile(input), output);
    }

}
