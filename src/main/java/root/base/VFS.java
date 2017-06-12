package root.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Alexey on 10.06.2017.
 */
public interface VFS {

    boolean isExist(String path);

    boolean isDirectory(String path);

    String getAbsolutePath(String file);

    byte[] getBytes(String file) throws IOException;

    String getUTF8Text(String file) throws IOException;

    Iterator<String> getIterator(String startDir);

}
