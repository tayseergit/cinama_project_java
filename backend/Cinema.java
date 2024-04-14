import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public interface Cinema {
    public static void addMonvieToHall(String HallName,String movieName)throws IOException {
        File hall = new File("D:\\Cinema project\\Cinema Halls\\"+HallName+"\\movies names.txt");
        FileWriter fw = new FileWriter(hall,true);
        fw.write(movieName+"\n");
        fw.flush();
        fw.close();

    }
    public  void Reset();
}
