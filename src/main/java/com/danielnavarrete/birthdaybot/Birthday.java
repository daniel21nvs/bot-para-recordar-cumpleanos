package com.danielnavarrete.birthdaybot;

import org.springframework.cglib.core.Local;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Birthday {

    public static void main(String[] args) throws IOException {

        String line = "";
        String splitBy = ",";
        int bMonth; //mes de cumpleaños
        int bDay; // día de cumpleaños
        LocalDate bDate; // fecha nacimiento
        short age;

        // fecha actual
        LocalDate currentDate = LocalDate.now();
        System.out.println("Fecha actual: "+ currentDate);

        if( currentDate.getDayOfWeek().getValue() == 7 ){
            //es domingo
            System.out.println("Es domingo, buscando cumpleaños para la semana siguiente...");
            //fecha fin
            LocalDate nextWeek = LocalDate.now().plusDays(7);
            System.out.println("Buscar cumpleaños hasta: "+ nextWeek);

            // lista con información de los cumpleaños de la semana
            List nextBirthdays = new ArrayList<String>();

            try {
                BufferedReader br = new BufferedReader(new FileReader("./././data.csv"));
                br.readLine(); // lee la primera fila (contiene las columnas)

                // recorremos los registros de los cumpleaños almacenados en el csv
                while ((line = br.readLine()) != null){
                    String[] birthDay = line.split(splitBy);

                    // construccion fecha de cumpleaños
                    bMonth = Integer.parseInt(birthDay[6]); // obtenemos el mes de cumpleaños
                    bDay = Integer.parseInt(birthDay[7]); // obtenemos el dia de cumpleaños


                    String date = birthDay[5]; // obtenemos la fecha de nacimiento
                    DateTimeFormatter oldFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    bDate = LocalDate.parse( date, oldFormatter); // formateamos fecha de nacimiento para calcular edad

                    age = (short) (currentDate.getYear() - bDate.getYear()); // calcular edad

                    LocalDate birthday = LocalDate.of(currentDate.getYear(), bMonth, bDay);

                    // se evalúa si el cumpleaños registrado se encuentra dentro de la siguiente semana
                    // si es verdadero, entonces se almacenan sus datos en la lista "nextBirthdays"
                    if(birthday.compareTo(currentDate) > 0 && birthday.compareTo(nextWeek) <= 0 ){
                        String person;
                        person = "Name: "+birthDay[1]
                                + " Lastname: "+ birthDay[2]
                                + " Birthday: "+ birthday
                                + " Age: "+ age;
                        nextBirthdays.add(person);
                    }
                }

                //mostrar informacion de cumpleaños encontrados para la siguiente semana
                if( nextBirthdays.size() > 0){
                    System.out.println("cumpleaños en la próxima semana");
                    // ordenar lista de forma ascendente ( fecha de cumpleaños )
                    Collections.sort(nextBirthdays);
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

}
