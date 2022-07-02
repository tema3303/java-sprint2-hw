import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReports {


    HashMap<Integer, ArrayList<MonthlyRecord>> monthReport;
    Reading reading = new Reading();
    Integer[] month;
    YearlyReport reportYear = new YearlyReport();



    public MonthlyReports() {
        this.monthReport = new HashMap<>();
        this.month = new Integer[]{1, 2, 3};
    }

    void addRecord() {
        for (Integer actualMonth : month) {
            String contentOfFile = reading.readFileContentsOrNull("resources/m.20210" + actualMonth + ".csv");
            String[] lines = contentOfFile.split("\n");
            ArrayList<MonthlyRecord> monthlyRecords = new ArrayList<>();

            for (int j = 1; j < lines.length; j++) {
                String[] content = lines[j].split(",");
                String itemName = content[0];
                boolean isExpense = Boolean.valueOf(content[1]);
                int quantity = Integer.valueOf(content[2]);
                double sumOfOne = Integer.valueOf(content[3]);

                monthlyRecords.add(new MonthlyRecord(itemName, isExpense, quantity, sumOfOne));
            }
            monthReport.put(actualMonth, monthlyRecords);
        }
    }

    void printMonthInfo() {
        if (monthReport.isEmpty()) {
            System.out.println("Файлы не считаны");
            return;
        }
        for (Integer actualMonth : month) {
            double maxSumExpense = 0;
            String maxItemExpense = "";
            double maxSumProfit = 0;
            String maxItemProfit = "";
            String month = "";
            ArrayList<MonthlyRecord> monthlyRecords = monthReport.get(actualMonth);

            if (actualMonth == 1) {
                month = "Январь";
            } else if (actualMonth == 2) {
                month = "Февраль";
            } else if (actualMonth == 3) {
                month = "Март";
            }

            for (MonthlyRecord monthlyRecord : monthlyRecords) {
                double sum = monthlyRecord.sumOfOne * monthlyRecord.quantity;

                if (!monthlyRecord.isExpense && sum > maxSumProfit) {
                    maxSumProfit = sum;
                    maxItemProfit = monthlyRecord.itemName;
                } else if (monthlyRecord.isExpense && sum > maxSumExpense) {
                    maxSumExpense = sum;
                    maxItemExpense = monthlyRecord.itemName;
                }
            }
            System.out.println(month);
            System.out.println("Самая прибыльный товар: " + maxItemProfit + ". Сумма: " + maxSumProfit);
            System.out.println("Самая большая трата: " + maxItemExpense + ". Сумма: " + maxSumExpense);
        }
    }

    double getMonthIncome(Integer monthNumber) {
        double income = 0;
        ArrayList<MonthlyRecord> monthlyRecords = monthReport.get(monthNumber);
        for (MonthlyRecord monthlyRecord : monthlyRecords) {
            double sum = monthlyRecord.sumOfOne * monthlyRecord.quantity;
            if (!monthlyRecord.isExpense) {
                income += sum;
            }
        }
        return income;
    }

    double getMonthProfit(Integer monthNumber) {
        double income = 0;
        ArrayList<MonthlyRecord> monthlyRecords = monthReport.get(monthNumber);
        for (MonthlyRecord monthlyRecord : monthlyRecords) {
            double sum = monthlyRecord.sumOfOne * monthlyRecord.quantity;
            if (monthlyRecord.isExpense) {
                income += sum;
            }
        }
        return income;
    }

}
