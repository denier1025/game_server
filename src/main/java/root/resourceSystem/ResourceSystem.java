package root.resourceSystem;

import root.items.Hummer;

import java.io.*;

/**
 * Created by Alexey on 11.06.2017.
 */
public class ResourceSystem {

    public static void writeToBinFile(String filePath, Object serObject) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(serObject);
        } catch(IOException e) {
            throw new IOException(e);
        }
    }

    public static Object readFromBinFile(String filePath) throws Exception {
        Object object;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            object = ois.readObject();
        } catch(IOException | ClassNotFoundException e) {
            throw new Exception(e);
        }
        return object;
    }

    public static Object workWithSAX(String xmlFile) {
        Object object = null;
        try {
            object = ReadXMLFileSAX.readXML(xmlFile);
        } catch (Exception e) {
            //todo: log("Exception in -->" + this.getClass() + "-->workWithSAX()-->ReadXMLFileSAX.readXML("*.xml"). Stack: " + e)
        }
        return object;
    }

}
