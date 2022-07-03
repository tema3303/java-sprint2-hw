import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReports reportMonth = new MonthlyReports();
        YearlyReport reportYear = new YearlyReport();
        Integer[] month = new Integer[]{1,2,3};

        while (true){
            System.out.println("1. Считать все месячные отчеты");
            System.out.println("2. Считать годовой отчёт");
            System.out.println("3. Сверить отчеты");
            System.out.println("4. Вывести информацию о всех месячных отчётах");
            System.out.println("5. Вывести информацию о годовом отчёте");
            System.out.println("0. Выход");
            int command = scanner.nextInt();
            if(command == 1){
                reportMonth.addRecord();
            } else if (command == 2) {
                reportYear.addRecord();
            } else if (command == 3) {
                if(reportMonth.monthReport.isEmpty() || reportYear.yearlyRecords.isEmpty()){
                    System.out.println("Файлы не считаны");
                } else {
                    for (int i = 1; i <= month.length; i++) {
                        if (reportMonth.getMonthIncome(i) != reportYear.getYearIncome(i) || reportMonth.getMonthProfit(i) != reportYear.getYearProfit(i)) {
                            System.out.println("Ошибка в месяце - " + i);
                        }
                    }
                    System.out.println("Сверка файлов проведена успешно!");
                }
            } else if (command == 4) {
                    reportMonth.printMonthInfo();
            } else if (command == 5) {
                    reportYear.printYearInfo();
            } else if (command == 0) {
                break;
            } else {
                System.out.println("Такой команды нет.");
            }
        }
    }
}