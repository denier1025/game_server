package root.VFS;

import root.base.VFS;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alexey on 10.06.2017.
 */
public class VFSImpl implements VFS {

    private String root;

    public VFSImpl(String root) {
        this.root = root;
    }

    @Override
    public boolean isExist(String path) {
        return new File(path).exists();
    }

    @Override
    public boolean isDirectory(String path) {
        return new File(path).isDirectory();
    }

    @Override
    public String getAbsolutePath(String file) {
        return new File(file).getAbsolutePath();
    }

    @Override
    public byte[] getBytes(String file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            while(true) {
                int read = bis.read();
                if(read == -1) {
                    break;
                }
                baos.write(read);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public String getUTF8Text(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            BufferedWriter bw = new BufferedWriter(new StringWriter())){
            while (true) {
                String read = br.readLine();
                if (read == null) {
                    break;
                }
                bw.write(read);
            }
            return bw.toString();
        } catch(IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
        return new FileIterator(startDir);
    }

    private class FileIterator implements Iterator<String> {

        private Queue<File> files = new LinkedList<>();

        public FileIterator(String path) {
            files.add(new File(root + path));
        }

        @Override
        public boolean hasNext() {
            return !files.isEmpty();
        }

        @Override
        public String next() {
            File file = files.peek();
            if(file.isDirectory()) {
                for(File subFile : file.listFiles()) {
                    files.add(subFile);
                }
            }
            return files.poll().getAbsolutePath();
        }
    }

}
