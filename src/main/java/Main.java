import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        GsonPar parser = new GsonPar();
        AllComAndSec allComAndSec = parser.parse();

        System.out.println("Краткое название - Дата основания:");

        List<Company> companies = new ArrayList<>(allComAndSec.getCompanies());

        for (int i = 0; i < companies.size(); i++) {
            String oldDateString = companies.get(i).getFounded();
            SimpleDateFormat oldDateFormat = new SimpleDateFormat("d.MM.yyyy");
            SimpleDateFormat newDateFormat = new SimpleDateFormat("d/M/yy");

            Date date = oldDateFormat.parse(oldDateString);
            String result = newDateFormat.format(date);

            System.out.println(companies.get(i).getName() + " - " + result);
        }


        System.out.println("----------------------------------");

        //вывод просроченных бумаг

        int count = 0;
        System.out.println("Просроченные ценные бумаги:");
        for (int i = 0; i < companies.size(); i++) {
            for (int j = 0; j < companies.get(i).getSecurities().size(); j++) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                String date = companies.get(i).getSecurities().get(j).getDate();

                if (LocalDate.parse(date, formatter).isBefore(LocalDate.now())) {
                    System.out.println("Организация: " + companies.get(i).getSecurities().get(j).getName() + " Код: " + companies.get(i).getSecurities().get(j).getCode() + " Дата истечения: " + companies.get(i).getSecurities().get(j).getDate());
                    count++;
                }
            }
        }
        System.out.println("Всего просроченных бумаг: " + count);

        System.out.println("----------------------------------");

        // запрос пользователя в виде даты.

        System.out.println("Введите дату");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String data = bf.readLine().replace("/", ".");
        LocalDate localDate;
try {
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d.M.yyyy");
     localDate = LocalDate.parse(data, formatter1);
}catch (Exception e){
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d.M.yy");
     localDate = LocalDate.parse(data, formatter1).minusYears(100);
}

        System.out.println("Дата ввода: "+localDate);

        for (int i = 0; i < companies.size(); i++) {

            String date = companies.get(i).getFounded();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y");
            LocalDate localDate1 = LocalDate.parse(date, formatter);

            if (localDate1.isAfter(localDate)) {
                System.out.println("Организация: " + companies.get(i).getName() + ". Дата основания: " + companies.get(i).getFounded());
            }
        }
        // запрос в виде кода валюты

        System.out.println("----------------------------------");
        

        System.out.println("Введите валюту");

        BufferedReader bf1 = new BufferedReader(new InputStreamReader(System.in));
        String currency = bf1.readLine();


        System.out.println("Id и коды ценных бумаг:");
        for (int i = 0; i < companies.size(); i++) {
            for (int j = 0; j < companies.get(i).getSecurities().size(); j++) {
                for (int z = 0; z < companies.get(i).getSecurities().get(j).getCurrency().size(); z++) {
                    if (currency.equalsIgnoreCase(companies.get(i).getSecurities().get(j).getCurrency().get(z))) {
                        System.out.println("id " + companies.get(i).getId() + " Код: " + companies.get(i).getSecurities().get(j).getCode());
                    }
                }
            }
        }
    }
}