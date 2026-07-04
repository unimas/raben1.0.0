package com.dneumann.raben1.importers.implementations;

import com.dneumann.raben1.entities.ElementList;
import com.dneumann.raben1.entities.Item;
import com.dneumann.raben1.importers.ImportFromFile;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImportFromXmlFile implements ImportFromFile {

    public static final String ITEMS_TAG_NAME = "items";
    public static final String ITEM_TAG_NAME = "item";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String ID_ATTRIBUTE = "id";

    @Override
    public ElementList importFromFile(String filename) throws IOException, SAXException {
        Document document = null;
        try {
            document = prepareDocument(filename);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        return parseElements(document);
    }

    private Document prepareDocument(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));

        document.getDocumentElement().normalize();
        return document;
    }

    private ElementList parseElements(Document document) {
        List<String> columns = new ArrayList<>();
        columns.add(ID_ATTRIBUTE);

        Map<String, Item> rows = new HashMap<>();
        NodeList itemsList = document.getElementsByTagName(ITEMS_TAG_NAME);

        for (int itemListNum = 0; itemListNum < itemsList.getLength(); itemListNum++) {

            Element xmlItemList = (Element) itemsList.item(itemListNum);
            columns.add(xmlItemList.getAttribute(NAME_ATTRIBUTE));

            NodeList itemList = xmlItemList.getElementsByTagName(ITEM_TAG_NAME);

            for (int itemNum = 0; itemNum < itemList.getLength(); itemNum++) {

                Element xmlItem = (Element) itemList.item(itemNum);

                String id = xmlItem.getAttribute(ID_ATTRIBUTE);
                String value = xmlItem.getTextContent().trim();
                if (itemListNum == 0) {
                    Item item = createItem(id, value);
                    rows.put(String.valueOf(item.getId()), item);
                } else {
                    Item item = rows.get(id);
                    item.setAmount(Integer.valueOf(value));
                }
            }
        }
        return new ElementList(columns, new ArrayList(rows.values()));
    }

    private @NonNull Item createItem(String id, String value) {
        Item item = new Item();
        item.setId(Integer.valueOf(id));
        item.setName(value);
        return item;
    }
}
