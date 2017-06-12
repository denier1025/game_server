package root.resource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Alexey on 12.06.2017.
 */
public class ReadXMLFileSAX {

    public static Object readXML(String xmlFile) throws Exception {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            saxParser.parse(xmlFile, handler);
            return handler.getObject();
        } catch(Exception e) {
            throw new Exception(e);
        }
    }

}
