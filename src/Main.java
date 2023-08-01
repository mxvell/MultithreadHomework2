import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       int cores = 4;
       String source = ".\\source";
       String receive = "receiver";
//       File sourceDirectory = new File(source);
//       File[] fileList = sourceDirectory.listFiles();
//       int size = fileList.length / cores;
//     long StartTime =  System.currentTimeMillis();
//       for (int i = 0; i < cores -1 ; i++){
//           int start = size * i;
//           int end  = start + size;
//           Thread threadCopy = new Thread(new FileThreadCopy(start,end,source,receive,fileList));
//
//           threadCopy.start();
//
//       }
//       long EndTime = System.currentTimeMillis();
//        System.out.println("Time " + (EndTime - StartTime));
//



        File sourceFile = new File(source);
        File targetDir = new File(receive);

        FileCopyRunnable fileCopyRunnable = new FileCopyRunnable(sourceFile, targetDir);
        Thread thread = new Thread(fileCopyRunnable);
        thread.start();
    }
}
