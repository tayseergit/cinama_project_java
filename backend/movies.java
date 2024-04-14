import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class movies implements Cinema {
    static int count = 1;
    private String name;
    private String type;
    private List<ShowTimes> date = new ArrayList<>();

    //CONTAIN THE MOVIE PATHS LIKE
    //"D:\\Cinema project\\movies\\movie-1"
    List<String> path = new ArrayList<>();
    private String hostHall;
    private int ticketPrice;
    private int availableChairs;
    boolean chairCheck[];
   static int []  mostPopularMovies = {55,67,66};

    //THIS CONSTRUCTOR WILL CREATE THE PATH LIST...
    public movies() {
        int num = 1;
        try {
            File f = new File("D:\\Cinema project\\movies\\movie-1");
            while (f.exists()) {
                path.add(f.getCanonicalPath());
                num++;
                f = new File("D:\\Cinema project\\movies\\movie-" + num);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());


        }
    }

    //THIS CONSTROCTER ONLY FOR BACKEND TO GENERATE MOVIES DATA ...
    public movies(String name, String type, List<ShowTimes> date, String hostHall,int ticketPrice) {
        this.name = name;
        this.type = type;
        this.hostHall = hostHall;
        this.ticketPrice = ticketPrice;
        for (ShowTimes d : date) {
            this.date.add(d);

        }
    }

    // THIS METHOD PRINTS THE MOVIE DATA BY PASSING THE MOVIE PATH FOR IT ...
    public String[] ToString(int i) {
        String datafilm[] = new String[6];
        try {

            String pa = this.path.get(i);
            BufferedReader movieName = new BufferedReader(new FileReader(pa + "\\name.txt"));
            BufferedReader movieType = new BufferedReader(new FileReader(pa + "\\type.txt"));
            BufferedReader chairs = new BufferedReader(new FileReader(pa + "\\available Chairs.txt"));
            BufferedReader priceFile = new BufferedReader(new FileReader(pa + "\\price.txt"));
            BufferedReader morningTimeFile = new BufferedReader(new FileReader(pa + "\\morning Show Time.txt"));
            BufferedReader eveningTimeFile = new BufferedReader(new FileReader(pa + "\\evening Show Time.txt"));
            datafilm[0] = "NAME   :  " + movieName.readLine() + "\n";
            datafilm[1] = " Type   :  " + movieType.readLine() + "\n";
            // System.out.println("Type :" + movieType.readLine());
            int availableChairs = Integer.parseInt(chairs.readLine());
            datafilm[2] = "Available Chairs  :  " + availableChairs;
            int price = Integer.parseInt(priceFile.readLine());
            datafilm[3] = "Ticket price  :  " + price + " $\n";
            datafilm[4] = "Morning Show Time :  " + morningTimeFile.readLine() + "\n";
            datafilm[5] = "Evening Show Time :  " + eveningTimeFile.readLine() + "\n";

            // System.out.println("Available Chairs :" + availableChairs);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return datafilm;
    }

    // THIS METHOD PRINTS THE MOVIE DATA BY PASSING THE MOVIE PATH FOR IT ...
//    public static void ToString (String path){
//        try{
//            BufferedReader movieName = new BufferedReader(new FileReader(path+"\\name.txt"));
//            BufferedReader movieType = new BufferedReader(new FileReader(path+"\\type.txt"));
//            BufferedReader chairs = new BufferedReader(new FileReader(path+"\\available Chairs.txt"));
//            BufferedReader priceFile = new BufferedReader(new FileReader(path+"\\price.txt"));
//            BufferedReader morningTimeFile = new BufferedReader(new FileReader(path+"\\morning Show Time.txt"));
//            BufferedReader eveningTimeFile = new BufferedReader(new FileReader(path+"\\evening Show Time.txt"));
//            System.out.println("NAME :"+movieName.readLine());
//            System.out.println("Type :"+movieType.readLine());
//            int availableChairs = Integer.parseInt(chairs.readLine());
//            System.out.println("Available Chairs :"+availableChairs);
//
//        }catch (FileNotFoundException e){
//            System.out.println(e.getMessage());
//        }catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//    }


    // THIS METHOD FOR ADDING A NEW MOVIE ONLY THE OWNER CAN USE IT ...(WE WON'T  USR IT IN FORONTEND CURRENTLY)
    public void addMovie(String imgPath){
        try {
            //getting the count from file
            BufferedReader br = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\count.txt"));
            String line;
            line = br.readLine();
            count = Integer.parseInt(line);

            File movie = new File("D:\\Cinema project\\movies\\movie-" + count);
            movie.mkdir();

            //storing the image for movie in the file...
            File image = new File(imgPath);
            byte b [] = new byte[(int)image.length()];
            FileInputStream fis = new FileInputStream(image);
            fis.read(b);
            FileOutputStream fio = new FileOutputStream("D:\\Cinema project\\movies\\movie-" + count + "\\img.jpg");
            fio.write(b);

            //تخزين اسم ونوع الفلم في الفايلات
            File filename = new File("D:\\Cinema project\\movies\\movie-" + count + "\\name.txt");
            File filetype = new File("D:\\Cinema project\\movies\\movie-" + count + "\\type.txt");
            FileWriter nameWrite = new FileWriter(filename);
            nameWrite.write(this.name);
            nameWrite.flush();
            nameWrite.close();
            FileWriter typewrite = new FileWriter(filetype);
            typewrite.write(this.type);

            typewrite.flush();
            typewrite.close();
            //تخزين وقت مشاهدة الفلم في الفايلات
            for (ShowTimes d : date) {
                d.addDateToMovieFile(count);
            }
            //اضافة الفلم الى قاعته في السينما
            Cinema.addMonvieToHall(hostHall, name);

            //قراءة عدد الكراسي المتاحة لكل فلم وتخزينها داخل فايل الفلم
            BufferedReader chairsNumber = new BufferedReader(new FileReader("D:\\Cinema project\\Cinema Halls\\" + hostHall + "\\Chairs number.txt"));
            line = chairsNumber.readLine();
            availableChairs = Integer.parseInt(line);
            BufferedWriter chairs = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + count + "\\available Chairs.txt"));
            chairs.write(Integer.toString(availableChairs));
            chairs.flush();
            chairs.close();

            //creating a file for the tickets number
            BufferedWriter setTicketsNumber = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + count+ "\\Tickets Number.txt"));
            setTicketsNumber.write("0");
            setTicketsNumber.flush();
            setTicketsNumber.close();

            //getting the host hall name from the Cinema...
            BufferedReader hostHallName = new BufferedReader(new FileReader("D:\\Cinema project\\Cinema Halls\\" + hostHall + "\\name.txt"));
            line = hostHallName.readLine();
            hostHallName.close();

            BufferedWriter setHostHallName = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + count + "\\host Hall.txt"));
            setHostHallName.write(line);
            setHostHallName.flush();
            setHostHallName.close();

            //storing the ticket price for this movie...
            BufferedWriter setTicketPrice = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + count + "\\price.txt"));
            setTicketPrice.write(Integer.toString(ticketPrice));
            setTicketPrice.flush();
            setTicketPrice.close();

            //setting the default value of ChairCheck boolean array in the file...
            for (int i = 0; i < availableChairs; i++) {
                BufferedWriter CheCKChair = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-"+count +"\\chairCheck.txt",true));
                CheCKChair.write("false\n");
                System.out.println(i);
                CheCKChair.flush();
                CheCKChair.close();
            }

            //زيادة العداد بمقدار 1 وتخزينه في الفايل
            count++;
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\count.txt"));
            bw.write(Integer.toString(count));
            bw.flush();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void TicketsNumUpdate(int movieID) {
        int TicketsNumber;
        try {
            //creating the tickets number file in the movie if not exist...
            File ticketsNumberFile = new File("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt");
            if (!ticketsNumberFile.exists()) {
                BufferedWriter setTicketsNumber = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt"));
                setTicketsNumber.write("0");
                setTicketsNumber.flush();
                setTicketsNumber.close();
            }
            //updating the value of Tickets Number file
            BufferedReader getTicketsNumber = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt"));
            TicketsNumber = Integer.parseInt(getTicketsNumber.readLine());
            TicketsNumber++;
            FileWriter setNewTicketsNumber = new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt");
            setNewTicketsNumber.write(Integer.toString(TicketsNumber));
            setNewTicketsNumber.flush();
            setNewTicketsNumber.close();


            //update available chairs
            int availableChairs;
            BufferedReader availableChairsGet = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\available Chairs.txt"));
            availableChairs = Integer.parseInt(availableChairsGet.readLine());
            availableChairs--;

            FileWriter setAvailableChairs = new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\available Chairs.txt");
            setAvailableChairs.write(Integer.toString(availableChairs));
            setAvailableChairs.flush();
            setAvailableChairs.close();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
//This method filling the boolean list chairCheck[] from the specific file...
    public void fillChairChecklist(int movieID) throws IOException {
        int length;
        String line;
        BufferedReader hostHallFile = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\host Hall.txt"));
        line = hostHallFile.readLine();
      //  System.out.println(line);
        if (line.equals("First hall")) {
            // System.out.println("100");
            length = 100;
        } else if (line.equals("Second Hall")) {
            length = 75;
            // System.out.println("75");
        } else
            length = 50;

        try {
            String line2;
            chairCheck = new boolean[length];

            int count =0;
            BufferedReader CheCKChair = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\chairCheck.txt"));
            while ((line2=CheCKChair.readLine())!= null) {
                //   System.out.println(line2);
                if (line2.equals("false")) {
                    chairCheck[count] = false;
                } else
                    chairCheck[count] = true;
               // System.out.println(count);

                //System.out.println(chairCheck[count]);
                count++;

            }
            CheCKChair.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



//This method filling the specific file again from the chairCheck[] list after we have edited on it ...
    public void fillTheChairCheckFile(int movieID)throws IOException{
        File deleteChairCheck = new File("D:\\Cinema project\\movies\\movie-" + movieID + "\\chairCheck.txt");
        deleteChairCheck.delete();
        for (boolean c : chairCheck) {
            BufferedWriter chairCheckFile = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\chairCheck.txt",true));
            if (c==false)
                chairCheckFile.write("false\n");
            else
                chairCheckFile.write("true\n");

            chairCheckFile.flush();
            chairCheckFile.close();
        }
    }

    //This method calls the previous two methods and do what requires for choosing specific chair...(called in --> class User -->doWhatRequireForBooking())
    public boolean CheckTheChair(int movieID, int chairNum) throws IOException {
        boolean check = true;
        this.fillChairChecklist(movieID);
        if (chairCheck[chairNum-1] == true) {

            System.out.println("this chair is busy...");
            check=false;

        } else {
            chairCheck[chairNum-1] = true;
            this.fillTheChairCheckFile(movieID);
        }
        return check;

    }

    // returns int array of index for the most popular three movies...
    public static void theMostPopular(){

        try{

        int movieID = 1;
        String line;
        BufferedReader br = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\count.txt"));
        line = br.readLine();
        count = Integer.parseInt(line);
        int ticketsNumberArray [] = new int[count];

        File movie = new File("D:\\Cinema project\\movies\\movie-"+ movieID);
        while (movie.exists()) {
            //get the Tickets number from files...
            BufferedReader TicketsNumberFile = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt"));
            ticketsNumberArray[movieID-1]=Integer.parseInt(TicketsNumberFile.readLine());
           // System.out.println(ticketsNumberArray[movieID-1]);
            movieID++;
            movie = new File("D:\\Cinema project\\movies\\movie-"+ movieID);
        }
            Arrays.sort(ticketsNumberArray);


             getMovieID(ticketsNumberArray[movieID - 2],0);
            getMovieID(ticketsNumberArray[movieID - 1],1);
            getMovieID(ticketsNumberArray[movieID - 3],2);
//            System.out.println(ticketsNumberArray[movieID - 1]);
//            System.out.println(ticketsNumberArray[movieID - 2]);
//            System.out.println(ticketsNumberArray[movieID - 3]);


        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void getMovieID(int ticketsNumber,int index) {
        int movieID = 1;
        String line ;
        try {
            boolean check;

        File movie = new File("D:\\Cinema project\\movies\\movie-"+ movieID);
        while (movie.exists()) {
            check = false;
            //get the Tickets number from files...
            BufferedReader TicketsNumberFile = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt"));
            line = TicketsNumberFile.readLine();

            if (line.equals(Integer.toString(ticketsNumber))){
                for (int c : mostPopularMovies) {
                   if(c==(movieID-1)) {
                       check = true;
                      // System.out.println("insid");
                   }
                }if (!check) {
                    mostPopularMovies[index]=(movieID-1);
                  //  System.out.println(--movieID);
                    break;
                }
            }
            movieID++;
            movie = new File("D:\\Cinema project\\movies\\movie-"+ movieID);
        }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

   @Override
    public void Reset(){
    try {
            int length;
            int movieID = 1;
            String line;
            File movie = new File("D:\\Cinema project\\movies\\movie-"+ movieID);

            while (movie.exists()) {
                //delete the old chair check file...
                File deleteChairCheck = new File("D:\\Cinema project\\movies\\movie-" + movieID + "\\chairCheck.txt");
                deleteChairCheck.delete();
                //set the new chair check file...
                BufferedReader hostHallFile = new BufferedReader(new FileReader("D:\\Cinema project\\movies\\movie-" + movieID + "\\host Hall.txt"));
                line = hostHallFile.readLine();
                if (line.equals("First hall")) {
                    // System.out.println("100");
                    length = 100;
                } else if (line.equals("Second hall")) {
                    length = 75;
                    // System.out.println("75");
                } else
                    length = 50;
                for (int i = 0; i < length; i++) {
                    BufferedWriter CheCKChair = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\chairCheck.txt", true));
                    CheCKChair.write("false\n");
                    CheCKChair.flush();
                    CheCKChair.close();
                }

                //reset the available chairs...
                BufferedWriter CheCKChair = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\available Chairs.txt"));
                CheCKChair.write(Integer.toString(length));
                CheCKChair.flush();
                CheCKChair.close();
                //set the Tickets number file...
                BufferedWriter setTicketsNumber = new BufferedWriter(new FileWriter("D:\\Cinema project\\movies\\movie-" + movieID + "\\Tickets Number.txt"));
                setTicketsNumber.write("0");
                setTicketsNumber.flush();
                setTicketsNumber.close();

                movieID++;
                movie = new File("D:\\Cinema project\\movies\\movie-"+ movieID);
            }

        //reset the users files...
        int userID = 1;
            File user = new File("D:\\Cinema project\\Users\\user-"+userID);
        while (user.exists()) {
            Path directory = Path.of("D:\\Cinema project\\Users\\user-" + userID);
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
            userID++;
            user = new File("D:\\Cinema project\\Users\\user-"+userID);
        }
        FileWriter setCount = new FileWriter("D:\\Cinema project\\Users\\count.txt");
        setCount.write(Integer.toString(1));
        setCount.flush();
        setCount.close();

        }catch (FileNotFoundException e ){
        System.out.println(e.getMessage());
        }catch (IOException e){
        System.out.println(e.getMessage());
        }
    }
}
