package ru.sapteh;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        //read
        int idList = 0;
        String fileName = "Students.txt";
        ArrayList<Students> arrayList = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            while (bf.ready()) {
                Students students = studCheck(bf.readLine());
                arrayList.add(students);
            }
        }

        //input
        try(BufferedReader readStudents = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Введите данные студента:");
            for (Students studentsForeach: arrayList) {
                if(studentsForeach.getId() > idList){
                    idList = studentsForeach.getId();
                }
            }
            String[] str = readStudents.readLine().split(" ");
            String firstName = str[0];
            String lastName = str[1];
            String surname = str[2];
            str[3] = str[3].replaceAll("[^0-9]","/" );
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(str[3]);
            Students students = new Students(++idList,firstName,lastName,surname,date);
            arrayList.add(students);
        }
        //write
        try(FileWriter fw = new FileWriter(fileName)){
            for (Students student: arrayList) {
                fw.write(student.createStudent() + "\n");
            }
        }
    }
    public static Students studCheck(String readLine) throws ParseException {
        String[] strPerson = readLine.split(" ");
        int id = Integer.parseInt(strPerson[0]);
        String lastName = strPerson[1];
        String firstName = strPerson[2];
        String surname = strPerson[3];
        strPerson[4] = strPerson[4].replaceAll("[^0-9]", "/");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strPerson[4]);
        return new Students(id,lastName,firstName,surname,date);
    }
}