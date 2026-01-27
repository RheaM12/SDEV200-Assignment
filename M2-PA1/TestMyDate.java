import java.util.GregorianCalendar;

// MyDate class 
class MyDate {

    private int year;
    private int month; // 0-based (0 = January)
    private int day;

    // No-arg constructor: current date
    public MyDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(GregorianCalendar.YEAR);
        month = calendar.get(GregorianCalendar.MONTH);
        day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // Constructor with elapsed time
    public MyDate(long elapsedTime) {
        setDate(elapsedTime);
    }

    // Constructor with specified year, month, and day
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getter methods
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // Set date using elapsed time
    public void setDate(long elapsedTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);

        year = calendar.get(GregorianCalendar.YEAR);
        month = calendar.get(GregorianCalendar.MONTH);
        day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // Get month name
    public String getMonthName() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        return months[month];
    }
}


public class TestMyDate {

    public static void main(String[] args) {

        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate(34355555133101L);

        printDateBox("Date 1 (Current Date)", date1);
        System.out.println();
        printDateBox("Date 2 (Elapsed Time)", date2);
    }

    // Helper  to print a box for a MyDate object
    public static void printDateBox(String title, MyDate date) {
        System.out.println("+----------------------------+");
        System.out.printf("| %-26s|\n", title);
        System.out.println("+----------------------------+");
        System.out.printf("| Year  : %-4d               |\n", date.getYear());
        System.out.printf("| Month : %-9s          |\n", date.getMonthName());
        System.out.printf("| Day   : %-2d                 |\n", date.getDay());
        System.out.println("+----------------------------+");
    }
}
