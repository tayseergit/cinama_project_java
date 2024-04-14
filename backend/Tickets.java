import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Tickets extends movies {
    private String movieName;
    private String hostHall;
     int chairNumber;
    private String showTime;
     int movieID;
 //   private int ticketPrice;
    private int ID;
    private static int count;

    public Tickets(){}

    public Tickets(String movieName, String hostHall, int chairNumber, String showTime, int ID,int movieID) {
        this.movieName = movieName;
        this.hostHall = hostHall;
        this.chairNumber = chairNumber;
        this.showTime = showTime;
        this.ID = ID;
        this.movieID=movieID;
    }





    //this method should be called after every delete of ticket to update the files and delete the extra one...
    public void updateTicketsFiles(List<Tickets>editedTicketsList,int userID){
        try{
            int count = 1;
        for (Tickets t : editedTicketsList) {
            //uploading the show time
            BufferedWriter showTimeFile = new BufferedWriter(new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\Show time.txt"));
            showTimeFile.write(t.showTime);
            showTimeFile.flush();
            showTimeFile.close();

            //uploading the Film name

            BufferedWriter fileName2 = new BufferedWriter(new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\movie name.txt"));
            fileName2.write(t.movieName);
            fileName2.flush();
            fileName2.close();


            //uploading the chair number

            //  BufferedReader getTheTicketsNum = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-"+movieID+"\\Tickets Number.txt"));

            FileWriter setTheChairNum = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\Chair Number.txt");
            setTheChairNum.write(Integer.toString(t.chairNumber));
            setTheChairNum.flush();
            setTheChairNum.close();

            //uploading the host hall

            FileWriter setTheHostHall = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\host Hall.txt");
            setTheHostHall.write(t.hostHall);
            setTheHostHall.flush();
            setTheHostHall.close();

            //Uploading the ID...
            FileWriter IDFile = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\ID.txt");
            IDFile.write(Integer.toString(count ));
            IDFile.flush();
            IDFile.close();

            FileWriter movieIDFile = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count+"\\movieID.txt");
            movieIDFile.write(Integer.toString(t.movieID));
            movieIDFile.flush();
            movieIDFile.close();

            count++;
          //  System.out.println(count);
        }
            FileWriter updateCount = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\count.txt");
            updateCount.write(Integer.toString(count));
            updateCount.flush();
            updateCount.close();
            System.out.println(count);



            // this code from chat Gpt to delete a folder has files inside...
            Path directory = Path.of("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count);
           // System.out.println(count);
            Files.walkFileTree(directory, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

  //prints the data for every object alone ...
    public  String [] ToString() {
        String dataTickets [] =new String[6];


            dataTickets[0] = "NAME  :  " + movieName+"\n";
           // System.out.println(dataTickets[0]);
            dataTickets [1]= " Show Time  :  " +showTime +"\n";
           // System.out.println(dataTickets[1]);

            dataTickets[2]= "Host Hall  :  "+hostHall+"\n";
            // System.out.println(dataTickets[2]);

            dataTickets [3]= "Chair Number  :  "  + chairNumber+"\n";
            //System.out.println(dataTickets[3]);

            dataTickets[4] = "ID  :  "+ID+"\n";
            // System.out.println(dataTickets[4]);
            dataTickets[5] = ( movieID-1)+"" ;


        return dataTickets;
    }

//check if the user already has the tickets file or not and then create a new ticket ...(called in --> class User --> SaveToFile())
    public static void Reservation (int userID,int movieID ,String showTime,int chairNum){
        try{
        File tickets = new File("D:\\Cinema project\\Users\\user-"+userID+"\\Tickets");
        if (!tickets.exists()){
            tickets.mkdir();
            FileWriter countFile = new FileWriter("D:\\Cinema project\\Users\\user-"+userID+"\\Tickets\\count.txt");
            countFile.write(Integer.toString(1));
            countFile.flush();
            countFile.close();
            ReservationTOExistFile(userID,movieID,showTime,chairNum);
        }else {
            ReservationTOExistFile(userID,movieID,showTime,chairNum);
        }

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //create the files for a new ticket ... (called in --> Reservation())
    public static void ReservationTOExistFile(int userID,int movieID ,String showTime,int chairNum){
        try{
        BufferedReader countFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\count.txt"));
        count = Integer.parseInt(countFile.readLine());
        countFile.close();

        File f = new File("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count);
        f.mkdir();

        //uploading the show time
            BufferedReader ShowTimeReadFile = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-"+count+"\\"+showTime+".txt"));
            BufferedWriter showTimeFile = new BufferedWriter(new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count+"\\Show time.txt"));
            showTimeFile.write(ShowTimeReadFile.readLine());
            showTimeFile.flush();
            showTimeFile.close();

        //uploading the Film name
            BufferedReader filmName = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-"+movieID+"\\name.txt"));
            BufferedWriter fileName2 = new BufferedWriter(new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count+"\\movie name.txt"));
            fileName2.write(filmName.readLine());
            fileName2.flush();
            fileName2.close();
            filmName.close();

        //uploading the chair number
         TicketsNumUpdate(movieID);
          //  BufferedReader getTheTicketsNum = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-"+movieID+"\\Tickets Number.txt"));

            FileWriter setTheChairNum= new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count+"\\Chair Number.txt");
            setTheChairNum.write(Integer.toString(chairNum));
            setTheChairNum.flush();
            setTheChairNum.close();

        //uploading the host hall
            BufferedReader getTheHostHall = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-"+movieID+"\\host Hall.txt"));
            FileWriter setTheHostHall= new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count+"\\host Hall.txt");
            setTheHostHall.write(getTheHostHall.readLine());
            setTheHostHall.flush();
            setTheHostHall.close();

        //Uploading the ID...
            FileWriter IDFile = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count+"\\ID.txt");
            IDFile.write(Integer.toString(count));
            IDFile.flush();
            IDFile.close();

        //Uploading the movieID ...
            FileWriter movieIDFile = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-"+count+"\\movieID.txt");
            movieIDFile.write(Integer.toString(movieID));
            movieIDFile.flush();
            movieIDFile.close();


            //Increasing The count by 1...
            count++;
            FileWriter updateCount = new FileWriter("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\count.txt");
            updateCount.write(Integer.toString(count));
            updateCount.flush();
            updateCount.close();

    }catch (FileNotFoundException e){
        System.out.println(e.getMessage());
    }catch (IOException e){
        System.out.println(e.getMessage());
    }
    }


}
