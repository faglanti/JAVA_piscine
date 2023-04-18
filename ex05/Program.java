package ex05;

import java.util.Scanner;

//Task
//Вам следует обеспечить возможность формировать список учеников, указывать в какое время и какие дни недели проводятся занятия.
//Занятия могут проводиться в любой день недели с 13 до 18 часов. Общее количество занятий в неделю не превышает 10.
//Максимальное количество учеников в расписании - 10 человек. Максимальная длина имени ученика - 10 символов (не содержит пробелов).
//Нужно предусмотреть возможность отмечать посещаемость ученика (напротив имени указывать время и дату занятия и статус(HERE, NOT_HERE).
//Цикл приложеия: формирования списка учеников, внесение расписания, внесение посещений, вывод расписания в виде таблички.
//Каждый этап работы разделяется точкой, гарантируется корректность входных данных, но не гарантируется внесение уроков последовательно.
//Проект реализовать для сентября 2020 года.

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] arr_names = new String[10];

        int i = 0;
        int student_num = 0;
        String str;

        str = input.nextLine();
        while (!str.equals(".")) {
            arr_names[i] = str;
            if(arr_names[i].length() > 10) {
                System.err.println("Invalid student's name");
                System.exit(-1);
            }
            student_num++;
            i++;
            str = input.nextLine();
        }
        if (student_num > 10) {
            System.err.println("Invalid number of students");
            System.exit(-1);
        }
        str = input.nextLine();
        int day;
        int time;
        int less_week = 0;
        int[] classes = new int[180];
        int k = 0;
        while (!str.equals(".")) {
            char[] tmp = str.toCharArray();
            time = tmp[0] - '0' - 1;
            if (time < 1 || time > 6) {
                System.err.println("Invalid time");
                System.exit(-1);
            }
            day = day_of_week(tmp[2], tmp[3]) - 1;
            if (day == 8 || str.length() > 4) {
                System.err.println("Invalid day of week");
                System.exit(-1);
            }
            while (day < 31) {
                classes[day * 6 + time] = 1;
                day += 7;
            }
            str = input.nextLine();
        }
        int[][] std_classes = new int[student_num][180];
        str = input.next();
        int date;
        int status;

        while (!str.equals(".")) {
            i = 0;
            while (i < student_num) {
                if (str.equals(arr_names[i])) {
                    break ;
                }
                i++;
            }
            if (i >= student_num) {
                System.err.println("Invalid student's name");
                System.exit(-1);
            }
            time = input.nextInt() - 1;
            date = input.nextInt() - 1;
            if (classes[date * 6 + time] != 1) {
                System.err.println("Invalid lesson's time");
                System.exit(-1);
            }
            str = input.next();
            if (str.equals("NOT_HERE")) {
                status = -1;
            }
            else if (str.equals("HERE")) {
                status = 1;
            }
            else {
                status = 0;
                System.exit(-1);
            }
            std_classes[i][date * 6 + time] = status;
            str = input.next();
        }
        System.out.print("          ");
        k = 0;
        while (k < 180) {
            if (classes[k] == 1) {
                date = k / 6 + 1;
                time = k % 6 + 1;
                System.out.print(time + ":00 " + date_to_weekday(date % 7));
                if (date / 10 == 0) {
                    System.out.print("  " + date + '|');
                }
                else {
                    System.out.print(" " + date + '|');
                }
            }
            k++;
        }
        System.out.println();
        i = 0;
        k = 0;
        while (i < student_num) {
            k = 10 - arr_names[i].length();
            while (k > 0) {
                System.out.print(" ");
                k--;
            }
            System.out.print(arr_names[i]);
            while (k < 180) {
                if (classes[k] == 1) {
                    if (std_classes[i][k] == 1) {
                        System.out.print("         1|");
                    } else if (std_classes[i][k] == -1) {
                        System.out.print("        -1|");
                    } else {
                        System.out.print("          |");
                    }
                }
                k++;
            }
            System.out.println();
            i++;
        }
    }
    public static String date_to_weekday(int day) {
        if (day == 0) {
            return ("MO");
        }
        else if (day == 1) {
            return ("TU");
        }
        else if (day == 2) {
            return ("WE");
        }
        else if (day == 3) {
            return ("TH");
        }
        else if (day == 4) {
            return ("FR");
        }
        else if (day == 5) {
            return ("SA");
        }
        return ("SU");
    }
    public static int day_of_week(char a, char b) {
        if (a == 'T' && b == 'U') {
            return (1);
        }
        else if (a == 'W' && b == 'E') {
            return (2);
        }
        else if (a == 'T' && b == 'H') {
            return (3);
        }
        else if (a == 'F' && b == 'R') {
            return (4);
        }
        else if (a == 'S' && b == 'A') {
            return (5);
        }
        else if (a == 'S' && b == 'U') {
            return (6);
        }
        else if (a == 'M' && b == 'O') {
            return (7);
        }
        return (8);
    }
}
