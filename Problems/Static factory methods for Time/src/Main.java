import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;
//with constructor
    /*public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
     */

    public static Time noon() {
        int hour = 12;
        int minute = 0;
        int second = 0;
        return of(hour, minute, second);
    }

    public static Time midnight() {
        int hour = 0;
        int minute = 0;
        int second = 0;
        return Time.of(hour, minute, second);
    }

    public static Time ofSeconds(long seconds) {
        int tmp = (int) (seconds / 3600);
        int hour = tmp > 23 ? (tmp % 24) : tmp;
        int minutes = (int) ((seconds % 3600) / 60);
        int second = (int) (seconds % 60);
        return Time.of(hour, minutes, second);
    }

    public static Time of(int hour, int minute, int second) {
        if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60 && second >= 0 && second < 60) {
            //constructor
            //return new Time(hour, minute, second);
            //without constructor
            Time time = new Time();
            time.hour = hour;
            time.minute = minute;
            time.second = second;
            return time;
        }
        return null;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}