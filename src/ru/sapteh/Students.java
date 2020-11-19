package ru.sapteh;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Students {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String surname;
    private final Date date;
    public Students(int id, String lastName, String firstName, String surname, Date date){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surname = surname;
        this.date = date;
    }
    public String dateFormat(Date nextBirthday){
        return new SimpleDateFormat("E-FF-MMMM-yyyy").format(nextBirthday);
    }
    public Date getLastBirthday(Date dateBirthday){
        String[] str = String.format("%tF", dateBirthday).split("-");
        int month = Integer.parseInt(str[1].trim());
        int day = Integer.parseInt(str[2].trim());

        Date dateNow = new Date();
        String[] strNow = String.format("%tF", dateNow).split("-");
        int yearNow = Integer.parseInt(strNow[0].trim());
        int monthNow = Integer.parseInt(strNow[1].trim());
        int dayNow = Integer.parseInt(strNow[2].trim());

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MONTH, --month);

        if(monthNow == month && dayNow < day){
            return new GregorianCalendar(yearNow,calendar.get(Calendar.MONTH),day).getTime();
        }
        if (monthNow < month){
            return new GregorianCalendar(yearNow,calendar.get(Calendar.MONTH),day).getTime();
        }
        return new GregorianCalendar(++yearNow,calendar.get(Calendar.MONTH), day).getTime();
    }
    public int quantityDays (Date lastBirthday){
        String[] str = String.format("%tF", lastBirthday).split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);
        LocalDate dateNow = LocalDate.now();
        LocalDate localDateLastBirthday = LocalDate.of(year,month,day);
        return (int) ChronoUnit.DAYS.between(dateNow, localDateLastBirthday);
    }
    public int years (Date years){
        String[] str = String.format("%tF", years).split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);
        LocalDate dateNow = LocalDate.now();
        LocalDate localDateLastBirthday = LocalDate.of(year,month,day);
        return (int) ChronoUnit.YEARS.between(localDateLastBirthday, dateNow);
    }
    public int getId() {
        return id;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSurname() {
        return surname;
    }
    public Date getDate() {
        return date;
    }
    public String russianDate(){
        return new SimpleDateFormat("dd.MM.yyyy").format(getDate());
    }
    public String createStudent(){
        return String.format("%d %s %s %s %s     Возраст: %d     Дней до след д/р: %d     Следующая дата д/р: [%s]", getId(),getLastName(),getFirstName(),getSurname(),russianDate()
                , years(getDate()), quantityDays(getLastBirthday(getDate())), dateFormat(getLastBirthday(getDate())));
    }
}