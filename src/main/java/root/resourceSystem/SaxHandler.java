package root.resourceSystem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import root.utils.ReflectionHelper;

/**
 * Created by Alexey on 12.06.2017.
 */
public class SaxHandler extends DefaultHandler {

    private static final String CLASSNAME = "class";
    private String element;
    private Object object;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(!qName.equals(CLASSNAME)) {
            element = qName;
        } else {
            String className = attributes.getValue(0);
            try {
                object = ReflectionHelper.createInstance(className);
            } catch (ReflectiveOperationException e) {
                //todo: log("Exception in -->" + this.getClass() + "-->startElement-->ReflectionHelper.createInstance(className). Stack: " + e)
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(element != null) {
            String value = new String(ch, start, length);
            try {
                ReflectionHelper.setFieldValue(object, element, value);
            } catch (ReflectiveOperationException e) {
                //todo: log("Exception in -->" + this.getClass() + "-->characters-->ReflectionHelper.setFieldValue(object, element, value). Stack: " + e)
            }
        }
    }

    public Object getObject() {
        return object;
    }
}
