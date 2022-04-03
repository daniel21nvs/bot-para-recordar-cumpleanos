package com.danielnavarrete.birthdaybot;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Birthday {

    public static void main(String[] args) throws IOException {

        String line = "";
        String splitBy = ",";
        int bMonth; //mes de cumpleaños
        int bDay; // día de cumpleaños

        // fecha actual
        LocalDate currentDate = LocalDate.now();
        System.out.println("Fecha actual: "+ currentDate);

        //fecha fin
        LocalDate nextWeek = LocalDate.now().plusDays(7);
        System.out.println("Fecha fin: "+ nextWeek);

        System.out.println(currentDate.compareTo(nextWeek));

        // lista con información de los cumpleaños de la semana
        List nextBirthdays = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("./././data.csv"));
            br.readLine(); // lee la primera fila (contiene las columnas)

            // recorremos los registros de los cumpleaños almacenados en el csv
            while ((line = br.readLine()) != null){
                String[] birthDay = line.split(splitBy);

                //construccion fecha cumpleaños
                bMonth = Integer.parseInt(birthDay[6]); // obtenemos el mes de cumpleaños
                bDay = Integer.parseInt(birthDay[7]); // obtenemos el dia de cumpleaños
                LocalDate birthday = LocalDate.of(currentDate.getYear(), bMonth, bDay);

                System.out.println("Birthday");
                System.out.println(birthday);

                // se evalúa si el cumpleaños registrado se encuentra dentro de la siguiente semana
                // si es verdadero, entonces se almacenan sus datos en la lista "nextBirthdays"
                if(birthday.compareTo(currentDate) > 0 && birthday.compareTo(nextWeek) <= 0 ){

                    String person;
                    person = "Name: "+birthDay[1]
                            + " Lastname: "+ birthDay[2]
                            + " Birthday: "+ birthday;
                    nextBirthdays.add(person);

                }
            }

            //mostrar cumpleañeros
            if( nextBirthdays.size() > 0){
                System.out.println("cumpleaños en la próxima semana");
                for (int i =0; i < nextBirthdays.size(); i++){
                    System.out.println(nextBirthdays.get(i));
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // envio de email
        /*
        SendMail sm = new SendMail();
        sm.sendMail();
        */

    }

}
