package com.dneumann.raben1.exporters;

import com.dneumann.raben1.entities.ElementList;

import java.io.IOException;

public interface ExportToFile {

    void exportToFile(ElementList list, String filename) throws IOException;
}
