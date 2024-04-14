import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShowTimes {

    private String monthDay;
    private String hourMinute;
    private boolean isItevening;


    public ShowTimes(String monthDay, String hourMinute, boolean isItevening) {
        this.monthDay = monthDay;
        this.hourMinute = hourMinute;
        this.isItevening = isItevening;
    }

    public void addDateToMovieFile(int count){

        try {

            if(isItevening) {
                FileWriter eveningTime = new FileWriter("D:\\Cinema project\\movies\\movie-"+count+"\\evening Show Time.txt");
                eveningTime.write( monthDay+ "at  :"+hourMinute+ "pm"+"\n" );
                eveningTime.flush();
                eveningTime.close();
            }else {
                FileWriter morningTime = new FileWriter("D:\\Cinema project\\movies\\movie-"+count+"\\morning Show Time.txt");
                morningTime.write(monthDay+ "at  :"+hourMinute+ "am"+"\n" );
                morningTime.flush();
                morningTime.close();
            }



        }catch (IOException e ){
            System.out.println(e.getMessage());
        }

    }
}
