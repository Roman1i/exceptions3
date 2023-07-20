import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        System.out.println("Введите данные...");
        System.out.println("Данные:'Фамилия Имя Отчество дата рождения номер телефона пол'(в произвольном порядке)");
        System.out.println("Формат ввода:'Lastname Name Midlename dd.mm.yyyy 89005003010 m'");
        //Last Name Midle 01.01.2000 89005003010 f
        String input = sc.nextLine();
        String[] words = input.split(" ");
        if (words.length != 6) {
            throw new Exception("Ошибка ввода! Неверное кол-во элементов...");
        }
        String data = "";
        String lastname = "";
        int hasName = 0;
        int hasDate = 0;
        int hasNumber = 0;
        int hasGender = 0;
        for (int i = 0; i < words.length; i++) {
            try {
                data += String.format("номер: %s\n", Long.parseLong(words[i]));
                hasNumber += 1;
            } catch (NumberFormatException e) {
                if (words[i].split("\\.").length==3) {
                    data += String.format("дата: %s\n", words[i]);
                    hasDate += 1;
                } else if (words[i] instanceof String && words[i].length()>1) {
                    lastname = words[i];
                    data += String.format("ФИО: %s %s %s\n", words[i], words[i+1], words[i+2]);
                    hasName += 1;
                    i+=2;
                } else if (words[i] instanceof String && words[i].length()==1) {
                    data += String.format("пол: %s\n", words[i]);
                    hasGender += 1;
                } 
            }
        }
        data += "\n";

        if (hasDate != 1 || hasName != 1 || hasNumber !=1 || hasGender !=1){
            throw new Exception("Ошибка ввода! Неверный формат элементов...");
        }

        File file = new File(lastname);
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
            System.out.println("Данные успешно сохранены...");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
