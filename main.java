import java.util.*;

class Date implements Comparable<Date> {
    private int day, month, year;
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date");
        }
    }
    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12 || day < 1) return false;
        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return day <= daysInMonth[month - 1];
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void updateDate(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public String getDayOfWeek() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar cal = new GregorianCalendar(year, month - 1, day);
        return days[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public int calculateDifference(Date other) {
        Calendar cal1 = new GregorianCalendar(year, month - 1, day);
        Calendar cal2 = new GregorianCalendar(other.year, other.month - 1, other.day);
        long diffMillis = Math.abs(cal1.getTimeInMillis() - cal2.getTimeInMillis());
        return (int) (diffMillis / (1000 * 60 * 60 * 24));
    }

    public void printDate() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(months[month - 1] + " " + day + ", " + year);
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
        return this.day - other.day;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            List<Date> dates = new ArrayList<>();
            dates.add(new Date(1, 1, 2023));
            dates.add(new Date(15, 3, 2022));
            dates.add(new Date(28, 2, 2024));
            dates.add(new Date(10, 7, 2025));


            System.out.println("Original dates:");
            for (Date d : dates) {
                d.printDate();
            }


            Collections.sort(dates);
            System.out.println("\nSorted dates:");
            for (Date d : dates) {
                d.printDate();
            }

            Date d1 = new Date(1, 1, 2023);
            Date d2 = new Date(10, 7, 2025);
            System.out.println("\nDifference in days: " + d1.calculateDifference(d2));
            System.out.println("\nDay of week for 1 Jan 2023: " + d1.getDayOfWeek());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
