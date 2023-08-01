import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileThreadCopy implements Runnable {
    private int start;
    private int end;
    private String folderSource;
    private String folderReceiver;
    private File[] filesList;

    public FileThreadCopy(int start, int end, String folderSource, String folderReceiver, File[] filesList) {
        this.start = start;
        this.end = end;
        this.folderSource = folderSource;
        this.folderReceiver = folderReceiver;
        this.filesList = filesList;
    }

    public static void copyDirectory(File source, File target) {
        try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target);) {
            if (source.isDirectory()) {
                if (!target.exists()) {
                    target.mkdir();
                }

                String[] files = source.list();
                if (files != null) {
                    for (String file : files) {
                        File sourceFile = new File(source, file);
                        File targetFile = new File(target, file);
                        copyDirectory(sourceFile, targetFile);
                    }
                }
            } else {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            File sourceDir = filesList[i];
            File destination = new File(folderReceiver, sourceDir.getName());
            try {
                Files.copy(sourceDir.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("error to copy source " + sourceDir.getName());
                e.printStackTrace();
            }


        }

    }
}

