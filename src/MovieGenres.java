import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class MovieGenres {
    public static void main( String [ ] args ) {
        BSTNode temp = new BSTNode( );
        File file = new File("data/movies.csv");
        String[] inputLine;

        try{
            CSVReader reader = new CSVReader(new FileReader(file));
            reader.skip(1);

            while ((inputLine = reader.readNext()) != null) {
                int tempID = Integer.parseInt(inputLine[0]);
                String tempTitle;
                int tempYear = 0, length = inputLine[1].length();
                String lastLetter = inputLine[1].substring(length-1);
                if(lastLetter.equals(")")){
                    int titleYearEnd = inputLine[1].lastIndexOf(')') ;
                    int titleYearStart = inputLine[1].lastIndexOf('(') + 1;
                    tempTitle = inputLine[1].substring(0, (titleYearStart-2));
                    tempYear = Integer.parseInt(inputLine[1].substring(titleYearStart, titleYearEnd));
                }
                else{tempTitle = inputLine[1];}

                String tempGenre = inputLine[2];
                MovieInfo info = new MovieInfo(tempID, tempTitle, tempYear, tempGenre);
                System.out.println(tempID + "\t" + tempTitle + "\t" + tempYear + "\t" + tempGenre);

                temp.insert(info);

            }reader.close();
        }
        catch (IOException | CsvValidationException e) {e.printStackTrace();}
        catch(NumberFormatException ex) {System.out.println("!!Error!! Enter a valid number...");}
    }
}