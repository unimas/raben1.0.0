package com.dneumann.raben1.exporters.implementations;

import com.dneumann.raben1.entities.ElementList;
import com.dneumann.raben1.entities.Item;
import com.dneumann.raben1.exporters.ExportToFile;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class ExportToCsvFile implements ExportToFile {

    public static final String CSV_SEPARATOR = ",";

    @Override
    public void exportToFile(ElementList list, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writeColumnNamesToFile(list, writer);

            writeValuesToFile(list, writer);
        }
    }

    private void writeColumnNamesToFile(ElementList list, PrintWriter writer) {
        boolean firstColumn = true;
        for (String column : list.getColumnOrder()) {
            String separator = firstColumn ? "" : CSV_SEPARATOR;
            writer.print(separator + escapeCsv(column));
            firstColumn = false;
        }
        writer.println();
    }

    private void writeValuesToFile(ElementList list, PrintWriter writer) {
        for (Item item : list.getElements()) {
            writer.print(escapeCsv(String.valueOf(item.getId())));
            item.getValues().stream().forEach((value) -> writer.print(CSV_SEPARATOR + escapeCsv(value)));
            writer.println();
        }
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }

        if (value.contains(CSV_SEPARATOR) || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }

        return value;
    }
}
