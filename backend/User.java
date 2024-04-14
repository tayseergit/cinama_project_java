import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User extends Tickets implements Runnable  {
    private String Email;
    private String payingWay;
    private int movieID;
    private String showTime;
    private int chairNumber;
    List<Tickets> userTickets = new ArrayList<>();
    private static int count;

    public User(){}

    public User(String email, String payingWay, int movieID, String showTime,int chairNumber) {
        Email = email;
        this.payingWay = payingWay;
        this.movieID = movieID;
        this.showTime = showTime;
        this.chairNumber=chairNumber;
    }

    @Override
    public void run(){
        doWhatRequireForBooking();
    }


    //this method booking if the giving chair number is already free ...
    public void doWhatRequireForBooking(){
        try {
        movies m = new movies();
        boolean b = m.CheckTheChair(movieID, chairNumber);
           // System.out.println(b);
       if(b){
           saveToFile();
       }else
           System.out.println("you should choose another chair...");

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //get the user ID by giving his email ...
    public int getUserID(String email){
        int userNum=1;
        boolean result = false;


        try {
        File f =new File("D:\\Cinema project\\Users\\user-"+userNum);
        while (f.exists()){
            BufferedReader emailFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-"+userNum+"\\Email.txt"));
            if (emailFile.readLine().equals(email)){
                result=true;
              emailFile.close();
                break;
            }
            userNum++;
            f =new File("D:\\Cinema project\\Users\\user-"+userNum);
            emailFile.close();

        }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        if(result)
            return userNum;
        else
            return 0;


    }

//this method create the file for user if not exist...(called in ->doWhatRequireForBooking())...
    public void saveToFile(){
        try {
        int userNum=1;
        File f =new File("D:\\Cinema project\\Users\\user-"+userNum);
        while (f.exists()){
            BufferedReader emailFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-"+userNum+"\\Email.txt"));
            if (emailFile.readLine().equals(Email)){
                Reservation(userNum,movieID,showTime,chairNumber);
                emailFile.close();
                break;
            }
            userNum++;
            f =new File("D:\\Cinema project\\Users\\user-"+userNum);
            emailFile.close();

        }
        if(!f.exists()){
            SaveToNonExistFile();
            Reservation(userNum,movieID,showTime,chairNumber);
        }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //this method create a file for user as a new user...(called in ->SaveToFile())...
    public void SaveToNonExistFile(){
        try{
    //get the count
        BufferedReader line = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\count.txt"));
        count = Integer.parseInt(line.readLine());
        line.close();


        //creating the file
        File user = new File("D:\\Cinema project\\Users\\user-"+count);
        user.mkdir();

        //filling the Email and the payment way
            BufferedWriter userName = new BufferedWriter(new FileWriter("D:\\Cinema project\\Users\\user-"+count+"\\Email.txt"));
            userName.write(Email);
            userName.flush();
            userName.close();
            File payingFile = new File("D:\\Cinema project\\Users\\user-"+count+"\\"+this.payingWay);
            payingFile.mkdir();

            //increasing the count by 1...
            count++;

            FileWriter updateCount = new FileWriter("D:\\Cinema project\\Users\\count.txt");
            updateCount.write(Integer.toString(count));
            updateCount.flush();
            updateCount.close();


        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }


    //this method get or fill the user tickets array...
    public boolean getTickets(int userID){
        boolean check = false;

        try {
            File f = new File("D:\\Cinema project\\Users\\user-" + userID);
            if (f.exists()) {
                check = true;
                int count = 1;
                File ticketsFiles = new File("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count);
                //this while loop throw the Tickets Files and filling the Tickets objects list...
                while (ticketsFiles.exists()) {
                    Tickets ticket;
                    BufferedReader FilmName = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\movie name.txt"));
                    String movieName = FilmName.readLine();
                    FilmName.close();

                    BufferedReader showTimeFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\Show time.txt"));
                    String showTime = showTimeFile.readLine();
                    showTimeFile.close();

                    BufferedReader hostHallFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\host Hall.txt"));
                    String hostHall = hostHallFile.readLine();
                    hostHallFile.close();

                    BufferedReader chairNumberFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\Chair Number.txt"));
                    int chairNumber = Integer.parseInt(chairNumberFile.readLine());
                    chairNumberFile.close();

                    BufferedReader IdFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\ID.txt"));
                    int ID = Integer.parseInt(IdFile.readLine());
                    IdFile.close();

                    BufferedReader movieIDFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count + "\\movieID.txt"));
                    int movieID = Integer.parseInt(movieIDFile.readLine());
                    movieIDFile.close();
                    ticket = new Tickets(movieName, hostHall, chairNumber, showTime, ID, movieID);
                    userTickets.add(ticket);


                    count++;

                    ticketsFiles = new File("D:\\Cinema project\\Users\\user-" + userID + "\\Tickets\\ticket-" + count);

                }
            }

            }catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            }catch(IOException e){
                System.out.println(e.getMessage());
            }

       return check;
    }

//this method delete specific ticket and then update the files...
    public void deleteTicket(int userID,int index){
        try {
            Tickets t = new Tickets();
            int movieID, availableChairs,TicketsNumber;
            t = this.userTickets.get(index);
            movieID = t.movieID;
            //decreasing tickets number by 1...
            BufferedReader getTicketsNumber = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt"));
            TicketsNumber = Integer.parseInt(getTicketsNumber.readLine());
            TicketsNumber--;
            FileWriter setNewTicketsNumber = new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt");
            setNewTicketsNumber.write(Integer.toString(TicketsNumber));
            setNewTicketsNumber.flush();
            setNewTicketsNumber.close();
            //increasing the available chairs by 1 instead the deletion ticket...
            BufferedReader availableChairsGet = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\available Chairs.txt"));
            availableChairs = Integer.parseInt(availableChairsGet.readLine());
            availableChairs++;

            FileWriter setAvailableChairs = new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\available Chairs.txt");
            setAvailableChairs.write(Integer.toString(availableChairs));
            setAvailableChairs.flush();
            setAvailableChairs.close();

            //changing the chair to free...
            movies m = new movies();
            m.fillChairChecklist(movieID);
            m.chairCheck[t.chairNumber-1]=false;
            m.fillTheChairCheckFile(movieID);


            //delete the ticket object from the list of userTickets...
            userTickets.remove(index);
            t.updateTicketsFiles(this.userTickets,userID);

        }catch (FileNotFoundException e ){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
