import java.io.*;

public class FileCopyRunnable implements Runnable{
    private File source;
    private File target;

    public FileCopyRunnable(File source, File target) {
        this.source = source;
        this.target = target;
    }
    private void copyFile(File source, File target) {
        try {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(target);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (!source.exists()) {
            System.out.println("Source file/directory does not exist.");
            return;
        }

        if (source.isFile()) {
            copyFile(source, target);
        } else if (source.isDirectory()) {
            if (!target.exists()) {
                target.mkdirs();
            }

            String[] files = source.list();
            if (files != null) {
                for (String file : files) {
                    File sourceFile = new File(source, file);
                    File targetFile = new File(target, file);

                    FileCopyRunnable fileCopyRunnable = new FileCopyRunnable(sourceFile, targetFile);
                    Thread thread = new Thread(fileCopyRunnable);
                    thread.start();
                }
            }
        }
    }
    }

