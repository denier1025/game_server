package root.resourceSystem;

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

}
