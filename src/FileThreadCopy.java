import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileThreadCopy implements Runnable{
private int start;
private int end;
private File folderSource;
private File folderReceiver;
private File [] filesList;

    public FileThreadCopy(int start, int end, File folderSource, File folderReceiver, File[] filesList) {
        this.start = start;
        this.end = end;
        this.folderSource = folderSource;
        this.folderReceiver = folderReceiver;
        this.filesList = filesList;
    }
    public void copyFile(File in, File out) throws IOException{
        try (FileInputStream inputStream = new FileInputStream(in); FileOutputStream outputStream = new FileOutputStream(out)){
            byte[] byteArray = new byte[1024];
            int readByte = 0;
            for (;(readByte = inputStream.read(byteArray))> 0;){
                outputStream.write(byteArray, 0, readByte);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++){
            File in = filesList[i];
            File out =  new File(folderReceiver.getPath() + "\\" + in.getName());
            try {
                copyFile(in,out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "FileThreadCopy{" +
                "start=" + start +
                ", end=" + end +
                ", folderSource=" + folderSource +
                ", folderReceiver=" + folderReceiver +
                '}';
    }
}
