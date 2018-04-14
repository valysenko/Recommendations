import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class FoobarDirectoryIteration {


    public void iterateOverDirectory(File directory) throws IOException{

        File[] fileList = directory.listFiles();
        String path = "collection/";
        for (File file : fileList) {

            if(file.isDirectory()){
                //Do something with nested directory, such as recursively iterating over it
                System.out.println("SubDirectory : " + file.getName());
                iterateOverDirectory(file);
            }else{
                Files.move(file.toPath(),
                        (new File(path + file.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);

//                Files.copy(file.toPath(),
//                        (new File(path + file.getName())).toPath(),
//                        StandardCopyOption.REPLACE_EXISTING);
                //Do something with file
                System.out.println("File : " + file.getName());
            }

        }
    }


    public static void main(String[] args) throws IOException {

        String pathToDir = "documents/tmp";
        File directory = new File(pathToDir);

        FoobarDirectoryIteration foobar = new FoobarDirectoryIteration();
        foobar.iterateOverDirectory(directory);

    }

}
