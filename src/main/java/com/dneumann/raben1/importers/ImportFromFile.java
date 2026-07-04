package com.dneumann.raben1.importers;

import com.dneumann.raben1.entities.ElementList;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface ImportFromFile {
    ElementList importFromFile(String filename) throws IOException, SAXException;
}
