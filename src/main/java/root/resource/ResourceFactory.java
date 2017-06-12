package root.resource;

import root.base.Resource;

/**
 * Created by Alexey on 12.06.2017.
 */
public class ResourceFactory implements Resource {

    private static ResourceFactory resourceFactory;
    private static Object locker = new Object();

    private ResourceFactory(){
    }

    public static ResourceFactory getInstance() {
        if(resourceFactory == null) {
            synchronized (locker) {
                if(resourceFactory == null) {
                    resourceFactory = new ResourceFactory();
                }
            }
        }
        return resourceFactory;
    }

    @Override
    public Object getResourceFromXML(String xmlFile) {
        Object object = null;
        try {
            object = ReadXMLFileSAX.readXML(xmlFile);
        } catch (Exception e) {
            //todo: log("Exception in -->" + this.getClass() + "-->workWithSAX()-->ReadXMLFileSAX.readXML("*.xml"). Stack: " + e)
        }
        return object;
    }
}
