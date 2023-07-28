import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File source = new File("source");
        File receiver = new File("receiver");
        source.mkdirs();
        receiver.mkdirs();
        int cores = 4;
        File [] fileList = source.listFiles();
        for (File file : fileList){
            source = file.getParentFile();
        }
        int size = fileList.length / cores;
        for (int i = 1; i < cores; i++){
            int start = size * i;
            int end = start + size;
            FileThreadCopy fileThreadCopy = new FileThreadCopy(start,end,source,receiver,fileList);
            fileThreadCopy.copyFile(source,receiver);
            System.out.println(fileThreadCopy );
            fileThreadCopy.run();
        }
    }
}
