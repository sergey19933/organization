import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;

public class GsonPar {

    public AllComAndSec parse() throws IOException {
        Gson gson = new Gson();

        System.out.println("Введите путь до файла:");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String filePath = bf.readLine();

        try(FileReader reader=new FileReader(filePath)){

           AllComAndSec allComAndSec= gson.fromJson(reader,AllComAndSec.class);

            return allComAndSec;
        }catch (Exception e){
            System.out.println("Что то не так!");
        }

        return null;
    }
}
