package earthquake.xml;

import earthquake.entity.QuakeEntry;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.function.Consumer;

public class QuakeXMLLoader {
    public static void load(String uri, Consumer<QuakeEntry> entryProcessor) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            QuakeEntryHandler entryHandler = new QuakeEntryHandler(entryProcessor);
            saxParser.parse(uri, entryHandler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
